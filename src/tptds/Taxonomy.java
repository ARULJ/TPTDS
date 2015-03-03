package tptds;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Taxonomy {
	TaxaTree taxa;
	static ArrayList<String>  attrList = new ArrayList<String>();
	
	
	public Taxonomy(String fname) {
		super();
		setAttrTaxa();
		printAttrListToFile(fname);
		
	}

	public Taxonomy() {
		super();
		// TODO Auto-generated constructor stub
	}

	void setAttrTaxa() {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		String attr;
		System.out.println("Enter the attribute name:");
		attr = in.next();
		attrList.add(attr);
		System.out.println("Create taxonomy");
		taxa = createTaxonomy();
		printTaxaToFile(attr);
		System.out.println(attrList);
	}

	NodeObject createNodeObject() {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the node label:");
		return (new NodeObject(in.next()));
	}

	TaxaTree createTaxonomy() {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		int nChildren;

		TaxaTree taxon = new TaxaTree(createNodeObject());
		System.out.println("Enter the no. of children:");
		nChildren = in.nextInt();
		if (nChildren == 0) {
			System.out.println("no children, its a leaf node");
		} else {
			System.out.println("Enter " + nChildren + " children");
			for (int i = 0; i < nChildren; i++) {
				System.out.print("children " + (i + 1) + ":");
				TaxaTree newNode = createTaxonomy();
				taxon.add(newNode);
			}

		}
		return taxon;
	}

	public void printTaxaTreeNode(TaxaTree tn) {
		System.out.println();
		if (tn != null) {
			System.out.print("node:");
			tn.printNode();
			if (tn.getParent() != null) {
				System.out.print("parent:");
				tn.printNode();
			}
		}
	}

	private void printTaxonomy(TaxaTree tn) {
		System.out.println();
		if (tn != null) {
			printTaxaTreeNode(tn);

			if (tn.hasChildren()) {
				for (int i = 0; i < tn.children().length; i++) {
					printTaxonomy(tn.children()[i]);
				}
			} else
				System.out.println("its a leaf node");
		}
	}

	public void printTaxa() {
		printTaxonomy(taxa);
	}

	void printTaxaToFile(String filename) {

		try {
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(taxa);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			System.out.println("Exception during serialization: " + e);
			System.exit(0);
		}
	}

	void preOrder(TaxaTree node) {
		if (node == null)
			return;

		node.printNode();
		if (node.hasChildren()) {
			for (int i = 0; i < node.getNoChildren(); i++) {
				preOrder(node.children()[i]);
			}
		}
	}
	
	void printAttrListToFile(String filename) {
	
		try {
			FileWriter fout = new FileWriter(filename);
			for(String attr : attrList )
			 fout.write(attr+"\n");
			fout.close();
			
		} catch (IOException e) {
			System.out.println("Exception during writing file: " + e);
			System.exit(0);
		}

}
}
