package de.stone.camel.routes;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;

@ApplicationScoped
@ContextName("jms-samples")
public class ReceiveMsgBuilder extends RouteBuilder 
{
	
    @Override
    public void configure() throws Exception 
    {
    	from("jmsLocalNoTx:queue:{{receivemsg.queue.name}}")   
		.routeId("jms-receive-route")
        .autoStartup(false)
        
        .to("log:jms?showAll=true");
    }
}
