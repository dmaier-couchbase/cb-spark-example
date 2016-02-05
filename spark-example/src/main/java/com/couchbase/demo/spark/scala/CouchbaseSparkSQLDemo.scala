package com.couchbase.demo.spark.scala

import com.couchbase.demo.spark.Config
import org.apache.spark._
import org.apache.spark.sql._
import org.apache.spark.sql.sources._
import com.couchbase.spark.sql._


class CouchbaseSparkSQLDemo  {
    
  def demo() {
      
    val cfg = new SparkConf()
              .setAppName("CouchbaseSparkSQLDemo")
              .setMaster(Config.SPARK_CLUSTER)
              .set("spark.driver.host", Config.SPARK_DRIVER_HOST)
              .set("com.couchbase.nodes", Config.CB_NODES)
              .set(Config.CB_BUCKET, Config.CB_PWD)
    
    val ctx = new SparkContext(cfg)
    val sql = new SQLContext(ctx)
    
    val airline = sql.read.couchbase(schemaFilter = EqualTo("type", "airline"))
    
    airline
      .select("name", "callsign")
      .sort(airline("callsign").desc)
      .show(10)
    
    
    ctx.stop()
  }
}
