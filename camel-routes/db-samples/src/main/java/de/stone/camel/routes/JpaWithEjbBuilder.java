package de.stone.camel.routes;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;

import de.stone.camel.routes.processors.SampleEjb;

@ApplicationScoped
@ContextName("db-samples")
public class JpaWithEjbBuilder extends RouteBuilder
{

	@Override
	public void configure() throws Exception 
	{
		from("timer://insert-company?repeatCount=1")   // only run once
		.routeId("ejb-update-route")
        .autoStartup(false)
        
        .bean(SampleEjb.class, "updateCompany");
//        .to("ejb:java:module/SampleEjb?method=updateCompany");
	}

}
