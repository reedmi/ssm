package com.ssm.user;

import com.ssm.base.NGTest;
import com.ssm.base.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author reed.mi
 * @date 2017/3/29.
 */
public class UserNGTest extends NGTest {

	@Autowired
	UserMapper userMapper;

	@Test
	public void test() {
		Assert.assertEquals(userMapper.findAll().size(), 4);
	}
}
