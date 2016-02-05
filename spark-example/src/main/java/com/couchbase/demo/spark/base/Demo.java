/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.couchbase.demo.spark.base;

/**
 *
 * @author David Maier <david.maier at couchbase.com>
 */
public interface Demo {
   
    
    /**
     * Init the Spark context
     */
    public void init();
    
    /**
     * Run the demo
     * 
     * @throws Exception 
     */
    public void run() throws Exception;
    
    
    /**
     * Shut down the demo
     */
    public void shutdown();
    
    
    /**
     * The name of the demo
     * 
     * @return 
     */
    public String getName();
    
    
}
