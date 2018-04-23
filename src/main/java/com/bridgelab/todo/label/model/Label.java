/**
 * 
 */
package com.bridgelab.todo.label.model;

/**
 * @author bridgeit
 *
 */
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bridgelab.todo.user.model.User;



/**
 * @author bridgeit
 *
 */

@Entity
@Table(name="Label")
public class Label implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	@Column
	private int labelId;

	
	@Column
	private String labelName;
@ManyToOne
@JoinColumn(name="userId")
	private User user;


	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	public int getLabelId() {
		return labelId;
	}
	public void setLabelId(int labelId) {
		this.labelId = labelId;
	}
	

}

