package tptds;

import tptds.TaxaTree;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AnonymizationLevel {
	
	HashMap<String,String> AL = new HashMap<String,String>();
	HashMap<String,TaxaTree> taxaPtrs = new HashMap<String,TaxaTree>();
	
	

	public AnonymizationLevel() {
		super();

		Scanner in = new Scanner(System.in);
		ArrayList<String> Trace = new ArrayList<String>();
		System.out.println("Enter taxonomy file to be altered:");
		alterTaxa(in.next());
		System.out.println("Enter attr name:");
		String fname = in.next();
		System.out.println("Enter sensitive value:");
		String sv = in.next();

		TaxaTree node = new TaxaTree();
		TaxaTree taxa = readTaxaFromFile(fname);

		node = getNode(taxa, sv);
		getTrace(node, Trace);
		System.out.println(Trace);
	}

	public void loadTaxonomy(){
		
	}
	public void alterTaxa(String fname){
		new DatasetInfo(fname);
	}
	
	public void getTrace(TaxaTree taxa, ArrayList<String> trace) {
		trace.add(taxa.getNodelabel());
		taxa.printNode();
		while (taxa.getParent() != null) {
			taxa = taxa.getParent();
			taxa.printNode();
			trace.add(taxa.getNodelabel());
		}
	}

	private TaxaTree getNode(TaxaTree taxa, String label) {
		if (taxa != null) {
			if (taxa.getNodelabel().equals(label)) {
				return taxa;
			} else {
				if (taxa.hasChildren()) {
					TaxaTree nodefound = null;
					for (int i = 0; nodefound == null
							&& i < taxa.getNoChildren(); i++) {
						nodefound = getNode(taxa.children()[i], label);
					}
					return nodefound;
				}
			}
		}
		return null;
	}

	private TaxaTree readTaxaFromFile(String filename) {

		// Object deserialization
		try {
			TaxaTree taxon;
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fis);
			taxon = (TaxaTree) ois.readObject();
			ois.close();
			System.out.println("taxon read: " + taxon);
			taxon.printNode();
			return taxon;
		} catch (Exception e) {
			System.out.println("Exception during deserialization: " + e);
			System.exit(0);
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		new AnonymizationLevel();
	}
}
