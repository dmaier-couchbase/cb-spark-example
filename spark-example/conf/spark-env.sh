#!/usr/bin/env bash
export SPARK_WORKER_CORES=1
export SPARK_WORKER_MEMORY=1024m
export SPARK_WORKER_INSTANCES=4
export SPARK_CLASSPATH_EXT=/home/couchbase/opt/spark-1.6.0-bin-hadoop2.6/ext
export SPARK_CLASSPATH=$(find "$SPARK_CLASSPATH_EXT" -name '*.jar' | xargs echo | tr ' ' ':')