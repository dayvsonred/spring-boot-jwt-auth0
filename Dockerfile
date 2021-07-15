FROM mysql:5.7.15

ARG database
ARG password

RUN echo ${database}
RUN echo ${password}

MAINTAINER me
ENV MYSQL_DATABASE=${database} \
    MYSQL_ROOT_PASSWORD=${password}
ADD ./db/database100.sql /docker-entrypoint-initdb.d
EXPOSE 3306