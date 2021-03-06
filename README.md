MyCat
=====
什么是MyCAT？简单的说，MyCAT就是：<BR>
•	一个彻底开源的，面向企业应用开发的“大数据库集群”<BR>
•	支持事务、ACID、可以替代Mysql的加强版数据库<BR>
•	一个可以视为“Mysql”集群的企业级数据库，用来替代昂贵的Oracle集群<BR>
•	一个融合内存缓存技术、Nosql技术、HDFS大数据的新型SQL Server<BR>
•	结合传统数据库和新型分布式数据仓库的新一代企业级数据库产品<BR>
•	一个新颖的数据库中间件产品<BR>

MyCAT的目标是：低成本的将现有的单机数据库和应用平滑迁移到“云”端，解决数据存储和业务规模迅速增长情况下的数据瓶颈问题。
MyCAT的关键特性：<BR>
•	支持Mysql集群，可以作为Proxy使用<BR>
•	自动故障切换，高可用性<BR>
•	支持读写分离，支持Mysql双主多从，以及一主多从的模式<BR>
•	支持全局表，数据自动分片到多个节点，用于高效表关联查询<BR>
•	支持独有的基于E-R 关系的分片策略，实现了高效的表关联查询<BR>
•	多平台支持，部署和实施简单<BR>
MyCAT的优势：<BR>
•	基于阿里开源的Cobar产品而研发，Cobar的稳定性、可靠性、优秀的架构和性能，以及众多成熟的使用案例使得MyCAT一开始就拥有一个很好的起点，站在巨人的肩膀上，我们能看到更远。<BR>
•	广泛吸取业界优秀的开源项目和创新思路，将其融入到MyCAT的基因中，使得MyCAT在很多方面都领先于目前其他一些同类的开源项目，甚至超越某些商业产品。<BR>
•	MyCAT背后有一只强大的技术团队，其参与者都是5年以上资深软件工程师、架构师、DBA等，优秀的技术团队保证了MyCAT的产品质量。<BR>
•	MyCAT并不依托于任何一个商业公司，因此不像某些开源项目，将一些重要的特性封闭在其商业产品中，使得开源项目成了一个摆设。<BR>
MyCAT的长期路线规划：<BR>
•	在支持Mysql的基础上，后端增加更多的开源数据库和商业数据库的支持，包括原生支持PosteSQL、FireBird等开源数据库，以及通过JDBC等方式间接支持其他非开源的数据库如Oracle、DB2、SQL Server等<BR>
•	实现更为智能的自我调节特性，如自动统计分析SQL，自动创建和调整索引，根据数据表的读写频率，自动优化缓存和备份策略等<BR>
•	实现更全面的监控管理功能<BR>
•	与HDFS集成，提供SQL命令，将数据库装入HDFS中并能够快速分析<BR>
•	集成优秀的开源报表工具，使之具备一定的数据分析的能力<BR>
