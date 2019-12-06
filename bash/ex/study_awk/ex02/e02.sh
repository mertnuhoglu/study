#!/bin/bash
awk -F '\t' -v tags="exam" '{print $1, tags}' table.tsv
