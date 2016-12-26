package tut01.oscar.data;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final  SessionFactory sessionFactory  = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		
		try{
			
			Configuration configuration = new Configuration();
			
			//configuration.addAnnotatedClass(User.class); //xml config takes care of mapping in <mapping class="tut1.oscar.data.entities.User">
			
			return configuration
                    .configure("hibernate.cfg.xml")
	                .buildSessionFactory();
			
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("There was an error building the factory");
		}
		
	}
	
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
}
