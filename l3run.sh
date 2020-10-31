hadoop fs -rm -f -r output
rm -r output
sudo spark-submit --class ru.bmstu.iu9.distributed.lab3.DelaysAppSpark --master yarn-client --num-executors 3 target/distr-1.jar
hadoop fs -copyToLocal output