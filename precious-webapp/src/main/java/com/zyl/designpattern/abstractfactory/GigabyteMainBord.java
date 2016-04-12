/**
 * Created on 2016-4-12
 */
package com.zyl.designpattern.abstractfactory;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class GigabyteMainBord implements MainBord {

    @Override
    public void install() {
        System.out.println("I am gigabyte mainbord!");
    }
}