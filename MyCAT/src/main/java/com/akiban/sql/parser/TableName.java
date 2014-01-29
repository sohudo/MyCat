/*
 * Copyright (c) 2013, OpenCloudDB/MyCAT and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software;Designed and Developed mainly by many Chinese 
 * opensource volunteers. you can redistribute it and/or modify it under the 
 * terms of the GNU General Public License version 2 only, as published by the
 * Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * Any questions about this component can be directed to it's project Web address 
 * https://code.google.com/p/opencloudb/.
 *
 */
package com.akiban.sql.parser;

import com.akiban.sql.StandardException;

/**
 * A TableName represents a qualified name, externally represented as a schema name
 * and an object name separated by a dot. This class is mis-named: it is used to
 * represent the names of other object types in addition to tables.
 *
 */

public class TableName extends QueryTreeNode
{
    /* Both schemaName and tableName can be null, however, if 
    ** tableName is null then schemaName must also be null.
    */
    private String tableName;
    private String schemaName;
    private boolean hasSchema;

    /**
     * Initializer for when you have both the table and schema names.
     *
     * @param schemaName The name of the schema being referenced
     * @param tableName The name of the table being referenced
     */

    public void init(Object schemaName, Object tableName) {
        hasSchema = schemaName != null;
        this.schemaName = (String)schemaName;
        this.tableName = (String)tableName;
    }

    /**
     * Initializer for when you have both the table and schema names.
     *
     * @param schemaName The name of the schema being referenced
     * @param tableName The name of the table being referenced
     * @param tokBeginOffset begin position of token for the table name 
     *                                           identifier from parser.    pass in -1 if unknown
     * @param tokEndOffset end position of token for the table name 
     *                                       identifier from parser.    pass in -1 if unknown
     */
    public void init (Object schemaName, 
                      Object tableName, 
                      Object tokBeginOffset,
                      Object tokEndOffset) {
        init(schemaName, tableName);
        this.setBeginOffset(((Integer)tokBeginOffset).intValue());
        this.setEndOffset(((Integer)tokEndOffset).intValue());
    }

    /**
     * Fill this node with a deep copy of the given node.
     */
    public void copyFrom(QueryTreeNode node) throws StandardException {
        super.copyFrom(node);

        TableName other = (TableName)node;
        this.tableName = other.tableName;
        this.schemaName = other.schemaName;
        this.hasSchema = other.hasSchema;
    }

    /**
     * Get the table name (without the schema name).
     *
     * @return Table name as a String
     */

    public String getTableName() {
        return tableName;
    }

    /**
     * Return true if this instance was initialized with not null schemaName.
     *
     * @return true if this instance was initialized with not null schemaName
     */

    public boolean hasSchema(){
        return hasSchema;
    }

    /**
     * Get the schema name.
     *
     * @return Schema name as a String
     */

    public String getSchemaName() {
        return schemaName;
    }

    /**
     * Set the schema name.
     *
     * @param schemaName Schema name as a String
     */

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
        this.hasSchema = schemaName != null;
    }

    /**
     * Get the full table name (with the schema name, if explicitly
     * specified).
     *
     * @return Full table name as a String
     */

    public String getFullTableName() {
        if (schemaName != null)
            return schemaName + "." + tableName;
        else
            return tableName;
    }

    /**
     * Convert this object to a String.  See comments in QueryTreeNode.java
     * for how this should be done for tree printing.
     *
     * @return This object as a String
     */

    public String toString() {
        if (hasSchema)
            return getFullTableName();
        else
            return tableName;
    }

    /**
     * 2 TableNames are equal if their both their schemaNames and tableNames are
     * equal, or if this node's full table name is null (which happens when a
     * SELECT * is expanded).    Also, only check table names if the schema
     * name(s) are null.
     *
     * @param otherTableName The other TableName.
     *
     * @return boolean Whether or not the 2 TableNames are equal.
     */
    public boolean equals(TableName otherTableName) {
        if (otherTableName == null)
            return false;
                
        String fullTableName = getFullTableName();
        if (fullTableName == null) {
            return true;
        }
        else if ((schemaName == null) || 
                 (otherTableName.getSchemaName() == null)) {
            return tableName.equals(otherTableName.getTableName());
        }
        else {
            return fullTableName.equals(otherTableName.getFullTableName());
        }
    }

    /**
     * 2 TableNames are equal if their both their schemaNames and tableNames are
     * equal, or if this node's full table name is null (which happens when a
     * SELECT * is expanded).    Also, only check table names if the schema
     * name(s) are null.
     *
     * @param otherSchemaName The other TableName.
     * @param otherTableName The other TableName.
     *
     * @return boolean Whether or not the 2 TableNames are equal.
     */
    public boolean equals(String otherSchemaName, String otherTableName) {
        String fullTableName = getFullTableName();
        if (fullTableName == null) {
            return true;
        }
        else if ((schemaName == null) || 
                 (otherSchemaName == null)) {
            return tableName.equals(otherTableName);
        }
        else {
            return fullTableName.equals(otherSchemaName+"."+otherTableName);
        }
    }

    /**
     * Returns a hashcode for this tableName. This allows us to use TableNames
     * as keys in hash lists.
     *
     * @return hashcode for this tablename
     */
    public int hashCode() {
        return getFullTableName().hashCode();
    }

    /**
     * Compares two TableNames. Needed for hashing logic to work.
     *
     * @param other other tableName
     */
    public boolean equals(Object other) {
        if (!(other instanceof TableName) ) { 
            return false; 
        }

        TableName that = (TableName)other;

        return this.getFullTableName().equals(that.getFullTableName());
    }

}