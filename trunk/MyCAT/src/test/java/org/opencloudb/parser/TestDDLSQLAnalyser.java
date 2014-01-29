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
package org.opencloudb.parser;

import java.sql.SQLSyntaxErrorException;

import junit.framework.Assert;

import org.junit.Test;
import org.opencloudb.mpp.DDLParsInf;
import org.opencloudb.mpp.DDLSQLAnalyser;
import org.opencloudb.parser.SQLParserDelegate;

import com.akiban.sql.parser.QueryTreeNode;

public class TestDDLSQLAnalyser {
	@Test
	public void testSQL() throws SQLSyntaxErrorException {
		String sql = null;
		QueryTreeNode ast = null;
		DDLParsInf parsInf = null;

		sql = "CREATE TABLE Persons ( Id_P int,LastName varchar(255),FirstName varchar(255),Address varchar(255),City varchar(255))";
		ast = SQLParserDelegate.parse(sql, SQLParserDelegate.DEFAULT_CHARSET);
		parsInf = DDLSQLAnalyser.analyse(ast);
		Assert.assertEquals("Persons".toUpperCase(), parsInf.tableName);
		
//		sql = "CREATE TABLE \"Persons\" ( \"Id_P\" int,LastName varchar(255),FirstName varchar(255),Address varchar(255),City varchar(255))";
//		ast = SQLParserDelegate.parse(sql, SQLParserDelegate.DEFAULT_CHARSET);
//		parsInf = DDLSQLAnalyser.analyse(ast);
//		Assert.assertEquals("Persons".toUpperCase(), parsInf.tableName);
		
	
	}
	
}