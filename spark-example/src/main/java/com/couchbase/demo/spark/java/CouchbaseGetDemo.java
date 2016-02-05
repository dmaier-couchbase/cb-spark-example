
package com.couchbase.demo.spark.java;

import com.couchbase.client.java.document.JsonDocument;
import java.util.Arrays;
import java.util.List;
import org.apache.spark.api.java.JavaRDD;

/**
 *
 * @author David Maier <david.maier at couchbase.com>
 */
public class CouchbaseGetDemo extends CouchbaseBaseDemo {
    
    
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
