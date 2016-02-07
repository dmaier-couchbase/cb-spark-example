# Couchbase Spark Example

Apache Spark is used for distributed computation. The data input is expressed as R(esilient) D(istributed) D(ata) sets. An RDD is describing which data should be processed (parallizing collection, retrieving from several sources). The actual data is then processed in RAM by multiple Spark nodes in parallel. Transformation sequences and actions are describing how the RDD-s should be processed. Spark is able to recompute on failure (resilency). There are 3 main components:

* Spark Core: Handle RDD-s from several sources (and to several targets)
* Spark SQL: Handle data frames (RDD-s with a schema) whereby retrieving e.g. from database system
* Spark Streaming: Handle D(iscrete)Streams (RDD-s retrieven step by step as micro batches)

This repository contains a few Spark examples for demoing Couchbase with Spark. The examples are written in Java. Spark also supports Scala and Python.

## Archtiecture

It's expected that you have a Spark cluster up and running. I used the standalone cluster.

* Driver Program: Creates the Spark context, declares the trasnformation and actions on RDD-s of data and sumbits them to the Master 
* Cluster Manager: Aquires executors on worker nodes in the cluster.
* Workers and their Executors: Execute tasks and return results to the driver

![Spark Architecture](https://spark.apache.org/docs/1.1.0/img/cluster-overview.png)

https://spark.apache.org/docs/1.1.0/img/cluster-overview.png

## How to use

### Driver configuration

In my case the driver program is running on my local machine and connects to a VM which is running the master. It was necessary to specify the IP of the host where the driver program was running on because I had multiple networks defined in my demo environment.

* Driver host: 192.168.7.1 (VM network)
* Master host: 192.168.7.191 (VM where master is running on)

These settings can be changed in via the following constants in the Config interface.

```
public static final String SPARK_CLUSTER = "spark://192.168.7.191:7077";
public static final String SPARK_DRIVER_HOST = "192.168.7.1";
```

### Worker configuration

All my workers (Slaves) are also running on the same virtual machine as the master. So it's a single node cluster with 4 workers for testing purposes.

The required dependencies have to be available on the workers as well. I solved it the following way:

* Build the driver program by copying all dependencies to ./target/lib
* Copied the dependencies to the Spark cluster ($SPARK_HOME/ext)
* Extended the Spark classpath in the $SPARK_HOME/conf/spark-env.sh file

So my spark-env.sh is looking as the following one:

```
#!/usr/bin/env bash
export SPARK_WORKER_CORES=1
export SPARK_WORKER_MEMORY=1024m
export SPARK_WORKER_INSTANCES=4
export SPARK_CLASSPATH_EXT=/home/couchbase/opt/spark-1.6.0-bin-hadoop2.6/ext
export SPARK_CLASSPATH=$(find "$SPARK_CLASSPATH_EXT" -name '*.jar' | xargs echo | tr ' ' ':')
```

Optionally it would be possible to use the 'spark-submit' tool in order to submit your application. In this case you can specify a the dependencies via the '--packages' argument or you could submit a 'fat' jar.


### Couchbase configuration

The Couchbase connection settings can be also found in the Config interface:

```
public static final String CB_NODES = "192.168.7.191";
public static final String CB_BUCKET = "com.couchbase.bucket.travel-sample";
public static final String CB_PWD = "";
```

## Examples

* Java: Create context

```Java
@Override
    public void init() {
       
        cfg = new SparkConf().setMaster(Config.SPARK_CLUSTER).setAppName(getName())
                .set("spark.driver.host", Config.SPARK_DRIVER_HOST)
                .set(Config.CB_BUCKET, Config.CB_PWD)
                .set("com.couchbase.nodes", Config.CB_NODES);
                
        ctx = new JavaSparkContext(cfg);
        this.csc = couchbaseContext(ctx);
        
    }
```

* Java: Peform get

```Java
@Override
    public void run() throws Exception {
        
        //BTW: More useful if you process the data before you collect the results
        
        JavaRDD<JsonDocument> rdd = csc.couchbaseGet(Arrays.asList("airline_10226", "airline_10748"));
        List<JsonDocument> docs = rdd.collect();
               
        for (JsonDocument doc : docs) {
            
            System.out.println(doc.toString());
        }
    }
```

* Java: N1QL query

```Java
@Override
    public void run() throws Exception {
                
       JavaRDD<CouchbaseQueryRow> rdd = csc.couchbaseQuery(N1qlQuery.simple("SELECT * FROM `travel-sample` LIMIT 10"));
       
       List<CouchbaseQueryRow> rows = rdd.collect();
       
        for (CouchbaseQueryRow row : rows) {
            
            System.out.println(row.toString());
        }
    }
```
