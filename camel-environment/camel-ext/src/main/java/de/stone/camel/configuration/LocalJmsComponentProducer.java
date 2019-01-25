package de.stone.camel.configuration;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.jms.ConnectionFactory;

import org.apache.camel.component.jms.JmsComponent;

public class LocalJmsComponentProducer {

	@Resource(mappedName = "java:jboss/DefaultJMSConnectionFactory")
	private ConnectionFactory connectionFactory;
	
	@Produces
	@ApplicationScoped
    @Named("jmsLocal")
	public JmsComponent produceLocalJmsComponent()
	{
		JmsComponent component = new JmsComponent();
        component.setConnectionFactory(connectionFactory);

        return component;
	}
	
	@Produces
    @Named("jmsLocalNoTx")
	public JmsComponent produceLocalNoTxJmsComponent()
	{
        return JmsComponent.jmsComponent(connectionFactory);
	}
}
