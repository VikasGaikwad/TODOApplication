/**
 * 
 */
package com.bridgelab.todo.user.util;

import com.bridgelab.todo.label.model.Label;

/**
 * @author bridgeit
 *
 */
public class LabelDTO {
	private int labelId;
	private String labelName;
	public LabelDTO(Label label) {
		super();
		this.labelId = label.getLabelId();
		this.labelName = label.getLabelName();
	}
	public int getLabelId() {
		return labelId;
	}
	public void setLabelId(int labelId) {
		this.labelId = labelId;
	}
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
}
