
package com.couchbase.demo.spark;

import com.couchbase.client.java.document.JsonDocument;
import static com.couchbase.demo.spark.BaseDemo.SPARK_CLUSTER;
import com.couchbase.spark.japi.CouchbaseSparkContext;
import static com.couchbase.spark.japi.CouchbaseSparkContext.couchbaseContext;
import java.util.Arrays;
import java.util.List;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

/**
 *
 * @author David Maier <david.maier at couchbase.com>
 */
public class CouchbaseGetDemo extends BaseDemo {
    
   
    /**
     * The Couchbase spark context
     */
    private CouchbaseSparkContext csc;
    
    
    @Override
    public void init() {
        
        
        cfg = new SparkConf().setMaster(SPARK_CLUSTER).setAppName(getName())
                .set("spark.driver.host", "192.168.7.1")
                .set("com.couchbase.bucket.travel-sample", "")
                .set("com.couchbase.nodes", "192.168.7.191");
                
        
        ctx = new JavaSparkContext(cfg);
        
   
        this.csc = couchbaseContext(ctx);
    }
    
   
    
    @Override
    public void run() throws Exception {
        
        //BTW: More useful if you process the data before you collect the results
        JavaRDD<JsonDocument> rdd = csc.couchbaseGet(Arrays.asList("airline_10226", "airline_10748"));
        List<JsonDocument> docs = rdd.collect();
        
        for (JsonDocument doc : docs) {
            
            System.out.println(doc.toString());
        }
    }

    @Override
    public String getName() {

        return "CouchbaseGetDemo";
    }
    
    
    
}
