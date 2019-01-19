package de.stone.camel.routes;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;

import de.stone.camel.routes.processors.CompanyProcessor;

@ApplicationScoped
@ContextName("db-samples")
public class SqlInsertBuilder extends RouteBuilder
{

	@Override
	public void configure() throws Exception
	{
		from("timer://insert-company?repeatCount=1")   // only run once
		.routeId("sql-insert-route")
        .autoStartup(false)
        
		.to("language:simple:resource:classpath:sample-company.xml")  // read test xml from classpath
		.split(xpath("//company"))   // split xml with xpath
		.bean(CompanyProcessor.class, "processXml")   // process each company tag with a bean that extracts the values for the INSERT statement
		.to("sql:{{sql.insert.company}}?dataSource=postgres-ds");   // INSERT into db table. statement is read from properties file
	}

}
