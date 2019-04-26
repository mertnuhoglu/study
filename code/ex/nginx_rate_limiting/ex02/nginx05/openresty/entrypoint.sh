#!/bin/sh
set -e

exec /usr/local/openresty/bin/openresty -g "daemon off; error_log /dev/stderr info;"
