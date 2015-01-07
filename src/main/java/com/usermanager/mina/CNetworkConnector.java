package com.usermanager.mina;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.ReadFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class CNetworkConnector {

	private NioSocketConnector m_Connector = null;
	private IoSession m_Session = null;
	private int m_ReadTimeout;

	public CNetworkConnector() {
	}
	public static void main(String[] args) {
		CNetworkConnector connector = new CNetworkConnector();
		boolean start = connector.start("192.168.1.25", 8888, 30, 30);
		System.err.println(start);
	}

	public boolean start(String pAddress, int pPort, int pReadTimeout, int pConnectTimeout) {
		m_ReadTimeout = pReadTimeout;
		// 创建客户端连接器.
		m_Connector = new NioSocketConnector();
		// 设置编码过滤器
		m_Connector.getFilterChain().addLast("objectFilter", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		// 设置超时
		m_Connector.setConnectTimeoutMillis(pConnectTimeout * 1000);
		m_Connector.setConnectTimeoutCheckInterval(pConnectTimeout * 10);
		SocketSessionConfig lSocketConfig = m_Connector.getSessionConfig();
		lSocketConfig.setUseReadOperation(true);
		// 建立连接
		ConnectFuture cf = m_Connector.connect(new InetSocketAddress(pAddress, pPort));
		if (cf.getException() != null) {
			return false;
		}
		// 等待连接创建完成
		cf = cf.awaitUninterruptibly();

		if (!cf.isConnected())
			return false;
		// 判断连接是否正确完成
		if (cf.getException() != null) {
			return false;
		}

		m_Session = cf.getSession();

		return true;
	}

	public Object sendAndRead(Object msg) throws Exception {
		if (m_Session == null)
			throw new Exception("invalid operation");
		try {

			WriteFuture lFeture = m_Session.write(msg);
			lFeture.awaitUninterruptibly();
			ReadFuture lReadFuture = m_Session.read();
			if (lReadFuture.awaitUninterruptibly(m_ReadTimeout, TimeUnit.SECONDS)) {
				Object object = lReadFuture.getMessage();
				return object;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		} finally {
			m_Session.close(true);
			m_Session.getService().dispose();
		}
	}

}
