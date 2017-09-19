package com.eroom.web.dao.redis;
//package com.minijy.trade.dao.redis;
//
//import java.util.Map;
//import java.util.Set;
//import java.util.TreeSet;
//
//import javax.annotation.Resource;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.stereotype.Repository;
//
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisCluster;
//import redis.clients.jedis.JedisPool;
//
//@Repository
//public class RedisDao {
//
//    private Log log = LogFactory.getLog(getClass());
//
//    @Resource
//    protected JedisCluster jedisCluster;
//
//    // 添加key，value
//    public void set(String key, String value) {
//        // 新增数据
//        jedisCluster.set(key, value);
//    }
//
//    // 获取数据
//    public String get(String key) {
//        String value = jedisCluster.get(key);
//        return value;
//    }
//
//    public void expire(String key, int second) {
//        jedisCluster.expire(key, second);
//    }
//
//    public void setex(String key, int seconds, String value) {
//        jedisCluster.setex(key, seconds, value);
//    }
//
//    /**
//     * 将键值加上定值
//     * 
//     * @param key
//     * @param decrby
//     * @return
//     * @author tendy
//     */
//    public void incrBy(String key, long integer) {
//        jedisCluster.incrBy(key, integer);
//    }
//
//    /**
//     * 将键值加上1
//     * 
//     * @param key
//     * @param decrby
//     * @return
//     * @author tendy
//     */
//    public Long incr(String key) {
//        Long res = jedisCluster.incr(key);
//        return res;
//    }
//
//    /**
//     * 返回并弹出指定Key关联的链表中的第一个元素，即头部元素。如果该Key不存，返回null
//     * 
//     * @param key
//     * @return
//     * @author tendy
//     */
//    public String lpop(final String key) {
//        String val = jedisCluster.lpop(key);
//        return val;
//    }
//
//    /**
//     * 将键值减去定值
//     * 
//     * @param key
//     * @param decrby
//     * @return
//     * @author tendy
//     */
//    public Long decrby(String key, Long decrby) {
//        Long res = jedisCluster.decrBy(key, decrby);
//        return res;
//    }
//
//    /**
//     * 原子性的设置该Key为指定的Value，同时返回该Key的原有值
//     * 
//     * @param keys
//     * @author tendy
//     */
//    public String getset(final String key, final String value) {
//        String valueOrigin = jedisCluster.getSet(key, value);
//        return valueOrigin;
//    }
//
//    /**
//     * 删除key
//     * 
//     * @param keys
//     * @author tendy
//     */
//    public void del(final String... keys) {
//        jedisCluster.del(keys);
//    }
//
//    /**
//     * 链表中元素的数量
//     * 
//     * @param key
//     * @return
//     * @author tendy
//     */
//    public long llen(final String key) {
//        long llen = jedisCluster.llen(key);
//        return llen;
//    }
//
//    /**
//     * 向list中存入数据
//     * 
//     * @param key
//     * @param string
//     * @author tendy
//     */
//    public void lpush(final String key, final String... string) {
//        for (String str : string) {
//            jedisCluster.lpush(key, str);
//        }
//    }
//
//    /**
//     * 删除key
//     * 
//     * @param keys
//     * @author tendy
//     */
//    public void rem(final String... keys) {
//        jedisCluster.del(keys);
//    }
//
//    public void hset(String key, String field, String value) {
//        jedisCluster.hset(key, field, value);
//    }
//
//    public Set<String> keys(String pattern) {
//        Set<String> keys = new TreeSet<>();
//        Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
//        for (String k : clusterNodes.keySet()) {
//            JedisPool jp = clusterNodes.get(k);
//            Jedis connection = jp.getResource();
//            try {
//                keys.addAll(connection.keys(pattern));
//            } catch (Exception e) {
//                log.error("Getting keys from " + k + " error: {}", e);
//            } finally {
//                connection.close();// 用完一定要close这个链接！！！
//            }
//        }
//        return keys;
//    }
//
//    public Boolean exists(String key) {
//        return jedisCluster.exists(key);
//    }
//
//    public void hmset(String key, Map<String, String> hash) {
//        jedisCluster.hmset(key, hash);
//    }
//
//    public String hget(String key, String field) {
//        String value = jedisCluster.hget(key, field);
//        return value;
//    }
//
//    public Map<String, String> hgetAll(String key) {
//        Map<String, String> value = jedisCluster.hgetAll(key);
//        return value;
//    }
//}