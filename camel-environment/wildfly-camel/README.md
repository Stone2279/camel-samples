## What it does
Uses the stone/wildfly-prometheus base image and adds configuration.
Base image already contains a Prometheus jmx_exporter agent library for exposing metrics
to Prometheus. Also Hawtio as JMX-console is embedded.

## How to start
`docker-compose up`

This will start a Wildfly-Camel server and a Postgres DB.

## Ports
Wildfly will be started with default ports:

http://localhost:8080

### HawtIo console:

http://localhost:8080/hawtio

Default credentials: admin/admin

### JBoss-Managment :

http://localhost:9990

Default credentials: admin/admin

### Prometheus metrics:

http://localhost:9393/metrics

## Build the image
See/Execute the build.bat file
