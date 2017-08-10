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
        gopc.setMaxTotal(32);
        gopc.setMaxIdle(4);
        gopc.setMaxWaitMillis(6000);
        StringBuilder str = new StringBuilder();
        str.append("47.93.217.79:6179");
        str.append(",47.93.217.79:6180");
        str.append(",47.93.217.79:6181");
        str.append(",47.93.217.79:6182");
        str.append(",47.93.217.79:6183");
        str.append(",47.93.217.79:6184");
        hostAndPorts = getHostAndPort(str.toString());
        jedisCluster = new JedisCluster(hostAndPorts, 2000, 2000, 3, "", gopc);
        return jedisCluster;
    }

    public static void main(String[] arg){
        jedisCluster = getJedisCluster();
        System.out.println(jedisCluster.get("name"));
    }
}
