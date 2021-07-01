/*
 * This file is generated by jOOQ.
 */
package com.leviosa.pumpkin.db.gen.tables;


import com.leviosa.pumpkin.db.gen.Indexes;
import com.leviosa.pumpkin.db.gen.Keys;
import com.leviosa.pumpkin.db.gen.LinksStorage;
import com.leviosa.pumpkin.db.gen.tables.records.LinksTagsRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
import org.jooq.Schema;
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
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class LinksTags extends TableImpl<LinksTagsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>links_storage.links_tags</code>
     */
    public static final LinksTags LINKS_TAGS = new LinksTags();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<LinksTagsRecord> getRecordType() {
        return LinksTagsRecord.class;
    }

    /**
     * The column <code>links_storage.links_tags.id</code>.
     */
    public final TableField<LinksTagsRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>links_storage.links_tags.link_id</code>.
     */
    public final TableField<LinksTagsRecord, Long> LINK_ID = createField(DSL.name("link_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>links_storage.links_tags.tag_id</code>.
     */
    public final TableField<LinksTagsRecord, Long> TAG_ID = createField(DSL.name("tag_id"), SQLDataType.BIGINT.nullable(false), this, "");

    private LinksTags(Name alias, Table<LinksTagsRecord> aliased) {
        this(alias, aliased, null);
    }

    private LinksTags(Name alias, Table<LinksTagsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>links_storage.links_tags</code> table reference
     */
    public LinksTags(String alias) {
        this(DSL.name(alias), LINKS_TAGS);
    }

    /**
     * Create an aliased <code>links_storage.links_tags</code> table reference
     */
    public LinksTags(Name alias) {
        this(alias, LINKS_TAGS);
    }

    /**
     * Create a <code>links_storage.links_tags</code> table reference
     */
    public LinksTags() {
        this(DSL.name("links_tags"), null);
    }

    public <O extends Record> LinksTags(Table<O> child, ForeignKey<O, LinksTagsRecord> key) {
        super(child, key, LINKS_TAGS);
    }

    @Override
    public Schema getSchema() {
        return LinksStorage.LINKS_STORAGE;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.LINKS_TAGS_LINK_ID, Indexes.LINKS_TAGS_TAG_ID);
    }

    @Override
    public Identity<LinksTagsRecord, Long> getIdentity() {
        return (Identity<LinksTagsRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<LinksTagsRecord> getPrimaryKey() {
        return Keys.KEY_LINKS_TAGS_PRIMARY;
    }

    @Override
    public List<UniqueKey<LinksTagsRecord>> getKeys() {
        return Arrays.<UniqueKey<LinksTagsRecord>>asList(Keys.KEY_LINKS_TAGS_PRIMARY);
    }

    @Override
    public List<ForeignKey<LinksTagsRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<LinksTagsRecord, ?>>asList(Keys.LINKS_TAGS_IBFK_1, Keys.LINKS_TAGS_IBFK_2);
    }

    private transient Links _links;
    private transient Tags _tags;

    public Links links() {
        if (_links == null)
            _links = new Links(this, Keys.LINKS_TAGS_IBFK_1);

        return _links;
    }

    public Tags tags() {
        if (_tags == null)
            _tags = new Tags(this, Keys.LINKS_TAGS_IBFK_2);

        return _tags;
    }

    @Override
    public LinksTags as(String alias) {
        return new LinksTags(DSL.name(alias), this);
    }

    @Override
    public LinksTags as(Name alias) {
        return new LinksTags(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public LinksTags rename(String name) {
        return new LinksTags(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public LinksTags rename(Name name) {
        return new LinksTags(name, null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, Long, Long> fieldsRow() {
        return (Row3) super.fieldsRow();
    }
}
