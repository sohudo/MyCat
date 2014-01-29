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
import com.akiban.sql.types.ValueClassName;

/**
 * A BinaryOperatorNode represents a built-in binary operator as defined by
 * the ANSI/ISO SQL standard.    This covers operators like +, -, *, /, =, <, etc.
 * Java operators are not represented here: the JSQL language allows Java
 * methods to be called from expressions, but not Java operators.
 *
 */

public class BinaryOperatorNode extends ValueNode
{
    protected String operator;
    protected String methodName;

    public static enum OperatorType {
        PLUS, MINUS, TIMES, DIVIDE, CONCATENATE, MOD, DIV,
        EQ, NE, GT, GE, LT, LE, AND, OR, LIKE, LTRIM, TRIM, RTRIM,
        BITAND, BITOR, BITXOR, LEFT_SHIFT, RIGHT_SHIFT,
        LEFT, RIGHT
    }

    protected ValueNode leftOperand;
    protected ValueNode rightOperand;

    protected String leftInterfaceType;
    protected String rightInterfaceType;
    protected String resultInterfaceType;

    /**
     * Initializer for a BinaryOperatorNode
     *
     * @param leftOperand The left operand of the node
     * @param rightOperand The right operand of the node
     * @param operator The name of the operator
     * @param methodName The name of the method to call for this operator
     * @param leftInterfaceType The name of the interface for the left operand
     * @param rightInterfaceType The name of the interface for the right operand
     */

    public void init(Object leftOperand, Object rightOperand,
                     Object operator, Object methodName,
                     Object leftInterfaceType, Object rightInterfaceType) {
        this.leftOperand = (ValueNode)leftOperand;
        this.rightOperand = (ValueNode)rightOperand;
        this.operator = (String)operator;
        this.methodName = (String)methodName;
        this.leftInterfaceType = (String)leftInterfaceType;
        this.rightInterfaceType = (String)rightInterfaceType;
    }

    public void init(Object leftOperand, Object rightOperand, 
                     Object leftInterfaceType, Object rightInterfaceType) {
        this.leftOperand = (ValueNode)leftOperand;
        this.rightOperand = (ValueNode)rightOperand;
        this.leftInterfaceType = (String)leftInterfaceType;
        this.rightInterfaceType = (String)rightInterfaceType;
    }

    /**
     * Fill this node with a deep copy of the given node.
     */
    public void copyFrom(QueryTreeNode node) throws StandardException {
        super.copyFrom(node);

        BinaryOperatorNode other = (BinaryOperatorNode)node;
        this.operator = other.operator;
        this.methodName = other.methodName;
        this.leftOperand = (ValueNode)
            getNodeFactory().copyNode(other.leftOperand, getParserContext());
        this.rightOperand = (ValueNode)
            getNodeFactory().copyNode(other.rightOperand, getParserContext());
        this.leftInterfaceType = other.leftInterfaceType;
        this.rightInterfaceType = other.rightInterfaceType;
        this.resultInterfaceType = other.resultInterfaceType;
    }

    /**
     * Convert this object to a String.  See comments in QueryTreeNode.java
     * for how this should be done for tree printing.
     *
     * @return This object as a String
     */

    public String toString() {
        return "operator: " + operator + "\n" +
            "methodName: " + methodName + "\n" + 
            super.toString();
    }

    /**
     * Set the operator.
     *
     * @param operator The operator.
     */
    void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    /**
     * Set the methodName.
     *
     * @param methodName The methodName.
     */
    void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }

    /**
     * Set the interface type for the left and right arguments.
     * Used when we don't know the interface type until
     * later in binding.
     */
    public void setLeftRightInterfaceType(String iType) {
        leftInterfaceType = iType;
        rightInterfaceType = iType;
    }

    /**
     * Prints the sub-nodes of this object.  See QueryTreeNode.java for
     * how tree printing is supposed to work.
     *
     * @param depth The depth of this node in the tree
     */

    public void printSubNodes(int depth) {
        super.printSubNodes(depth);

        if (leftOperand != null) {
            printLabel(depth, "leftOperand: ");
            leftOperand.treePrint(depth + 1);
        }

        if (rightOperand != null) {
            printLabel(depth, "rightOperand: ");
            rightOperand.treePrint(depth + 1);
        }
    }

    /**
     * Set the leftOperand to the specified ValueNode
     *
     * @param newLeftOperand The new leftOperand
     */
    public void setLeftOperand(ValueNode newLeftOperand) {
        leftOperand = newLeftOperand;
    }

    /**
     * Get the leftOperand
     *
     * @return The current leftOperand.
     */
    public ValueNode getLeftOperand() {
        return leftOperand;
    }

    /**
     * Set the rightOperand to the specified ValueNode
     *
     * @param newRightOperand The new rightOperand
     */
    public void setRightOperand(ValueNode newRightOperand) {
        rightOperand = newRightOperand;
    }

    /**
     * Get the rightOperand
     *
     * @return The current rightOperand.
     */
    public ValueNode getRightOperand() {
        return rightOperand;
    }

    /**
     * Return whether or not this expression tree represents a constant expression.
     *
     * @return Whether or not this expression tree represents a constant expression.
     */
    public boolean isConstantExpression() {
        return (leftOperand.isConstantExpression() &&
                rightOperand.isConstantExpression());
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

        if (leftOperand != null) {
            leftOperand = (ValueNode)leftOperand.accept(v);
        }

        if (rightOperand != null) {
            rightOperand = (ValueNode)rightOperand.accept(v);
        }
    }

    /**
     * @inheritDoc
     */
    protected boolean isEquivalent(ValueNode o) throws StandardException {
        if (!isSameNodeType(o)) {
            return false;
        }
        BinaryOperatorNode other = (BinaryOperatorNode)o;
        return methodName.equals(other.methodName) && 
            leftOperand.isEquivalent(other.leftOperand) && 
            rightOperand.isEquivalent(other.rightOperand);
    }

}