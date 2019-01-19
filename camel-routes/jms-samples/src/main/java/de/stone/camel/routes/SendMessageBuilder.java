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
public class SendMessageBuilder extends RouteBuilder
{

	@Override
	public void configure() throws Exception 
	{
		JAXBContext jaxbContext = JAXBContext.newInstance(SomeData.class);
		DataFormat jaxb = new JaxbDataFormat(jaxbContext);
		
		from("timer://send-message-timer?fixedRate=true&period=5000")   // run every 5 seconds
    	.routeId("send-message-route")
        .autoStartup(false)
        
        .process(exchange -> exchange.getIn().setBody(new SomeData("hello", 11, 22.22)))
        .marshal(jaxb)
        .to("jmsLocalNoTx:queue:{{receivemsg.queue.name}}?jmsMessageType=Text");
	}

}
