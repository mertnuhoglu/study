#!/bin/bash
awk -F '\t' '{print $1}' table.tsv
