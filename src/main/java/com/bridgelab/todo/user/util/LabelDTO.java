/**
 * 
 */
package com.bridgelab.todo.user.util;

import java.util.List;

import com.bridgelab.todo.label.model.LabelRes;

/**
 * @author bridgeit
 *
 */
public class LabelDTO {
	

	private List<LabelRes> labels;

	public List<LabelRes> getLabels() {
		return labels;
	}

	public void setLabels(List<LabelRes> labels) {
		this.labels = labels;
	}
}
