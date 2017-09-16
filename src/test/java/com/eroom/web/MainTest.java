package com.eroom.web;


import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tendy on 2017/8/3.
 */
public class MainTest {

    private static JedisCluster jedisCluster=null;
    private static Set<HostAndPort> hostAndPorts=null;

    public static  Set<HostAndPort> getHostAndPort(String hostAndPort){
        Set<HostAndPort> hap = new HashSet<HostAndPort>();
        String[] hosts = hostAndPort.split(",");
        String[] hs = null;
        for(String host:hosts){
            hs=host.split(":");
            hap.add(new HostAndPort(hs[0], Integer.parseInt(hs[1])));
        }
        return hap;
    }

    public static JedisCluster getJedisCluster(){
        GenericObjectPoolConfig gopc = new GenericObjectPoolConfig();
        gopc.setMaxTotal(50);
        gopc.setMaxIdle(20);
        gopc.setMaxWaitMillis(10000);
        gopc.setTestOnBorrow(true);
        gopc.setTestOnReturn(true);
        //Idle时进行连接扫描
        gopc.setTestWhileIdle(true);
        //表示idle object evitor两次扫描之间要sleep的毫秒数
        gopc.setTimeBetweenEvictionRunsMillis(30000);
        //表示idle object evitor每次扫描的最多的对象数
        gopc.setNumTestsPerEvictionRun(10);
        //表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
        gopc.setMinEvictableIdleTimeMillis(60000);
        StringBuilder str = new StringBuilder();
        str.append("47.93.217.79:6661");
        str.append(",47.93.217.79:6662");
        str.append(",47.93.217.79:6663");
        str.append(",47.93.217.79:6664");
        str.append(",47.93.217.79:6665");
        str.append(",47.93.217.79:6666");
        hostAndPorts = getHostAndPort(str.toString());
        jedisCluster = new JedisCluster(hostAndPorts, 2000, 2000, 3, "he258987", gopc);
        return jedisCluster;
    }

    public static void main(String[] arg){
        jedisCluster = getJedisCluster();
        jedisCluster.set("duan", "100");
        String kString=jedisCluster.get("duan");
        System.out.println(kString);
        jedisCluster.set("duan", "1200");
        kString=jedisCluster.get("duan");
        System.out.println(kString);
        jedisCluster.set("duan", "1030");
        kString=jedisCluster.get("duan");
        System.out.println(kString);
        jedisCluster.set("duan", "1400");
        kString=jedisCluster.get("duan");
        System.out.println(kString);
        jedisCluster.set("duan", "1006");
        jedisCluster.del("duan");
        kString=jedisCluster.get("duan");
        System.out.println(kString);
    }
}


