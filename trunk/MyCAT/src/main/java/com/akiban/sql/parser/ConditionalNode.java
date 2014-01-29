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
 * A ConditionalNode represents an if/then/else operator with a single
 * boolean expression on the "left" of the operator and a list of expressions on 
 * the "right". This is used to represent the java conditional (aka immediate if).
 *
 */

public class ConditionalNode extends ValueNode
{
    private ValueNode testCondition;
    private ValueNodeList thenElseList;

    // True means we are here for NULLIF(V1,V2), false means we are here for following
    // CASE WHEN BooleanExpression THEN thenExpression ELSE elseExpression END
    private boolean thisIsNullIfNode;

    /**
     * Initializer for a ConditionalNode
     *
     * @param testCondition The boolean test condition
     * @param thenElseList ValueNodeList with then and else expressions
     */

    public void init(Object testCondition, Object thenElseList, Object thisIsNullIfNode) {
        this.testCondition = (ValueNode)testCondition;
        this.thenElseList = (ValueNodeList)thenElseList;
        this.thisIsNullIfNode = ((Boolean)thisIsNullIfNode).booleanValue();
    }

    /**
     * Fill this node with a deep copy of the given node.
     */
    public void copyFrom(QueryTreeNode node) throws StandardException {
        super.copyFrom(node);

        ConditionalNode other = (ConditionalNode)node;
        this.testCondition = (ValueNode)
            getNodeFactory().copyNode(other.testCondition, getParserContext());
        this.thenElseList = (ValueNodeList)
            getNodeFactory().copyNode(other.thenElseList, getParserContext());
        this.thisIsNullIfNode = other.thisIsNullIfNode;
    }

    public ValueNode getTestCondition() {
        return testCondition;
    }

    public void setTestCondition(ValueNode testCondition) {
        this.testCondition = testCondition;
    }

    public ValueNodeList getThenElseList() {
        return thenElseList;
    }

    public ValueNode getThenNode() {
        return thenElseList.get(0);
    }

    public void setThenNode(ValueNode thenNode) {
        thenElseList.set(0, thenNode);
    }

    public ValueNode getElseNode() {
        return thenElseList.get(1);
    }

    public void setElseNode(ValueNode elseNode) {
        thenElseList.set(1, elseNode);
    }

    public boolean isNullIfNode() {
        return thisIsNullIfNode;
    }

    /**
     * Prints the sub-nodes of this object.  See QueryTreeNode.java for
     * how tree printing is supposed to work.
     *
     * @param depth The depth of this node in the tree
     */

    public void printSubNodes(int depth) {
        super.printSubNodes(depth);

        if (testCondition != null) {
            printLabel(depth, "testCondition: ");
            testCondition.treePrint(depth + 1);
        }

        if (thenElseList != null) {
            printLabel(depth, "thenElseList: ");
            thenElseList.treePrint(depth + 1);
        }
    }

    /**
     * Return whether or not this expression tree represents a constant expression.
     *
     * @return Whether or not this expression tree represents a constant expression.
     */
    public boolean isConstantExpression() {
        return (testCondition.isConstantExpression() &&
                thenElseList.isConstantExpression());
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

        if (testCondition != null) {
            testCondition = (ValueNode)testCondition.accept(v);
        }

        if (thenElseList != null) {
            thenElseList = (ValueNodeList)thenElseList.accept(v);
        }
    }
                
    /**
     * {@inheritDoc}
     */
    protected boolean isEquivalent(ValueNode o) throws StandardException {
        if (isSameNodeType(o)) {
            ConditionalNode other = (ConditionalNode)o;
            return testCondition.isEquivalent(other.testCondition) &&
                thenElseList.isEquivalent(other.thenElseList);
        }
        return false;
    }

}