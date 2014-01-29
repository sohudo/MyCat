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
import com.akiban.sql.types.TypeId;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;

/**
        User type constants.    These are created by built-in types
        that use user types as their implementation. This could also
        potentially be used by an optimizer that wanted to store plans
        for frequently-used parameter values.

        This is also used to represent nulls in user types, which occurs
        when NULL is inserted into or supplied as the update value for
        a usertype column.

 */
public class UserTypeConstantNode extends ConstantNode 
{
    /*
    ** This value field hides the value in the super-type.  It is here
    ** Because user-type constants work differently from built-in constants.
    ** User-type constant values are stored as Objects, while built-in
    ** constants are stored as StorableDataValues.
    **
    ** RESOLVE: This is a bit of a mess, and should be fixed.    All constants
    ** should be represented the same way.
    */
    Object value;

    /**
     * Initializer for a typed null node
     * or a date, time, or timestamp value. Parameters may be:
     *
     * <ul>
     * <li>arg1 The TypeId for the type of the node</li>
     * </ul>
     *
     * <p>
     * - OR -
     * </p>
     *
     * <ul>
     * <li>arg1 the date, time, or timestamp value</li>
     * </ul>
     *
     * @exception StandardException thrown on failure
     */
    public void init(Object arg1) throws StandardException {
        if (arg1 instanceof TypeId) {
            super.init(arg1,
                       Boolean.TRUE,
                       DataTypeDescriptor.MAXIMUM_WIDTH_UNKNOWN);
        }
        else {
            Integer maxWidth = null;
            TypeId typeId = null;

            if (arg1 instanceof Date) {
                maxWidth = TypeId.DATE_MAXWIDTH;
                typeId = TypeId.getBuiltInTypeId(Types.DATE);
            }
            else if (arg1 instanceof Time) {
                maxWidth = TypeId.TIME_MAXWIDTH;
                typeId = TypeId.getBuiltInTypeId(Types.TIME);
            }
            else if (arg1 instanceof Timestamp) {
                maxWidth = TypeId.TIMESTAMP_MAXWIDTH;
                typeId = TypeId.getBuiltInTypeId(Types.TIMESTAMP);
            }
            else {
                assert false : "Unexpected class " + arg1.getClass().getName();
            }

            super.init(typeId,
                       (arg1 == null) ? Boolean.TRUE : Boolean.FALSE,
                       maxWidth);

            // TODO: For now, these are the same.
            setValue(arg1);
            value = arg1;
        }
    }

    /**
     * Fill this node with a deep copy of the given node.
     */
    public void copyFrom(QueryTreeNode node) throws StandardException {
        super.copyFrom(node);

        UserTypeConstantNode other = (UserTypeConstantNode)node;
        this.value = other.value;
    }

    /**
     * Return the object value of this user defined type.
     *
     * @return the value of this constant. can't use getValue() for this.
     *               getValue() returns the DataValueDescriptor for the built-in
     *               types that are implemented as user types (date, time, timestamp)
     */
    public Object getObjectValue() {
        return value; 
    }

    /**
     * Return whether or not this node represents a typed null constant.
     *
     */
    public boolean isNull() {
        return (value == null);
    }

    /**
     * Return an Object representing the bind time value of this
     * expression tree.  If the expression tree does not evaluate to
     * a constant at bind time then we return null.
     * This is useful for bind time resolution of VTIs.
     * RESOLVE: What do we do for primitives?
     *
     * @return An Object representing the bind time value of this expression tree.
     *               (null if not a bind time constant.)
     *
     */
    public Object getConstantValueAsObject() {
        return value;
    }

}