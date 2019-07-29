/**
 * chsi
 * Created on 2017-11-02
 */
package com.zyl.mysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class InsertTestDataBatch {
    private static Logger log = LoggerFactory.getLogger(InsertTestDataBatch.class);
    private static final String URL = "jdbc:mysql:cluster1:3306/test";
    private static final String USER_NAME = "root";
    private static final String PASSWORD= "123456";
    private static final String DRIVER_NAME = "org.gjt.mm.mysql.Driver";

    public static void main(String[] args) {
        Connection connection = getConnection();
        if ( null == connection ) {
            return;
        }
//        String sql = "insert into "
    }

    private static Connection getConnection() {
        try {
            Class.forName(DRIVER_NAME);
            return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch ( ClassNotFoundException e ) {
            log.error("连接Mysql异常：{}", e);
        } catch ( SQLException e ) {
            log.error("连接Mysql异常：{}", e);
        }
        return null;
    }
}