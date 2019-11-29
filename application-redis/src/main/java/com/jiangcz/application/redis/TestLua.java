package com.jiangcz.application.redis;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;

/**
 * 在java中使用lua脚本
 */
public class TestLua {
    @SuppressWarnings({ "resource", "rawtypes" })

    public void testLua() {
        // 如果是简单的对象，使用原来的封装会容易一些
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        //RedisTemplate rt = applicationContext.getBean(RedisTemplate.class);
        // 如果是简单的操作，使用原来的Jedis会简单些
        //Jedis jedis = (Jedis)rt.getConnectionFactory().getConnection().getNativeConnection();
        Jedis jedis = new Jedis("49.234.227.194", 6379);//直接连接可能不成功 因为redis 可能加校验了
        jedis.auth("123456");
        // 执行简单的脚本
        String helloLua = (String)jedis.eval("return 'Hello Lua'");
        System.out.println(helloLua);
        // 执行带参数的脚本
        jedis.eval("redis.call('set',KEYS[1],ARGV[1])", 1, "lua-key","lua-value");
        String luaKey = jedis.get("lua-key");
        System.out.println(luaKey);
        // 缓存脚本，返回sha1签名标识
        String sha1 = (String)jedis.scriptLoad("redis.call('set',KEYS[1],ARGV[1])");
        // 通过标识执行脚本
        jedis.evalsha(sha1, 1, new String[] {"sha-key","sha-val1"});
        // 获取执行脚本后的数据
        String value = jedis.get("sha-key");
        System.out.println(value);
        jedis.close();
    }
}
