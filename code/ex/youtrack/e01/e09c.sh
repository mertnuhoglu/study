# Update custom fields: `Assignee`, `Estimation` ve `Requirement ID` alanlarını update edelim:
curl -X POST \
	'https://youtrack.layermark.com/api/issues/TEST-91?fields=customFields(id,name)' \
  -H "Authorization: Bearer perm:${YOUTRACK_AUTH}" \
  -H 'Accept: application/json' \
	-H 'Content-Type: application/json' \
	-d '{
	"customFields": [
	{"name":"Assignee","$type":"SingleUserIssueCustomField","value":{"login":"BE1"}},
	{"name":"Estimation","$type":"PeriodIssueCustomField","value":{"minutes":1440}},
	{"name":"Type","$type":"SingleEnumIssueCustomField","value":{"name":"Requirement Implementation"}},
	{"name":"Requirement ID","$type":"SimpleIssueCustomField","value":25}
	] }'
