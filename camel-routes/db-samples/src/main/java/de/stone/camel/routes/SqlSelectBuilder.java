package de.stone.camel.routes;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;

@ApplicationScoped
@ContextName("db-samples")
public class SqlSelectBuilder extends RouteBuilder 
{
	
    @Override
    public void configure() throws Exception 
    {
    	from("timer://select-company?fixedRate=true&period=5000")   // run every 5 seconds
    	.routeId("sql-select-route")
        .autoStartup(false)
    	.to("sql:{{sql.select.company}}?dataSource=postgres-ds")   // select all rows from table. statement is read from sql.properties file
    	.log("${body}");   // just log the results...;
    }
}
