package org.login.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


/**
 * Users Entity Bean
 * @author shixk
 */
@Entity
@Table(name = "users",uniqueConstraints =  @UniqueConstraint(columnNames ={"id"}) )
public class Users implements Serializable{

	private static final long serialVersionUID = -2910093294469655324L;
	
	private Integer id;
	private String userName;
	private String password;
	private String authority;
		
	@Id
	@Column(name = "id",length=8)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USERS_ID_SEQ")
	@SequenceGenerator(name="USERS_ID_SEQ", sequenceName="SEQ_ID_USERS")  
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="username",length=50,nullable=false)
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name="password",length=6,nullable=false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="authority",length=1,nullable=false)
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Users() {}

	public Users(Integer id, String userName, String password) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
	}
	
}
