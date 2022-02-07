curl -X POST \
https://youtrack.layermark.com/api/issues \
-H 'Accept: application/json' \
-H "Authorization: Bearer perm:${YOUTRACK_AUTH}" \
-H 'Content-Type: application/json' \
-d '{"project":{"id":"0-21"},"summary":"Test Youtrack API POST curt 2"}'



