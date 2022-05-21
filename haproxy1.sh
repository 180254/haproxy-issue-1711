#!/bin/bash

DOCKER_BUILDKIT=1 docker build -t demo-haproxy1:latest -f haproxy1.Dockerfile .
docker run --rm -it --network host demo-haproxy1:latest
