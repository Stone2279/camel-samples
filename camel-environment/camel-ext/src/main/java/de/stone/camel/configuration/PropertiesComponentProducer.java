package de.stone.camel.configuration;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.apache.camel.component.properties.PropertiesComponent;

public class PropertiesComponentProducer 
{

	@Produces
	@ApplicationScoped
	@Named("properties")
	public PropertiesComponent createPropertiesComponent()
	{
	    PropertiesComponent component = new PropertiesComponent();
	    component.setEncoding("UTF-8");
	    component.setLocation("classpath:route-config.properties,classpath:/sql/sql.properties");
	    
	    return component;
	}
}
