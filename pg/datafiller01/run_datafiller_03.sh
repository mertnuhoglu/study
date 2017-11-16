#!/bin/sh
datafiller --size=10 library03.sql > library_test_data03.sql
datafiller --size=10 --filter library03.sql | psql library

