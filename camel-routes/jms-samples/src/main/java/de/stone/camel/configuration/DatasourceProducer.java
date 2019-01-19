package de.stone.camel.configuration;

import javax.annotation.Resource;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.sql.DataSource;

public class DatasourceProducer {

	    @Resource(lookup = "java:jboss/datasources/jms-samples/postgres-ds")
	    DataSource dataSource;

	    @Produces
	    @Named("postgres-ds")
	    public DataSource getDataSource() 
	    {
	        return dataSource;
	    }
}
