package tptds;

import java.io.Serializable;

public class NodeObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String label;

	public NodeObject(String label) {
		super();
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void printLabel() {
		System.out.println("label:" + label);
	}

}
