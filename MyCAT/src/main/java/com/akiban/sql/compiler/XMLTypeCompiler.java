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
package com.akiban.sql.compiler;

import com.akiban.sql.parser.*;

import com.akiban.sql.StandardException;
import com.akiban.sql.types.DataTypeDescriptor;
import com.akiban.sql.types.TypeId;

/**
 * This class implements TypeCompiler for the XML type.
 */

public class XMLTypeCompiler extends TypeCompiler
{
    protected XMLTypeCompiler(TypeId typeId) {
        super(typeId);
    }

    /**
     * Tell whether this type (XML) can be converted to the given type.
     *
     * An XML value can't be converted to any other type, per
     * SQL/XML[2003] 6.3 <cast specification>
     *
     * @see TypeCompiler#convertible
     */
    public boolean convertible(TypeId otherType, boolean forDataTypeFunction) {
        // An XML value cannot be converted to any non-XML type.    If
        // user wants to convert an XML value to a string, then
        // s/he must use the provided SQL/XML serialization operator
        // (namely, XMLSERIALIZE).
        return otherType.isXMLTypeId();
    }

    /**
     * Tell whether this type (XML) is compatible with the given type.
     *
     * @param otherType The TypeId of the other type.
     */
    public boolean compatible(TypeId otherType) {
        // An XML value is not compatible (i.e. cannot be "coalesced")
        // into any non-XML type.
        return otherType.isXMLTypeId();
    }

    /**
     * @see TypeCompiler#getCorrespondingPrimitiveTypeName
     */
    public String getCorrespondingPrimitiveTypeName() {
        int formatId = getStoredFormatIdFromTypeId();
        if (formatId == TypeId.FormatIds.XML_TYPE_ID)
            return "org.apache.derby.iapi.types.XML"; // TODO: What is really used?

        assert false : "unexpected formatId in getCorrespondingPrimitiveTypeName(): " + formatId;
        return null;
    }

    /**
     * Get the method name for getting out the corresponding primitive
     * Java type.
     *
     * @return String The method call name for getting the
     *                              corresponding primitive Java type.
     */
    public String getPrimitiveMethodName() {
        return "getXML";                        // TODO: How does this really work?
    }

    /**
     * @see TypeCompiler#getCastToCharWidth
     *
     * While it is true XML values can't be cast to char, this method
     * can get called before we finish type checking--so we return a dummy
     * value here and let the type check throw the appropriate error.
     */
    public int getCastToCharWidth(DataTypeDescriptor dts) {
        return -1;
    }

}