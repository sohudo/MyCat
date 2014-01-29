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
 * A DDLStatementNode represents any type of DDL statement: CREATE TABLE,
 * CREATE INDEX, ALTER TABLE, etc.
 *
 */

public abstract class DDLStatementNode extends StatementNode
{
    public static final int UNKNOWN_TYPE = 0;
    public static final int ADD_TYPE = 1;
    public static final int DROP_TYPE = 2;
    public static final int MODIFY_TYPE = 3;
    public static final int LOCKING_TYPE = 4;

    private TableName objectName;
    private boolean initOk;

    /**
       sub-classes can set this to be true to allow implicit
       creation of the main object's schema at execution time.
    */
    boolean implicitCreateSchema;

    public void init(Object objectName) throws StandardException {
        initAndCheck(objectName);
    }

    /**
       Initialize the object name we will be performing the DDL
       on and check that we are not in the system schema
       and that DDL is allowed.
    */
    protected void initAndCheck(Object objectName) throws StandardException {
        this.objectName = (TableName)objectName;
        initOk = true;
    }

    /**
     * Fill this node with a deep copy of the given node.
     */
    public void copyFrom(QueryTreeNode node) throws StandardException {
        super.copyFrom(node);

        DDLStatementNode other = (DDLStatementNode)node;
        this.objectName = (TableName)getNodeFactory().copyNode(other.objectName,
                                                               getParserContext());
        this.initOk = other.initOk;
        this.implicitCreateSchema = other.implicitCreateSchema;
    }

    /**
     * A DDL statement is always atomic
     *
     * @return true 
     */
    public boolean isAtomic() {
        return true;
    }

    /**
     * Return the name of the table being dropped.
     * This is the unqualified table name.
     *
     * @return the relative name
     */
    public String getRelativeName() {
        return objectName.getTableName() ;
    }

    /**
     * Return the full dot expression name of the 
     * object being dropped.
     * 
     * @return the full name
     */
    public String getFullName() {
        return objectName.getFullTableName() ;
    }

    public final TableName getObjectName() { 
        return objectName; 
    }

    /**
     * Convert this object to a String.  See comments in QueryTreeNode.java
     * for how this should be done for tree printing.
     *
     * @return This object as a String
     */

    public String toString() {
        return ((objectName==null)?"":
                "name: " + objectName.toString() +"\n") + super.toString();
    }
        
}