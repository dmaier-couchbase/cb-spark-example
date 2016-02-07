package com.couchbase.demo.spark.scala

import org.apache.spark.sql.sources._
import com.couchbase.spark.sql._


class CouchbaseSparkSQLDemo extends Demo {
    
  def demo() {
    
    val ctxs = Contexts.init("CouchbaseSparkSQLDemo")
    val sql = ctxs._2
    
    val airline = sql.read.couchbase(schemaFilter = EqualTo("type", "airline"))
     
    airline
      .select("name", "callsign")
      .sort(airline("callsign").desc)
      .show(10)
    
    Contexts.shutdown(ctxs._1)
  }
}
