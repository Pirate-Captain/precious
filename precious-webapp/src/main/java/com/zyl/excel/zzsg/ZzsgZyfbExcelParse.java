/**
 * Created on 2016-4-26
 */
package com.zyl.excel.zzsg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class ZzsgZyfbExcelParse {
    private static final String PRE_ZY_CODE = "ZYDM-";
    private static int currentCode = 0;
    private static Map<String, String> provMap = new HashMap<String, String>();
    private static Map<String, List<String>> replaceOldZyMap = new HashMap<String, List<String>>();
    private static Map<String, List<String>> addNewZyMap = new HashMap<String, List<String>>();
    
    public static void main(String[] args) {
        new ZzsgZyfbExcelParse().parse();
    }

    public void parse() {
        HSSFWorkbook book = null;
        try {
            book = new HSSFWorkbook(new FileInputStream("e:/zzsgzyfb.xls"));
            HSSFSheet sheet = book.getSheetAt(0);
            int rows = sheet.getPhysicalNumberOfRows();
            int colNumbers = sheet.getRow(0).getPhysicalNumberOfCells();
            Map<String, ZyInfo> zyInfoMap = new HashMap<String, ZyInfo>();
            ZyInfo zyInfo = new ZyInfo();
            zyInfo.setZyCode("zydm");
            zyInfo.setZyName("zymc");
            
            List<String> ssList = new ArrayList<String>();
            Map<String, String> ssBkMap = new HashMap<String, String>();
            ssBkMap.putAll(provMap);
            for ( int index = 3; index < colNumbers; index++ ) {
                String ssDm = StringUtils.trim(getValue(sheet.getRow(0).getCell(index)));
                ssList.add(provMap.get(ssDm));
                ssBkMap.remove(ssDm);
                zyInfo.setZyfbList(ssList);
            }
            
            for ( Entry<String, String> entry : ssBkMap.entrySet() ) {
                System.out.println("没有的：" + entry.getKey());
            }
            
            for ( int i = 1; i < rows; i++ ) {
                String zkZy = StringUtils.trim(getValue(sheet.getRow(i).getCell(1)));
                String bkZy = StringUtils.trim(getValue(sheet.getRow(i).getCell(2)));
                List<String> zyNameList = getZyNameList(zkZy, bkZy);
                
                List<String> ssfbList = new ArrayList<String>();
                for ( int j = 3; j < colNumbers; j++ ) {
                    ssfbList.add(StringUtils.trim(getValue(sheet.getRow(i).getCell(j))));
                }
                
                addToMap(zyNameList, ssfbList, zyInfoMap);
            }
            List<ZyInfo> zyfbList = new ArrayList<ZyInfo>();
            zyfbList.add(zyInfo);
            for ( Entry<String, ZyInfo> entry : zyInfoMap.entrySet() ) {
                zyfbList.add(entry.getValue());
            }
            writeExcel(zyfbList);
        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            try {
                book.close();
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        }
    }
    
    public void writeExcel(List<ZyInfo> zyInfoList) {
        if ( null == zyInfoList || zyInfoList.size() == 0 ) {
            return;
        }
        HSSFWorkbook book = null;
        book = new HSSFWorkbook();
        HSSFSheet sheet = book.createSheet();
        
        for ( int i = 0; i < zyInfoList.size(); i++ ) {
            ZyInfo cellInfo = zyInfoList.get(i);
            HSSFRow row = sheet.createRow(i);
            
            HSSFCell dlNameCell = row.createCell(0);
            dlNameCell.setCellType(HSSFCell.CELL_TYPE_STRING);
            dlNameCell.setCellValue(cellInfo.getZyCode());
            
            HSSFCell zyCodeCell = row.createCell(1);
            zyCodeCell.setCellType(HSSFCell.CELL_TYPE_STRING);
            zyCodeCell.setCellValue(cellInfo.getZyName());
            
            int indexTmp = 1;
            for ( int f=0; f < cellInfo.getZyfbList().size(); f++ ) {
                String cellValue = cellInfo.getZyfbList().get(f);
                HSSFCell cell = row.createCell(++indexTmp);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(cellValue);
            }
        }
        
        try {
            book.write(new FileOutputStream(new File("e:/zzsgzyfb-fb.xls")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                book.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void addToMap(List<String> zyNameList, List<String> ssfbList, Map<String, ZyInfo> zyInfoMap) {
        if ( null == zyNameList || zyNameList.isEmpty() ) {
            return;
        }
        for ( String zyName : zyNameList ) {
            ZyInfo zyInfo = zyInfoMap.get(zyName);
            if ( null == zyInfo ) {
                zyInfo = new ZyInfo();
                zyInfo.setZyCode(getZyCode());
                zyInfo.setZyName(zyName);
                List<String> ssfbTmpList = new ArrayList<String>();
                for ( int i=0; i<ssfbList.size(); i++ ) {
                    ssfbTmpList.add("");
                }
                zyInfo.setZyfbList(ssfbTmpList);
                zyInfoMap.put(zyName, zyInfo);
            }
            
            for ( int m=0; m<ssfbList.size(); m++ ) {
                String fbInfo = ssfbList.get(m);
                if ( StringUtils.isNotBlank(fbInfo) ) {
                    String zyInfoFb = zyInfo.getZyfbList().get(m);
                    int zyInfoFbInt = StringUtils.isBlank(zyInfoFb) ? 0 : Integer.parseInt(zyInfoFb);
                    
                    zyInfo.getZyfbList().set(m, String.valueOf(zyInfoFbInt + Integer.parseInt(fbInfo)));
                }
            }
        }
    }
    
    private String getZyCode() {
        String currentCodeStr = String.valueOf(currentCode);
        int zeroLength = 5-currentCodeStr.length();
        for ( int i=0; i<zeroLength; i++ ) {
            currentCodeStr = "0" + currentCodeStr;
        }
        currentCode++;
        return PRE_ZY_CODE+currentCodeStr;
    }
    
    private List<String> getZyNameList(String zkZy, String bkZy) {
        Vector<String> vector = new Vector<String>();
        if ( StringUtils.isNotBlank(zkZy) ) {
            zkZy = StringUtils.trim(StringUtils.replace(zkZy, "；", "、"));
            String[] zkZyArr = StringUtils.split(zkZy, "、");
            vector.addAll(Arrays.asList(zkZyArr));
        }
        if ( StringUtils.isNotBlank(bkZy) ) {
            bkZy = StringUtils.trim(StringUtils.replace(bkZy, "；", "、"));
            String[] bkZyArr = StringUtils.split(bkZy, "、");
            vector.addAll(Arrays.asList(bkZyArr));
        }
        
        Vector<String> zyNameVector = new Vector<String>();
        zyNameVector.addAll(vector);
        Iterator<String> iterator = vector.iterator();
        while ( iterator.hasNext() ) {
            String zyName = iterator.next();
            List<String> replaceOldZyList = replaceOldZyMap.get(zyName);
            if ( null != replaceOldZyList && !replaceOldZyList.isEmpty() ) {
                zyNameVector.remove(zyName);
                zyNameVector.addAll(replaceOldZyMap.get(zyName));
            }
            
            List<String> addNewZyList = addNewZyMap.get(zyName);
            if ( null != addNewZyList && !addNewZyList.isEmpty() ) {
                zyNameVector.addAll(addNewZyMap.get(zyName));
            }
        }
        return zyNameVector.subList(0, vector.size());
    }

    private static String getValue(HSSFCell hssfCell) {
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

    public class ZyInfo {
        private String zyName;
        private String zyCode;
        private List<String> zyfbList;

        public String getZyName() {
            return zyName;
        }

        public void setZyName(String zyName) {
            this.zyName = zyName;
        }

        public String getZyCode() {
            return zyCode;
        }

        public void setZyCode(String zyCode) {
            this.zyCode = zyCode;
        }

        public List<String> getZyfbList() {
            return zyfbList;
        }

        public void setZyfbList(List<String> zyfbList) {
            this.zyfbList = zyfbList;
        }
    }

    static {
        provMap.put("山西", "ss14");
        provMap.put("北京", "ss11");
        provMap.put("天津", "ss12");
        provMap.put("河北", "ss13");
        provMap.put("内蒙古", "ss15");
        provMap.put("辽宁", "ss21");
        provMap.put("上海", "ss31");
        provMap.put("江苏", "ss32");
        provMap.put("吉林", "ss22");
        provMap.put("黑龙江", "ss23");
        provMap.put("浙江", "ss33");
        provMap.put("安徽", "ss34");
        provMap.put("福建", "ss35");
        provMap.put("江西", "ss36");
        provMap.put("山东", "ss37");
        provMap.put("湖北", "ss42");
        provMap.put("河南", "ss41");
        provMap.put("湖南", "ss43");
        provMap.put("广东", "ss44");
        provMap.put("四川", "ss51");
        provMap.put("广西", "ss45");
        provMap.put("海南", "ss46");
        provMap.put("重庆", "ss50");
        provMap.put("云南", "ss53");
        provMap.put("贵州", "ss52");
        provMap.put("西藏", "ss54");
        provMap.put("陕西", "ss61");
        provMap.put("甘肃", "ss62");
        provMap.put("青海", "ss63");
        provMap.put("宁夏", "ss64");
        provMap.put("新疆", "ss65");
    }
}