package de.stone.camel.configuration;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.jms.ConnectionFactory;

import org.apache.camel.component.jms.JmsComponent;
import org.springframework.transaction.PlatformTransactionManager;

public class JmsComponentProducer {

	@Resource(mappedName = "java:jboss/DefaultJMSConnectionFactory")
	private ConnectionFactory connectionFactory;
	
	@Produces
    @Named("jmsLocalNoTx")
	public JmsComponent configuredJmsCompponent()
	{
        return JmsComponent.jmsComponent(connectionFactory);
	}
	
	@Produces
    @Named("jmsLocal")
    public JmsComponent createJmsComponent(PlatformTransactionManager transactionManager) 
	{
		JmsComponent jmsComponent = JmsComponent.jmsComponentTransacted(connectionFactory, transactionManager);
		jmsComponent.setTransacted(true);
        return JmsComponent.jmsComponentTransacted(connectionFactory, transactionManager);
    }
}
