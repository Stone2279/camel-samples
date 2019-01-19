package de.stone.camel.routes.processors;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.language.XPath;

public class CompanyProcessor 
{
	public void processXml(
            @XPath("//name") String name,
            @XPath("//age") Integer age, 
            @XPath("//address") String address,
            @XPath("//salary") Double salary,
			Exchange exchange) throws Exception
	{
		exchange.getIn().setHeader("name", name);
		exchange.getIn().setHeader("age", age);
		exchange.getIn().setHeader("address", address);
		exchange.getIn().setHeader("salary", salary);
	}
	
	public void process(Exchange exchange)
	{
		Map<String, Object> row = exchange.getIn().getBody(Map.class);
		row.put("age", 9999);
	}
}
