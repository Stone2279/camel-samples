package de.stone.artemis.client;

public class Main {

	public static void main(String[] args) throws Exception 
	{
		QueueMessageSender sender = new QueueMessageSender("localhost", 8080, "jmsuser", "jmsuser");
		sender.connect("sampleTxQ");
		
		sender.sendMessage("<someData><someString>Runs fine</someString><someInt>12</someInt><someDouble>33.33</someDouble></someData>", 10000);
		
		sender.close();
	}

}
