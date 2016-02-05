/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.couchbase.demo.spark.base;

import com.couchbase.demo.spark.Config;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

/**
 *
 * @author David Maier <david.maier at couchbase.com>
 */
public abstract class BaseDemo implements Demo {

    
    protected SparkConf cfg;
    protected JavaSparkContext ctx;
    
    @Override
    public void init() {
      
        cfg = new SparkConf().setMaster(Config.SPARK_CLUSTER).setAppName(getName())
                .set("spark.driver.host", Config.SPARK_DRIVER_HOST);
        
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
