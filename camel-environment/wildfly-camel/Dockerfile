FROM stone/wildfly-prometheus:1.0.0

# add wildfly default user
RUN /wildfly/bin/add-user.sh admin admin --silent

# provide settings
COPY standalone-full-camel.xml /wildfly/standalone/configuration/
COPY application-roles.properties /wildfly/standalone/configuration/
COPY application-users.properties /wildfly/standalone/configuration/
COPY prometheus-config.yaml /wildfly/standalone/configuration/

# create dirs that will be mounted and set privileges
RUN mkdir /wildfly/standalone/data
RUN mkdir /wildfly/standalone/data/content
RUN mkdir /wildfly/standalone/data/activemq
RUN chown default:root /wildfly/standalone/configuration
RUN chown default:root /wildfly/standalone/deployments

# add additional drivers and modules
COPY --chown=default:root modules/org/postgresql /wildfly/modules/system/layers/base/org/postgresql
RUN curl http://central.maven.org/maven2/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar -o /wildfly/modules/system/layers/base/org/postgresql/main/postgresql.jar

# start command
CMD ["/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0", "-c", "standalone-full-camel.xml"]