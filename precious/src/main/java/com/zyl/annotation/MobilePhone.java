/*
 * chsi
 * Created on 2017-11-14
 */
package com.zyl.annotation;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public interface MobilePhone {
    @MethodAnnotation(version = 11)
    int showVersion(int p1, @ParamterAnnotation(minor = 3) int type);
}