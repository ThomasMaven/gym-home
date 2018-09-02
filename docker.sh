#!/usr/bin/env bash

push_service() {
    name=$1
    docker build ./$name/ -t $name
    docker tag $name jpiecuch/$name:1.0
    docker push jpiecuch/$name:1.0
}

push_service accounts
push_service authorization
push_service config
push_service web
push_service exercises
