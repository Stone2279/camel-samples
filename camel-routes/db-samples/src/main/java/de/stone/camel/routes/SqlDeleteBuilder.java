package de.stone.camel.routes;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;

@ApplicationScoped
@ContextName("db-samples")
public class SqlDeleteBuilder extends RouteBuilder
{

	@Override
	public void configure() throws Exception 
	{
		from("timer://delete-company?repeatCount=1")   // only run once
		.routeId("sql-delete-route")
        .autoStartup(false)
        .to("sql:{{sql.delete.company}}?dataSource=postgres-ds");   // delete all rows from table. statement is read from sql.properties file
	}

}
