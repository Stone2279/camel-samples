package de.stone.camel.routes;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;

@ApplicationScoped
@ContextName("${artifactId}")
public class MyRouteBuilder extends RouteBuilder {
	
    @Override
    public void configure() throws Exception {

    }
}
