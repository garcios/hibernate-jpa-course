package tut3.oscar.data;


import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tut1.oscar.data.HibernateUtil;
import tut3.oscar.data.entities.Bank;

public class Application {

	public static void main(String[] args) {
		
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			
			Transaction transaction = session.beginTransaction();
			
			Date today = new Date();
			
			Bank bank = new Bank();
			bank.setName("Banco De Oro");
			bank.setAddressLine1("12 Makati Street");
			bank.setAddressLine2("2nd Floor");
	        bank.setCity("Makati");
			bank.setState("MA");
			bank.setZipCode("3000");
			bank.setCreatedBy("Kazier Garcia");
			bank.setCreatedDate(today);
			bank.setLastUpdatedBy("Oscar Garcia");
			bank.setLastUpdatedDate(today);
			bank.setInternational(false);
			
			bank.getContacts().add("Doti");
			bank.getContacts().add("Rey");
			bank.getContacts().add("Alan");
			
			session.save(bank);
			
			transaction.commit();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
			HibernateUtil.getSessionFactory().close();
		}
		

	}

}
