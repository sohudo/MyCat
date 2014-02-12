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
 * The result set generated by this node (RowCountResultSet) implements the
 * filtering of rows needed for the <result offset clause> and the <fetch first
 * clause>.  It sits on top of the normal SELECT's top result set, but under any
 * ScrollInsensitiveResultSet. The latter's positioning is needed for the correct
 * functioning of <result offset clause> and <fetch first clause> in the
 * presence of scrollable and/or updatable result sets and CURRENT OF cursors.
 */
public final class RowCountNode extends SingleChildResultSetNode
{
    /**
     * If not null, this represents the value of a <result offset clause>.
     */
    private ValueNode offset;
    /**
     * If not null, this represents the value of a <fetch first clause>.
     */
    private ValueNode fetchFirst;

    /**
     * Initializer for a RowCountNode
     *
     * @exception StandardException
     */
    public void init(Object childResult,
                     Object rcl,
                     Object offset,
                     Object fetchFirst)
            throws StandardException {

        init(childResult, null);
        resultColumns = (ResultColumnList)rcl;

        this.offset = (ValueNode)offset;
        this.fetchFirst = (ValueNode)fetchFirst;
    }

    /**
     * Fill this node with a deep copy of the given node.
     */
    public void copyFrom(QueryTreeNode node) throws StandardException {
        super.copyFrom(node);

        RowCountNode other = (RowCountNode)node;
        this.offset = (ValueNode)getNodeFactory().copyNode(other.offset,
                                                           getParserContext());
        this.fetchFirst = (ValueNode)getNodeFactory().copyNode(other.fetchFirst,
                                                               getParserContext());
    }

    /**
     * Convert this object to a String.  See comments in QueryTreeNode.java
     * for how this should be done for tree printing.
     *
     * @return  This object as a String
     */

    public String toString() {
        return "offset: " + offset + "\n" +
            "fetchFirst:" + fetchFirst + "\n" +
            super.toString();
    }

}