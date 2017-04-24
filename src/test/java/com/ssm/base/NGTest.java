package com.ssm.base;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * @author reed.mi
 * @date 2017/3/29.
 */
@ContextConfiguration({
		"classpath:spring/context.xml",
		"classpath:spring/context-db.xml",
		"classpath:spring/context-redis.xml",
		"classpath:spring/context-quartz.xml"
})
public class NGTest extends AbstractTestNGSpringContextTests {

}