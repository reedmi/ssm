package com.ssm.user;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ssm.base.NGTest;
import com.ssm.base.dao.UserMapper;
import com.ssm.base.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

	@Test
	public void testEqual() {
		User u1 = new User();
		u1.setId(1L);
		u1.setBirthDay(new Date());
		u1.setName("reedmi");
		u1.setAge("1");

		List<User> users = new ArrayList<>();
		users.add(u1);

		Assert.assertEquals(users.contains(u1), true);
	}

	@Test
	public void testIn() {
		List<User> users = userMapper.findIn("1,2,3");
		System.out.println(JSONObject.toJSONString(users));
	}

	@Test
	public void testBetween() {
		String startTime = "2017-04-24 20:00:00";
		String endTime = "2017-04-25 20:00:00";
		List<User> users = userMapper.findBirthDayBetween(startTime, endTime);
		System.out.println(JSON.toJSONString(users));
	}
}
