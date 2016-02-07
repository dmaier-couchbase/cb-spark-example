package com.couchbase.demo.spark.scala

import com.couchbase.demo.spark.Config
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.streaming._




object Contexts {
  
  /**
   * Init all required contexts
   */
  def init(name : String) : (SparkContext, SQLContext, StreamingContext) = {
    
    //The config to use
    val cfg = new SparkConf()
              .setAppName(name)
              .setMaster(Config.SPARK_CLUSTER)
              .set("spark.driver.host", Config.SPARK_DRIVER_HOST)
              .set("com.couchbase.nodes", Config.CB_NODES)
              .set(Config.CB_BUCKET, Config.CB_PWD)
    
    val ctx = new SparkContext(cfg)
    val sql = new SQLContext(ctx)
    val ssc = new StreamingContext(ctx,  Seconds(1))

    return (ctx, sql, ssc);
  }
  
  /**
   * Shutdown a context
   */
  def shutdown(ctx : SparkContext) = {
    
    ctx.stop()
    
  }  
  
}
