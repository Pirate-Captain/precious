/**
 * chsi
 * Created on 2017-10-31
 */
package com.zyl.hbase;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Hbase 数据读取
 *
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class HbaseRead extends AbstractHbase {
    private static Logger log = LoggerFactory.getLogger(HbaseRead.class);

    public static void main(String[] args) throws IOException {
        HTable hTable = (HTable) connection.getTable(TableName.valueOf("zbbm:zb_info"));
        Scan scan = new Scan();
        scan.setFilter(new PageFilter(100));
        scan.addColumn(Bytes.toBytes("base_info"), Bytes.toBytes("sg"));
        scan.setMaxVersions(5);
        ResultScanner results = hTable.getScanner(scan);
        for ( Result result : results ) {
            for ( Cell cell : result.rawCells() ) {
                log.info("数据查询的结果：" + cell.toString());
            }
        }
        hTable.close();
    }
}