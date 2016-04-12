/**
 * Created on 2016-4-12
 */
package com.zyl.designpattern.abstractfactory.normal;

import com.zyl.designpattern.abstractfactory.AsusMainBord;
import com.zyl.designpattern.abstractfactory.Cpu;
import com.zyl.designpattern.abstractfactory.InterCpu;
import com.zyl.designpattern.abstractfactory.MainBord;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class ComputerSchema1 implements ComputerSchema {

    @Override
    public Cpu createCpu() {
        return new InterCpu(11511);
    }

    @Override
    public MainBord createMainBord() {
        return new AsusMainBord();
    }

}
