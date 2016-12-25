package tut1.oscar.data.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

@Entity
@Table(name="FINANCES_USER")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) //IDENTITY, MySQL DB generates a unique if based on PK
	@Column(name="USER_ID")
	//If using sequence to generate the id from Oracle DB
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_seq")
	//@SequenceGenerator(name="user_seq", sequenceName="USER_ID_SEQ")
	//USER_ID_SEQ is the Oracle DB sequence name
	private Long userId;
	
	@Column(name="FIRST_NAME")
	private String firstName;

	@Column(name="LAST_NAME")
	private String lastName;
	
	//exception will be thrown by Hibernate instead of at the Database level
	@Column(name="BIRTH_DATE", nullable=false)  
	@Temporal(TemporalType.DATE)
	private Date birthDate;
	
	@Column(name="EMAIL_ADDRESS")
	private String emailAddress;
	
	@Column(name="LAST_UPDATED_DATE")
	private Date lastUpdateDate;
	
	@Column(name="LAST_UPDATED_BY")
	private String lastUpdateBy;
	
	//updatable=false -> prevents column from from being updated, the generated SQL will not include this column
	@Column(name="CREATED_DATE", updatable=false)  
	private Date createdDate;
	
	@Column(name="CREATED_BY", updatable=false)
	private String createdBy;

	@Formula("lower(datediff(curdate(), birth_date)/365)")
	private int age;
	
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	

	//Transient annotation, column is not meant to persists in DB table
	@Transient
	private boolean valid;


	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate="
				+ birthDate + ", emailAddress=" + emailAddress + ", lastUpdateDate=" + lastUpdateDate
				+ ", lastUpdateBy=" + lastUpdateBy + ", createdDate=" + createdDate + ", createdBy=" + createdBy
				+ ", age=" + age + ", valid=" + valid + "]";
	}

	
	
}
