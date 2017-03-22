package com.ssm.user;

import com.ssm.base.Junit4Test;
import com.ssm.base.dao.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ReedMi on 2017/3/22.
 */
public class UserJunit4Test extends Junit4Test {

    @Autowired
    UserMapper userMapper;

    @Test
    public void test() {
        Assert.assertEquals(userMapper.findAll().size(), 4);
    }
}
