package com.bridgelab.todo.label.model;

public class LabelRes {

	private int labelId;
	private String labelName;
	
	public LabelRes(Label object) {
		this.labelId = object.getLabelId();
		this.labelName = object.getLabelName();
		
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
