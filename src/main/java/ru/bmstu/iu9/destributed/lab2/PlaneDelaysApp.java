package ru.bmstu.iu9.destributed.lab2;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class PlaneDelaysApp {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: ru.bmstu.iu9.destributed.lab2.PlaneDelaysApp <input path> <output path>");
            System.exit(-1);
        }

        Job job = Job.getInstance();
        job.setJarByClass(PlaneDelaysApp.class);
        job.setJobName("Plane delays");
        job.getConfiguration().set("mapreduce.output.textoutputformat.separator", ",");
        TextOutputFormat
        MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class, AirportMapper.class);
        MultipleInputs.addInputPath(job, new Path(args[1]), TextInputFormat.class, ArrivalMapper.class);

        FileOutputFormat.setOutputPath(job, new Path(args[1]));

    }
}
