package tut7.oscar.data;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.Session;

import tut1.oscar.data.HibernateUtil;
import tut7.oscar.data.entities.Account;
import tut7.oscar.data.entities.Budget;
import tut7.oscar.data.entities.Transaction;


/**
 * Using Join table for one-to-may relationship.
 * 
 * Budget is the source entity, while Transaction is the target entity.
 * 
 * @author Oscar
 *
 */
public class Application {

	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try {
			org.hibernate.Transaction transaction = session.beginTransaction();
			
			Account account = createNewAccount();

			Budget budget = new Budget();
			budget.setGoalAmount(new BigDecimal("10000.00"));
			budget.setName("Emergency Fund");
			budget.setPeriod("Yearly");
			
			budget.getTransactions().add(createNewBeltPurchase(account));
			budget.getTransactions().add(createShoePurchase(account));
			
			session.save(budget);
			transaction.commit();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
			HibernateUtil.getSessionFactory().close();
		}
	}

	private static Transaction createNewBeltPurchase(Account account) {
		Transaction beltPurchase = new Transaction();
		beltPurchase.setAccount(account);
		beltPurchase.setTitle("Dress Belt");
		beltPurchase.setAmount(new BigDecimal("50.00"));
		beltPurchase.setClosingBalance(new BigDecimal("0.00"));
		beltPurchase.setCreatedBy("Oscar Garcia");
		beltPurchase.setCreatedDate(new Date());
		beltPurchase.setInitialBalance(new BigDecimal("0.00"));
		beltPurchase.setLastUpdatedBy("Oscar Garcia");
		beltPurchase.setLastUpdatedDate(new Date());
		beltPurchase.setNotes("New Dress Belt");
		beltPurchase.setTransactionType("Debit");
		return beltPurchase;
	}

	private static Transaction createShoePurchase(Account account) {
		Transaction shoePurchase = new Transaction();
		shoePurchase.setAccount(account);
		shoePurchase.setTitle("Work Shoes");
		shoePurchase.setAmount(new BigDecimal("100.00"));
		shoePurchase.setClosingBalance(new BigDecimal("0.00"));
		shoePurchase.setCreatedBy("Oscar Garcia");
		shoePurchase.setCreatedDate(new Date());
		shoePurchase.setInitialBalance(new BigDecimal("0.00"));
		shoePurchase.setLastUpdatedBy("Oscar Garcia");
		shoePurchase.setLastUpdatedDate(new Date());
		shoePurchase.setNotes("Nice Pair of Shoes");
		shoePurchase.setTransactionType("Debit");
		return shoePurchase;
	}

	private static Account createNewAccount() {
		Account account = new Account();
		account.setCloseDate(new Date());
		account.setOpenDate(new Date());
		account.setCreatedBy("Oscar Garcia");
		account.setInitialBalance(new BigDecimal("50.00"));
		account.setName("Savings Account");
		account.setCurrentBalance(new BigDecimal("100.00"));
		account.setLastUpdatedBy("Oscar Garcia");
		account.setLastUpdatedDate(new Date());
		account.setCreatedDate(new Date());
		return account;
	}
	
}
