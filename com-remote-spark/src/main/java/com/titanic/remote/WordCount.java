package com.titanic.remote;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WordCount
{
    public static void main(String[] args)
    {
        SparkConf sc = new SparkConf();
        sc.setAppName("word_count");

        JavaSparkContext jsc = new JavaSparkContext(sc);
        JavaRDD<String> textFile = jsc.textFile("hdfs://192.9.7.68:8020/tmp/README.md");

        JavaRDD<String> wordRDD =  textFile.flatMap(new FlatMapFunction<String, String>()
        {
            public Iterator<String> call(String s) throws Exception
            {
                List<String> list = new ArrayList<String>();
                String[] arrStr = s.split("  ");
                for (int i = 0; i < arrStr.length; i++)
                {
                    list.add(arrStr[i]);
                }
                return list.iterator();
            }
        });

        JavaPairRDD<String,Integer> pairRDD =  wordRDD.mapToPair(new PairFunction<String, String, Integer>()
        {
            public Tuple2<String, Integer> call(String s) throws Exception
            {
                Tuple2<String, Integer> tuple2 = new Tuple2<String, Integer>(s,1);
                return tuple2;
            }
        });

        JavaPairRDD<String,Integer> countRDD =  pairRDD.reduceByKey(new Function2<Integer, Integer, Integer>()
        {
            public Integer call(Integer integer, Integer integer2) throws Exception
            {
                return integer + integer2;
            }
        });

        System.out.println(countRDD.collectAsMap());
        jsc.stop();
    }
}
