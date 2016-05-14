/**
 * 
 */
package com.ezijing.qa.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @author cuixiaohui
 *
 */
public class RedisClient {

	private Jedis jedis;								//非切片额客户端连接
	private JedisPool jedispool;						//非切片连接池
	private ShardedJedis shardedjedis;					//切片额客户端连接
	private ShardedJedisPool shardedjedispool;			//切片连接池
	
	public RedisClient(){
		
	}
	
	
	/*
	 * 初始化非切片池
	 * 
	 * */
	private void initialPool(String redishost,int port)
	{
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(5);
		config.setMaxWaitMillis(10001);
		config.setTestOnBorrow(false);
		
		jedispool = new JedisPool(config,redishost,port);
	}
	
	
	/*
	 * 初始化切片池
	 * 
	 * */
	private void initialShardedPool(String redishost,int port)
	{
		
		//池基本配置
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxIdle(5);
		config.setMaxWaitMillis(10001);
		config.setTestOnBorrow(false);
		
		//slave 链接
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add(new JedisShardInfo(redishost, port, "master"));
		
		//构造池
		shardedjedispool = new ShardedJedisPool(config, shards);
	}
	
	public void show() {     
        KeyOperate(); 
        StringOperate(); 
        ListOperate(); 
        SetOperate();
        SortedSetOperate();
        HashOperate(); 
        jedispool.returnResource(jedis);
        shardedjedispool.returnResource(shardedjedis);
    } 

      private void KeyOperate() {
         System.out.println("KeyOperate");
         System.out.println("系统中所有的键如下：");
         Set<String> keys = jedis.keys("*");
         Iterator<String> iter = keys.iterator();
         while(iter.hasNext())
         {
        	 String key = iter.next();
        	 System.out.println(key);
         }
      }

      private void StringOperate() {
    	  System.out.println("StringOperate");
      }

      private void ListOperate() {
    	  System.out.println("ListOperate");
      }

      private void SetOperate() {
    	  System.out.println("SetOperate");
      }

      private void SortedSetOperate() {
    	  System.out.println("SortedSetOperate");
      }
    
      private void HashOperate() {
    	  System.out.println("HashOperate");
      }
	

}
