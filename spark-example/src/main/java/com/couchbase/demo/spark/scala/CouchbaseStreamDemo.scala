package com.couchbase.demo.spark.scala

import com.couchbase.spark.streaming._

/**
 * Experimental
 */
class CouchbaseStreamDemo extends Demo {

  def demo() {
    
    val ctxs = Contexts.init("CouchbaseStreamDemo")
    val ssc = ctxs._3
   
    ssc
      .couchbaseStream()
      .print()
 
    ssc
      .start()
      
    ssc
      .awaitTermination()
    
    Contexts.shutdown(ctxs._1)
    
  }
  
}
