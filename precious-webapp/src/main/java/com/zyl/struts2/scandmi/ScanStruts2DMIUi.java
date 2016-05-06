package com.zyl.struts2.scandmi;
/**
 * Created on 2016-5-5
 */


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class ScanStruts2DMIUi {
    private JTextField inputField;
    private JCheckBox checkBox;
    private JButton scanButton;
    private JLabel tipsLable;
    private JTextArea jTextArea;
    private JFrame mainFrame;
    private ScanStruts2DMI scanStruts2DMI;
    private int width = 720;
    private int height = 800;
    
    public static void main(String[] args) {
        new ScanStruts2DMIUi().init();
    }
    private void init() {
        mainFrame = new JFrame("扫描Struts2动态方法调用");
        mainFrame.setSize(width, height);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        Container contentPanle = mainFrame.getContentPane();
        contentPanle.setLayout(new FlowLayout());
        contentPanle.add(new JLabel("请输入路径："));
        
        inputField = new JTextField(30);
        inputField.addMouseListener(new MouseListener() {
            
            @Override
            public void mouseReleased(MouseEvent e) {
                
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser inputFile = new JFileChooser();
                inputFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                inputFile.showDialog(new JLabel(), "选择");
                if ( null != inputFile.getSelectedFile() ) {
                    inputField.setText(inputFile.getSelectedFile().getAbsolutePath());
                }
            }
        });
        contentPanle.add(inputField);
        
        checkBox = new JCheckBox("是否扫描子目录");
        contentPanle.add(checkBox);
        
        scanButton = new JButton("开始扫描");
        contentPanle.add(scanButton);
        
        tipsLable = new JLabel("请选择您要扫描的路径！");
        tipsLable.setSize(width-20, tipsLable.getHeight());
        contentPanle.add(tipsLable);
        
        jTextArea = new JTextArea(38,60);
        JScrollPane scrollPane = new JScrollPane(jTextArea);
        scrollPane.setAutoscrolls(true);
        contentPanle.add(scrollPane, BorderLayout.CENTER);
        
        scanStruts2DMI = new ScanStruts2DMI(jTextArea, tipsLable);
        scanButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    
                    @Override
                    public void run() {
                        String inputStr = inputField.getText();
                        if ( null == inputStr || inputStr.trim().equals("") ) {
                            JOptionPane.showConfirmDialog(mainFrame, "请录入扫描路径！","提示",JOptionPane.CLOSED_OPTION);
                            return;
                        }
                        boolean selected = checkBox.isSelected();
                        scanButton.setEnabled(false);
                        tipsLable.setText("开始扫描：" + inputStr);
                        jTextArea.setText("开始扫描：" + inputStr);
                        scanStruts2DMI.scanDmi(selected, inputStr);
                        jTextArea.append("\n扫描结束！");
                        tipsLable.setText("已扫描结束，扫描结果如下：");
                        scanButton.setEnabled(true);
                    }
                }).start();
            }
        });
        
        
        int w = (Toolkit.getDefaultToolkit().getScreenSize().width - width) / 2;
        int h = (Toolkit.getDefaultToolkit().getScreenSize().height - height) / 2;
        mainFrame.setLocation(w, h);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
    }
}