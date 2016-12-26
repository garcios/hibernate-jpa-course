package tut03.oscar.data;


import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tut01.oscar.data.HibernateUtil;
import tut03.oscar.data.entities.Address;
import tut03.oscar.data.entities.User;

public class Application2 {

	public static void main(String[] args) {
		
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			
			Transaction transaction = session.beginTransaction();
			
			Date today = new Date();
			
			User user = new User();
			Address address = new Address();
			user.setAge(22);
			user.setBirthDate(today);
			user.setCreatedBy("Oscar");
			user.setCreatedDate(today);
			user.setEmailAddress("dummy2@gmail.com");
			user.setFirstName("Oscar");
			user.setLastName("Garcia");
			user.setLastUpdatedBy("Oscar");
			user.setLastUpdatedDate(today);
			
			address.setAddressLine1("line 1");
			address.setAddressLine2("line2");
			address.setCity("Philippines");
			address.setState("LA");
			address.setZipCode("12345");
			
			user.setAddress(address);
			session.save(user);			
			
			transaction.commit();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
			HibernateUtil.getSessionFactory().close();
		}
		

	}

}
