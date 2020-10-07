package ru.bmstu.iu9.destributed.lab2;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class PlaneDelaysApp {
    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            System.err.println("Usage: ru.bmstu.iu9.destributed.lab2.PlaneDelaysApp <input path> <output path>");
            System.exit(-1);
        }

        Job job = Job.getInstance();
        job.setJarByClass(PlaneDelaysApp.class);
        job.setJobName("Plane delays");
        job.getConfiguration().set("mapreduce.output.textoutputformat.separator", ",");
        MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class, AirportMapper.class);
        MultipleInputs.addInputPath(job, new Path(args[1]), TextInputFormat.class, ArrivalMapper.class);
        // Test
        FileOutputFormat.setOutputPath(job, new Path(args[2]));
        job.setReducerClass(DelaysReducer.class);

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
