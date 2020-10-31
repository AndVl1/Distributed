hadoop fs -rm -f -r output
spark-submit --class ru.bmstu.iu9.distributed.lab3.DelaysAppSpark --master yarn-client --num-executors 3 targer/hadoop-examples-1.0-SNAPSHOT.jar
rm -r output
hadoop fs -copyToLocal output