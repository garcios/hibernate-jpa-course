package tut10.oscar.data;

import org.hibernate.Session;

import tut01.oscar.data.HibernateUtil;
import tut10.oscar.data.entities.Bank;

/**
 * 
 * Delete an entity.
 * Transition from Persistent to Removed state.
 * 
 * @author Oscar
 *
 */
public class DeleteApplication {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
	
		
		try {
			org.hibernate.Transaction transaction = session.beginTransaction();

			Bank bank = session.get(Bank.class, 1L);
			
			
			System.out.println(session.contains(bank));
			session.delete(bank);
			System.out.println("++++++++Method Invoked");
			System.out.println(session.contains(bank));
			
			
			transaction.commit(); //issue sql to DB
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
			HibernateUtil.getSessionFactory().close();
		}
	}


}
