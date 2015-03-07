
package dump;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.aspectj.org.eclipse.jdt.core.dom.ThisExpression;

public class AdultRecordParser {

	private ArrayList<dataRecord> newRecord = new ArrayList<dataRecord>();

	public void parse(int recordID, String line) {
		StringTokenizer st = new StringTokenizer(line, ", ");
		newRecord.add(recordID, new dataRecord(recordID, st));
		newRecord.get(recordID).printRecord();
	}

	void readFromFile(String filename) {
		File file = new File(filename);
		try {

			Scanner sc = new Scanner(file);
			int recordID = 0;

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (!(line.isEmpty())) {
					//System.out.println("read :" + line);
					parse(recordID, line);
					recordID++;
				}

			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {

		System.out.println(args[0]);
		AdultRecordParser recordParser = new AdultRecordParser();
		recordParser.readFromFile(args[0]);

	}
}
