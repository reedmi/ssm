package com.ssm.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author reed.mi
 * @date 2017/4/16.
 */
@Controller
public class MessageController {

	@RequestMapping("/mq/send")
	public @ResponseBody String send(String msg) {
		Map<String, Object> ret = new HashMap<>();
		ret.put("data", msg);
		return "ok";
	}
}
