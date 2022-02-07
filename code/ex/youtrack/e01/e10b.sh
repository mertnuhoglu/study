# issue'ları linklemek:
# bir issue'yu başka bir issue'ya depends on olarak set etmek

curl -X POST 'https://youtrack.layermark.com/api/commands' \
  -H "Authorization: Bearer perm:${YOUTRACK_AUTH}" \
  -H 'Accept: application/json' \
	-H 'Content-Type: application/json' \
	-d '{
	"query": "depends on TEST-91",
	"issues": [ { "idReadable": "TEST-94" } ] }'

