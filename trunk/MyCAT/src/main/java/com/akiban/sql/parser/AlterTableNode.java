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
 * A AlterTableNode represents a DDL statement that alters a table.
 * It contains the name of the object to be created.
 *
 */
public class AlterTableNode extends DDLStatementNode
{
    // The alter table action
    public TableElementList tableElementList = null;

    /**
     * updateStatistics will indicate that we are here for updating the
     * statistics. It could be statistics of just one index or all the
     * indexes on a given table. 
     */
    private boolean updateStatistics = false;
    /**
     * The flag updateStatisticsAll will tell if we are going to update the 
     * statistics of all indexes or just one index on a table. 
     */
    private boolean updateStatisticsAll = false;
    /**
     * If statistic is getting updated for just one index, then 
     * indexNameForUpdateStatistics will tell the name of the specific index 
     * whose statistics need to be updated.
     */
    private String indexNameForUpdateStatistics;

    public boolean compressTable = false;
    public boolean sequential = false;
    // The following three (purge, defragment and truncateEndOfTable) apply for 
    // inplace compress.
    public boolean purge = false;
    public boolean defragment = false;
    public boolean truncateEndOfTable = false;
                
    public int behavior;             // currently for DROP COLUMN

    private int changeType = UNKNOWN_TYPE;

    private boolean truncateTable = false;

    /**
     * Initializer for a TRUNCATE TABLE
     *
     * @param objectName The name of the table being truncated
     * @exception StandardException Thrown on error
     */

    public void init(Object objectName) throws StandardException { 
        initAndCheck(objectName);
        /* For now, this init() only called for truncate table */
        truncateTable = true;
    }

    /**
     * Initializer for a AlterTableNode for updating the statistics. The user
     * can ask for update statistic of all the indexes or only a specific index
     *
     * @param objectName The name of the table whose index(es) will have
     *                                                  their statistics updated.
     * @param updateStatisticsAll If true then update the statistics of all 
     *                                                  the indexes on the table. If false, then update
     *                                                  the statistics of only the index provided as
     *                                                  3rd parameter here
     * @param indexName Only used if updateStatisticsAll is set to 
     *                                                  false. 
     *
     * @exception StandardException Thrown on error
     */
    public void init(Object objectName,
                     Object updateStatisticsAll,
                     Object indexName) 
            throws StandardException {
        initAndCheck(objectName);
        this.updateStatisticsAll = ((Boolean)updateStatisticsAll).booleanValue();
        this.indexNameForUpdateStatistics = (String)indexName;
        updateStatistics = true;
    }

    /**
     * Initializer for a AlterTableNode for COMPRESS using temporary tables
     * rather than inplace compress
     *
     * @param objectName The name of the table being altered
     * @param sequential Whether or not the COMPRESS is SEQUENTIAL
     *
     * @exception StandardException Thrown on error
     */
    public void init(Object objectName, Object sequential) throws StandardException {
        initAndCheck(objectName);

        this.sequential = ((Boolean)sequential).booleanValue();
        /* For now, this init() only called for compress table */
        compressTable = true;
    }

    /**
     * Initializer for a AlterTableNode for INPLACE COMPRESS
     *
     * @param objectName The name of the table being altered
     * @param purge PURGE during INPLACE COMPRESS?
     * @param defragment DEFRAGMENT during INPLACE COMPRESS?
     * @param truncateEndOfTable TRUNCATE END during INPLACE COMPRESS?
     *
     * @exception StandardException Thrown on error
     */

    public void init(Object objectName, Object purge, Object defragment, 
                     Object truncateEndOfTable) 
            throws StandardException {
        initAndCheck(objectName);

        this.purge = ((Boolean)purge).booleanValue();
        this.defragment = ((Boolean)defragment).booleanValue();
        this.truncateEndOfTable = ((Boolean)truncateEndOfTable).booleanValue();
        compressTable = true;
    }

