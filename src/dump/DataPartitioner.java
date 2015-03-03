package alt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class DataPartitioner {

	static int partition_parameter;

	public static class PartitionMapper extends
			Mapper<LongWritable, Text, IntWritable, Text> {

		public void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {
			Random genRand = new Random();
			int rand = genRand.nextInt(partition_parameter) + 1;
			context.write(new IntWritable(rand), value);
		}
	}

	public static class PartitionReducer extends
			Reducer<IntWritable, Text, NullWritable, Text> {

		public void reduce(IntWritable key, Iterable<Text> values,
				Context context) throws IOException, InterruptedException {

			ArrayList<Text> list = new ArrayList<Text>();
			for (Text val : values) {
				list.add(new Text(val+"\n"));
			}

			context.write(NullWritable.get(), new Text(list.toString()));
		}

	}

	/*
	 * public class TextArrayWritable extends ArrayWritable { public
	 * TextArrayWritable() { super(Text.class); } }
	 */
	/**
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws IOException,
			ClassNotFoundException, InterruptedException {

		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, args)
				.getRemainingArgs(); // get all args
		if (otherArgs.length != 3) {
			System.err
					.println("Usage: DataPartitoner <in> <out> <partition_parameter>");
			System.exit(-1);
		}

		partition_parameter = Integer.parseInt(otherArgs[2]);
		Job job = new Job(conf, "DataPartitioner");
		job.setJarByClass(DataPartitioner.class);
		job.setMapperClass(PartitionMapper.class);
		job.setReducerClass(PartitionReducer.class);
		
		// set map output key type
		job.setMapOutputKeyClass(IntWritable.class);
		// set map output value type
		job.setMapOutputValueClass(Text.class);
		// set output key type
		job.setOutputKeyClass(NullWritable.class);
		// set output value type
		job.setOutputValueClass(Text.class);
		// set the HDFS path of the input data
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		// set the HDFS path for the output
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));

		// Wait till job completion
		System.exit(job.waitForCompletion(true) ? 0 : 1);

	}

}

