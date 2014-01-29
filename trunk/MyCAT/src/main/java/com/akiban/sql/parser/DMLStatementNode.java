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
 * A DMLStatementNode represents any type of DML statement: a cursor declaration,
 * an INSERT statement, and UPDATE statement, or a DELETE statement.    All DML
 * statements have result sets, but they do different things with them.  A
 * SELECT statement sends its result set to the client, an INSERT statement
 * inserts its result set into a table, a DELETE statement deletes from a
 * table the rows corresponding to the rows in its result set, and an UPDATE
 * statement updates the rows in a base table corresponding to the rows in its
 * result set.
 *
 */

public abstract class DMLStatementNode extends StatementNode
{
    /**
     * The result set is the rows that result from running the
     * statement.    What this means for SELECT statements is fairly obvious.
     * For a DELETE, there is one result column representing the
     * key of the row to be deleted (most likely, the location of the
     * row in the underlying heap).  For an UPDATE, the row consists of
     * the key of the row to be updated plus the updated columns.    For
     * an INSERT, the row consists of the new column values to be
     * inserted, with no key (the system generates a key).
     *
     * The parser doesn't know anything about keys, so the columns
     * representing the keys will be added after parsing (perhaps in
     * the binding phase?).
     *
     */
    private ResultSetNode resultSet;

    /**
     * Initializer for a DMLStatementNode
     *
     * @param resultSet A ResultSetNode for the result set of the
     *                                  DML statement
     */

    public void init(Object resultSet) {
        this.resultSet = (ResultSetNode)resultSet;
    }

    /**
     * Fill this node with a deep copy of the given node.
     */
    public void copyFrom(QueryTreeNode node) throws StandardException {
        super.copyFrom(node);

        DMLStatementNode other = (DMLStatementNode)node;
        this.resultSet = (ResultSetNode)getNodeFactory().copyNode(other.resultSet,
                                                                  getParserContext());
    }

    /**
     * Prints the sub-nodes of this object.  See QueryTreeNode.java for
     * how tree printing is supposed to work.
     *
     * @param depth The depth of this node in the tree
     */

    public void printSubNodes(int depth) {
        if (resultSet != null) {
            printLabel(depth, "resultSet: ");
            resultSet.treePrint(depth + 1);
        }
    }

    /**
     * Get the ResultSetNode from this DML Statement.
     * (Useful for view resolution after parsing the view definition.)
     *
     * @return ResultSetNode The ResultSetNode from this DMLStatementNode.
     */
    public ResultSetNode getResultSetNode() {
        return resultSet;
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

        if (resultSet != null) {
            resultSet = (ResultSetNode)resultSet.accept(v);
        }
    }

}