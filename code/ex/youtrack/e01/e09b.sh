# Update custom fields: `Assignee` alanını update edelim sadece:
curl -X POST \
'https://example.youtrack.cloud/api/issues/SP-8?fields=customFields(id,name)' \
-H 'Accept: application/json' \
-H 'Authorization: Bearer perm:am9obi5kb2U=.UG9zdG1hbiBKb2huIERvZQ==.jJe0eYhhkV271j1lCpfknNYOEakNk7' \
-H 'Content-Type: application/json' \
-d '{
"customFields": [
	{"name":"Priority","$type":"SingleEnumIssueCustomField","value":{"name":"Major"}}
] 
}'
  ##> {
  ##>   "customFields": [
  ##>     {
  ##>       "name": "Priority",
  ##>       "id": "92-81",
  ##>       "$type": "SingleEnumIssueCustomField"
  ##>     },
  ##>     {
  ##>       "name": "Assignee",
  ##>       "id": "94-14",
  ##>       "$type": "SingleUserIssueCustomField"
  ##>     },
  ##>     {
  ##>       "name": "Kanban State",
  ##>       "id": "92-79",
  ##>       "$type": "SingleEnumIssueCustomField"
  ##>     },
  ##>     {
  ##>       "name": "Stage_KNBN",
  ##>       "id": "92-80",
  ##>       "$type": "StateIssueCustomField"
  ##>     }
  ##>   ],
  ##>   "$type": "Issue"
  ##> }

