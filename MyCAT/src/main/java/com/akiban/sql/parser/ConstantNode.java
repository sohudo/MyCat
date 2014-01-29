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
import com.akiban.sql.types.TypeId;

/**
 * ConstantNode holds literal constants as well as nulls.
 * <p>
 * A NULL from the parser may not yet know its type; that
 * must be set during binding, as it is for parameters.
 * <p>
 * the DataValueDescriptor methods want to throw exceptions
 * when they are of the wrong type, but to do that they
 * must check typeId when the value is null, rather than
 * the instanceof check they do for returning a valid value.
 * <p>
 * For code generation, we generate a static field.  Then we set the 
 * field be the proper constant expression (something like <code>
 * getDatavalueFactory().getCharDataValue("hello", ...)) </code>)
 * in the constructor of the generated method.  Ideally
 * we would have just 
 */
public abstract class ConstantNode extends ValueNode
{
    Object value;

    /**
     * Initializer for non-numeric types
     *
     * @param typeId The Type ID of the datatype
     * @param nullable True means the constant is nullable
     * @param maximumWidth The maximum number of bytes in the data value
     *
     * @exception StandardException
     */
    public void init(Object typeId,
                     Object nullable,
                     Object maximumWidth) 
            throws StandardException {
        setType((TypeId)typeId,
                ((Boolean)nullable).booleanValue(),
                ((Integer)maximumWidth).intValue());
    }

    /**
     * Fill this node with a deep copy of the given node.
     */
    public void copyFrom(QueryTreeNode node) throws StandardException {
        super.copyFrom(node);

        ConstantNode other = (ConstantNode)node;
        this.value = other.value;       // Assumed to be immutable.
    }

    /**
     * Get the value in this ConstantNode
     */
    public Object getValue() {
        return value;
    }

    /**
     * Set the value in this ConstantNode.
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * Convert this object to a String.  See comments in QueryTreeNode.java
     * for how this should be done for tree printing.
     *
     * @return This object as a String
     */

    public String toString() {
        return "value: " + value + "\n" +
            super.toString();
    }

    /**
     * Return whether or not this expression tree represents a constant expression.
     *
     * @return Whether or not this expression tree represents a constant expression.
     */
    public boolean isConstantExpression() {
        return true;
    }

    /**
     * Return whether or not this node represents a typed null constant.
     *
     */
    boolean isNull() {
        return (value == null);
    }
                
    protected boolean isEquivalent(ValueNode o) throws StandardException {
        if (isSameNodeType(o)) {
            ConstantNode other = (ConstantNode)o;

            // value can be null which represents a SQL NULL value.
            return ((other.getValue() == null && getValue() == null) || 
                    (other.getValue() != null && other.getValue().equals(getValue())));
        }
        return false;
    }
}