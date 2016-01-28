package com.couchbase.demo.spark;

import org.apache.spark.api.java.JavaRDD;

/**
 * Just using Spark without any relationship to Couchbase
 * 
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class SimpleDemo extends BaseDemo {
    
    @Override
    public void run() throws Exception {
      
       init();
              
       String inputFile = "/tmp/in.txt";
       
       ctx.textFile(inputFile);
      
       JavaRDD<String> lines =  ctx.textFile(inputFile);
       long count = lines.count();
       
       System.out.println("count = " + count);
       
       ctx.close();
       
    }

    @Override
    public String getName() {
      
       return "SimpleDemo";
    }
    
}
