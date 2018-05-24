/*
 * Created on 2016-4-26
 */
package com.zyl.excel.zzsg;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhuyl <a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class ZzsgZyfbExcelParseCount {
    private static Map<String, String> zkMap = new HashMap<String, String>();
    private static Map<String, String> bkMap = new HashMap<String, String>();
    private static final String FILE_PATH = "D:\\logs\\zbbm\\zzsg\\2018\\zzsgzy.xlsx";

    public static void main(String[] args) {
        new ZzsgZyfbExcelParseCount().parse();
    }

    private void parse() {
        Workbook book = null;
        try {
            book = ZzsgZyfbUtil.getWorkbook(FILE_PATH);
            Sheet sheet = book.getSheetAt(0);
            int rows = sheet.getPhysicalNumberOfRows();
            for ( int i=1; i< rows; i++ ) {
                String zkZy = StringUtils.trim(ZzsgZyfbUtil.getValue(sheet.getRow(i).getCell(1)));
                parseZy(zkZy, zkMap);
                String bkZy = StringUtils.trim(ZzsgZyfbUtil.getValue(sheet.getRow(i).getCell(2)));
                parseZy(bkZy, bkMap);
            }
            System.out.println("专科：" + zkMap.size());
            System.out.println("本科：" + bkMap.size());
        } finally {
            try {
                book.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void parseZy(String zyName, Map<String, String> zyMap) {
        String[] zyNames = StringUtils.split(zyName, "、");
        if ( null != zyNames && zyNames.length > 0 ) {
            for ( String tmpName : zyNames ) {
                zyMap.put(tmpName, tmpName);
            }
        }
    }
}