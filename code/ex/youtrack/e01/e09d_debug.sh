# debug Error01 in e17.md
# Update custom fields: `Assignee`, `Estimation` ve `Requirement ID` alanlarını update edelim:
curl -X POST \
	'https://youtrack.layermark.com/api/issues/DMB-238?fields=customFields(id,name)' \
  -H "Authorization: Bearer perm:${YOUTRACK_AUTH}" \
  -H 'Accept: application/json' \
	-H 'Content-Type: application/json' \
	-d '{"customFields":[{"name":"Estimation","$type":"PeriodIssueCustomField","value":{"minutes":9600}}]}'
