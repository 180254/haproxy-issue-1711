#!/bin/bash

DOCKER_BUILDKIT=1 docker build -t demo-server:latest -f server.Dockerfile .
docker run --rm -it --network host demo-server:latest
