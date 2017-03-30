package com.ssm.base.obervers;

import org.springframework.context.ApplicationEvent;

/**
 * @author reed.mi
 * @date 2017/3/29.
 */
public class EmailEvent extends ApplicationEvent {

	private String address;
	private String text;

	public EmailEvent(Object source) {
		super(source);
	}

	public EmailEvent(Object source, String address, String text) {
		super(source);
		this.address = address;
		this.text = text;
	}

	public void handle() {
		System.out.println("EmailEvent hande.\taddress :" + address + "\ttext:" + text);
	}
}
