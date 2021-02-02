package com.sunyue.test.redis;

import com.sunyue.test.utils.ApplicationContextUtils;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.DigestUtils;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * 本类由mybatis初始化 启动时并无法由spring容器管理（盲猜参数无法传递id），故无法注入拿到RedisTemplate
 * 要拿到RedisTemplate 需要动下工厂 在工厂启动时拿到RedisTemplate
 *
 * 实现细节:
 * 多表关联查询下的内容保持最新 - 由于DB增删改只会使Redis清空对应key的缓存 所以目的就是让有关联的缓存数据同时进同时出
 *
 * 实现完毕之后的优化问题:
 * 1.key过长会影响redis性能，采用MD5算法对key进行处理，这里利用的是MD5加密算法的唯一性
 * */
public class RedisCache implements Cache {

    //自定义Cache必要条件
    private final String id;

    //根据断点 : id:mapper的namespace
    public RedisCache(String id) {
        this.id = id;
    }

    //返回cache的唯一标识
    @Override
    public String getId() {
        return this.id;
    }

    //重点实现 加入缓存
    @Override
    public void putObject(Object key, Object value) {
        System.out.println("加入缓存：key================》" + key + "值:value ===================》" + value);
        RedisTemplate redisTemplate = getRedisTemplate();
        redisTemplate.opsForHash().put(id.toString(),keyToMd5(key.toString()),value);
    }

    //重点实现 查询缓存
    @Override
    public Object getObject(Object key) {
        System.out.println("查询缓存：key================》" + key );
        RedisTemplate redisTemplate = getRedisTemplate();
        return redisTemplate.opsForHash().get(id.toString(),keyToMd5(key.toString()));
    }

    //根据指定的key删除缓存 目前没有实现
    @Override
    public Object removeObject(Object o) {
        return null;
    }

    //清空缓存 执行增删改的时候都会进行clear
    @Override
    public void clear() {
        System.out.println("清空缓存");
        RedisTemplate redisTemplate = getRedisTemplate();
        //清空缓存
        redisTemplate.delete(id.toString());
    }

    //计算缓存数量
    @Override
    public int getSize() {

        RedisTemplate redisTemplate = getRedisTemplate();
        //获取哈希中的键值对数量
        return redisTemplate.opsForHash().size(id.toString()).intValue();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }

    //统一加工redisTemplate 并返回
    private RedisTemplate getRedisTemplate(){
        //获取redisTemplate
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        //设置序列化策略,因为设置了RedisConfig实例
/*        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());*/
        return redisTemplate;
    }


    //优化: 对key做MD5处理
    private String keyToMd5(String key){
        return  DigestUtils.md5DigestAsHex(key.getBytes());
    }

}


