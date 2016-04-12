/**
 * Created on 2016-4-12
 */
package com.zyl.designpattern.abstractfactory.normal;

import com.zyl.designpattern.abstractfactory.AmdCpu;
import com.zyl.designpattern.abstractfactory.Cpu;
import com.zyl.designpattern.abstractfactory.GigabyteMainBord;
import com.zyl.designpattern.abstractfactory.MainBord;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class ComputerSchema2 implements ComputerSchema {

    @Override
    public Cpu createCpu() {
        return new AmdCpu(11533);
    }

    @Override
    public MainBord createMainBord() {
        return new GigabyteMainBord();
    }
}