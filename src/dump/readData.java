package test;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class readData{

	static class dataRecord{
		int a_age;
		String b_workclass; 
		long c_fnlwgt;
		String d_education; 
		String e_marital_status;
		String f_occupation;
		String g_relationship;
		String h_race;
		String i_sex;
		int j_capital_gain;
		int k_capital_loss;
		int l_hours_per_week;
		String m_native_country;
		String n_wgt_class;
	}
	
	void readFromFile(String input_file){
		File file = new File(input_file);
		//dataRecord [][] record = new dataRecord[][];
		int count = 0;
		
		try {
		    FileReader reader = new FileReader(file);
		    BufferedReader buffReader = new BufferedReader(reader);
		  
		    StringTokenizer tokens;
		    String line;
		    while((line = buffReader.readLine()) != null){
		    	
		    	//System.out.println("---- Split by comma ',' ------");
				tokens = new StringTokenizer(line, ",");
				//record[count] = assignValues(tokens);
				count++;
		    }
		    
				
		 }
		  catch(IOException e){
		    System.exit(0);
		}
	}
	

	dataRecord assignValues(StringTokenizer tokens){
		dataRecord dr = new dataRecord();
	
		dr.a_age = Integer.parseInt(tokens.nextToken());
		dr.b_workclass = tokens.nextToken();
		dr.c_fnlwgt= Integer.parseInt(tokens.nextToken());
		dr.d_education = tokens.nextToken();
		dr.e_marital_status = tokens.nextToken();
		dr.f_occupation = tokens.nextToken();
		dr.g_relationship = tokens.nextToken();
		dr.h_race = tokens.nextToken();
		dr.i_sex = tokens.nextToken();
		dr.j_capital_gain=Integer.parseInt(tokens.nextToken());
		dr.k_capital_loss=Integer.parseInt(tokens.nextToken());
		dr.l_hours_per_week = Integer.parseInt(tokens.nextToken());
		dr.m_native_country = tokens.nextToken();		
		dr.n_wgt_class = tokens.nextToken();
		
		return dr;
	}
}