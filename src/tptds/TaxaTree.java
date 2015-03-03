package tptds;

import java.io.Serializable;

public class TaxaTree implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TaxaTree parent;

	private TaxaTree[] children = new TaxaTree[0];

	public TaxaTree() {
		// Nothing needed.
	}

	public TaxaTree(Object userObject) {
		m_userData = userObject;
	}

	public String getNodelabel() {
		NodeObject n = (NodeObject) m_userData;
		return n.getLabel();

	}

	public void add(TaxaTree child, int index) {
		// Add the child to the list of children.
		if (index < 0 || index == children.length) // then append
		{
			TaxaTree[] newChildren = new TaxaTree[children.length + 1];
			System.arraycopy(children, 0, newChildren, 0, children.length);
			newChildren[children.length] = child;
			children = newChildren;
		} else if (index > children.length) {
			throw new IllegalArgumentException("Cannot add child to index "
					+ index + ".  There are only " + children.length
					+ " children.");
		} else // insert
		{
			TaxaTree[] newChildren = new TaxaTree[children.length + 1];
			if (index > 0) {
				System.arraycopy(children, 0, newChildren, 0, index);
			}
			newChildren[index] = child;
			System.arraycopy(children, index, newChildren, index + 1,
					children.length - index);
			children = newChildren;
		}

		// Set the parent of the child.
		child.parent = this;
	}

	public void add(TaxaTree child) {
		add(child, -1);
	}

	public TaxaTree remove(int index) {
		if (index < 0 || index >= children.length)
			throw new IllegalArgumentException(
					"Cannot remove element with index " + index
							+ " when there are " + children.length
							+ " elements.");

		// Get a handle to the node being removed.
		TaxaTree node = children[index];
		node.parent = null;

		// Remove the child from this node.
		TaxaTree[] newChildren = new TaxaTree[children.length - 1];
		if (index > 0) {
			System.arraycopy(children, 0, newChildren, 0, index);
		}
		if (index != children.length - 1) {
			System.arraycopy(children, index + 1, newChildren, index,
					children.length - index - 1);
		}
		children = newChildren;

		return node;
	}

	public void removeFromParent() {
		if (parent != null) {
			int position = this.index();
			parent.remove(position);
			parent = null;
		}
	}

	public TaxaTree getParent() {
		return parent;
	}

	public boolean isRoot() {
		if (parent == null) {
			return true;
		} else {
			return false;
		}
	}

	public TaxaTree[] children() {
		return children;
	}

	public boolean hasChildren() {
		if (children.length == 0) {
			return false;
		} else {
			return true;
		}
	}

	public int index() {
		if (parent != null) {
			for (int i = 0;; i++) {
				Object node = parent.children[i];

				if (this == node) {
					return i;
				}
			}
		}

		// Only ever make it here if this is the root node.
		return -1;
	}

	private Object m_userData;

	public void setUserObject(Object userObject) {
		m_userData = userObject;
	}

	public Object getUserObject() {
		return m_userData;
	}

	public int getNoChildren() {
		return children.length;
	}

	public void printNode() {
		NodeObject n = (NodeObject) m_userData;
		n.printLabel();

	}

}
