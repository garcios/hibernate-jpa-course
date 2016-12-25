package tut3.oscar.data;


import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tut1.oscar.data.HibernateUtil;
import tut3.oscar.data.entities.Address;
import tut3.oscar.data.entities.User2;

public class Application4 {

	public static void main(String[] args) {
		
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			
			Transaction transaction = session.beginTransaction();
			
			Date today = new Date();
			
			User2 user = new User2();
			Address address = new Address();
			Address address2 = new Address();

			user.setAge(22);
			user.setBirthDate(today);
			user.setCreatedBy("Oscar");
			user.setCreatedDate(today);
			user.setEmailAddress("dummy2@gmail.com");
			user.setFirstName("Oscar");
			user.setLastName("Garcia");
			user.setLastUpdatedBy("Oscar");
			user.setLastUpdatedDate(today);
			
			setAddressFields(address);
			setAddressFields2(address2);
			
			user.getAddress().add(address);
			user.getAddress().add(address2);
			
			
			session.save(user);			
			
			transaction.commit();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
			HibernateUtil.getSessionFactory().close();
		}
		

	}

	/**
	 * @param address
	 */
	private static void setAddressFields(Address address) {
		address.setAddressLine1("line 1");
		address.setAddressLine2("line2");
		address.setCity("Philippines");
		address.setState("LA");
		address.setZipCode("12345");
	}

	/**
	 * @param address
	 */
	private static void setAddressFields2(Address address) {
		address.setAddressLine1("xxx line 1");
		address.setAddressLine2("xxx line2");
		address.setCity("Singapore");
		address.setState("SG");
		address.setZipCode("5678");
	}
}
