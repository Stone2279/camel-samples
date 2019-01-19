package de.stone.camel.routes;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;

import de.stone.camel.routes.processors.CompanyProcessor;

@ApplicationScoped
@ContextName("db-samples")
public class SqlUpdateBuilder extends RouteBuilder
{

	@Override
	public void configure() throws Exception 
	{		 		
		from("timer://update-company?repeatCount=1")   // only run once
		.routeId("sql-update-route")
        .autoStartup(false)
        
    	.to("sql:{{sql.select.company}}?dataSource=postgres-ds")   // select all entries from table company
    	.split(body())   // split the result set and process each row
//    		.stopOnException()  
			.bean(CompanyProcessor.class, "process")   // update each company with a bean
			.to("sql:{{sql.update.company}}?dataSource=postgres-ds")   // update row in db
    	.end()   // here the split is finished and all rows of the result set are processed
		.log("Finished......");   // This will be only called once after the split block finished
	}
	
}
