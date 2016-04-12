/**
 * Created on 2016-4-12
 */
package com.zyl.designpattern.abstractfactory.normal;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class TestInstallComputer {
    public static void main(String[] args) {
        ComputerEnginee enginess = new ComputerEnginee();
        ComputerSchema schema1 = new ComputerSchema1();
        enginess.installComputer(schema1);
        
        ComputerSchema schema2 = new ComputerSchema2();
        enginess.installComputer(schema2);
    }
}
