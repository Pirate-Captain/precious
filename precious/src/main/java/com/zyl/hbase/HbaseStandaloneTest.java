package com.zyl.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.TableNotFoundException;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;

public class HbaseStandaloneTest {
    public static void main(String[] args) throws TableNotFoundException, IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "192.168.40.135");// 使用eclipse时必须添加这个，否则无法定位
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        
        Connection connection = ConnectionFactory.createConnection(conf);
        Table table = connection.getTable(TableName.valueOf("my_test"));
        System.out.println(table.getName());
        
        HTableDescriptor descriptor = table.getTableDescriptor();
        HColumnDescriptor[] columnFamilies = descriptor.getColumnFamilies();
        System.out.println(columnFamilies.length);
        
//        HBaseAdmin admin = new HBaseAdmin(conf);// 新建一个数据库管理员
//        HTableDescriptor tableDescriptor = admin.getTableDescriptor(Bytes.toBytes("my_test"));
//        byte[] name = tableDescriptor.getName();
//        System.out.println("result:");
//
//        System.out.println("table name: " + new String(name));
//        HColumnDescriptor[] columnFamilies = tableDescriptor.getColumnFamilies();
//        for ( HColumnDescriptor d : columnFamilies ) {
//            System.out.println("column Families: " + d.getNameAsString());
//        }
    }
}