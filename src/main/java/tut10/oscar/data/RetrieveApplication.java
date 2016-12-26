package tut10.oscar.data;

import org.hibernate.Session;

import tut01.oscar.data.HibernateUtil;
import tut10.oscar.data.entities.Bank;

/**
 * 
 * Retrieving  an entity.
 * Transition from nothing to persistent state.
 * 
 * @author Oscar
 *
 */
public class RetrieveApplication {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		

		
		try {
			org.hibernate.Transaction transaction = session.beginTransaction();

			//if we pass in a non-existent record, we get Null object.
			//The get method hits the DB and executes the select statement
			//Bank bank = session.get(Bank.class, 1L);
			
			//if we issue another get command with passing same id,
			//record is retrieved from cache not from DB
			
			
			//This statement returns a proxy object and does not hit the DB.
			//It will only hit the DB when  bank.getName() is called.
			//When you attempt to load a non-existent object, you get ObjectNotFoundException.
			Bank bank = session.load(Bank.class, 1L);
			
			
			
			System.out.println("+++++++++Method Executed");
			
			System.out.println("+++++++++" + bank.getName());
			
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
			HibernateUtil.getSessionFactory().close();
		}
	}


}
