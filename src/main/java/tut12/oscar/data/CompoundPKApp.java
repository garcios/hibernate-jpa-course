package tut12.oscar.data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tut01.oscar.data.HibernateUtil;
import tut12.oscar.data.entities.Currency;
import tut12.oscar.data.entities.ids.CurrencyId;


public class CompoundPKApp {
	public static void main(String[] args) {

		SessionFactory sessionFactory = null;
		Session session = null;
		Session session2 = null;
		org.hibernate.Transaction tx = null;
		org.hibernate.Transaction tx2 = null;

		try {
			sessionFactory = HibernateUtil.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Currency currency = new Currency();
			currency.setCountryName("United States");
			currency.setName("Dollar");
			currency.setSymbol("$");

			session.persist(currency);
			tx.commit();

			session2 = sessionFactory.openSession();
			tx2 = session2.beginTransaction();

			Currency dbCurrency = (Currency) session2.get(Currency.class,
					new CurrencyId("Dollar", "United States"));
			System.out.println(dbCurrency.getName());
			
			tx2.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			if (tx2 != null) {
				tx2.rollback();
			}
		} finally {
			session.close();
			sessionFactory.close();
		}
	}



}
