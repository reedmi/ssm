package com.ssm.cache;

import com.ssm.base.NGTest;
import com.ssm.base.cache.UserRedisRepo;
import com.ssm.base.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author reed.mi
 * @date 2017/4/5.
 */
public class RedisNGTest extends NGTest {

	@Autowired
	UserRedisRepo redisUtil;

	@Test
	public void testSave() {

		Long id = 1L;
		String name = "ssm";

		final User user = new User();
		user.setId(id);
		user.setName(name);

		Assert.assertEquals(redisUtil.save(user), Boolean.TRUE);

		User existingUser = redisUtil.getUser(id);
		Assert.assertNotNull(existingUser);
		Assert.assertEquals(existingUser.getName(), name);
	}
}
