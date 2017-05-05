package com.ssm.cache;

import com.ssm.base.NGTest;
import com.ssm.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author reed.mi
 * @date 2017/4/5.
 */
public class RedisNGTest extends NGTest {

	@Autowired
	RedisUtils redisUtils;

	@Test
	public void testSave() {

		String key = "ssm-key";
		String value = "ssm-value";

		boolean exist = redisUtils.checkKeyExists(key);
		if(!exist) {
			redisUtils.setValueByKey(key, value);
		}
		Assert.assertEquals(redisUtils.getValueByKey(key), value);
	}
}
