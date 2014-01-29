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

import java.util.List;
import java.util.Iterator;

/**
 * A CreateTriggerNode is the root of a QueryTree 
 * that represents a CREATE TRIGGER
 * statement.
 *
 */

public class CreateTriggerNode extends DDLStatementNode
{
    public static final int TRIGGER_EVENT_UPDATE = 1;
    public static final int TRIGGER_EVENT_DELETE = 2;
    public static final int TRIGGER_EVENT_INSERT = 4;

    private TableName triggerName;
    private TableName tableName;
    private int triggerEventMask;
    private ResultColumnList triggerCols;
    private boolean isBefore;
    private boolean isRow;
    private boolean isEnabled;
    private List<TriggerReferencingStruct> refClause;
    private ValueNode whenClause;
    private String whenText;
    private int whenOffset;
    private StatementNode actionNode;
    private String actionText;
    private String originalActionText; // text w/o trim of spaces
    private int actionOffset;

    /**
     * Initializer for a CreateTriggerNode
     *
     * @param triggerName name of the trigger
     * @param tableName name of the table which the trigger is declared upon
     * @param triggerEventMask TRIGGER_EVENT_XXX
     * @param triggerCols columns trigger is to fire upon.  Valid for UPDATE case only.
     * @param isBefore is before trigger (false for after)
     * @param isRow true for row trigger, false for statement
     * @param isEnabled true if enabled
     * @param refClause the referencing clause
     * @param whenClause the WHEN clause tree
     * @param whenText the text of the WHEN clause
     * @param whenOffset offset of start of WHEN clause
     * @param actionNode the trigger action tree
     * @param actionText the text of the trigger action
     * @param actionOffset offset of start of action clause
     *
     * @exception StandardException Thrown on error
     */
    public void init (Object triggerName,
                      Object tableName,
                      Object triggerEventMask,
                      Object triggerCols,
                      Object isBefore,
                      Object isRow,
                      Object isEnabled,
                      Object refClause,
                      Object whenClause,
                      Object whenText,
                      Object whenOffset,
                      Object actionNode,
                      Object actionText,
                      Object actionOffset) throws StandardException {
        initAndCheck(triggerName);
        this.triggerName = (TableName)triggerName;
        this.tableName = (TableName)tableName;
        this.triggerEventMask = ((Integer)triggerEventMask).intValue();
        this.triggerCols = (ResultColumnList)triggerCols;
        this.isBefore = ((Boolean)isBefore).booleanValue();
        this.isRow = ((Boolean)isRow).booleanValue();
        this.isEnabled = ((Boolean)isEnabled).booleanValue();
        this.refClause = (List<TriggerReferencingStruct>)refClause; 
        this.whenClause = (ValueNode)whenClause;
        this.whenText = (whenText == null) ? null : ((String)whenText).trim();
        this.whenOffset = ((Integer)whenOffset).intValue();
        this.actionNode = (StatementNode)actionNode;
        this.originalActionText = (String)actionText;
        this.actionText = (actionText == null) ? null : ((String)actionText).trim();
        this.actionOffset = ((Integer)actionOffset).intValue();
        implicitCreateSchema = true;
    }

    /**
     * Fill this node with a deep copy of the given node.
     */
    public void copyFrom(QueryTreeNode node) throws StandardException {
        super.copyFrom(node);

        CreateTriggerNode other = (CreateTriggerNode)node;
        this.triggerName = (TableName)getNodeFactory().copyNode(other.triggerName,
                                                                getParserContext());
        this.tableName = (TableName)getNodeFactory().copyNode(other.tableName,
                                                              getParserContext());
        this.triggerEventMask = other.triggerEventMask;
        this.triggerCols = (ResultColumnList)getNodeFactory().copyNode(other.triggerCols,
                                                                       getParserContext());
        this.isBefore = other.isBefore;
        this.isRow = other.isRow;
        this.isEnabled = other.isEnabled;
        this.refClause = other.refClause;
        this.whenClause = (ValueNode)getNodeFactory().copyNode(other.whenClause,
                                                               getParserContext());
        this.whenText = other.whenText;
        this.whenOffset = other.whenOffset;
        this.actionNode = (StatementNode)getNodeFactory().copyNode(other.actionNode,
                                                                   getParserContext());
        this.actionText = other.actionText;
        this.originalActionText = other.originalActionText;
        this.actionOffset = other.actionOffset;
    }

    public String statementToString() {
        return "CREATE TRIGGER";
    }

    /**
     * Prints the sub-nodes of this object.  See QueryTreeNode.java for
     * how tree printing is supposed to work.
     *
     * @param depth     The depth of this node in the tree
     */

    public void printSubNodes(int depth) {
        super.printSubNodes(depth);

        if (triggerCols != null) {
            printLabel(depth, "triggerColumns: ");
            triggerCols.treePrint(depth + 1);
        }
        if (whenClause != null) {
            printLabel(depth, "whenClause: ");
            whenClause.treePrint(depth + 1);
        }
        if (actionNode != null) {
            printLabel(depth, "actionNode: ");
            actionNode.treePrint(depth + 1);
        }
    }

    /**
     * Convert this object to a String.  See comments in QueryTreeNode.java
     * for how this should be done for tree printing.
     *
     * @return  This object as a String
     */
    public String toString() {
        String refString = "null";
        if (refClause != null) {
            StringBuffer buf = new StringBuffer();
            for (TriggerReferencingStruct trn : refClause) {
                buf.append("\t");
                buf.append(trn.toString());
                buf.append("\n");
            }
            refString = buf.toString();
        }
        return super.toString() +
            "tableName: "+tableName+        
            "\ntriggerEventMask: "+triggerEventMask+        
            "\nisBefore: "+isBefore+        
            "\nisRow: "+isRow+      
            "\nisEnabled: "+isEnabled+      
            "\nwhenText: "+whenText+
            "\nrefClause: "+refString+
            "\nactionText: "+actionText+
            "\n";
    }

}