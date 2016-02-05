package com.couchbase.demo.spark;

import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.demo.spark.base.CouchbaseBaseDemo;
import com.couchbase.spark.rdd.CouchbaseQueryRow;
import java.util.List;
import org.apache.spark.api.java.JavaRDD;

/**
 *
 * @author David Maier <david.maier at couchbase.com>
 */
public class CouchbaseN1QLDemo extends CouchbaseBaseDemo {

    @Override
    public void run() throws Exception {
        
       JavaRDD<CouchbaseQueryRow> rdd = csc.couchbaseQuery(N1qlQuery.simple("SELECT * FROM `travel-sample` LIMIT 10"));
       
       List<CouchbaseQueryRow> rows = rdd.collect();
       
        for (CouchbaseQueryRow row : rows) {
            
            System.out.println(row.toString());
        }
    }

    @Override
    public String getName() {
      
        return "CouchbaseN1QLDemo";
    }
    
}
