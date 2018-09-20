#!/usr/bin/env bash

oc create -f config/templates -n gym-home
oc create -f accounts/templates -n gym-home
oc create -f authorization/templates -n gym-home
oc create -f exercises/templates -n gym-home
oc create -f web/templates -n gym-home