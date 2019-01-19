FROM wildflyext/wildfly-camel:8.0.0
RUN curl http://central.maven.org/maven2/io/prometheus/jmx/jmx_prometheus_javaagent/0.10/jmx_prometheus_javaagent-0.10.jar -o /wildfly/bin/jmx_prometheus_javaagent-0.10.jar
COPY standalone.conf /wildfly/bin/
COPY prometheus-config.yaml /wildfly/standalone/configuration/