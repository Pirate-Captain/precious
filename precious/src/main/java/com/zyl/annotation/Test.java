/*
 * chsi
 * Created on 2017-11-14
 */
package com.zyl.annotation;

import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author zhuyl <a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class Test {
    public static void main(String[] args) {
        Iphone iphone = new Iphone();
        printAnnotations(iphone.getClass());

        Class<?>[] interfaces = iphone.getClass().getInterfaces();
        for ( Class<?> type : interfaces ) {
            printAnnotations(type);
        }

        System.out.println("==========");
        printAnnotations(MobilePhone.class);
    }

    private static void printAnnotations(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        for ( Method method : methods ) {
            MethodAnnotation methodAnnotationA = AnnotationUtils.findAnnotation(method, MethodAnnotation.class);
            System.out.println(methodAnnotationA);
            Annotation[] methodAnnotations = clazz.getAnnotations();
            for ( Annotation methodAnnotation : methodAnnotations ) {
                System.out.println(methodAnnotation);
            }
            Annotation[][] paramterAnnotations = method.getParameterAnnotations();
            Class<?>[] paramters = method.getParameterTypes();
            for ( int x = 0; x < paramterAnnotations.length; x++ ) {
                Class<?> parameter = paramters[x];
                for ( Annotation annotation : paramterAnnotations[x] ) {
                    System.out.println(parameter.getName() + " " + annotation);
                }
            }
        }
    }
}