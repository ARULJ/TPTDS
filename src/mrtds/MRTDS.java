package mrtds;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.Reducer;

public class MRTDS {

	public static void main(String[] args) {
		JobClient client = new JobClient();
		JobConf conf = new JobConf(mrtds.MRTDS.class);

		// TODO: specify output types
		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);

		// TODO: specify input and output DIRECTORIES (not files)
		conf.setInputPath(new Path("src"));
		conf.setOutputPath(new Path("out"));

		conf.setMapperClass(IGPL_InitilaizationMap.class);

		conf.setReducerClass(IGPL_InitilaizationReduce.class);

		client.setConf(conf);
		try {
			JobClient.runJob(conf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
