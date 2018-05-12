/**
 * 
 */
package com.bridgelab.todo.user.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bridgelab.todo.label.model.Label;
import com.bridgelab.todo.notes.model.Notes;

/**
 * @author bridgeit
 *
 */

@Entity
@Table(name="registration")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private long userId;
	
	@Column
	private String username;
	
	@Column
	private String email;
	
	@Column
	private String password;
	
	@OneToMany(mappedBy="user")
	private Set<Notes> notes;
	
	@OneToMany(mappedBy="user")
	private Set<Label> label;
	
	@Column
	private String randomUUID;
	
	@Column
	private boolean status;
	
	@Column
	@Lob
	private byte[] image;

	public User() {

	}

	

//	public User(long userId, String username, String email, String password, Set<Notes> notes, String randomUUID,
//			boolean status) {
//		
//		this.userId = userId;
//		this.username = username;
//		this.email = email;
//		this.password = password;
//		this.notes = notes;
//		this.randomUUID = randomUUID;
//		this.status = status;
//	}



	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRandomUUID() {
		return randomUUID;
	}

	public void setRandomUUID(String randomUUID) {
		this.randomUUID = randomUUID;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}



	public byte[] getImage() {
		return image;
	}



	public void setImage(byte[] image) {
		this.image = image;
	}
	
	

}
