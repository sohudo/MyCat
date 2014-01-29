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
import com.akiban.sql.types.AliasInfo;

/**
    This is an interface for NodeFactories.
    <p>
    There is one of these per parser context, possibly wrapped for higher-level uses.
 */

public abstract class NodeFactory
{
    /**
     * Get a node that takes no initializer arguments.
     *
     * @param nodeType Identifier for the type of node.
     * @param pc A SQLParserContext
     *
     * @return A new QueryTree node.
     *
     * @exception StandardException Thrown on error.
     */
    public abstract QueryTreeNode getNode(int nodeType, SQLParserContext pc)
            throws StandardException;

    /**
     * Get a node that takes one initializer argument.
     *
     * @param nodeType Identifier for the type of node.
     * @param arg1 The initializer argument
     * @param pc A SQLParserContext
     *
     * @return A new QueryTree node.
     *
     * @exception StandardException Thrown on error.
     */
    public final QueryTreeNode getNode(int nodeType, Object arg1, SQLParserContext pc)
            throws StandardException {
        QueryTreeNode retval = getNode(nodeType, pc);

        retval.init(arg1);

        return retval;
    }

    /**
     * Get a node that takes two initializer arguments.
     *
     * @param nodeType Identifier for the type of node.
     * @param arg1 An initializer argument
     * @param arg2 An initializer argument
     * @param pc A SQLParserContext
     *
     * @return A new QueryTree node.
     *
     * @exception StandardException Thrown on error.
     */
    public final QueryTreeNode getNode(int nodeType,
                                       Object arg1,
                                       Object arg2,
                                       SQLParserContext pc)
            throws StandardException {
        QueryTreeNode retval =  getNode(nodeType, pc);

        retval.init(arg1, arg2);

        return retval;
    }

    /**
     * Get a node that takes three initializer arguments.
     *
     * @param nodeType Identifier for the type of node.
     * @param arg1 An initializer argument
     * @param arg2 An initializer argument
     * @param arg3 An initializer argument
     * @param pc A SQLParserContext
     *
     * @return A new QueryTree node.
     *
     * @exception StandardException Thrown on error.
     */
    public final QueryTreeNode getNode(int nodeType,
                                       Object arg1,
                                       Object arg2,
                                       Object arg3,
                                       SQLParserContext pc)
            throws StandardException {
        QueryTreeNode retval =  getNode(nodeType, pc);

        retval.init(arg1, arg2, arg3);

        return retval;
    }

    /**
     * Get a node that takes four initializer arguments.
     *
     * @param nodeType Identifier for the type of node.
     * @param arg1 An initializer argument
     * @param arg2 An initializer argument
     * @param arg3 An initializer argument
     * @param arg4 An initializer argument
     * @param pc A SQLParserContext
     *
     * @return A new QueryTree node.
     *
     * @exception StandardException Thrown on error.
     */
    public final QueryTreeNode getNode(int nodeType,
                                       Object arg1,
                                       Object arg2,
                                       Object arg3,
                                       Object arg4,
                                       SQLParserContext pc)
            throws StandardException {
        QueryTreeNode retval =  getNode(nodeType, pc);

        retval.init(arg1, arg2, arg3, arg4);

        return retval;
    }


    /**
     * Get a node that takes five initializer arguments.
     *
     * @param nodeType Identifier for the type of node.
     * @param arg1 An initializer argument
     * @param arg2 An initializer argument
     * @param arg3 An initializer argument
     * @param arg4 An initializer argument
     * @param arg5 An initializer argument
     * @param pc A SQLParserContext
     *
     * @return A new QueryTree node.
     *
     * @exception StandardException Thrown on error.
     */
    public final QueryTreeNode getNode(int nodeType,
                                       Object arg1,
                                       Object arg2,
                                       Object arg3,
                                       Object arg4,
                                       Object arg5,
                                       SQLParserContext pc)
            throws StandardException {
        QueryTreeNode retval =  getNode(nodeType, pc);

        retval.init(arg1, arg2, arg3, arg4, arg5);

        return retval;
    }

