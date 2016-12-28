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
 * Remove an entity using JPA.
 * 
 * 
 * 
 * @author Oscar
 *
 */
public class ReattachApplication {

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
			System.out.println(em.contains(bank));
			//em.clear(); //detaches all entities in the persistence context
			em.detach(bank); //if you want specific entity to be detached
			System.out.println(em.contains(bank));
			
			bank.setName("Something else");
			Bank bank2 = em.merge(bank); //bank2 will be in the persistence context 
			//Note that merge, will either fire an insert or update sql depending on 
			//whether it is in DB or not
			
			tx.commit();
		}catch(Exception e){
			tx.rollback();
		}finally{
			em.close();
			factory.close();
		}
		

	}

}
