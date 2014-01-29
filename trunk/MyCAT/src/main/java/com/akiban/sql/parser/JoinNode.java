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

import java.util.Properties;

/**
 * A JoinNode represents a join result set for either of the basic DML
 * operations: SELECT and INSERT.    For INSERT - SELECT, any of the
 * fields in a JoinNode can be used (the JoinNode represents
 * the (join) SELECT statement in the INSERT - SELECT).  For INSERT,
 * the resultColumns in the selectList will contain the names of the columns
 * being inserted into or updated.
 *
 */

public class JoinNode extends TableOperatorNode
{
    /* Join semantics */
    public static enum JoinType {
        INNER, CROSS, LEFT_OUTER, RIGHT_OUTER, FULL_OUTER, UNION, STRAIGHT
    }
    
    /** If this flag is true, this node represents a natural join. */
    private boolean naturalJoin;

    private ValueNode joinClause;
    private ResultColumnList usingClause;
    //User provided optimizer overrides
    private Properties joinOrderStrategyProperties;

    /**
     * Initializer for a JoinNode.
     *
     * @param leftResult The ResultSetNode on the left side of this join
     * @param rightResult The ResultSetNode on the right side of this join
     * @param onClause The ON clause
     * @param usingClause The USING clause
     * @param selectList The result column list for the join
     * @param tableProperties Properties list associated with the table
     * @param joinOrderStrategyProperties User provided optimizer overrides
     *
     * @exception StandardException Thrown on error
     */
    public void init(Object leftResult,
                     Object rightResult,
                     Object onClause,
                     Object usingClause,
                     Object selectList,
                     Object tableProperties,
                     Object joinOrderStrategyProperties)
            throws StandardException {
        super.init(leftResult, rightResult, tableProperties);
        resultColumns = (ResultColumnList)selectList;
        joinClause = (ValueNode)onClause;
        this.usingClause = (ResultColumnList)usingClause;
        this.joinOrderStrategyProperties = (Properties)joinOrderStrategyProperties;
    }

    /**
     * Fill this node with a deep copy of the given node.
     */
    public void copyFrom(QueryTreeNode node) throws StandardException {
        super.copyFrom(node);

        JoinNode other = (JoinNode)node;
        this.naturalJoin = other.naturalJoin;
        this.joinClause = (ValueNode)getNodeFactory().copyNode(other.joinClause,
                                                               getParserContext());
        this.usingClause = (ResultColumnList)getNodeFactory().copyNode(other.usingClause,
                                                                       getParserContext());
        this.joinOrderStrategyProperties = other.joinOrderStrategyProperties; // TODO: Clone?
    }

    /** 
     * Convert the joinType to a string.
     *
     * @param joinType The joinType as an enum.
     *
     * @return String The joinType as a String.
     */
    public static String joinTypeToString(JoinType joinType) {
        switch(joinType) {
        case INNER:
            return "INNER JOIN";

        case CROSS:
            return "CROSS JOIN";

        case LEFT_OUTER:
            return "LEFT OUTER JOIN";

        case RIGHT_OUTER:
            return "RIGHT OUTER JOIN";

        case FULL_OUTER:
            return "FULL OUTER JOIN";

        case UNION:
            return "UNION JOIN";

        default:
            assert false : "Unexpected joinType";
            return null;
        }
    }

    public ValueNode getJoinClause() {
        return joinClause;
    }
    public void setJoinClause(ValueNode joinClause) {
        this.joinClause = joinClause;
    }

    public ResultColumnList getUsingClause() {
        return usingClause;
    }
    public void setUsingClause(ResultColumnList usingClause) {
        this.usingClause = usingClause;
    }

    /**
     * Convert this object to a String.  See comments in QueryTreeNode.java
     * for how this should be done for tree printing.
     *
     * @return This object as a String
     */

    public String toString() {
        String str = super.toString();
        if (naturalJoin)
            str = "naturalJoin: " + naturalJoin + "\n" + str;
        if (joinOrderStrategyProperties != null)
            str = "joinOrderStrategyProperties: " + joinOrderStrategyProperties + "\n" + str;
        return str;
    }

    /**
     * Prints the sub-nodes of this object.  See QueryTreeNode.java for
     * how tree printing is supposed to work.
     *
     * @param depth The depth of this node in the tree
     */

    public void printSubNodes(int depth) {
        super.printSubNodes(depth);

        if (joinClause != null) {
            printLabel(depth, "joinClause: ");
            joinClause.treePrint(depth + 1);
        }

        if (usingClause != null) {
            printLabel(depth, "usingClause: ");
            usingClause.treePrint(depth + 1);
        }
    }

    /**
     * Flag this as a natural join so that an implicit USING clause will
     * be generated in the bind phase.
     */
    void setNaturalJoin() {
        naturalJoin = true;
    }

    /** Is this a natural join? */
    public boolean isNaturalJoin() {
        return naturalJoin;
    }

    /**
     * Return the logical left result set for this qualified
     * join node.
     * (For RIGHT OUTER JOIN, the left is the right
     * and the right is the left and the JOIN is the NIOJ).
     */
    public ResultSetNode getLogicalLeftResultSet() {
        return leftResultSet;
    }

    /**
     * Return the logical right result set for this qualified
     * join node.
     * (For RIGHT OUTER JOIN, the left is the right
     * and the right is the left and the JOIN is the NIOJ).
     */
    public ResultSetNode getLogicalRightResultSet() {
        return rightResultSet;
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

        if (resultColumns != null) {
            resultColumns = (ResultColumnList)resultColumns.accept(v);
        }

        if (joinClause != null) {
            joinClause = (ValueNode)joinClause.accept(v);
        }

        if (usingClause != null) {
            usingClause = (ResultColumnList)usingClause.accept(v);
        }
    }

}