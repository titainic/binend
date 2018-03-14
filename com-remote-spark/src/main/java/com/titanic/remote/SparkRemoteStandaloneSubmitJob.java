package com.titanic.remote;

import org.apache.spark.deploy.SparkSubmit;

/**
 * spark远程提交任务到集群Standalone模式
 * https://www.cnblogs.com/zengxiaoliang/p/6508330.html
 */
public class SparkRemoteStandaloneSubmitJob
{


    public static void submitStandaloneJob()
    {
        String[] args = new String[]{"--master", "spark://192.9.7.68:7077",
                "--name", "test java submit job to spark",
                "--class", "com.titanic.remote.WordCount",
                "/opt/soft/submit_jar/com-remote-spark-1.0-SNAPSHOT.jar"};
                //在集群master和tomcat部署机器上面的/opt/soft/submit_jar/com-remote-spark-1.0-SNAPSHOT.jar都要存放这个运行的jar

        SparkSubmit.main(args);
    }
}
