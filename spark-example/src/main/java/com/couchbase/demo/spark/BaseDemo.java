/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.couchbase.demo.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

/**
 *
 * @author David Maier <david.maier at couchbase.com>
 */
public abstract class BaseDemo implements Demo {

    //Environment
    public final static String SPARK_CLUSTER = "spark://192.168.7.191:7077"; 
    
    protected SparkConf cfg;
    protected JavaSparkContext ctx;
    
    @Override
    public void init() {
      
        cfg = new SparkConf().setMaster(SPARK_CLUSTER).setAppName(getName())
                .set("spark.driver.host", "192.168.7.1");
        
        ctx = new JavaSparkContext(cfg);
    }

    @Override
    public abstract void run() throws Exception;

    
    
    @Override
    public void shutdown() {
        
        this.ctx.close();
    }
    
    
    @Override
    public abstract String getName();
    
}
