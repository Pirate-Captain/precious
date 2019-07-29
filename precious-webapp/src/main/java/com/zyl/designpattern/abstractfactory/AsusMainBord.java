/**
 * Created on 2016-4-12
 */
package com.zyl.designpattern.abstractfactory;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class AsusMainBord implements MainBord {

    @Override
    public void install() {
        System.out.println("I am Asus mainbord！");
    }
}