package de.stone.camel.configuration;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.camel.component.jpa.JpaComponent;
import org.springframework.transaction.PlatformTransactionManager;

public class JpaProducer {

	@Produces
	@PersistenceContext
	private EntityManager em;

	@Produces
	@ApplicationScoped
	@Named("jpa")
	public JpaComponent jpaComponent(PlatformTransactionManager transactionManager, EntityManager entityManager) 
	{
		JpaComponent component = new JpaComponent();
		component.setTransactionManager(transactionManager);
		component.setEntityManagerFactory(entityManager.getEntityManagerFactory());
		return component;
	}
}
