package de.stone.camel.routes.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class SomeData 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "somedata_seq_gen")
	@SequenceGenerator(name = "somedata_seq_gen", sequenceName = "somedata_id_seq", allocationSize=1)
	private Integer id = null;
	
	private String someString;
	private int someInt;
	private double someDouble;

	public SomeData() 
	{
	}
	
	public SomeData(String someString, int someInt, double someDouble) 
	{
		super();
		this.someString = someString;
		this.someInt = someInt;
		this.someDouble = someDouble;
	}

	public Integer getId() 
	{
		return id;
	}

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public String getSomeString() 
	{
		return someString;
	}

	public void setSomeString(String someString) 
	{
		this.someString = someString;
	}

	public int getSomeInt() 
	{
		return someInt;
	}

	public void setSomeInt(int someInt) 
	{
		this.someInt = someInt;
	}

	public double getSomeDouble() 
	{
		return someDouble;
	}

	public void setSomeDouble(double someDouble) 
	{
		this.someDouble = someDouble;
	}

}
