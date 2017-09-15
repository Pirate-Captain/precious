/**
 * chsi
 * Created on 2017-09-15
 */
package com.zyl.sort.bigdata;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class FileInfo implements Comparable<FileInfo> {
    private String fileName;
    private Integer currentData;
    private BufferedReader br;

    public FileInfo(String fileName) {
        this.fileName = fileName;
        try {
            br = new BufferedReader(new FileReader(fileName));
            currentData = next();
        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
        }
    }

    public Integer getCurrentData() {
        return currentData;
    }

    public String getFileName() {
        return fileName;
    }

    public Integer next() {
        if ( null == br ) {
            return null;
        }
        String line = "";
        try {
            line = br.readLine();
        } catch ( IOException e ) {
            e.printStackTrace();
        }
        if ( StringUtils.isBlank(line) ) {
            try {
                br.close();
                br = null;
            } catch ( IOException e ) {
                e.printStackTrace();
            }
            return null;
        }
        Integer result = Integer.parseInt(line);
        currentData = result;
        return result;
    }


    @Override
    public int compareTo(FileInfo o) {
        if ( this.currentData == o.getCurrentData() ) {
            return this.currentData - o.getCurrentData();
        }
        return this.fileName.compareTo(o.getFileName());
    }
}