    /**
     * Get a node that takes six initializer arguments.
     *
     * @param nodeType Identifier for the type of node.
     * @param arg1 An initializer argument
     * @param arg2 An initializer argument
     * @param arg3 An initializer argument
     * @param arg4 An initializer argument
     * @param arg5 An initializer argument
     * @param arg6 An initializer argument
     * @param pc A SQLParserContext
     *
     * @return A new QueryTree node.
     *
     * @exception StandardException Thrown on error.
     */
    public final QueryTreeNode getNode(int nodeType,
                                       Object arg1,
                                       Object arg2,
                                       Object arg3,
                                       Object arg4,
                                       Object arg5,
                                       Object arg6,
                                       SQLParserContext pc)
            throws StandardException {
        QueryTreeNode retval =  getNode(nodeType, pc);

        retval.init(arg1, arg2, arg3, arg4, arg5, arg6);

        return retval;
    }

    /**
     * Get a node that takes seven initializer arguments.
     *
     * @param nodeType Identifier for the type of node.
     * @param arg1 An initializer argument
     * @param arg2 An initializer argument
     * @param arg3 An initializer argument
     * @param arg4 An initializer argument
     * @param arg5 An initializer argument
     * @param arg6 An initializer argument
     * @param arg7 An initializer argument
     * @param pc A SQLParserContext
     *
     * @return A new QueryTree node.
     *
     * @exception StandardException Thrown on error.
     */
    public final QueryTreeNode getNode(int nodeType,
                                       Object arg1,
                                       Object arg2,
                                       Object arg3,
                                       Object arg4,
                                       Object arg5,
                                       Object arg6,
                                       Object arg7,
                                       SQLParserContext pc)
            throws StandardException {
        QueryTreeNode retval =  getNode(nodeType, pc);

        retval.init(arg1, arg2, arg3, arg4, arg5, arg6, arg7);

        return retval;
    }

    /**
     * Get a node that takes eight initializer arguments.
     *
     * @param nodeType Identifier for the type of node.
     * @param arg1 An initializer argument
     * @param arg2 An initializer argument
     * @param arg3 An initializer argument
     * @param arg4 An initializer argument
     * @param arg5 An initializer argument
     * @param arg6 An initializer argument
     * @param arg7 An initializer argument
     * @param arg8 An initializer argument
     * @param pc A SQLParserContext
     *
     * @return A new QueryTree node.
     *
     * @exception StandardException Thrown on error.
     */
    public final QueryTreeNode getNode(int nodeType,
                                       Object arg1,
                                       Object arg2,
                                       Object arg3,
                                       Object arg4,
                                       Object arg5,
                                       Object arg6,
                                       Object arg7,
                                       Object arg8,
                                       SQLParserContext pc)
            throws StandardException {
        QueryTreeNode retval =  getNode(nodeType, pc);

        retval.init(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8);

        return retval;
    }

    /**
     * Get a node that takes nine initializer arguments.
     *
     * @param nodeType Identifier for the type of node.
     * @param arg1 An initializer argument
     * @param arg2 An initializer argument
     * @param arg3 An initializer argument
     * @param arg4 An initializer argument
     * @param arg5 An initializer argument
     * @param arg6 An initializer argument
     * @param arg7 An initializer argument
     * @param arg8 An initializer argument
     * @param arg9 An initializer argument
     * @param pc A SQLParserContext
     *
     * @return A new QueryTree node.
     *
     * @exception StandardException Thrown on error.
     */
    public final QueryTreeNode getNode(int nodeType,
                                       Object arg1,
                                       Object arg2,
                                       Object arg3,
                                       Object arg4,
                                       Object arg5,
                                       Object arg6,
                                       Object arg7,
                                       Object arg8,
                                       Object arg9,
                                       SQLParserContext pc)
            throws StandardException {
        QueryTreeNode retval =  getNode(nodeType, pc);

        retval.init(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);

        return retval;
    }

    /**
     * Get a node that takes ten initializer arguments.
     *
     * @param nodeType Identifier for the type of node.
     * @param arg1 An initializer argument
     * @param arg2 An initializer argument
     * @param arg3 An initializer argument
     * @param arg4 An initializer argument
     * @param arg5 An initializer argument
     * @param arg6 An initializer argument
     * @param arg7 An initializer argument
     * @param arg8 An initializer argument
     * @param arg9 An initializer argument
     * @param arg10 An initializer argument
     * @param pc A SQLParserContext
     *
     * @return A new QueryTree node.
     *
     * @exception StandardException Thrown on error.
     */
    public final QueryTreeNode getNode(int nodeType,
                                       Object arg1,
                                       Object arg2,
                                       Object arg3,
                                       Object arg4,
                                       Object arg5,
                                       Object arg6,
                                       Object arg7,
                                       Object arg8,
                                       Object arg9,
                                       Object arg10,
                                       SQLParserContext pc)
            throws StandardException {
        QueryTreeNode retval =  getNode(nodeType, pc);

        retval.init(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9,
                    arg10);

        return retval;
    }

