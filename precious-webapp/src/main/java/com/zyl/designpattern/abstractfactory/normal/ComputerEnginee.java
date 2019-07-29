/**
 * Created on 2016-4-12
 */
package com.zyl.designpattern.abstractfactory.normal;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class ComputerEnginee {
    
    public void installComputer(ComputerSchema schema) {
        schema.createCpu().calculate();
        schema.createMainBord().install();
    }
}
