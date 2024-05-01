import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import io.github.cdimascio.dotenv.dotenv

plugins {
    kotlin("jvm") version "1.9.20"
    application
    id("nu.studer.jooq") version "6.0"
    id("org.flywaydb.flyway") version "9.20.0"
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

buildscript {
    dependencies {
        classpath("io.github.cdimascio:dotenv-kotlin:6.2.2")
    }
}

val dotenv = dotenv {
    directory = rootProject.projectDir.absolutePath
    ignoreIfMissing = true
}

group = "com.menosbel"
version = "1.0-SNAPSHOT"

project.ext {
    set("GENERATE_JOOQ", dotenv["GENERATE_JOOQ"] == "1")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.javalin:javalin:6.1.3")
    implementation("org.slf4j:slf4j-simple:2.0.7")
    implementation("com.eclipsesource.minimal-json:minimal-json:0.9.5")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.16.1")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")
    implementation("org.jooq:jooq:3.19.3")
    implementation("org.postgresql:postgresql:42.2.23")
    implementation("org.flywaydb:flyway-core:10.8.0")
    implementation("org.flywaydb:flyway-database-postgresql:10.4.1")
    jooqGenerator("org.postgresql:postgresql:42.2.23")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    testImplementation("org.assertj:assertj-core:3.24.2")
    testImplementation("io.mockk:mockk:1.13.4")
    testImplementation("io.rest-assured:rest-assured:5.3.0")
    testImplementation("io.rest-assured:kotlin-extensions:5.3.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "21"
}

project.setProperty("mainClassName", "com.menosbel.api.MainKt")

tasks.getByName<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
    archiveFileName.set("url_shortner.jar")
    manifest {
        attributes(mapOf(
            "Main-Class" to "com.menosbel.api.MainKt",
        ))
    }
}

flyway {
    url = "jdbc:postgresql://${dotenv["DB_HOST"]}:${dotenv["DB_PORT"]}/${dotenv["DB_NAME"]}"
    user = dotenv["DB_USER"]
    password = dotenv["DB_PASSWORD"]
    schemas = arrayOf("public")
    locations = arrayOf("filesystem:${project.projectDir}/src/main/resources/db")
    cleanDisabled = false
}


fun setupFlywayTestDB(task: org.flywaydb.gradle.task.AbstractFlywayTask) {
    task.apply {
        url = "jdbc:postgresql://${dotenv["TEST_DB_HOST"]}:${dotenv["TEST_DB_PORT"]}/${dotenv["TEST_DB_NAME"]}"
        user = dotenv["TEST_DB_USER"]
        password = dotenv["TEST_DB_PASSWORD"]
        schemas = arrayOf("public")
        locations = arrayOf("filesystem:${project.projectDir}/src/main/resources/db")
    }
}

tasks.register<org.flywaydb.gradle.task.FlywayMigrateTask>("flywayMigrateTest") { setupFlywayTestDB(this) }
tasks.register<org.flywaydb.gradle.task.FlywayCleanTask>("flywayCleanTest") { setupFlywayTestDB(this) }
tasks.register<org.flywaydb.gradle.task.FlywayInfoTask>("flywayInfoTest") { setupFlywayTestDB(this) }

jooq {
    version.set("3.19.1")

    configurations {
        create("main") {
            generateSchemaSourceOnCompilation.set(project.ext["GENERATE_JOOQ"] as Boolean)
            jooqConfiguration.apply {
                logging = org.jooq.meta.jaxb.Logging.WARN
                jdbc.apply {
                    driver = "org.postgresql.Driver"
                    url = "jdbc:postgresql://${dotenv["DB_HOST"]}:${dotenv["DB_PORT"]}/${dotenv["DB_NAME"]}"
                    user = dotenv["DB_USER"]
                    password = dotenv["DB_PASSWORD"]
                }
                generator.apply {
                    name = "org.jooq.codegen.DefaultGenerator"
                    strategy.name = "org.jooq.codegen.DefaultGeneratorStrategy"
                    database.apply {
                        name = "org.jooq.meta.postgres.PostgresDatabase"
                        inputSchema = "public"
                        includes = ".*"
                        excludes = ""
                    }
                    generate.apply {
                        isDeprecated = false
                        isRecords = true
                        isImmutablePojos = true
                        isFluentSetters = true
                        isSequences = true
                    }
                    target.apply {
                        packageName = "com.menosbel.UrlShortner.infrastructure.db.jooq.generated"
                        directory = "src/main/jooq"
                    }
                }
            }
        }
    }
}

tasks.named<nu.studer.gradle.jooq.JooqGenerate>("generateJooq") {
    dependsOn("flywayMigrate")
    dependsOn("flywayMigrateTest")
    inputs.files(fileTree("resources/db"))
        .withPropertyName("migrations")
        .withPathSensitivity(PathSensitivity.RELATIVE)
    allInputsDeclared.set(true) // make jOOQ task participate in incremental builds and build caching
    outputs.cacheIf { true }
}