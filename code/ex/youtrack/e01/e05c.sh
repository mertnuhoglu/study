curl -X POST \
https://youtrack.layermark.com/api/issues \
-H 'Accept: application/json' \
-H "Authorization: Bearer perm:${YOUTRACK_AUTH}" \
-H 'Content-Type: application/json' \
-d '{
"project":{"id":"0-7"},
"summary":"REST API test 01",
"description":"Testing REST API from curl"
}'


