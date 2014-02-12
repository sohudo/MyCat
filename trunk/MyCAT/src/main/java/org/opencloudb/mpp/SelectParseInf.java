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
package org.opencloudb.mpp;

import java.util.List;

import org.opencloudb.route.RouteParseInf;

import com.akiban.sql.parser.GroupByList;
import com.akiban.sql.parser.NumericConstantNode;
import com.akiban.sql.parser.OrderByList;
import com.akiban.sql.parser.ResultColumn;

public class SelectParseInf extends RouteParseInf {
	public ShardingParseInfo ctx;
    public OrderByList orderByList;
    public GroupByList groupByList;
    public List<ResultColumn> aggregatColumns; 
    public NumericConstantNode offsetNode;
    public NumericConstantNode offCountNode;
	public void clear() {
		super.clear();
		orderByList=null;
		groupByList=null;
		aggregatColumns=null;
		if (ctx != null) {
			ctx.clear();
		}
	}
}