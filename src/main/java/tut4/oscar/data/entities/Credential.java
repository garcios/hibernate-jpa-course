package tut4.oscar.data.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import tut3.oscar.data.entities.User;

@Entity
@Table(name="CREDENTIAL")
public class Credential {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CREDENTIAL_ID")
	private Long credentialId;
	
	//User is target entity.
	//Credential is source entity because it holds the foreign key.
	@OneToOne(cascade=CascadeType.ALL)
	//CascadeType.ALL allows both User and Credential to be persisted at
	//at the same time.
	@JoinColumn(name="USER_ID", referencedColumnName="USER_ID")
	//name="USER_ID" is the table column from CREDENTIAL(source)
	//referencedColumnName="USER_ID" is the table column from FINANCES_USER(target)
	//if they have the same name, referencedColumnName is not necessary
	private User user;
	
	
	@Column(name="USERNAME")
	private String userName;
	
	@Column(name="PASSWORD")
	private String password;

	public Long getCredentialId() {
		return credentialId;
	}

	public void setCredentialId(Long credentialId) {
		this.credentialId = credentialId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
