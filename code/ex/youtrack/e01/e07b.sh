# Tüm issue'ların listesini çeker ve ayrıca tüm issue'ların customField'larını da indirir.
curl -X GET \
	'https://youtrack.layermark.com/api/issues?query=in:test&fields=id,idReadable,summary,customFields(id,projectCustomField(field(name)))' \
  -H 'Accept: application/json' \
	-H 'Content-Type: application/json' \
  -H "Authorization: Bearer perm:${YOUTRACK_AUTH}"

