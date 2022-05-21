#!/bin/bash

DOCKER_BUILDKIT=1 docker build -t demo-haproxy2:latest -f haproxy2.Dockerfile .
docker run --rm -it --network host demo-haproxy2:latest
