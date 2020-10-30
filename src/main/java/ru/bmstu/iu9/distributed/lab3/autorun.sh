hadoop fs 

spark-submit --class ru.bmstu.ui9.distributed.lab3.DelaysAppSpark --master yarn-client --num-executors 3 targer/hadoop-examples-1.0-SNAPSHOT.jar

rm -r output

hadoop fs -copyToLocal output