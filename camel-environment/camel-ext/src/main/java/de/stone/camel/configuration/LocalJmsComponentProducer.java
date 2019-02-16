package de.stone.camel.configuration;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.jms.ConnectionFactory;

import org.apache.camel.component.jms.JmsComponent;
import org.springframework.transaction.PlatformTransactionManager;

public class LocalJmsComponentProducer {

	@Resource(mappedName = "java:jboss/DefaultJMSConnectionFactory")
	private ConnectionFactory connectionFactory;
	
	@Produces
	@ApplicationScoped
    @Named("jmsLocal")
	public JmsComponent produceLocalJmsComponent(PlatformTransactionManager transactionManager)
	{
		return JmsComponent.jmsComponentTransacted(connectionFactory, transactionManager);
	}
	
	@Produces
    @Named("jmsLocalNoTx")
	public JmsComponent produceLocalNoTxJmsComponent()
	{
        return JmsComponent.jmsComponent(connectionFactory);
	}
}
