package tut10.oscar.data;

import org.hibernate.Session;

import tut01.oscar.data.HibernateUtil;
import tut10.oscar.data.entities.Bank;

/**
 * 
 * Flush
 * 
 * 
 * @author Oscar
 *
 */
public class FlushApplication {

	public static void main(String[] args) {
	
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		org.hibernate.Transaction transaction = session.beginTransaction();
		try {
			Bank bank = (Bank) session.get(Bank.class, 1L);
			bank.setName("Something Different");
			System.out.println("Calling Flush");
			session.flush();
			
			bank.setAddressLine1("Another Address Line");
			System.out.println("Calling commit");
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}finally{
			session.close();
			HibernateUtil.getSessionFactory().close();
		}
	}




}
