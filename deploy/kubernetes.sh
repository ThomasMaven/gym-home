#!/usr/bin/env bash

kubectl apply -f config/templates
kubectl apply -f accounts/templates
kubectl apply -f accounts/templates-kubernetes
kubectl apply -f authorization/templates
kubectl apply -f exercises/templates
kubectl apply -f web/templates