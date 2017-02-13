package com.zyl.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Redis工具类,用于获取RedisPool. 
 * 参考官网说明如下： 
 * You shouldn't use the same instance from different threads because you'll have strange errors. 
 * And sometimes creating lots of Jedis instances is not good enough because it means lots of sockets and connections, 
 * which leads to strange errors as well. A single Jedis instance is not threadsafe! 
 * To avoid these problems, you should use JedisPool, which is a threadsafe pool of network connections. 
 * This way you can overcome those strange errors and achieve great performance. 
 * To use it, init a pool: 
 *  JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost"); 
 *  You can store the pool somewhere statically, it is thread-safe. 
 *  JedisPoolConfig includes a number of helpful Redis-specific connection pooling defaults. 
 *  For example, Jedis with JedisPoolConfig will close a connection after 300 seconds if it has not been returned.
 * @author zhuyl<a href="mailto:zhuyl@chsi.com.cn">zhu Youliang</a>
 * @version $Id$
 */
public class JedisUtil {
    private static Logger log = LoggerFactory.getLogger(JedisUtil.class);
}