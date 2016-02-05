package com.couchbase.demo.spark.base;

import com.couchbase.demo.spark.Config;
import com.couchbase.spark.japi.CouchbaseSparkContext;
import static com.couchbase.spark.japi.CouchbaseSparkContext.couchbaseContext;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

/**
 *
 * @author David Maier <david.maier at couchbase.com>
 */
public abstract class CouchbaseBaseDemo extends BaseDemo {
    
    /**
     * The Couchbase spark context
     */
    protected CouchbaseSparkContext csc;

    @Override
    public void init() {
       
        cfg = new SparkConf().setMaster(Config.SPARK_CLUSTER).setAppName(getName())
                .set("spark.driver.host", Config.SPARK_DRIVER_HOST)
                .set(Config.CB_BUCKET, Config.CB_PWD)
                .set("com.couchbase.nodes", Config.CB_NODES);
                
        
        ctx = new JavaSparkContext(cfg);
        this.csc = couchbaseContext(ctx);
        
    }

    @Override
    public abstract void run() throws Exception;

    @Override
    public abstract String getName();
}
