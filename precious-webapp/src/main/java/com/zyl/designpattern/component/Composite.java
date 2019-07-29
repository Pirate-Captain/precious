/**
 * Created on 2016-4-26
 */
package com.zyl.designpattern.component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class Composite extends Component {
    private List<Component> componentList;

    @Override
    public void addChild(Component component) {
        if ( null == componentList ) {
            componentList = new ArrayList<Component>();
        }
        componentList.add(component);
    }

    @Override
    public Component getChild(int index) {
        if ( null != componentList ) {
            return componentList.get(index);
        }
        return null;
    }

    @Override
    public void removeChild(Component component) {
        if ( null != componentList ) {
            componentList.remove(component);
        }
    }

    @Override
    void printComponent() {
        System.out.println(this.getName());
        if ( null == componentList || componentList.isEmpty() ) {
            return;
        }
        for ( Component component : componentList ) {
            System.out.print("    ");
            component.printComponent();
        }
    }
}