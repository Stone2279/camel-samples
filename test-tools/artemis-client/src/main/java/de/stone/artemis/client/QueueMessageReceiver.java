package de.stone.artemis.client;

import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.QueueReceiver;
import javax.jms.TextMessage;

public class QueueMessageReceiver extends QueueClient implements MessageListener
{
	private QueueReceiver receiver;

	public QueueMessageReceiver(String host, int port, String user, String password) 
	{
		super(host, port, user, password);
	}

	@Override
	public void connect(String queueName) throws Exception 
	{
		super.connect(queueName);
		MessageConsumer consumer = session.createConsumer(queue);
		consumer.setMessageListener(this);
		connection.start();
	}

	@Override
	public void onMessage(Message message) 
	{
		try
		{
			if(message instanceof TextMessage)
			{
				System.out.println("- Received message with messageID: " + message.getJMSMessageID());
				System.out.println("\tContent:\n" + ((TextMessage) message).getText());
			}
		}
		catch(final Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
