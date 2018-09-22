#!/usr/bin/env bash

step=$1
commit=$2
docker_user=$3
docker_password=$4
modules=(accounts authorization config exercises web)

build() {
    cd $1
    ./mvnw clean package
    cd ..
}

component_tests() {
    cd $1
    ./mvnw verify -Pcomponent-test
    cd ..
}

build_image() {
    cd $1
    ./mvnw dockerfile:build -Ddockerfile.tag=$2
    cd ..
}

push_image() {
    cd $1
    mvn dockerfile:push -Ddockerfile.username=$3 -Ddockerfile.password=$4
    cd ..
}

iterate() {
    step=$1
    commit=$2
    docker_user=$3
    docker_password=$4
    for i in "${modules[@]}"
    do
        $step $i $commit $docker_user $docker_password
    done
}

iterate $step $commit $docker_user $docker_password