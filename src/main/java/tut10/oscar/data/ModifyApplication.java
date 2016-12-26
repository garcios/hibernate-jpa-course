package tut10.oscar.data;

import java.util.Date;

import org.hibernate.Session;

import tut01.oscar.data.HibernateUtil;
import tut10.oscar.data.entities.Bank;

/**
 * 
 * Modifying an entity.
 * 
 * 
 * @author Oscar
 *
 */
public class ModifyApplication {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
	
		
		try {
			org.hibernate.Transaction transaction = session.beginTransaction();

			Bank bank = session.get(Bank.class, 1L);
			
			bank.setName("New Hope Bank");
			bank.setLastUpdatedBy("Oscar Garcia");
			bank.setLastUpdatedDate(new Date());
			
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
			HibernateUtil.getSessionFactory().close();
		}
	}



}
