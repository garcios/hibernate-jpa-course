package tut8.oscar.data;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import org.hibernate.Session;

import tut1.oscar.data.HibernateUtil;
import tut8.oscar.data.entities.Account;
import tut8.oscar.data.entities.Address;
import tut8.oscar.data.entities.Credential;
import tut8.oscar.data.entities.Transaction;
import tut8.oscar.data.entities.User;

/**
 * 
 * Uni-directional One-to-many association.
 * 
 * Account can be associated with many Users (joint bank account).
 * A user can have many accounts.
 * 
 * Either entity can be chosen as the owner.
 * In this example, we choose Account as the owning entity.  
 * Note that the owning entity will have the @Jointable annotation.
 * 
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
			Account account2 = createNewAccount();
			User user = createUser();
			User user2 = createUser();
			
			account.getUsers().add(user);
			account.getUsers().add(user2);
			account2.getUsers().add(user);
			account2.getUsers().add(user2);
			
			session.save(account);
			session.save(account2);
			
			transaction.commit();
			
			Account dbAccount = (Account) session.get(Account.class, account.getAccountId());
			System.out.println(dbAccount.getUsers().iterator().next().getEmailAddress());
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			session.close();
			HibernateUtil.getSessionFactory().close();
		}
	}

	private static User createUser() {
		User user = new User();
		Address address = createAddress();
		user.setAddresses(Arrays.asList(new Address[]{createAddress()}));
		user.setBirthDate(new Date());
		user.setCreatedBy("Oscar Garcia");
		user.setCreatedDate(new Date());
		user.setCredential(createCredential(user));
		user.setEmailAddress("test@test.com");
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setLastUpdatedBy("system");
		user.setLastUpdatedDate(new Date());
		return user;
	}

	private static Credential createCredential(User user) {
		Credential credential = new Credential();
		credential.setUser(user);
		credential.setUsername("test_username");
		credential.setPassword("test_password");
		return credential;
	}

	private static Address createAddress() {
		Address address = new Address();
		address.setAddressLine1("101 Address Line");
		address.setAddressLine2("102 Address Line");
		address.setCity("New York");
		address.setState("PA");
		address.setZipCode("10000");
		address.setAddressType("PRIMARY");
		return address;
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
	

