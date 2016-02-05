package com.couchbase.demo.spark;

/**
 *
 * @author David Maier <david.maier at couchbase.com>
 */
public interface Config {
    
    //Spark Config
    public static final String SPARK_CLUSTER = "spark://192.168.7.191:7077";
    public static final String SPARK_DRIVER_HOST = "192.168.7.1";
    
    //Couchbase Config
    public static final String CB_NODES = "192.168.7.191";
    public static final String CB_BUCKET = "com.couchbase.bucket.travel-sample";
    public static final String CB_PWD = "";

}
