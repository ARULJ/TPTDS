package tptds;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class DatasetInfo {
	

	public DatasetInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	ArrayList<String> attrlist;

	public ArrayList<String> getAttrlist(String dir) {
		attrlist = readAttrListFromFile(dir+"/AttributeList");
		return attrlist;

	}
	

	public DatasetInfo(String DataDir) {
		super();
		buildAttrTaxa(DataDir);		
	}


	public void buildAttrTaxa(String DataDir) {
		Scanner in = new Scanner(System.in);
		int flag;
		do {
			new Taxonomy(DataDir);
			System.out.println("continue?(enter 0 to exit):");
			flag = in.nextInt();
		} while (flag != 0);
		in.close();
		System.out.println("Exiting..");
	}

	ArrayList<String> readAttrListFromFile(String filename) {
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
			System.out.println("Exception during reading from file:" + e);
			System.exit(0);
		}
		return null;
	}
}
