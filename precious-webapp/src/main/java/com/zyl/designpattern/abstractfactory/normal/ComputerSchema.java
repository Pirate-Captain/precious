/**
 * Created on 2016-4-12
 */
package com.zyl.designpattern.abstractfactory.normal;

import com.zyl.designpattern.abstractfactory.Cpu;
import com.zyl.designpattern.abstractfactory.MainBord;

/**
 * 这种抽象工厂模式不够灵活，因为如果要添加新的产品，就需要修改所有抽象工厂的子类
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public interface ComputerSchema {
    Cpu createCpu();
    MainBord createMainBord();
}