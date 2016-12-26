package tut10.oscar.data;

import org.hibernate.Session;

import tut01.oscar.data.HibernateUtil;
import tut10.oscar.data.entities.Bank;

/**
 * 
 * Reattaching detached entities.
 * 
 * 
 * @author Oscar
 *
 */
public class ReaattachApplication {

	public static void main(String[] args) {
	
		
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			org.hibernate.Transaction transaction = session.beginTransaction();

			Bank bank = session.get(Bank.class, 1L);
			transaction.commit(); 
			session.close(); //detached bank entity
			
			Session session2 = HibernateUtil.getSessionFactory().openSession();
			org.hibernate.Transaction transaction2 = session2.beginTransaction();
	
			System.out.println(session2.contains(bank));
			session2.update(bank); //reattached bank entity
            bank.setName("Test Bank");
			System.out.println("Method Invoked");
			System.out.println(session2.contains(bank));

			transaction2.commit(); 
			session2.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			HibernateUtil.getSessionFactory().close();
		}
	}


}
