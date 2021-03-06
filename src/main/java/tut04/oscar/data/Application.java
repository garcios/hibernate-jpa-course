package tut04.oscar.data;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tut01.oscar.data.HibernateUtil;
import tut03.oscar.data.entities.User;
import tut04.oscar.data.entities.Credential;

//One-to-one Uni-directional relationship between Credential and User
public class Application {

	public static void main(String[] args) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			
			Transaction transaction = session.beginTransaction();
			
			Date today = new Date();
			
			User user = new User();
			user.setAge(22);
			user.setBirthDate(today);
			user.setCreatedBy("Oscar");
			user.setCreatedDate(today);
			user.setEmailAddress("dummy2@gmail.com");
			user.setFirstName("Oscar");
			user.setLastName("Garcia");
			user.setLastUpdatedBy("Oscar");
			user.setLastUpdatedDate(today);
			

			Credential credential = new Credential();
			credential.setPassword("pass123");
			credential.setUserName("oscar123");
			credential.setUser(user);
			
			
			//because there is Cascade.ALL in credential,
			//both user and credential will be created in DB
			session.save(credential);	
			
			
			transaction.commit();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
			HibernateUtil.getSessionFactory().close();
		}
	}
	
}
