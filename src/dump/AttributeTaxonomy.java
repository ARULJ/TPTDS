package dump;

import java.io.*;
import java.lang.*;
import java.util.Scanner;

import edu.msu.cme.rdp.taxatree.Taxon;
import edu.msu.cme.rdp.taxatree.TaxonHolder;
import edu.msu.cme.rdp.taxatree.VisitInfo;
import edu.msu.cme.rdp.taxatree.VisitInfo.VisitType;
import edu.msu.cme.rdp.taxatree.interfaces.TreeVisitor;
import edu.msu.cme.rdp.taxatree.utils.PrintVisitor;

public class AttributeTaxonomy extends Taxon implements TreeVisitor<Taxon> {

	TaxonHolder<Taxon> taxonomy;
	VisitInfo<Taxon> nodeVisit;
	PrintVisitor<Taxon> print_Taxa; 

	public AttributeTaxonomy(Taxon copy) {
		super(copy);
		taxonomy = new TaxonHolder<Taxon>(copy);
	}

	void addChildNode(Taxon node, int parent_id) {
		taxonomy.addChild(node, parent_id);
	}

	public void printTaxa() {
		taxonomy.topDownVisit(this);
		
		nodeVisit = new VisitInfo<Taxon>(taxonomy,null);
	
		
		print_Taxa = new PrintVisitor<Taxon>();
		print_Taxa.visitNode(nodeVisit);
	

		
	}

	public static void main(String[] args) throws IOException {

		int nTaxa;
		int parent_id;
		int child_id;
		String node_label;


		AttributeTaxonomy newTaxonomy = new AttributeTaxonomy(Taxon.ROOT_TAXON);

		Scanner in = new Scanner(System.in);

		do {

			System.out.println("parent id:");
			parent_id = in.nextInt();
			System.out.println("#no of children:");
			nTaxa = in.nextInt();

			for (int i = 0; i < nTaxa; i++) {

				System.out.println("Children #" + i + ": id :");
				child_id = in.nextInt();
				System.out.println("Children #" + i + ": label :");
				node_label = in.next();

				newTaxonomy.addChildNode(new Taxon(child_id, node_label,
						"no rank"), parent_id);
			}

			
			newTaxonomy.printTaxa();
			

			System.out.println("press 0 to exit");
		} while (in.nextInt() != 0);

	}

	@Override
	public boolean visitNode(VisitInfo<Taxon> arg0) {
		// TODO Auto-generated method stub
		return false;
	}
}
