package tptds;

import java.util.List;

public class Specification {

	private String attribute;
	private String parent;
	private List<String> child;

	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public List<String> getChild() {
		return child;
	}
	public void setChild(List<String> child) {
		this.child = child;
	}

	public void printSpec() {
		System.out.print("Attribute:" + attribute);
		System.out.println(": spec:" + parent + "-->" + child);

	}
	
}
