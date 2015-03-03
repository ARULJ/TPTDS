package tptds;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class DatasetInfo {
	

	ArrayList<String> attrlist;

	public ArrayList<String> getAttrlist() {
		return attrlist;
	}
	

	public DatasetInfo(String AttrFile) {
		super();
		buildAttrTaxa(AttrFile);
	    attrlist = readAttrListFromFile(AttrFile);
		System.out.print(attrlist);	
	}


	public void buildAttrTaxa(String AttrFile) {
		Scanner in = new Scanner(System.in);
		int flag;
		do {
			new Taxonomy(AttrFile);
			System.out.println("continue?(enter 0 to exit):");
			flag = in.nextInt();
		} while (flag != 0);
		in.close();
		System.out.println("Exiting..");
	}

	TaxaTree readTaxaFromFile(String filename) {

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

	ArrayList<String> readAttrListFromFile(String filename) {

		// Object deserialization
		try {
			ArrayList<String> attrlist = new ArrayList<String>();
			FileReader fin = new FileReader(filename);
			Scanner src = new Scanner(fin);
			while(src.hasNext()){
				attrlist.add(src.nextLine());
			}
			src.close();
			return attrlist;
		} catch (Exception e) {
			System.out.println("Exception during deserialization: " + e);
			System.exit(0);
		}
		return null;
	}

	/*public static void main(String[] args) throws IOException {
		new AttributeData(args[0]);
	}*/
}
