#!/bin/bash
column=$1
awk '{print $'$column'}'
