/**
 * Created on 2016-4-12
 */
package com.zyl.designpattern.abstractfactory.normal;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class ComputerEnginee {
    
    public void installComputer(ComputerSchema schema) {
        schema.createCpu().calculate();
        schema.createMainBord().install();
    }
}
