# Couchbase Spark Example

These are a few Spark examples for demoing Couchbase with Spark.

## Archtiecture

* Driver Program: Creates the Spark context, declares the trasnformation and actions on RDD-s of data and sumbits them to the Master 
* Cluster Manager: Aquires executors on worker nodes in the cluster.
* Worker Nodes
  * Executors: Execute tasks and return results to the driver

![Spark Architecture](https://spark.apache.org/docs/1.1.0/img/cluster-overview.png)

https://spark.apache.org/docs/1.1.0/img/cluster-overview.png

## How to use

It's expected that you have a Spark cluster up and running. I used the standalone cluster with the default configuration.

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

TODO


