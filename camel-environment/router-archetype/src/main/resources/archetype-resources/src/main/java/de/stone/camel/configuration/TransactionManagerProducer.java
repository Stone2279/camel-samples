package de.stone.camel.configuration;

import javax.annotation.Resource;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

public class TransactionManagerProducer 
{
	@Resource(mappedName = "java:/TransactionManager")
    private TransactionManager transactionManager;
	
	@Resource
	private UserTransaction userTransaction;
	
	@Produces
    @Named("transactionManager")
    public PlatformTransactionManager createTransactionManager() 
	{
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
        jtaTransactionManager.setUserTransaction(userTransaction);
        jtaTransactionManager.setTransactionManager(transactionManager);
        jtaTransactionManager.afterPropertiesSet();
        
        return jtaTransactionManager;
    }
}
