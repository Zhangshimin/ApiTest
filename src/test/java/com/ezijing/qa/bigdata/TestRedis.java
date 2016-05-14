/**
 * 
 */
package com.ezijing.qa.bigdata;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ezijing.qa.util.RedisClient;

/**
 * @author cuixiaohui
 *
 */
public class TestRedis {

	@Test
	public void test() {
		new RedisClient().show();
	}

}