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
 * A SetRoleNode is the root of a QueryTree that represents a SET ROLE
 * statement.
 */

public class SetRoleNode extends MiscellaneousStatementNode
{
    private String name;
    private int type;

    /**
     * Initializer for a SetRoleNode
     *
     * @param roleName The name of the new role, null if NONE specified
     * @param type Type of role name could be USER or dynamic parameter
     *
     */
    public void init(Object roleName, Object type) {
        this.name = (String)roleName;
        if (type != null) {
            this.type = ((Integer)type).intValue();
        }
    }

    /**
     * Fill this node with a deep copy of the given node.
     */
    public void copyFrom(QueryTreeNode node) throws StandardException {
        super.copyFrom(node);

        SetRoleNode other = (SetRoleNode)node;
        this.name = other.name;
        this.type = other.type;
    }

    /**
     * Convert this object to a String.  See comments in QueryTreeNode.java
     * for how this should be done for tree printing.
     *
     * @return  This object as a String
     */

    public String toString() {
        return super.toString() +
            (type == StatementType.SET_ROLE_DYNAMIC ?
             "roleName: ?\n" :
             "rolename: " + name + "\n");
    }

    public String statementToString() {
        return "SET ROLE";
    }

}