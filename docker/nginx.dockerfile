FROM nginx:latest

MAINTAINER Yuriy Kozhynov <yuriy@kozhynov.com>

# Copy custom nginx config and certificates
COPY ./docker/config/nginx/ /etc/nginx/

# Copy static files
COPY ./src/main/resources/public/ /usr/share/nginx/html/

# Can be mapped to host directory if needed
VOLUME /usr/share/nginx/html

EXPOSE 80 443

ENTRYPOINT ["nginx"]
CMD ["-g", "daemon off;"]