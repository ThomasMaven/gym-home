#!/usr/bin/env bash

oc apply -f config/templates -n gym-home
oc apply -f accounts/templates -n gym-home
oc apply -f accounts/templates-openshift -n gym-home
oc apply -f authorization/templates -n gym-home
oc apply -f exercises/templates -n gym-home
oc apply -f web/templates -n gym-home