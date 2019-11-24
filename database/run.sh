#!/usr/bin/env bash

docker rm -f timetrackdb
docker run -d --name timetrackdb -p 5432:5432 timetrackdb:0.0.1
