package com.ssm.thrift.client;

import com.ssm.thrift.bean.ThriftBean;
import com.ssm.thrift.service.ThriftService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class Client {

	public static void main(String[] args) {
		try {
			TTransport transport;

			transport = new TSocket("localhost", 9090);
			transport.open();

			TProtocol protocol = new TBinaryProtocol(transport);
			ThriftService.Client client = new ThriftService.Client(protocol);

			ThriftBean bean = new ThriftBean();
			bean.setId(1L);
			bean.setName("reedmi.com");

			client.send(bean);

			transport.close();
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException x) {
			x.printStackTrace();
		}
	}

}