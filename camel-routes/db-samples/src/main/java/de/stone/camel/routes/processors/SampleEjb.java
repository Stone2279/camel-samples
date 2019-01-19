package de.stone.camel.routes.processors;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import de.stone.camel.routes.entities.Company;

@Stateless
public class SampleEjb 
{

	@PersistenceContext
	private EntityManager em;
	
	public void updateCompany()
	{
		List<Company> allCompanies = loadAllCompanies();
		
		allCompanies.forEach(company -> {
			company.setAddress("home-sweet-home");
		});
	}
	
	private List<Company> loadAllCompanies()
	{
		CriteriaQuery<Company> query = em.getCriteriaBuilder().createQuery(Company.class);
		Root<Company> from = query.from(Company.class);
		query.select(from);
		return em.createQuery(query).getResultList();
	}
}
