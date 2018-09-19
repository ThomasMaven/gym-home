#!/usr/bin/env bash

oc create -f config/templates
oc create -f accounts/templates
oc create -f authorization/templates
oc create -f exercises/templates
oc create -f web/templates