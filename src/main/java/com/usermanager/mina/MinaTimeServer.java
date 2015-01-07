package com.usermanager.mina;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MinaTimeServer {

	public MinaTimeServer() {

	}

	public static void main(String[] args) {
		MinaTimeServer server = new MinaTimeServer();
		server.StartMina();
	}
	public void StartMina() {
		SocketAcceptor acceptor = new NioSocketAcceptor();
		DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
		//设定这个过滤器将以对象为单位读取数据
		ProtocolCodecFilter filter = new ProtocolCodecFilter(new ObjectSerializationCodecFactory());
		chain.addLast("objectFilter", filter);

		//设定服务器消息处理器
		TimeServerHandler timeServerHandler = new TimeServerHandler();
		acceptor.setHandler(timeServerHandler);
		//服务器绑定的端口
		int bindPort = 8888;
		//绑定端口，启动服务器
		try {
			acceptor.bind(new InetSocketAddress(bindPort));

		} catch (IOException e) {
			System.out.println("Mina Server start for error!" + bindPort);
			e.printStackTrace();
		}
		System.out.println("Mina Server run done! on port:" + bindPort);
	}

}