    /**
     * Get a node that takes eleven initializer arguments.
     *
     * @param nodeType Identifier for the type of node.
     * @param arg1 An initializer argument
     * @param arg2 An initializer argument
     * @param arg3 An initializer argument
     * @param arg4 An initializer argument
     * @param arg5 An initializer argument
     * @param arg6 An initializer argument
     * @param arg7 An initializer argument
     * @param arg8 An initializer argument
     * @param arg9 An initializer argument
     * @param arg10 An initializer argument
     * @param arg11 An initializer argument
     * @param pc A SQLParserContext
     *
     * @return A new QueryTree node.
     *
     * @exception StandardException Thrown on error.
     */
    public final QueryTreeNode getNode(int nodeType,
                                       Object arg1,
                                       Object arg2,
                                       Object arg3,
                                       Object arg4,
                                       Object arg5,
                                       Object arg6,
                                       Object arg7,
                                       Object arg8,
                                       Object arg9,
                                       Object arg10,
                                       Object arg11,
                                       SQLParserContext pc)
            throws StandardException {
        QueryTreeNode retval =  getNode(nodeType, pc);

        retval.init(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9,
                    arg10, arg11);

        return retval;
    }

    /**
     * Get a node that takes twelve initializer arguments.
     *
     * @param nodeType Identifier for the type of node.
     * @param arg1 An initializer argument
     * @param arg2 An initializer argument
     * @param arg3 An initializer argument
     * @param arg4 An initializer argument
     * @param arg5 An initializer argument
     * @param arg6 An initializer argument
     * @param arg7 An initializer argument
     * @param arg8 An initializer argument
     * @param arg9 An initializer argument
     * @param arg10 An initializer argument
     * @param arg11 An initializer argument
     * @param arg12 An initializer argument
     * @param pc A SQLParserContext
     *
     * @return A new QueryTree node.
     *
     * @exception StandardException Thrown on error.
     */
    public final QueryTreeNode getNode(int nodeType,
                                       Object arg1,
                                       Object arg2,
                                       Object arg3,
                                       Object arg4,
                                       Object arg5,
                                       Object arg6,
                                       Object arg7,
                                       Object arg8,
                                       Object arg9,
                                       Object arg10,
                                       Object arg11,
                                       Object arg12,
                                       SQLParserContext pc)
            throws StandardException {
        QueryTreeNode retval =  getNode(nodeType, pc);

        retval.init(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9,
                    arg10, arg11, arg12);

        return retval;
    }

    /**
     * Get a node that takes thirteen initializer arguments.
     *
     * @param nodeType Identifier for the type of node.
     * @param arg1 An initializer argument
     * @param arg2 An initializer argument
     * @param arg3 An initializer argument
     * @param arg4 An initializer argument
     * @param arg5 An initializer argument
     * @param arg6 An initializer argument
     * @param arg7 An initializer argument
     * @param arg8 An initializer argument
     * @param arg9 An initializer argument
     * @param arg10 An initializer argument
     * @param arg11 An initializer argument
     * @param arg12 An initializer argument
     * @param arg13 An initializer argument
     * @param pc A SQLParserContext
     *
     * @return A new QueryTree node.
     *
     * @exception StandardException Thrown on error.
     */
    public final QueryTreeNode getNode(int nodeType,
                                       Object arg1,
                                       Object arg2,
                                       Object arg3,
                                       Object arg4,
                                       Object arg5,
                                       Object arg6,
                                       Object arg7,
                                       Object arg8,
                                       Object arg9,
                                       Object arg10,
                                       Object arg11,
                                       Object arg12,
                                       Object arg13,
                                       SQLParserContext pc)
            throws StandardException {
        QueryTreeNode retval =  getNode(nodeType, pc);

        retval.init(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9,
                    arg10, arg11, arg12, arg13);

        return retval;
    }

