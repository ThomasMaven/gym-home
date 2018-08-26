#!/usr/bin/env bash

docker build ./accounts/ -t accounts
docker build ./authorization/ -t authorization
docker build ./config/ -t config
docker build ./eureka/ -t eureka
docker build ./web/ -t web
docker build ./exercises/ -t exercises
