/*
 * Copyright 2012-2015 org.opencloudb.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * (created at 2012-6-15)
 */
package org.opencloudb;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.opencloudb.config.loader.ConfigLoader;
import org.opencloudb.config.loader.SchemaLoader;
import org.opencloudb.config.loader.xml.XMLConfigLoader;
import org.opencloudb.config.loader.xml.XMLSchemaLoader;
import org.opencloudb.config.model.DataNodeConfig;
import org.opencloudb.config.model.DataSourceConfig;
import org.opencloudb.config.model.QuarantineConfig;
import org.opencloudb.config.model.SchemaConfig;
import org.opencloudb.config.model.SystemConfig;
import org.opencloudb.config.model.UserConfig;
import org.opencloudb.config.util.ConfigException;
import org.opencloudb.mysql.MySQLDataNode;
import org.opencloudb.mysql.nio.MySQLDataSource;
import org.opencloudb.util.SplitUtil;

/**
 * @author mycat
 */
public class ConfigInitializer {
	private volatile SystemConfig system;
	private volatile MycatCluster cluster;
	private volatile QuarantineConfig quarantine;
	private volatile Map<String, UserConfig> users;
	private volatile Map<String, SchemaConfig> schemas;
	private volatile Map<String, MySQLDataNode> dataNodes;
	private volatile Map<String, DataSourceConfig> dataSources;

	public ConfigInitializer() {
		SchemaLoader schemaLoader = new XMLSchemaLoader();
		XMLConfigLoader configLoader = new XMLConfigLoader(schemaLoader);
		schemaLoader = null;
		this.system = configLoader.getSystemConfig();
		this.users = configLoader.getUserConfigs();
		this.schemas = configLoader.getSchemaConfigs();
		this.dataSources = configLoader.getDataSources();
		this.dataNodes = initDataNodes(configLoader);
		this.quarantine = configLoader.getQuarantineConfig();
		this.cluster = initCobarCluster(configLoader);

		this.checkConfig();
	}

	private void checkConfig() throws ConfigException {
		if (users == null || users.isEmpty())
			return;
		for (UserConfig uc : users.values()) {
			if (uc == null) {
				continue;
			}
			Set<String> authSchemas = uc.getSchemas();
			if (authSchemas == null) {
				continue;
			}
			for (String schema : authSchemas) {
				if (!schemas.containsKey(schema)) {
					String errMsg = "schema " + schema + " refered by user "
							+ uc.getName() + " is not exist!";
					throw new ConfigException(errMsg);
				}
			}
		}

		for (SchemaConfig sc : schemas.values()) {
			if (null == sc) {
				continue;
			}
		}
	}

	public SystemConfig getSystem() {
		return system;
	}

	public MycatCluster getCluster() {
		return cluster;
	}

	public QuarantineConfig getQuarantine() {
		return quarantine;
	}

	public Map<String, UserConfig> getUsers() {
		return users;
	}

	public Map<String, SchemaConfig> getSchemas() {
		return schemas;
	}

	public Map<String, MySQLDataNode> getDataNodes() {
		return dataNodes;
	}

	public Map<String, DataSourceConfig> getDataSources() {
		return dataSources;
	}

	private MycatCluster initCobarCluster(ConfigLoader configLoader) {
		return new MycatCluster(configLoader.getClusterConfig());
	}

	private Map<String, MySQLDataNode> initDataNodes(ConfigLoader configLoader) {
		Map<String, DataNodeConfig> nodeConfs = configLoader.getDataNodes();
		Map<String, MySQLDataNode> nodes = new HashMap<String, MySQLDataNode>(
				nodeConfs.size());
		for (DataNodeConfig conf : nodeConfs.values()) {
			MySQLDataNode dataNode = getDataNode(conf, configLoader);
			if (nodes.containsKey(dataNode.getName())) {
				throw new ConfigException("dataNode " + dataNode.getName()
						+ " duplicated!");
			}
			nodes.put(dataNode.getName(), dataNode);
		}
		return nodes;
	}

	private MySQLDataNode getDataNode(DataNodeConfig dnc,
			ConfigLoader configLoader) {
		String[] dsNames = SplitUtil.split(dnc.getDataSource(), ',');
		checkDataSourceExists(dsNames);
		MySQLDataNode node = new MySQLDataNode(dnc);
		MySQLDataSource[] dsList = new MySQLDataSource[dsNames.length];
		int size = dnc.getPoolSize();
		for (int i = 0; i < dsList.length; i++) {
			DataSourceConfig dsc = dataSources.get(dsNames[i]);
			dsList[i] = new MySQLDataSource(node, i, dsc, size);
		}
		node.setSources(dsList);
		return node;
	}

	private void checkDataSourceExists(String... nodes) {
		if (nodes == null || nodes.length < 1) {
			return;
		}
		for (String node : nodes) {
			if (!dataSources.containsKey(node)) {
				throw new ConfigException("dataSource '" + node
						+ "' is not found!");
			}
		}
	}
}