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
package org.opencloudb.config.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author mycat
 */
public class SchemaConfig {

	private final String name;
	private final Map<String, TableConfig> tables;
	private final boolean noSharding;
	private final String dataNode;
	private final Set<String> metaDataNodes;
	private final Set<String> allDataNodes;
	/**
	 * key is join relation ,A.ID=B.PARENT_ID value is Root Table ,if a->b*->c*
	 * ,then A is root table
	 */
	private final Map<String, TableConfig> joinRel2TableMap = new HashMap<String, TableConfig>();

	public SchemaConfig(String name, String dataNode,
			Map<String, TableConfig> tables) {
		this.name = name;
		this.dataNode = dataNode;
		this.tables = tables;
		buildJoinMap(tables);
		this.noSharding = (tables == null || tables.isEmpty()) ? true : false;
		if (!noSharding && dataNode != null) {
			throw new RuntimeException(
					name+" in sharidng mode schema can't have dataNode ");
		}
		this.metaDataNodes = buildMetaDataNodes();
		this.allDataNodes = buildAllDataNodes();
	}

	private void buildJoinMap(Map<String, TableConfig> tables2) {

		if (tables == null || tables.isEmpty()) {
			return;
		}
		for (TableConfig tc : tables.values()) {
			if (tc.isChildTable()) {
				TableConfig rootTc = tc.getRootParent();
				String joinRel1 = tc.getName() + '.' + tc.getJoinKey() + '='
						+ tc.getParentTC().getName() + '.' + tc.getParentKey();
				String joinRel2 = tc.getParentTC().getName() + '.'
						+ tc.getParentKey() + '=' + tc.getName() + '.'
						+ tc.getJoinKey();
				joinRel2TableMap.put(joinRel1, rootTc);
				joinRel2TableMap.put(joinRel2, rootTc);
			}

		}

	}

	public Map<String, TableConfig> getJoinRel2TableMap() {
		return joinRel2TableMap;
	}

	public String getName() {
		return name;
	}

	public String getDataNode() {
		return dataNode;
	}

	public Map<String, TableConfig> getTables() {
		return tables;
	}

	public boolean isNoSharding() {
		return noSharding;
	}

	public Set<String> getMetaDataNodes() {
		return metaDataNodes;
	}

	public Set<String> getAllDataNodes() {
		return allDataNodes;
	}

	public String getRandomDataNode() {
		if (allDataNodes == null || allDataNodes.isEmpty()) {
			return null;
		}
		return allDataNodes.iterator().next();
	}

	/**
	 * 取得含有不同Meta信息的数据节点,比如表和表结构。
	 */
	private Set<String> buildMetaDataNodes() {
		Set<String> set = new HashSet<String>();
		if (!isEmpty(dataNode)) {
			set.add(dataNode);
		}
		if (!noSharding) {
			for (TableConfig tc : tables.values()) {
				set.add(tc.getDataNodes().get(0));
			}
		}

		return set;
	}

	/**
	 * 取得该schema的所有数据节点
	 */
	private Set<String> buildAllDataNodes() {
		Set<String> set = new HashSet<String>();
		if (!isEmpty(dataNode)) {
			set.add(dataNode);
		}
		if (!noSharding) {
			for (TableConfig tc : tables.values()) {
				set.addAll(tc.getDataNodes());
			}
		}
		return set;
	}

	private static boolean isEmpty(String str) {
		return ((str == null) || (str.length() == 0));
	}

}