/*
 * chsi
 * Created on 2017-11-14
 */
package com.zyl.annotation;

/**
 * @author zhuyl <a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public interface MobilePhone {
    @MethodAnnotation(version = 11)
    int showVersion(int p1, @ParamterAnnotation(minor = 3) int type);
}