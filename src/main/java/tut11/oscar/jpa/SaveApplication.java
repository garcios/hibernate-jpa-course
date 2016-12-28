package tut11.oscar.jpa;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import tut10.oscar.data.entities.Bank;

/**
 * In JPA configuration, no need to register each entity class.
 * 
 * EntityManager vs Session
 * ========================
 * persist()       save()
 * merge()         merge()
 * remove()        delete()
 * detach()        evict()
 * close()         close()
 * clear()         clear()
 * find()          get()
 * getReference()  load()
 * flush()         flush()  
 * 
 * =======================
 * 
 * Save an entity using JPA.
 * 
 * 
 * 
 * @author Oscar
 *
 */
public class SaveApplication {

	public static void main(String[] args) {
		
		EntityManagerFactory factory = null;
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try{
			factory = Persistence.createEntityManagerFactory("infinite-finances");
			em = factory.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			Bank bank = createBank();
			em.persist(bank);
			
			tx.commit();
		}catch(Exception e){
			tx.rollback();
		}finally{
			em.close();
			factory.close();
		}
		

	}

	private static Bank createBank() {
		Bank bank = new Bank();
		bank.setName("First United Federal");
		bank.setAddressLine1("103 Washington Plaza");
		bank.setAddressLine2("Suite 332");
		bank.setAddressType("PRIMARY");
		bank.setCity("New York");
		bank.setCreatedBy("Kevin Bowersox");
		bank.setCreatedDate(new Date());
		bank.setInternational(false);
		bank.setLastUpdatedBy("Kevin Bowersox");
		bank.setLastUpdatedDate(new Date());
		bank.setState("NY");
		bank.setZipCode("10000");
		return bank;
	}

}
