/**
 * Created on 2016-5-4
 */
package com.zyl.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;
import org.junit.Test;

/**
 * @author iaskbear <a href="mailto:iaskbear@gmail.com">iaskbear</a>
 * @version $Id$
 */
public class ZookeeperBaseApi {
    public static void main(String[] args) throws IOException {
        new ZookeeperBaseApi().testT();
    }
    
    @Test
    public void testT() throws IOException {
        ZooKeeper zk = new ZooKeeper("192.168.40.131:2181", 10000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println(event.getType());
            }
        });
        
        try {
            String createParentDirResult = zk.create("/zk_learn", "Parent directory".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println(createParentDirResult);
            String createResult = zk.create("/zk_learn/first", "Hello world".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.out.println(createResult);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                zk.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}