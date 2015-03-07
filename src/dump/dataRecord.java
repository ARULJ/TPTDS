package dump;

import java.util.StringTokenizer;

public class dataRecord {
	int RecordID;
	int a_age;
	String b_workclass;
	long c_fnlwgt;
	String d_education;
	int e_education_num;
	String f_marital_status;
	String g_occupation;
	String h_relationship;
	String i_race;
	String j_Gender;
	int k_capital_gain;
	int l_capital_loss;
	int m_hours_per_week;
	String n_native_country;
	String o_classes;

	dataRecord(int recordID, StringTokenizer st) {
		this.RecordID = recordID;
		this.a_age = Integer.parseInt(st.nextToken());
		this.b_workclass = st.nextToken();
		this.c_fnlwgt = Long.valueOf(st.nextToken());
		this.d_education = st.nextToken();
		this.e_education_num = Integer.parseInt(st.nextToken());
		this.f_marital_status = st.nextToken();
		this.g_occupation = st.nextToken();
		this.h_relationship = st.nextToken();
		this.i_race = st.nextToken();
		this.j_sex = st.nextToken();
		this.k_capital_gain = Integer.parseInt(st.nextToken());
		this.l_capital_loss = Integer.parseInt(st.nextToken());
		this.m_hours_per_week = Integer.parseInt(st.nextToken());
		this.n_native_country = st.nextToken();
		this.o_classes = st.nextToken();

	}

	void printRecord() {
		System.out.print("\n" + this.RecordID + "\t");
		System.out.print(this.a_age + "\t");
		System.out.print(this.b_workclass + "\t");
		System.out.print(this.c_fnlwgt + "\t");
		System.out.print(this.d_education + "\t");
		System.out.print(this.e_education_num + "\t");
		System.out.print(this.f_marital_status + "\t");
		System.out.print(this.g_occupation + "\t");
		System.out.print(this.h_relationship + "\t");
		System.out.print(this.i_race + "\t");
		System.out.print(this.j_sex + "\t");
		System.out.print(this.k_capital_gain + "\t");
		System.out.print(this.l_capital_loss + "\t");
		System.out.print(this.m_hours_per_week + "\t");
		System.out.print(this.n_native_country + "\t");
		System.out.print(this.o_classes + "\n");

	}

	public int getRecordID() {
		return this.RecordID;
	}
}
