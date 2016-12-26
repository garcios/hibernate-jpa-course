package tut01.oscar.data;

import java.util.Calendar;
import java.util.Date;

import org.hibernate.Session;

import tut01.oscar.data.entities.User;

public class Application {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().openSession();
	
		try {

			session.getTransaction().begin();
			
			
			User user = new User();
			user.setBirthDate(getMyBirthday());
			user.setCreatedBy("Oscar");
	        user.setCreatedDate(new Date());
	        user.setEmailAddress("dummy@gmail.com");
			user.setFirstName("Oscar");
	        user.setLastName("Garcia");
			user.setLastUpdateBy("Oscar");
			user.setLastUpdateDate(new Date());
			
			session.save(user);
			session.getTransaction().commit();
			
			
			session.beginTransaction();
			User dbuser = session.get(User.class, user.getUserId());
			dbuser.setFirstName("Joe");
			session.update(dbuser);
			
			session.refresh(dbuser);
			System.out.println("++++++++++Age:" + dbuser.getAge());
			System.out.println(dbuser);
			
			session.getTransaction().commit();
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
			HibernateUtil.getSessionFactory().close();
		}

	}

	private static Date getMyBirthday() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1974, 2, 10);
		return calendar.getTime();
	}

	
}
