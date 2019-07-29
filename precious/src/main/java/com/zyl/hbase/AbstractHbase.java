/**
 * chsi
 * Created on 2017-10-31
 */
package com.zyl.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public abstract class AbstractHbase {
    private static Logger log = LoggerFactory.getLogger(AbstractHbase.class);
    private static Configuration configuration;
    protected static Connection connection;

    static {
        try {
            configuration = HBaseConfiguration.create();
            configuration.set("hbase.zookeeper.quorum", HbaseConfigUtil.getConfig("hbase.zookeeper.quorum"));
            configuration.set("hbase.zookeeper.property.clientPort", HbaseConfigUtil.getConfig("hbase.zookeeper.property.clientPort"));
            System.setProperty("HADOOP_USER_NAME", "hadoop");
            connection = ConnectionFactory.createConnection(configuration);
        } catch ( IOException e ) {
            log.error("初始化hbase链接异常：{}", e);
        }
    }
}