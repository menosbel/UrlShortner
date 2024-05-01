FROM gradle:8.7.0-jdk21 as builder

WORKDIR /app

ADD . .

RUN gradle shadowJar

FROM amazoncorretto:21-alpine3.17

WORKDIR /app

COPY --from=builder /app/build/libs/url_shortner.jar url_shortner.jar
COPY --from=builder /app/.env .env

CMD java -jar url_shortner.jar