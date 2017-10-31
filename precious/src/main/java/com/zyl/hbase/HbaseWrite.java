/**
 * chsi
 * Created on 2017-10-31
 */
package com.zyl.hbase;


import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Hbase 写数据
 *
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class HbaseWrite extends AbstractHbase {
    private static Logger log = LoggerFactory.getLogger(HbaseWrite.class);
    private static final int DATA_ROWS = 10000;

    public static void main(String[] args) throws IOException {
        Random random = new Random();
        long startTime = System.currentTimeMillis();
        HTable table = (HTable) connection.getTable(TableName.valueOf("zbbm:zb_info"));
        List<Put> putList = new ArrayList<>();
        for ( int index=0; index<=DATA_ROWS; index++ ) {
            String rowKey = "1" + StringUtils.leftPad(String.valueOf(index), 5, "0");
            Put put = new Put(Bytes.toBytes(rowKey));
            put.addColumn(Bytes.toBytes("base_info"), Bytes.toBytes("sg"), Bytes.toBytes(String.valueOf(random.nextInt(20) + 180)));
            putList.add(put);
        }
        table.put(putList);
        table.flushCommits();
        table.close();
        long endTime = System.currentTimeMillis();
        log.info("写入数据耗时：{}", (endTime - startTime));
    }
}