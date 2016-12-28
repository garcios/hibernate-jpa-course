package tut11.oscar.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import tut10.oscar.data.entities.Bank;

/**
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
 * Modify an entity using JPA.
 * 
 * 
 * 
 * @author Oscar
 *
 */
public class ModifyApplication {

	public static void main(String[] args) {
		
		EntityManagerFactory factory = null;
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try{
			factory = Persistence.createEntityManagerFactory("infinite-finances");
			em = factory.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			Bank bank = em.find(Bank.class, 1L);
			bank.setName("Another Demonstration");
			
			
			tx.commit();
		}catch(Exception e){
			tx.rollback();
		}finally{
			em.close();
			factory.close();
		}
		

	}

}
