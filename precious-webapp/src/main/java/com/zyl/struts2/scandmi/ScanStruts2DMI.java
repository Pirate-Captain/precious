/**
 * Created on 2016-5-5
 */
package com.zyl.struts2.scandmi;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class ScanStruts2DMI {
    private JTextArea jTextArea;
    private JLabel tipsLable;
    
    public ScanStruts2DMI(JTextArea jTextArea, JLabel tipsLable) {
        this.jTextArea = jTextArea;
        this.tipsLable = tipsLable;
    }
    
    public void scanDmi(boolean needDeepFind, String path) {
        File file = new File(path);
        scanEveryFile(needDeepFind, file);
    }
    
    private void scanEveryFile(boolean needDeepFind, File file) {
        printLableTips("正在扫描：" + file.getAbsolutePath());
        if ( file.isDirectory() ) {
            File[] files = file.listFiles();
            if ( null == files || files.length == 0 ) {
                return;
            }
            for ( File loopFile : files ) {
                if ( loopFile.getName().contains("target") ) {
                    continue;
                }
                if ( loopFile.isDirectory() && !needDeepFind ) {
                    continue;
                }
                scanEveryFile(needDeepFind, loopFile);
            }
            return;
        }
        
        if ( !file.getName().endsWith(".xml") ) {
            return;
        }
        
        try {
            scanFile(file);
        } catch ( Exception e) {
            printTips("解析：" + file.getAbsolutePath() + " 异常，" + e);
        }
    }
    
    @SuppressWarnings({ "rawtypes" })
    private void scanFile(File file) {
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(file);
            Element root = document.getRootElement();
            if ( !root.getName().equals("struts") ) {
                return;
            }
            List packageList = root.elements("package");
            if ( null == packageList || packageList.isEmpty() ) {
                return;
            }
            Iterator packageIterator = packageList.iterator();
            while ( packageIterator.hasNext() ) {
                Element packageElement = (Element)packageIterator.next();
                scanEveryPackage(file, packageElement);
            }
        } catch ( DocumentException e ) {
            printTips("解析：" + file.getAbsolutePath() + " 异常，" + e);
        }
    }
    
    @SuppressWarnings("rawtypes")
    private void scanEveryPackage(File file, Element packageElement) {
        List actionList = packageElement.elements("action");
        if ( null == actionList || actionList.isEmpty() ) {
            return;
        }
        Iterator actionIterator = actionList.iterator();
        while ( actionIterator.hasNext() ) {
            Element actionElement = (Element)actionIterator.next();
            Attribute methodAttribute = getGivenAttribute(actionElement, "method");
            if ( null == methodAttribute ) {
                Attribute actionNameAttribute = getGivenAttribute(actionElement, "name");
                printTips(file.getName() + "中，actionName为：" + actionNameAttribute.getValue() + " 有动态方法调用");
            }
        }
    }
    
    private Attribute getGivenAttribute(Element element, String attributeName) {
        if ( null == element ) {
            return null;
        }
        return element.attribute(attributeName);
    }
    
    private void printTips(String tipsInfo) {
        jTextArea.append("\n" + tipsInfo);
    }
    
    private void printLableTips(String tipsInfo) {
        int length = tipsInfo.length();
        if ( length > 100 ) {
            tipsInfo = tipsInfo.substring(0, 50) + "..." + tipsInfo.subSequence(length - 50, length);
        }
        tipsLable.setText(tipsInfo);
    }
}