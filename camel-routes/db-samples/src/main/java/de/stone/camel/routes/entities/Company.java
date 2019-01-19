package de.stone.camel.routes.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import org.apache.camel.component.jpa.Consumed;

@Entity
@NamedQuery(name = "pendindCompanies", query = "SELECT c FROM Company c WHERE c.status = 10")
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq_gen")
	@SequenceGenerator(name = "company_seq_gen", sequenceName = "company_id_seq", allocationSize=1)
	private Integer id;

	private String name;

	private Integer age;

	private String address;

	private Float salary;
	
	private Integer status;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Float getSalary() {
		return salary;
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Consumed
	public void consumed()
	{
		this.setStatus(30);
	}
}
