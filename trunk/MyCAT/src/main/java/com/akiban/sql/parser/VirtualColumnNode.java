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
import com.akiban.sql.types.DataTypeDescriptor;

/**
 * A VirtualColumnNode represents a virtual column reference to a column in
 * a row returned by an underlying ResultSetNode. The underlying column could
 * be in a base table,  view (which could expand into a complex
 * expression), subquery in the FROM clause, temp table, expression result, etc.    
 * By the time we get to code generation, all VirtualColumnNodes should stand only 
 * for references to columns in a base table within a FromBaseTable.
 *
 */

public class VirtualColumnNode extends ValueNode
{
    /* A VirtualColumnNode contains a pointer to the immediate child result
     * that is materializing the virtual column and the ResultColumn
     * that represents that materialization.
     */
    private ResultSetNode sourceResultSet;
    private ResultColumn sourceColumn;

    /* columnId is redundant since a ResultColumn also has one, but
     * we need it here for generate()
     */
    int columnId;

    /**
     * Initializer for a VirtualColumnNode.
     *
     * @param sourceResultSet The ResultSetNode where the value is originating
     * @param sourceColumn The ResultColumn where the value is originating
     * @param columnId The columnId within the current Row
     */

    public void init(Object sourceResultSet,
                     Object sourceColumn,
                     Object columnId) 
            throws StandardException {
        ResultColumn source = (ResultColumn)sourceColumn;
        this.sourceResultSet = (ResultSetNode)sourceResultSet;
        this.sourceColumn = source;
        this.columnId = ((Integer)columnId).intValue();
        setType(source.getType());
    }

    /**
     * Fill this node with a deep copy of the given node.
     */
    public void copyFrom(QueryTreeNode node) throws StandardException {
        super.copyFrom(node);

        VirtualColumnNode other = (VirtualColumnNode)node;
        this.sourceResultSet = (ResultSetNode)
            getNodeFactory().copyNode(other.sourceResultSet, getParserContext());
        this.sourceColumn = (ResultColumn)
            getNodeFactory().copyNode(other.sourceColumn, getParserContext());
        this.columnId = other.columnId;
    }

    /**
     * Prints the sub-nodes of this object.  See QueryTreeNode.java for
     * how tree printing is supposed to work.
     *
     * @param depth The depth of this node in the tree
     */

    public void printSubNodes(int depth) {
        super.printSubNodes(depth);

        printLabel(depth, "sourceColumn: ");
        sourceColumn.treePrint(depth + 1);
        printLabel(depth, "sourceResultSet: ");
        sourceResultSet.treePrint(depth + 1);
    }

    /**
     * Return the ResultSetNode that is the source of this VirtualColumnNode.
     *
     * @return ResultSetNode
     */
    public ResultSetNode getSourceResultSet() {
        return sourceResultSet;
    }

    /**
     * Return the ResultColumn that is the source of this VirtualColumnNode.
     *
     * @return ResultColumn
     */
    public ResultColumn getSourceColumn() {
        return sourceColumn;
    }

    /**
     * Get the name of the table the ResultColumn is in, if any.    This will be null
     * if the user did not supply a name (for example, select a from t).
     * The method will return B for this example, select b.a from t as b
     * The method will return T for this example, select t.a from t
     *
     * @return A String containing the name of the table the Column
     *               is in. If the column is not in a table (i.e. is a
     *               derived column), it returns NULL.
     */
    public String getTableName() {
        return sourceColumn.getTableName();
    }

    /**
     * Get the name of the schema the ResultColumn's table is in, if any.
     * The return value will be null if the user did not supply a schema name
     * (for example, select t.a from t).
     * Another example for null return value (for example, select b.a from t as b).
     * But for following query select app.t.a from t, this will return APP
     *
     * @return A String containing the name of the schema for the Column's table.
     *               If the column is not in a schema (i.e. derived column), it returns NULL.
     */
    public String getSchemaName() throws StandardException {
        return sourceColumn.getSchemaName();
    }

    /**
     * Get the DataTypeDesciptor from this Node.
     *
     * @return The DataTypeDescriptor from this Node.    This
     *               may be null if the node isn't bound yet.
     */
    public DataTypeDescriptor getType() {
        return sourceColumn.getType();
    }
        
    public void setType(DataTypeDescriptor dtd) throws StandardException {
        sourceColumn.setType(dtd);
    }
        
    protected boolean isEquivalent(ValueNode o) throws StandardException {
        if (isSameNodeType(o)) {
            VirtualColumnNode other = (VirtualColumnNode)o;
            return sourceColumn.isEquivalent(other.sourceColumn);
        }
        return false;
    }

}