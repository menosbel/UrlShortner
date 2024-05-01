/*
 * This file is generated by jOOQ.
 */
package com.menosbel.UrlShortner.infrastructure.db.jooq.generated.tables;


import com.menosbel.UrlShortner.infrastructure.db.jooq.generated.Keys;
import com.menosbel.UrlShortner.infrastructure.db.jooq.generated.Public;
import com.menosbel.UrlShortner.infrastructure.db.jooq.generated.tables.records.UrlInfoRecord;

import java.util.Collection;
import java.util.function.Function;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.Function4;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.Records;
import org.jooq.Row4;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.SelectField;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class UrlInfo extends TableImpl<UrlInfoRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.url_info</code>
     */
    public static final UrlInfo URL_INFO = new UrlInfo();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UrlInfoRecord> getRecordType() {
        return UrlInfoRecord.class;
    }

    /**
     * The column <code>public.url_info.id</code>.
     */
    public final TableField<UrlInfoRecord, Integer> ID = createField(DSL.name("id"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.url_info.key_url</code>.
     */
    public final TableField<UrlInfoRecord, String> KEY_URL = createField(DSL.name("key_url"), SQLDataType.VARCHAR(20).nullable(false), this, "");

    /**
     * The column <code>public.url_info.long_url</code>.
     */
    public final TableField<UrlInfoRecord, String> LONG_URL = createField(DSL.name("long_url"), SQLDataType.VARCHAR(500).nullable(false), this, "");

    /**
     * The column <code>public.url_info.short_url</code>.
     */
    public final TableField<UrlInfoRecord, String> SHORT_URL = createField(DSL.name("short_url"), SQLDataType.VARCHAR(50), this, "");

    private UrlInfo(Name alias, Table<UrlInfoRecord> aliased) {
        this(alias, aliased, (Field<?>[]) null, null);
    }

    private UrlInfo(Name alias, Table<UrlInfoRecord> aliased, Field<?>[] parameters, Condition where) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
    }

    /**
     * Create an aliased <code>public.url_info</code> table reference
     */
    public UrlInfo(String alias) {
        this(DSL.name(alias), URL_INFO);
    }

    /**
     * Create an aliased <code>public.url_info</code> table reference
     */
    public UrlInfo(Name alias) {
        this(alias, URL_INFO);
    }

    /**
     * Create a <code>public.url_info</code> table reference
     */
    public UrlInfo() {
        this(DSL.name("url_info"), null);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<UrlInfoRecord, Integer> getIdentity() {
        return (Identity<UrlInfoRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<UrlInfoRecord> getPrimaryKey() {
        return Keys.URL_INFO_PKEY;
    }

    @Override
    public UrlInfo as(String alias) {
        return new UrlInfo(DSL.name(alias), this);
    }

    @Override
    public UrlInfo as(Name alias) {
        return new UrlInfo(alias, this);
    }

    @Override
    public UrlInfo as(Table<?> alias) {
        return new UrlInfo(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public UrlInfo rename(String name) {
        return new UrlInfo(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public UrlInfo rename(Name name) {
        return new UrlInfo(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public UrlInfo rename(Table<?> name) {
        return new UrlInfo(name.getQualifiedName(), null);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public UrlInfo where(Condition condition) {
        return new UrlInfo(getQualifiedName(), aliased() ? this : null, null, condition);
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public UrlInfo where(Collection<? extends Condition> conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public UrlInfo where(Condition... conditions) {
        return where(DSL.and(conditions));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public UrlInfo where(Field<Boolean> condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public UrlInfo where(SQL condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public UrlInfo where(@Stringly.SQL String condition) {
        return where(DSL.condition(condition));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public UrlInfo where(@Stringly.SQL String condition, Object... binds) {
        return where(DSL.condition(condition, binds));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    @PlainSQL
    public UrlInfo where(@Stringly.SQL String condition, QueryPart... parts) {
        return where(DSL.condition(condition, parts));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public UrlInfo whereExists(Select<?> select) {
        return where(DSL.exists(select));
    }

    /**
     * Create an inline derived table from this table
     */
    @Override
    public UrlInfo whereNotExists(Select<?> select) {
        return where(DSL.notExists(select));
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, String, String, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function4<? super Integer, ? super String, ? super String, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function4<? super Integer, ? super String, ? super String, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
