/**
 * Created on 2016-4-26
 */
package com.zyl.excel.zzsg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class ZzsgZyfbCheckExcelParse {
    
    public static void main(String[] args) {
        new ZzsgZyfbCheckExcelParse().parse();
    }
    
    public void parse() {
        HSSFWorkbook book = null;
        try {
            book = new HSSFWorkbook(new FileInputStream("e:/zzsgzyfb.xls"));
            HSSFSheet sheet = book.getSheetAt(0);
            int rows = sheet.getPhysicalNumberOfRows();
            Map<String, List<ZzsgZyCheckInfo>> checkMap = new HashMap<String, List<ZzsgZyCheckInfo>>();
            for ( int i=1; i< rows; i++ ) {
                String dlName = StringUtils.trim(getValue(sheet.getRow(i).getCell(0)));
                String zkZy = StringUtils.trim(getValue(sheet.getRow(i).getCell(1)));
                String bkZy = StringUtils.trim(getValue(sheet.getRow(i).getCell(2)));
                addToCheckMap(dlName, zkZy, bkZy, checkMap);
            }
            List<ZzsgZyCheckInfo> checkInfoList = new ArrayList<ZzsgZyCheckInfo>();
            for ( Entry<String, List<ZzsgZyCheckInfo>> entry : checkMap.entrySet() ) {
                if ( null != entry.getValue() && !entry.getValue().isEmpty() ) {
                    checkInfoList.addAll(entry.getValue());
                }
            }
            Collections.sort(checkInfoList, new Comparator<ZzsgZyCheckInfo>(){

                @Override
                public int compare(ZzsgZyCheckInfo o1, ZzsgZyCheckInfo o2) {
                    return o1.getZyName().compareTo(o2.getZyName());
                }
            });
            writeExcel(checkInfoList);
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
    
    public void writeExcel(List<ZzsgZyCheckInfo> cellInfoList) {
        if ( null == cellInfoList || cellInfoList.size() == 0 ) {
            return;
        }
        HSSFWorkbook book = null;
        book = new HSSFWorkbook();
        HSSFSheet sheet = book.createSheet();
        
        for ( int i = 0; i < cellInfoList.size(); i++ ) {
            ZzsgZyCheckInfo cellInfo = cellInfoList.get(i);
            HSSFRow row = sheet.createRow(i);
            
            HSSFCell dlNameCell = row.createCell(0);
            dlNameCell.setCellType(HSSFCell.CELL_TYPE_STRING);
            dlNameCell.setCellValue(cellInfo.getDlName());
            
            HSSFCell zyCodeCell = row.createCell(1);
            zyCodeCell.setCellType(HSSFCell.CELL_TYPE_STRING);
            zyCodeCell.setCellValue(cellInfo.getZyName());
            
            HSSFCell zyNameCell = row.createCell(2);
            zyNameCell.setCellType(HSSFCell.CELL_TYPE_STRING);
            zyNameCell.setCellValue(cellInfo.getCc());
            
            if ( null == cellInfo.getZyCodeList() || cellInfo.getZyCodeList().isEmpty() ) {
                CellStyle style = book.createCellStyle();
                style.setFillForegroundColor(IndexedColors.RED.getIndex());
                style.setFillPattern(CellStyle.SOLID_FOREGROUND);
                zyCodeCell.setCellStyle(style);
                continue;
            }
            
            int indexTmp = 2;
            for ( int j = 0; j < cellInfo.getZyCodeList().size(); j++ ) {
                String cellValue = cellInfo.getZyCodeList().get(j);
                HSSFCell cell = row.createCell(++indexTmp);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(cellValue);
            }
            for ( int f=0; f < cellInfo.getZyNameList().size(); f++ ) {
                String cellValue = cellInfo.getZyNameList().get(f);
                HSSFCell cell = row.createCell(++indexTmp);
                cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(cellValue);
            }
        }
        
        try {
            book.write(new FileOutputStream(new File("e:/zzsgzyfb-new.xls")));
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
    
    private void addToCheckMap(String dlName, String zkZy, String bkZy, Map<String, List<ZzsgZyCheckInfo>> checkMap) {
        parseZy(dlName, zkZy, true,checkMap);
        parseZy(dlName, bkZy, false,checkMap);
    }
    
    private void parseZy(String dlName, String zyInfo, boolean isZk, Map<String, List<ZzsgZyCheckInfo>> checkMap) {
        if ( StringUtils.isBlank(zyInfo) ) {
            return;
        }
        String cc = isZk ? "专科" : "本科";
        System.out.println("解析层次为：" + cc + " 的专业：" + zyInfo);
        String[] zkZyArr = StringUtils.split(zyInfo, "、");
        for ( String zkZyStr : zkZyArr ) {
            zkZyStr = StringUtils.trim(zkZyStr);
            List<ZzsgZyCheckInfo> list = checkMap.get(zkZyStr);
            if ( null == list ) {
                list = new ArrayList<ZzsgZyCheckInfo>();
                checkMap.put(zkZyStr, list);
            }
            ZzsgZyCheckInfo checkInfo = getByNameAndCc(zkZyStr, cc, list);
            if ( null != checkInfo ) {
                continue;
            }
            checkInfo = new ZzsgZyCheckInfo();
            checkInfo.setDlName(dlName);
            checkInfo.setZyName(zkZyStr);
            List<String> zyCodeList = getZyCodesByName(zkZyStr, isZk);
            checkInfo.setZyCodeList(zyCodeList);
            List<String> zyNameLikes = new ArrayList<String>();
            if ( null != zyCodeList && !zyCodeList.isEmpty() ) {
                for ( String zyCode : zyCodeList ) {
                    List<String> tmpList = this.getZyNamesByZyCode(zyCode, zkZyStr, isZk);
                    if ( null != tmpList && !tmpList.isEmpty() ) {
                        zyNameLikes.addAll(tmpList);
                    }
                }
            }
            checkInfo.setZyNameList(zyNameLikes);
            checkInfo.setCc(cc);
            list.add(checkInfo);
        }
    }
    
    private ZzsgZyCheckInfo getByNameAndCc(String name, String cc, List<ZzsgZyCheckInfo> checkInfoList) {
        if ( null == checkInfoList || checkInfoList.isEmpty() ) {
            return null;
        }
        for ( ZzsgZyCheckInfo checkInfo : checkInfoList ) {
            if ( StringUtils.equals(checkInfo.getZyName(), name) && StringUtils.equals(checkInfo.getCc(), cc) ) {
                return checkInfo;
            }
        }
        
        return null;
    }
    
    private Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@172.16.1.97:1521:ora9", "zb", "chsitest");
            return conn;
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
        return null;
    }
    
    private void closeAll(ResultSet rs, Statement ps, Connection conn) {
        if ( null != rs ) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        if ( null != ps ) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        if ( null != conn ) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    private List<String> getZyCodesByName(String zyName, boolean isZk) {
        Connection conn = getConnection();
        if ( null == conn ) {
            System.out.println("获取数据库连接失败！");
            return null;
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select distinct zydm from dic_zy d where zymc = ? and cc ='" + (isZk ? "专科" : "本科") + "'";
            ps = conn.prepareStatement(sql);
            ps.setString(1, zyName);
            rs = ps.executeQuery();
            List<String> zydmList = new ArrayList<String>();
            while( rs.next() ) {
                zydmList.add(rs.getString("zydm"));
            }
            
            return zydmList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, ps, conn);
        }
        
        return null;
    }
    
    private List<String> getZyNamesByZyCode(String zyCode, String zyName, boolean isZk) {
        Connection conn = getConnection();
        if ( null == conn ) {
            System.out.println("获取数据库连接失败！");
            return null;
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select distinct zymc from dic_zy d where zydm = ? and cc ='" + (isZk ? "专科" : "本科") + "'";
            ps = conn.prepareStatement(sql);
            ps.setString(1, zyCode);
            rs = ps.executeQuery();
            List<String> zydmList = new ArrayList<String>();
            while( rs.next() ) {
                String tmpZymc = rs.getString("zymc");
                if ( !StringUtils.equals(zyName, tmpZymc) ) {
                    zydmList.add(tmpZymc);
                }
            }
            
            return zydmList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll(rs, ps, conn);
        }
        
        return null;
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
    
    public class ZzsgZyCheckInfo {
        private String dlName;
        private String dlCode;
        private String zyName;
        private String cc;
        private List<String> zyCodeList;
        private List<String> zyNameList;

        public String getDlName() {
            return dlName;
        }

        public void setDlName(String dlName) {
            this.dlName = dlName;
        }

        public String getDlCode() {
            return dlCode;
        }

        public void setDlCode(String dlCode) {
            this.dlCode = dlCode;
        }

        public String getZyName() {
            return zyName;
        }

        public void setZyName(String zyName) {
            this.zyName = zyName;
        }

        public String getCc() {
            return cc;
        }

        public void setCc(String cc) {
            this.cc = cc;
        }

        public List<String> getZyCodeList() {
            return zyCodeList;
        }

        public void setZyCodeList(List<String> zyCodeList) {
            this.zyCodeList = zyCodeList;
        }

        public List<String> getZyNameList() {
            return zyNameList;
        }

        public void setZyNameList(List<String> zyNameList) {
            this.zyNameList = zyNameList;
        }
    }
}