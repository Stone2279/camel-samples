package de.stone.artemis.client;

import javax.jms.MessageProducer;
import javax.jms.TextMessage;

public class QueueMessageSender extends QueueClient
{
	private MessageProducer sender;
	
	public QueueMessageSender(String host, int port, String user, String password) 
	{
		super(host, port, user, password);
	}
	
	@Override
	public void connect(String queueName) throws Exception 
	{
		super.connect(queueName);
		sender = session.createProducer(queue);
		connection.start();
	}

	public void sendMessage(final String message) throws Exception
	{
		sendMessage(message, 1);
	}
	
	public void sendMessage(final String message, final int repeat) throws Exception
	{
		sendMessage(message, repeat, 0);
	}
	
	public void sendMessage(final String message, final int repeat, final int throttle) throws Exception
	{
		for (int i = 0; i < repeat; i++)
		{
			TextMessage textMessage = session.createTextMessage(message);
			sender.send(textMessage);
			if(throttle > 0)
			{
				Thread.sleep(throttle);
			}
		}
	}
}
