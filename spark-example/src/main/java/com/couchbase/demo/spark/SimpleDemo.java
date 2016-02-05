package com.couchbase.demo.spark;

import com.couchbase.demo.spark.helper.Helper;
import com.couchbase.demo.spark.base.BaseDemo;
import java.util.Arrays;
import java.util.List;
import org.apache.spark.api.java.JavaRDD;

/**
 * Just using Spark on a CSV file
 * 
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class SimpleDemo extends BaseDemo {
    
    public static final String FILE_INPUT="https://raw.githubusercontent.com/dmaier-couchbase/cb-spark-example/master/spark-example/src/main/java/simpledemo.csv";
    
    
    @Override
    public void run() throws Exception {

       
       List<String> input = Arrays.asList(Helper.wget(FILE_INPUT).toString().split("\n"));
  
       //BTW: For text files better use 
       //JavaRDD<String> lines =  ctx.textFile(tempFile);
       
       JavaRDD<String> lines =  ctx.parallelize(input);
       long count = lines.count();
       
       System.out.println("count = " + count);
       
       
       ctx.close();
    }

    @Override
    public String getName() {
      
       return "SimpleDemo";
    }
    
}
