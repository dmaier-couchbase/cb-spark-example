package com.couchbase.demo.spark;
/**
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
        
    }
}
