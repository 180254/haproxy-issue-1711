#!/bin/bash

DOCKER_BUILDKIT=1 docker build -t demo-haproxy3:latest -f haproxy3.Dockerfile .
docker run --rm -it --network host demo-haproxy3:latest
