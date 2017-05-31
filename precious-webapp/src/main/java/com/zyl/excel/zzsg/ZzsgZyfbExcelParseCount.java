/**
 * Created on 2016-4-26
 */
package com.zyl.excel.zzsg;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class ZzsgZyfbExcelParseCount {
    private static Map<String, String> zkMap = new HashMap<String, String>();
    private static Map<String, String> bkMap = new HashMap<String, String>();


    public static void main(String[] args) {
        new ZzsgZyfbExcelParseCount().parse();
    }

    public void parse() {
        HSSFWorkbook book = null;
        try {
            book = new HSSFWorkbook(new FileInputStream("e:/zzsgzyfb.xls"));
            HSSFSheet sheet = book.getSheetAt(0);
            int rows = sheet.getPhysicalNumberOfRows();
            for ( int i=1; i< rows; i++ ) {
                String zkZy = StringUtils.trim(getValue(sheet.getRow(i).getCell(1)));
                parseZy(zkZy, zkMap);
                String bkZy = StringUtils.trim(getValue(sheet.getRow(i).getCell(2)));
                parseZy(bkZy, bkMap);
            }
            System.out.println("专科：" + zkMap.size());
            System.out.println("本科：" + bkMap.size());
        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
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

    private static String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            return String.valueOf(((Double)hssfCell.getNumericCellValue()).intValue());
        } else {
            // 返回字符串类型的值
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
}