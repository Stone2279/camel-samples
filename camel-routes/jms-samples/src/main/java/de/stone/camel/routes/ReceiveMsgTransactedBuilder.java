package de.stone.camel.routes;

import javax.enterprise.context.ApplicationScoped;
import javax.xml.bind.JAXBContext;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.spi.DataFormat;

import de.stone.camel.routes.entities.SomeData;

@ApplicationScoped
@ContextName("jms-samples")
public class ReceiveMsgTransactedBuilder extends RouteBuilder
{

	@Override
	public void configure() throws Exception 
	{
		
		// prepare JAXB for un-/marshalling 'someData' XML's
		JAXBContext jaxbContext = JAXBContext.newInstance(SomeData.class);
		DataFormat jaxb = new JaxbDataFormat(jaxbContext);
		
		from("jmsLocal:queue:{{receivemsg.tx.queue.name}}")   
		.routeId("jms-receive-tx-route")
        .autoStartup(true)
        
        .unmarshal(jaxb)	// try to unmarshal the received JMS message
        .to("jpa:" + SomeData.class.getName())
        .choice()
    		.when(simple("${body.someString} == 'Fail'"))
    			.throwException(new IllegalArgumentException("Someone told me to fail"))
    		.otherwise()
    			.log("Success")
    	.end();
	}

}
