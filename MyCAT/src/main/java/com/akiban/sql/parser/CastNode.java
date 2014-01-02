/**
 * Copyright 2011-2013 Akiban Technologies, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/* The original from which this derives bore the following: */

/*

   Derby - Class org.apache.derby.impl.sql.compile.CastNode

   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to you under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */

package com.akiban.sql.parser;

import com.akiban.sql.StandardException;
import com.akiban.sql.types.DataTypeDescriptor;

import java.sql.Types;

/**
 * An CastNode represents a cast expressionr.
 *
 */

public class CastNode extends ValueNode
{
    private ValueNode castOperand;
    private int targetCharType;
    private boolean forDataTypeFunction = false;
        
    /** This variable gets set by the parser to indicate that this CAST node 
     * has been generated by the parser. This means that we should use the 
     * collation info of the current compilation schmea for this node's 
     * collation setting. If this variable does not get set to true, then it 
     * means that this CAST node has been an internally generated node and we 
     * should not touch the collation info set for this CAST node because it 
     * has been already set correctly by the class that generated this CAST 
     * node. Collation info is part of the DataTypeDescriptor that's defined
     * on the ValueNode (the super class of this CastNode class)
     */ 
    private boolean externallyGeneratedCastNode = false;

    /**
     * Initializer for a CastNode
     *
     * @param castOperand The operand of the node
     * @param castTarget DataTypeDescriptor (target type of cast)
     *
     * @exception StandardException Thrown on error
     */

    public void init(Object castOperand, Object castTarget) throws StandardException {
        this.castOperand = (ValueNode)castOperand;
        setType((DataTypeDescriptor)castTarget);
    }

    /**
     * Initializer for a CastNode
     *
     * @param castOperand The operand of the node
     * @param charType CHAR or VARCHAR JDBC type as target
     * @param charLength target type length
     *
     * @exception StandardException Thrown on error
     */

    public void init(Object castOperand, Object charType, Object charLength) 
            throws StandardException {
        this.castOperand = (ValueNode)castOperand;
        int charLen = ((Integer)charLength).intValue();
        targetCharType = ((Integer)charType).intValue();
        if (charLen < 0)                        // unknown, figure out later
            return;
        setType(DataTypeDescriptor.getBuiltInDataTypeDescriptor(targetCharType, charLen));
    }

    /**
     * Fill this node with a deep copy of the given node.
     */
    public void copyFrom(QueryTreeNode node) throws StandardException {
        super.copyFrom(node);

        CastNode other = (CastNode)node;
        this.castOperand = (ValueNode)
            getNodeFactory().copyNode(other.castOperand, getParserContext());
        this.targetCharType = other.targetCharType;
        this.forDataTypeFunction = other.forDataTypeFunction;
        this.externallyGeneratedCastNode = other.externallyGeneratedCastNode;
    }

    public ValueNode getCastOperand() {
        return castOperand;
    }

    /**
     * Convert this object to a String.  See comments in QueryTreeNode.java
     * for how this should be done for tree printing.
     *
     * @return This object as a String
     */

    public String toString() {
        return "castTarget: " + getType() + "\n" +
            super.toString();
    }

    /**
     * Prints the sub-nodes of this object.  See QueryTreeNode.java for
     * how tree printing is supposed to work.
     *
     * @param depth     The depth of this node in the tree
     */

    public void printSubNodes(int depth) {
        super.printSubNodes(depth);

        if (castOperand != null) {
            printLabel(depth, "castOperand: ");
            castOperand.treePrint(depth + 1);
        }
    }

    /**
     * Return whether or not this expression tree represents a constant expression.
     *
     * @return  Whether or not this expression tree represents a constant expression.
     */
    public boolean isConstantExpression() {
        return castOperand.isConstantExpression();
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

        if (castOperand != null) {
            castOperand = (ValueNode)castOperand.accept(v);
        }
    }

    /** This method gets called by the parser to indiciate that this CAST node 
     * has been generated by the parser. This means that we should use the 
     * collation info of the current compilation schmea for this node's 
     * collation setting. If this method does not get called, then it means
     * that this CAST node has been an internally generated node and we should
     * not touch the collation of this CAST node because it has been already 
     * set correctly by the class that generated this CAST node. 
     */
    void setForExternallyGeneratedCASTnode() {
        externallyGeneratedCastNode = true;
    }

    /** set this to be a dataTypeScalarFunction
     * 
     * @param b true to use function conversion rules
     */
    void setForDataTypeFunction(boolean b) {
        forDataTypeFunction = b;
    }

    /**
     * {@inheritDoc}
     * @throws StandardException 
     */
    protected boolean isEquivalent(ValueNode o) throws StandardException {
        if (isSameNodeType(o)) {
            CastNode other = (CastNode)o;
            return getType().equals(other.getType()) && 
                castOperand.isEquivalent(other.castOperand);
        }
        return false;
    }
}