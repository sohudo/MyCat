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
package com.akiban.sql.types;

import com.akiban.sql.StandardException;

/**
 *  Type descriptor which wraps all 3 kinds of types supported in Derby's
 *  JSQL language: SQL types, Java primitives, Java classes.
 *
 *  This interface was originally added to support the serializing of WorkUnit
 *  signatures.
 *
 *
 */
public final class JSQLType
{
    public static final byte SQLTYPE = 0;
    public static final byte JAVA_CLASS = 1;
    public static final byte JAVA_PRIMITIVE = 2;

    public static final byte NOT_PRIMITIVE = -1;
    public static final byte BOOLEAN = 0;
    public static final byte CHAR = 1;
    public static final byte BYTE = 2;
    public static final byte SHORT = 3;
    public static final byte INT = 4;
    public static final byte LONG = 5;
    public static final byte FLOAT = 6;
    public static final byte DOUBLE = 7;

    // these two arrays are in the order of the primitive constants
    static private final String[] wrapperClassNames = {
        "java.lang.Boolean",
        "java.lang.Integer", // we can't serialize char, so we convert it to int
        "java.lang.Integer",
        "java.lang.Integer",
        "java.lang.Integer",
        "java.lang.Long",
        "java.lang.Float",
        "java.lang.Double"
    };

    static private final String[] primitiveNames = {
        "boolean",
        "char",
        "byte",
        "short",
        "int",
        "long",
        "float",
        "double"
    };

    private byte category = JAVA_PRIMITIVE;
    private DataTypeDescriptor sqlType;
    private String javaClassName;
    private byte primitiveKind;

    /**
     * Create a JSQLType from a SQL type.
     *
     * @param sqlType the SQL type to wrap
     */
    public JSQLType(DataTypeDescriptor sqlType) { 
        initialize(sqlType); 
    }

    /**
     * Create a JSQLType given the name of a Java primitive or java class.
     *
     * @param javaName name of java primitive or class to wrap
     */
    public JSQLType(String javaName) {
        byte primitiveID = getPrimitiveID(javaName);
        if (primitiveID != NOT_PRIMITIVE) { 
            initialize(primitiveID); 
        }
        else { 
            initialize(javaName); 
        }
    }

    /**
     * Create a JSQLType for a Java primitive.
     *
     * @param primitiveKind primitive to wrap
     */
    public JSQLType(byte primitiveKind) { 
        initialize(primitiveKind); 
    }

    /**
     * What kind of type is this:
     *
     * @return one of the following: SQLTYPE, JAVA_PRIMITIVE, JAVA_CLASS
     */
    public byte getCategory() { 
        return category; 
    }

    /**
     * If this is a JAVA_PRIMITIVE, what is its name?
     *
     * @return BOOLEAN, INT, ... if this is a JAVA_PRIMITIVE.
     *               NOT_PRIMITIVE if this is SQLTYPE or JAVA_CLASS.
     */
    public byte getPrimitiveKind() { 
        return primitiveKind; 
    }

    /**
     * If this is a JAVA_CLASS, what is it's name?
     *
     * @return java class name if this is a JAVA_CLASS
     *               null if this is SQLTYPE or JAVA_PRIMITIVE
     */
    public String getJavaClassName() { 
        return javaClassName; 
    }

    public String getPrimitiveTypeName() {
        if (primitiveKind == NOT_PRIMITIVE)
            return null;
        else
            return primitiveNames[primitiveKind];
    }

    /**
     * What's our SQLTYPE?
     *
     * @return the DataTypeDescriptor corresponding to this type
     *
     */
    public DataTypeDescriptor getSQLType() throws StandardException {
        // Might not be filled in if this is a JAVA_CLASS or JAVA_PRIMITIVE.
        if (sqlType == null) {
            String className;

            if (category == JAVA_CLASS) {
                className = javaClassName;
            }
            else {
                className = getWrapperClassName(primitiveKind);
            }

            sqlType = DataTypeDescriptor.getSQLDataTypeDescriptor(className);
        }

        return sqlType;
    }

    // Give read-only access to array of strings
    public static String getPrimitiveName(byte index) {
        return primitiveNames[index];
    }

    private void initialize(byte primitiveKind) { 
        initialize(JAVA_PRIMITIVE, null, null, primitiveKind);
    }

    private void initialize(DataTypeDescriptor sqlType) { 
        initialize(SQLTYPE, sqlType, null, NOT_PRIMITIVE); 
    }

    private void initialize(String javaClassName) { 
        initialize(JAVA_CLASS, null, javaClassName, NOT_PRIMITIVE); 
    }

    /**
     * Initialize this JSQL type. Minion of all constructors.
     *
     * @param category SQLTYPE, JAVA_CLASS, JAVA_PRIMITIVE
     * @param sqlType corresponding SQL type if category=SQLTYPE
     * @param javaClassName corresponding java class if category=JAVA_CLASS
     * @param primitiveKind kind of primitive if category=JAVA_PRIMITIVE
     */
    private void initialize (byte category, DataTypeDescriptor sqlType,
                             String javaClassName, byte primitiveKind) {
        this.category = category;
        this.sqlType = sqlType;
        this.javaClassName = javaClassName;
        this.primitiveKind = primitiveKind;
    }

    /**
     * Gets the name of the java wrapper class corresponding to a primitive.
     *
     * @param primitive BOOLEAN, INT, ... etc.
     *
     * @return name of the java wrapper class corresponding to the primitive
     */
    private static String getWrapperClassName(byte primitive) {
        if (primitive == NOT_PRIMITIVE) { 
            return ""; 
        }
        return wrapperClassNames[primitive];
    }

    /**
     * Translate the name of a java primitive to an id
     *
     * @param name name of primitive
     *
     * @return BOOLEAN, INT, ... etc if the name is that of a primitive.
     *               NOT_PRIMITIVE otherwise
     */
    private static byte getPrimitiveID (String name) {
        for (byte ictr = BOOLEAN; ictr <= DOUBLE; ictr++) {
            if (primitiveNames[ictr].equals(name)) { 
                return ictr; 
            }
        }

        return NOT_PRIMITIVE;
    }

}