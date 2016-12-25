package tut4.oscar.data;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tut1.oscar.data.HibernateUtil;
import tut4.oscar.data.entities.User3;
import tut4.oscar.data.entities.Credential2;

//Bi-directional relationship between Credential and User3
public class Application2 {

	public static void main(String[] args) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			
			Transaction transaction = session.beginTransaction();
			
			Date today = new Date();
			
			User3 user = new User3();
			user.setAge(22);
			user.setBirthDate(today);
			user.setCreatedBy("Oscar");
			user.setCreatedDate(today);
			user.setEmailAddress("dummy2@gmail.com");
			user.setFirstName("Oscar");
			user.setLastName("Garcia");
			user.setLastUpdatedBy("Oscar");
			user.setLastUpdatedDate(today);
			

			Credential2 credential = new Credential2();
			credential.setPassword("pass123");
			credential.setUserName("oscar123");
			credential.setUser(user);
	
			//because it is bi-directional
			user.setCredential(credential);
			
			
			//because there is Cascade.ALL in credential,
			//both user and credential will be created in DB
			//credential is the source, so we save the source instead of the target.
			session.save(credential);	
			
			
			User3 dbUser = session.get(User3.class, credential.getUser().getUserId());
			System.out.println("++++++++" + dbUser.getFirstName());
			
			transaction.commit();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
			HibernateUtil.getSessionFactory().close();
		}
	}
	
}
