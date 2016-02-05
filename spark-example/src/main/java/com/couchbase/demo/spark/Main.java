package com.couchbase.demo.spark;


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
        simple.init();
        simple.run();
        simple.shutdown();
        
        CouchbaseGetDemo get = new CouchbaseGetDemo();
        get.init();
        get.run();
        get.shutdown();
        
        CouchbaseN1QLDemo n1ql = new CouchbaseN1QLDemo();
        n1ql.init();
        n1ql.run();
        n1ql.shutdown();
        
    }
}
