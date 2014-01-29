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

import java.math.BigDecimal;
import java.sql.Types;

public final class NumericConstantNode extends ConstantNode
{
    /**
     * Initializer for a typed null node
     *
     * @param arg1 The TypeId for the type of node OR An object containing the value of the constant.
     *
     * @exception StandardException
     */
    public void init(Object arg1) throws StandardException {
        int precision = 0, scal = 0, maxwidth = 0;
        Boolean isNullable;
        boolean valueInP; // value in Predicate-- if TRUE a value was passed in
        TypeId typeId = null;
        int typeid = 0;

        if (arg1 instanceof TypeId) {
            typeId = (TypeId)arg1;
            isNullable = Boolean.TRUE;
            valueInP = false;
            maxwidth = 0;
        }
        else {
            isNullable = Boolean.FALSE;
            valueInP = true;
        }

        switch (getNodeType()) {
        case NodeTypes.TINYINT_CONSTANT_NODE:
            precision = TypeId.SMALLINT_PRECISION;
            scal = TypeId.SMALLINT_SCALE;
            if (valueInP) {
                maxwidth = TypeId.SMALLINT_MAXWIDTH;
                typeid = Types.TINYINT;
                setValue((Byte)arg1);
            } 
            break;

        case NodeTypes.INT_CONSTANT_NODE:
            precision = TypeId.INT_PRECISION;
            scal = TypeId.INT_SCALE;
            if (valueInP) {
                maxwidth = TypeId.INT_MAXWIDTH;
                typeid = Types.INTEGER;
                setValue((Integer)arg1);
            }
            break;

        case NodeTypes.SMALLINT_CONSTANT_NODE:
            precision = TypeId.SMALLINT_PRECISION;
            scal = TypeId.SMALLINT_SCALE;
            if (valueInP) {
                maxwidth = TypeId.SMALLINT_MAXWIDTH;
                typeid = Types.SMALLINT;
                setValue((Short)arg1);
            }
            break;

        case NodeTypes.LONGINT_CONSTANT_NODE:
            precision = TypeId.LONGINT_PRECISION;
            scal = TypeId.LONGINT_SCALE;
            if (valueInP) {
                maxwidth = TypeId.LONGINT_MAXWIDTH;
                typeid = Types.BIGINT;
                setValue((Long)arg1);
            }
            break;

        case NodeTypes.DECIMAL_CONSTANT_NODE:
            if (valueInP) {
                typeid = Types.DECIMAL;
                String image = (String)arg1;
                int length = image.length();
                int idx = image.indexOf('.');
                precision = length;
                if (!Character.isDigit(image.charAt(0)) && (idx != 0))
                    precision--;                    // Has a sign.
                if (idx < 0)
                    scal = 0;
                else {
                    precision--;
                    scal = length - idx - 1;
                }
                maxwidth = length;
                setValue(new BigDecimal(image));
            }
            else {
                precision = TypeId.DEFAULT_DECIMAL_PRECISION;
                scal = TypeId.DEFAULT_DECIMAL_SCALE;
                maxwidth = TypeId.DECIMAL_MAXWIDTH;
            }
            break;

        case NodeTypes.DOUBLE_CONSTANT_NODE:
            precision = TypeId.DOUBLE_PRECISION;
            scal = TypeId.DOUBLE_SCALE;
            if (valueInP) {
                maxwidth = TypeId.DOUBLE_MAXWIDTH;
                typeid = Types.DOUBLE;
                setValue((Double)arg1);
            }
            break;

        case NodeTypes.FLOAT_CONSTANT_NODE:
            precision = TypeId.REAL_PRECISION;
            scal = TypeId.REAL_SCALE;
            if (valueInP) {
                maxwidth = TypeId.REAL_MAXWIDTH;
                typeid = Types.REAL;
                setValue((Float)arg1);
            }
            break;

        default:
            assert false : "Unexpected nodeType = " + getNodeType();
            break;
        }

        setType((typeId != null) ? typeId : TypeId.getBuiltInTypeId(typeid),
                precision, 
                scal, 
                isNullable.booleanValue(), 
                maxwidth);
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
     * @exception StandardException Thrown on error
     */
    Object getConstantValueAsObject() throws StandardException {
        return value;
    }

}