package com.ssm.base.controller;

import com.ssm.base.obervers.EmailPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author reed.mi
 * @date 2017/3/30.
 */
@Controller
@RequestMapping("/email")
public class EmailController {

	@Autowired
	EmailPublisher emailPublisher;

	@RequestMapping("/send")
	public void send(@RequestParam String address, @RequestParam String text) {
		emailPublisher.send(address, text);
	}
}
