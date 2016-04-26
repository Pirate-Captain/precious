/**
 * Created on 2016-4-26
 */
package com.zyl.designpattern.component;

/**
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class Leaf extends Component {

    @Override
    void printComponent() {
        System.out.println("    "+this.getName());
    }
}
