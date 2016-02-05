package com.couchbase.demo.spark;

import com.couchbase.demo.spark.java.CouchbaseGetDemo;
import com.couchbase.demo.spark.java.CouchbaseN1QLDemo;
import com.couchbase.demo.spark.java.SimpleDemo;
import com.couchbase.demo.spark.scala.CouchbaseSparkSQLDemo;


/**
 * Entry point of the application
 * 
 * 
 * 
 * @author David Maier <david.maier at couchbase.com>
 */
public class Main {
    
    public static void main(String[] args) throws Exception {
        
        
        SimpleDemo simple = new SimpleDemo();
        simple.demo();
        
        CouchbaseGetDemo get = new CouchbaseGetDemo();
        get.demo();
        
        CouchbaseN1QLDemo n1ql = new CouchbaseN1QLDemo();
        n1ql.demo();
        
        CouchbaseSparkSQLDemo sqlDemo = new CouchbaseSparkSQLDemo();
        sqlDemo.demo();
        
    }
}
