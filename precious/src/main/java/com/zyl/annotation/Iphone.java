/*
 * chsi
 * Created on 2017-11-14
 */
package com.zyl.annotation;

/**
 * @author zhuyl <a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class Iphone implements MobilePhone {

    @Override
    public int showVersion(int p1, int type) {
        return 0;
    }
}