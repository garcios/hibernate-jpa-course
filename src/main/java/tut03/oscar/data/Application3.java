package tut03.oscar.data;


import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tut01.oscar.data.HibernateUtil;
import tut03.oscar.data.entities.Bank2;

public class Application3 {

	public static void main(String[] args) {
		
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			
			Transaction transaction = session.beginTransaction();
			
			Date today = new Date();
			
			Bank2 bank = new Bank2();
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
			
			bank.getContacts().put("MANAGER","Doti");
			bank.getContacts().put("TELLER","Rey");
			bank.getContacts().put("MAILMAN","Alan");
			
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
