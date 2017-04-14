package com.ssm.thrift.server;

import com.ssm.thrift.service.ThriftService;
import com.ssm.thrift.service.impl.ServiceImpl;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TServer.Args;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

public class Server {

	public static void StartsimpleServer(ThriftService.Processor<ServiceImpl> processor) {
		try {
			TServerTransport serverTransport = new TServerSocket(9090);
			TServer server = new TSimpleServer(new Args(serverTransport).processor(processor));
			System.out.println("Starting the simple server...");
			server.serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		StartsimpleServer(new ThriftService.Processor<>(new ServiceImpl()));
	}

}