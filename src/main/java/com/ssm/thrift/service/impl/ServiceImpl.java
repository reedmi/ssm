package com.ssm.thrift.service.impl;

import com.ssm.thrift.bean.ThriftBean;
import com.ssm.thrift.service.ThriftService;
import org.apache.thrift.TException;

/**
 * @author reed.mi
 * @date 2017/4/13.
 */
public class ServiceImpl implements ThriftService.Iface {

	@Override
	public boolean send(ThriftBean bean) throws TException {
		System.out.println(bean.getId() + "\t" + bean.getName());
		return true;
	}
}
