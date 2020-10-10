package ru.bmstu.iu9.distributed.lab2.mapper;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import ru.bmstu.iu9.distributed.lab2.writable.Key;
import java.io.IOException;

public class AirportMapper extends Mapper<LongWritable, Text, Key, Text> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException,
            InterruptedException {
        Text description = new Text();

        String[] lines = value.toString().split("\n");

        for(String line : lines) {
            int firstComma = 0;
            for (char symbol: line.toCharArray()){
                if (symbol == ','){
                    break;
                }
                firstComma++;
            }
            int code;
            try {
                String codeString = line
                        .substring(0, firstComma)
                        .replace('"', '\0');
                System.out.println(codeString);
                code = Integer.parseInt(codeString);
            } catch (Exception e){
                // This is header, so we must just continue to next line
                e.printStackTrace();
                continue;
            }
            String descriptionString = line.substring(firstComma).replace('"', '\0');
            System.out.println(descriptionString + " " + code);
            description.set(
                    line.substring(firstComma).replace('"', '\0')
            );
            context.write(new Key(code, AIRPORT_KEY), description);
        }
    }

    private final static int AIRPORT_KEY = 0;
}
