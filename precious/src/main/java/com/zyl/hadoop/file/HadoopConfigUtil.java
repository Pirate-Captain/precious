/**
 * chsi
 * Created on 2017-09-06
 */
package com.zyl.hadoop.file;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class HadoopConfigUtil {
    private static Properties configProperties = null;

    static {
        configProperties = new Properties();
        try {
            configProperties.load(new InputStreamReader(HadoopConfigUtil.class.getResourceAsStream("/hadoop.config")));
        } catch ( IOException e ) {
            throw new RuntimeException(e);
        }
    }

    public static String getConfig(String propertiesName) {
        return configProperties.getProperty(propertiesName);
    }
}