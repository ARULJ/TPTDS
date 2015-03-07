package tptds;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class AnonymizationLevel {

	ArrayList<Specification> AL = new ArrayList<Specification>();
	HashMap<String, TaxaTree> taxaPtrs = new HashMap<String, TaxaTree>();
	ArrayList<String> attrlist;

	DatasetInfo data = new DatasetInfo();

	public AnonymizationLevel() {
		super();
		loadTaxonomy("data");
		initializeAL();
		for(Specification spec : AL){
			spec.printSpec();
		}
	}

	void initializeAL() {
		List<String> valSet;
		Specification [] spec = new Specification[attrlist.size()];
		int i = 0;
		for (String attr : attrlist) {
			TaxaTree taxa = taxaPtrs.get(attr);
			valSet = new ArrayList<String>();
			if (taxa != null) {
				spec[i] = new Specification();
				spec[i].setAttribute(attr);
				spec[i].setParent(taxa.getNodelabel());
				if (taxa.hasChildren()) {
					for (TaxaTree child : taxa.children()) {
						valSet.add(child.getNodelabel());
					}
					spec[i].setChild(valSet);
					AL.add(spec[i]);
					//spec[i].printSpec();
				} else
					System.out.println(taxa.getNodelabel() + " is a leaf");
			}
			i++;
		}

	}


	public HashMap<String, TaxaTree> getTaxaPtrs() {
		return taxaPtrs;
	}

	public ArrayList<String> getTrace(String attr, String sv) {
		ArrayList<String> Trace = new ArrayList<String>();
		TaxaTree node = new TaxaTree();
		node = getNode(taxaPtrs.get(attr), sv);
		getTaxaTrace(node, Trace);
		System.out.println(Trace);
		return Trace;
	}

	public void loadTaxonomy(String DataDir) {
		attrlist = data.getAttrlist(DataDir);
		System.out.println(attrlist);
		for (String attr : attrlist) {
			TaxaTree taxa = readTaxaFromFile(DataDir + "/" + attr);
//			taxa.preOrder(taxa);
			taxaPtrs.put(attr, taxa);
		}
	}

	public void getTaxaTrace(TaxaTree taxa, ArrayList<String> trace) {
		trace.add(taxa.getNodelabel());
		taxa.printNode();
		while (taxa.getParent() != null) {
			taxa = taxa.getParent();
			taxa.printNode();
			trace.add(taxa.getNodelabel());
		}
		Collections.reverse(trace);
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

	private TaxaTree readTaxaFromFile (String filename) {

		// Object deserialization
		try {
			TaxaTree taxon;
			FileInputStream fis = new FileInputStream(filename);
			ObjectInputStream ois = new ObjectInputStream(fis);
			taxon = (TaxaTree) ois.readObject();
			ois.close();
			// System.out.println("taxon read: " + taxon);
			// taxon.printNode();
			return taxon;
		} catch (Exception e)
		{
			System.out.println("Exception during deserialization: " + e);
			System.exit(0);
		}
		return null;
	}

	public static void main(String[] args) throws IOException {
		new AnonymizationLevel();
	}
}
