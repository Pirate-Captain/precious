/*
 * chsi
 * Created on 2019-07-29
 */
package com.zyl.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 自定义类加载器
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class MyClassLoader extends ClassLoader {
    private String root;

    public void setRoot(String root) {
        this.root = root;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
            byte[] classData = loadClassData(name);
            if ( null == classData ) {
                throw new ClassNotFoundException();
            } else {
                return defineClass(name, classData,0, classData.length );
            }
    }

    private byte[] loadClassData(String name) {
        FileInputStream fis = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.out.println(File.separator);
        String absolutelyPath = root + File.separatorChar + name.replace(".", File.separator) + ".class";
        try {
            fis = new FileInputStream(absolutelyPath);
            byte[] buffer = new byte[1024];
            int length;
            while ( (length = fis.read(buffer)) != -1 ) {
                bos.write(buffer, 0, length);
            }
            return bos.toByteArray();
        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if ( null != fis ) {
                try {
                    fis.close();
                } catch ( IOException e ) {
                    e.printStackTrace();
                }
            }
            try {
                bos.close();
            } catch ( IOException e ) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader loader = new MyClassLoader();
        loader.setRoot("d:/logs/classloader");
        Class clazz = loader.loadClass("com.zyl.classloader.MyDefindClass");
        Object obj = clazz.newInstance();
        System.out.println(obj.getClass().getClassLoader());
    }
}