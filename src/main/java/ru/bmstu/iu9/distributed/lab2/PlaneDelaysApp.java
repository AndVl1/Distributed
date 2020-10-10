package ru.bmstu.iu9.distributed.lab2;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import ru.bmstu.iu9.distributed.lab2.comparator.GroupingComparator;
import ru.bmstu.iu9.distributed.lab2.mapper.AirportMapper;
import ru.bmstu.iu9.distributed.lab2.mapper.ArrivalMapper;
import ru.bmstu.iu9.distributed.lab2.partitioner.DataPartitioner;
import ru.bmstu.iu9.distributed.lab2.reducer.DelaysReducer;
import ru.bmstu.iu9.distributed.lab2.writable.Key;

public class PlaneDelaysApp {
    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            System.err.println("Usage: ru.bmstu.iu9.distributed.lab2.PlaneDelaysApp <airports path> <delays path> <output path>");
            System.exit(-1);
        }

        Job job = Job.getInstance();
        job.setJarByClass(PlaneDelaysApp.class);
        job.setJobName("Plane delays");
        MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class, AirportMapper.class);
        MultipleInputs.addInputPath(job, new Path(args[1]), TextInputFormat.class, ArrivalMapper.class);
        FileOutputFormat.setOutputPath(job, new Path(args[2]));

//        job.setPartitionerClass(DataPartitioner.class);
        job.setReducerClass(DelaysReducer.class);
        job.setMapOutputKeyClass(Key.class);
//        job.setGroupingComparatorClass(GroupingComparator.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setNumReduceTasks(2);
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
