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
 * An OrderByColumn is a column in the ORDER BY clause.  An OrderByColumn
 * can be ordered ascending or descending.
 *
 * We need to make sure that the named columns are
 * columns in that query, and that positions are within range.
 *
 */
public class OrderByColumn extends OrderedColumn 
{
    private ValueNode expression;
    private boolean ascending = true;
    private boolean nullsOrderedLow = false;

    /**
     * Initializer.
     *
     * @param expression Expression of this column
     */
    public void init(Object expression) {
        this.expression = (ValueNode)expression;
    }

    /**
     * Fill this node with a deep copy of the given node.
     */
    public void copyFrom(QueryTreeNode node) throws StandardException {
        super.copyFrom(node);

        OrderByColumn other = (OrderByColumn)node;
        this.expression = (ValueNode)getNodeFactory().copyNode(other.expression,
                                                               getParserContext());
        this.ascending = other.ascending;
        this.nullsOrderedLow = other.nullsOrderedLow;
    }

    /**
     * Convert this object to a String.  See comments in QueryTreeNode.java
     * for how this should be done for tree printing.
     *
     * @return This object as a String
     */
    public String toString() {
        return super.toString();
    }

    /**
     * Prints the sub-nodes of this object.  See QueryTreeNode.java for
     * how tree printing is supposed to work.
     *
     * @param depth The depth of this node in the tree
     */
    public void printSubNodes(int depth) {
        super.printSubNodes(depth);

        if (expression != null) {
            printLabel(depth, "expression: ");
            expression.treePrint(depth + 1);
        }
    }

    public ValueNode getExpression() {
        return expression;
    }

    /**
     * Mark the column as descending order
     */
    public void setDescending() {
        ascending = false;
    }

    /**
     * Get the column order.    Overrides 
     * OrderedColumn.isAscending.
     *
     * @return true if ascending, false if descending
     */
    public boolean isAscending() {
        return ascending;
    }

    /**
     * Mark the column as ordered NULL values lower than non-NULL values.
     */
    public void setNullsOrderedLow() {
        nullsOrderedLow = true;
    }

    /**
     * Get the column NULL ordering. Overrides
     * OrderedColumn.getIsNullsOrderedLow.
     *
     * @return true if NULLs ordered low, false if NULLs ordered high
     */
    public boolean isNullsOrderedLow() {
        return nullsOrderedLow;
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

        if (expression != null) {
            expression = (ValueNode)expression.accept(v);
        }
    }

}