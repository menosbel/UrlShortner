/*
 * This file is generated by jOOQ.
 */
package com.menosbel.UrlShortner.infrastructure.db.jooq.generated;


import com.menosbel.UrlShortner.infrastructure.db.jooq.generated.tables.FlywaySchemaHistory;
import com.menosbel.UrlShortner.infrastructure.db.jooq.generated.tables.UrlInfo;
import com.menosbel.UrlShortner.infrastructure.db.jooq.generated.tables.records.FlywaySchemaHistoryRecord;
import com.menosbel.UrlShortner.infrastructure.db.jooq.generated.tables.records.UrlInfoRecord;

import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<FlywaySchemaHistoryRecord> FLYWAY_SCHEMA_HISTORY_PK = Internal.createUniqueKey(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, DSL.name("flyway_schema_history_pk"), new TableField[] { FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK }, true);
    public static final UniqueKey<UrlInfoRecord> URL_INFO_PKEY = Internal.createUniqueKey(UrlInfo.URL_INFO, DSL.name("url_info_pkey"), new TableField[] { UrlInfo.URL_INFO.ID }, true);
}
