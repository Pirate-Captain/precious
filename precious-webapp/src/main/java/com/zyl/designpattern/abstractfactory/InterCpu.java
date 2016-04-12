/**
 * Created on 2016-4-12
 */
package com.zyl.designpattern.abstractfactory;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class InterCpu implements Cpu {
    private int pins = 0;
    
    public InterCpu(int pins) {
        this.pins = pins;
    }

    @Override
    public void calculate() {
        System.out.println("I am inter cpu , and my pins are " + pins);
    }
}