    /**
     * Get a node that takes fourteen initializer arguments.
     *
     * @param nodeType Identifier for the type of node.
     * @param arg1 An initializer argument
     * @param arg2 An initializer argument
     * @param arg3 An initializer argument
     * @param arg4 An initializer argument
     * @param arg5 An initializer argument
     * @param arg6 An initializer argument
     * @param arg7 An initializer argument
     * @param arg8 An initializer argument
     * @param arg9 An initializer argument
     * @param arg10 An initializer argument
     * @param arg11 An initializer argument
     * @param arg12 An initializer argument
     * @param arg13 An initializer argument
     * @param arg14 An initializer argument
     * @param pc A SQLParserContext
     *
     * @return A new QueryTree node.
     *
     * @exception StandardException Thrown on error.
     */
    public final QueryTreeNode getNode(int nodeType,
                                       Object arg1,
                                       Object arg2,
                                       Object arg3,
                                       Object arg4,
                                       Object arg5,
                                       Object arg6,
                                       Object arg7,
                                       Object arg8,
                                       Object arg9,
                                       Object arg10,
                                       Object arg11,
                                       Object arg12,
                                       Object arg13,
                                       Object arg14,
                                       SQLParserContext pc)
            throws StandardException {
        QueryTreeNode retval =  getNode(nodeType, pc);

        retval.init(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9,
                    arg10, arg11, arg12, arg13, arg14);

        return retval;
    }

    /** 
     * Make a deep copy of the given node for the given context, which
     * may not be the same as the node's context.
     */
    public QueryTreeNode copyNode(QueryTreeNode node, SQLParserContext pc)
            throws StandardException {
        if (node == null) return null; // TODO: Convenience or bug-hider?

        QueryTreeNode retval =  getNode(node.getNodeType(), pc);

        retval.copyFrom(node);

        return retval;
    }

    /**
     * Copy node's user data.
     *
     * Default implementation just does a pointer copy.
     * Binding implementation needs to provide one that deals with any
     * references it has.
     */
    public Object copyUserData(QueryTreeNode node, Object userData)
            throws StandardException {
        return userData;
    }

    /**
     * Get one of the several types of create alias nodes. Carved out of parser
     * so this could be used by ALTER PUBLICATION.
     *
     * @param aliasName The name of the alias
     * @param targetName The full path/method name
     * @param aliasSpecificInfo The full path of the target method name,
     * if any
     * @param aliasType The type of alias to create
     * @param delimitedIdentifier Whether or not to treat the class name
     * as a delimited identifier if trying to
     * resolve it as a class alias.
     * @param pc A SQLParserContext
     *
     * @return A CreateAliasNode matching the given parameters
     *
     * @exception StandardException Thrown on error
     */
    public QueryTreeNode getCreateAliasNode(Object aliasName,
                                            Object targetName,
                                            Object aliasSpecificInfo,
                                            AliasInfo.Type aliasType,
                                            Boolean createOrReplace,
                                            SQLParserContext pc)
            throws StandardException {
        int nodeType = NodeTypes.CREATE_ALIAS_NODE;
        String methodName = null;

        if ((aliasType != AliasInfo.Type.SYNONYM) &&
            (aliasType != AliasInfo.Type.UDT)) {
            String fullStaticMethodName = (String)targetName;
            if (fullStaticMethodName != null) {
                int lastPeriod;
                int paren = fullStaticMethodName.indexOf('(');
                if (paren == -1) {
                    // not a Java signature - split based on last period
                    lastPeriod = fullStaticMethodName.lastIndexOf('.');
                } 
                else {
                    // a Java signature - split on last period before the '('
                    lastPeriod = fullStaticMethodName.substring(0, paren).lastIndexOf('.');
                }
                if (lastPeriod != -1 && lastPeriod != fullStaticMethodName.length()-1) {
                    targetName = fullStaticMethodName.substring(0, lastPeriod);
                    methodName = fullStaticMethodName.substring(lastPeriod + 1);
                }
            }
        }

        return getNode(nodeType,
                       aliasName,
                       targetName,
                       methodName,
                       aliasSpecificInfo,
                       aliasType,
                       createOrReplace,
                       pc);
    }

}