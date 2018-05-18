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
        String[] param = new String[] { "--master", "spark://192.9.7.68:7077",
                "--class", "com.tyky.hunan.spark.BuyCatSpark",
//                     zhe                   "--num-executors","8",
//                                        "--driver-memory","2g",
//                                        "executor-memory","25g",
//                                        "--executor-cores","4",
                "/home/titanic/soft/intellij_workspace/tyky-hunanxian-spark/target/tyky.changshaxian.spark-1.0-SNAPSHOT.jar"};

        SparkSubmit.main(param);
    }
}
