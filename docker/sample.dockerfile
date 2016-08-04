FROM java:latest

MAINTAINER Yuriy Kozhynov <yuriy@kozhynov.com>

WORKDIR /opt/sample

ENV DEBUG_PORT 5005
ENV PORT 9876
ENV JAVA_OPTS "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"
ENV PATH $WORKDIR:$PATH

# Copy app files to container
COPY ./build/install/sample $WORKDIR

EXPOSE $PORT $DEBUG_PORT

ENTRYPOINT ["bin/sample"]
