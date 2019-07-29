/**
 * Created on 2016-4-26
 */
package com.zyl.designpattern.component;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class Leaf extends Component {

    @Override
    void printComponent() {
        System.out.println("    "+this.getName());
    }
}
