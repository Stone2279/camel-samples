package de.stone.camel.routes;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;

import de.stone.camel.routes.entities.Company;

@ApplicationScoped
@ContextName("db-samples")
public class JpaSelectBuilder extends RouteBuilder 
{

	@Override
	public void configure() throws Exception 
	{
		from("jpa:" + Company.class.getName() + "?consumeDelete=false&consumer.namedQuery=pendindCompanies")
		.routeId("jpa-select-route")
        .autoStartup(false)
        
        .split(body())
	        .log("${body.name}")
		.end();
		
	}

}