    /**
     * Initializer for a AlterTableNode
     *
     * @param objectName The name of the table being altered
     * @param tableElementList The alter table action
     * @param lockGranularity The new lock granularity, if any
     * @param changeType ADD_TYPE or DROP_TYPE
     * @param behavior If drop column is CASCADE or RESTRICTED
     *
     * @exception StandardException Thrown on error
     */

    public void init(Object objectName, Object tableElementList, Object lockGranularity,
                     Object changeType, Object behavior) 
            throws StandardException {
        initAndCheck(objectName);
        this.tableElementList = (TableElementList)tableElementList;
        int[] ct = (int[])changeType, bh = (int[])behavior;
        this.changeType = ct[0];
        this.behavior = bh[0];
        switch (this.changeType ) {
        case ADD_TYPE:
        case DROP_TYPE:
        case MODIFY_TYPE:
        case LOCKING_TYPE:
            break;

        default:
            throw new StandardException("Not implemented");
        }
    }

    /**
     * Fill this node with a deep copy of the given node.
     */
    public void copyFrom(QueryTreeNode node) throws StandardException {
        super.copyFrom(node);

        AlterTableNode other = (AlterTableNode)node;
        this.tableElementList = (TableElementList)
            getNodeFactory().copyNode(other.tableElementList, getParserContext());
       // this.lockGranularity = other.lockGranularity;
        this.updateStatistics = other.updateStatistics;
        this.updateStatisticsAll = other.updateStatisticsAll;
        this.indexNameForUpdateStatistics = other.indexNameForUpdateStatistics;
        this.compressTable = other.compressTable;
        this.sequential = other.sequential;
        this.purge = other.purge;
        this.defragment = other.defragment;
        this.truncateEndOfTable = other.truncateEndOfTable;
        this.behavior = other.behavior;
        this.changeType = other.changeType;
        this.truncateTable = other.truncateTable;
    }

    /**
     * Convert this object to a String.  See comments in QueryTreeNode.java
     * for how this should be done for tree printing.
     *
     * @return This object as a String
     */

    public String toString() {
        return super.toString() +
            "objectName: " + getObjectName() + "\n" +
            //"lockGranularity: " + lockGranularity + "\n" +
            "compressTable: " + compressTable + "\n" +
            "sequential: " + sequential + "\n" +
            "truncateTable: " + truncateTable + "\n" +
            "purge: " + purge + "\n" +
            "defragment: " + defragment + "\n" +
            "truncateEndOfTable: " + truncateEndOfTable + "\n" +
            "updateStatistics: " + updateStatistics + "\n" +
            "updateStatisticsAll: " + updateStatisticsAll + "\n" +
            "indexNameForUpdateStatistics: " +
            indexNameForUpdateStatistics + "\n";
    }

    /**
     * Prints the sub-nodes of this object.  See QueryTreeNode.java for
     * how tree printing is supposed to work.
     * @param depth The depth to indent the sub-nodes
     */
    public void printSubNodes(int depth) {
        if (tableElementList != null) {
            printLabel(depth, "tableElementList: ");
            tableElementList.treePrint(depth + 1);
        }
    }

    public String statementToString() {
        if (truncateTable)
            return "TRUNCATE TABLE";
        else
            return "ALTER TABLE";
    }

    public boolean isUpdateStatistics() {
        return updateStatistics;
    }

    public boolean isUpdateStatisticsAll() {
        return updateStatisticsAll;
    }

    public String getIndexNameForUpdateStatistics() {
        return indexNameForUpdateStatistics;
    }

    public boolean isCompressTable() {
        return compressTable;
    }

    public boolean isTruncateTable() {
        return truncateTable;
    }

    public int getChangeType() { 
        return changeType; 
    }

    /**
     * Accept the visitor for all visitable children of this node.
     * 
     * @param v the visitor
     *
     * @exception StandardException on error
     */
    void acceptChildren(Visitor v) throws StandardException {
        super.acceptChildren(v);

        if (tableElementList != null) {
            tableElementList.accept(v);
        }
    }
}