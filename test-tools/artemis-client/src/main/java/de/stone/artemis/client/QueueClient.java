package de.stone.artemis.client;

import java.util.HashMap;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.InitialContext;

import org.apache.activemq.artemis.api.core.TransportConfiguration;
import org.apache.activemq.artemis.api.jms.ActiveMQJMSClient;
import org.apache.activemq.artemis.api.jms.JMSFactoryType;
import org.apache.activemq.artemis.core.remoting.impl.netty.NettyConnectorFactory;
import org.apache.activemq.artemis.core.remoting.impl.netty.TransportConstants;

public abstract class QueueClient 
{
	private String host = "localhost";
	private int port = 8080;
	private String user;
	private String password;

	protected InitialContext context;
	protected ConnectionFactory connectionFactory;
	protected Connection connection;
	protected Session session;
	protected Queue queue;

	public QueueClient(String host, int port, String user, String password) 
	{
		this.host = host;
		this.port = port;
		this.user = user;
		this.password = password;
	}
	
	protected void connect(final String queueName) throws Exception
	{				 
		TransportConfiguration transportConfiguration = new TransportConfiguration(NettyConnectorFactory.class.getName(), createProperties());
		connectionFactory = ActiveMQJMSClient.createConnectionFactoryWithoutHA(JMSFactoryType.CF, transportConfiguration);
		
		connection = connectionFactory.createConnection(user, password);
		session = connection.createSession();
		queue = session.createQueue(queueName);
	}
	
	public void close()
	{
		try
		{
			session.close();
			connection.close();
			context.close();			
		}
		catch(final Exception e) {}
	}
	
	private Map<String, Object> createProperties()
	{
		Map<String, Object> props = new HashMap<>();
        props.put(TransportConstants.HOST_PROP_NAME, host);
        props.put(TransportConstants.PORT_PROP_NAME, port);
        props.put(TransportConstants.HTTP_UPGRADE_ENABLED_PROP_NAME, "true");
        props.put(TransportConstants.HTTP_ENABLED_PROP_NAME, "false");
        props.put(TransportConstants.SSL_ENABLED_PROP_NAME, "false");
        
        return props;
	}

}
