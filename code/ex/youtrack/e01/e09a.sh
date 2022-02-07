# Update custom fields: `Assignee` alanını update edelim sadece:
curl -X POST \
	'https://youtrack.layermark.com/api/issues/TEST-91?fields=customFields(id,name)' \
  -H "Authorization: Bearer perm:${YOUTRACK_AUTH}" \
  -H 'Accept: application/json' \
	-H 'Content-Type: application/json' \
	-d '{
	"customFields": [
	{"name":"Assignee","$type":"SingleUserIssueCustomField","value":{"login":"BE1"}}
	] }'
  ##> {
  ##>   "customFields": [
  ##>     {
  ##>       "name": "State",
  ##>       "id": "110-38",
  ##>       "$type": "StateIssueCustomField"
  ##>     },
  ##>     {
  ##>       "name": "Priority",
  ##>       "id": "110-36",
  ##>       "$type": "SingleEnumIssueCustomField"
  ##>     },
  ##>     {
  ##>       "name": "Assignee",
  ##>       "id": "121-7",
  ##>       "$type": "SingleUserIssueCustomField"
  ##>     },
  ##>     {
  ##>       "name": "Type",
  ##>       "id": "110-37",
  ##>       "$type": "SingleEnumIssueCustomField"
  ##>     },
  ##>     {
  ##>       "name": "Estimation",
  ##>       "id": "148-7",
  ##>       "$type": "PeriodIssueCustomField"
  ##>     },
  ##>     {
  ##>       "name": "Spent time",
  ##>       "id": "148-6",
  ##>       "$type": "PeriodIssueCustomField"
  ##>     },
  ##>     {
  ##>       "name": "Due Date",
  ##>       "id": "111-47",
  ##>       "$type": "DateIssueCustomField"
  ##>     },
  ##>     {
  ##>       "name": "Requirement ID",
  ##>       "id": "111-42",
  ##>       "$type": "SimpleIssueCustomField"
  ##>     },
  ##>     {
  ##>       "name": "Verifier",
  ##>       "id": "121-22",
  ##>       "$type": "SingleUserIssueCustomField"
  ##>     },
  ##>     {
  ##>       "name": "Sprint",
  ##>       "id": "110-44",
  ##>       "$type": "MultiVersionIssueCustomField"
  ##>     },
  ##>     {
  ##>       "name": "Story points",
  ##>       "id": "111-32",
  ##>       "$type": "SimpleIssueCustomField"
  ##>     },
  ##>     {
  ##>       "name": "Volume",
  ##>       "id": "110-119",
  ##>       "$type": "SingleEnumIssueCustomField"
  ##>     },
  ##>     {
  ##>       "name": "Uncertainty",
  ##>       "id": "110-120",
  ##>       "$type": "SingleEnumIssueCustomField"
  ##>     },
  ##>     {
  ##>       "name": "Complexity",
  ##>       "id": "110-121",
  ##>       "$type": "SingleEnumIssueCustomField"
  ##>     },
  ##>     {
  ##>       "name": "SubProject",
  ##>       "id": "110-100",
  ##>       "$type": "MultiEnumIssueCustomField"
  ##>     },
  ##>     {
  ##>       "name": "Epic",
  ##>       "id": "110-99",
  ##>       "$type": "SingleEnumIssueCustomField"
  ##>     },
  ##>     {
  ##>       "name": "Timer time",
  ##>       "id": "111-22",
  ##>       "$type": "SimpleIssueCustomField"
  ##>     },
  ##>     {
  ##>       "name": "Fixed Version",
  ##>       "id": "110-98",
  ##>       "$type": "SingleBuildIssueCustomField"
  ##>     }
  ##>   ],
  ##>   "$type": "Issue"
  ##> }
