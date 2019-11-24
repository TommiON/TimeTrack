FROM library/postgres:9.6.1

ENV POSTGRES_USER timetrack
ENV POSTGRES_PASSWORD timetrack
ENV POSTGRES_DB timetrack

ADD init_database.sql /docker-entrypoint-initdb.d/
