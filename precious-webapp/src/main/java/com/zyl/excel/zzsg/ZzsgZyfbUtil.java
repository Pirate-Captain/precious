/*
 * chsi
 * Created on 2018-05-22
 */
package com.zyl.excel.zzsg;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author zhuyl <a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class ZzsgZyfbUtil {
    public static Workbook getWorkbook(String filePath) {
        String dzdFileNameTmp = StringUtils.lowerCase(filePath);
        if ( StringUtils.endsWith(dzdFileNameTmp, "xls") ) {
            try {
                return new HSSFWorkbook(new FileInputStream(filePath));
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        } else if ( StringUtils.endsWith(dzdFileNameTmp, "xlsx") ) {
            try {
                return new XSSFWorkbook(new FileInputStream(filePath));
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String getValue(Cell hssfCell) {
        if ( hssfCell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN ) {
            // 返回布尔类型的值
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if ( hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC ) {
            // 返回数值类型的值
            return String.valueOf(((Double) hssfCell.getNumericCellValue()).intValue());
        } else {
            // 返回字符串类型的值
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
}