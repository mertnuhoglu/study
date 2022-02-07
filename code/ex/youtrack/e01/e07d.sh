# Bir projedeki tüm custom field'ların listesini çeker
curl -X GET \
	'https://youtrack.layermark.com/api/admin/projects/0-7/customFields?fields=id,canBeEmpty,emptyFieldText,project(id,name),field(id,name)' \
	-H 'Accept: application/json' \
  -H "Authorization: Bearer perm:${YOUTRACK_AUTH}"
	-H 'Cache-Control: no-cache' \
	-H 'Content-Type: application/json'
  ##> [
  ##>   {
  ##>     "emptyFieldText": "No Priority",
  ##>     "project": {
  ##>       "name": "test",
  ##>       "id": "0-7",
  ##>       "$type": "Project"
  ##>     },
  ##>     "canBeEmpty": true,
  ##>     "field": {
  ##>       "name": "Priority",
  ##>       "id": "89-0",
  ##>       "$type": "CustomField"
  ##>     },
  ##>     "id": "110-36",
  ##>     "$type": "EnumProjectCustomField"
  ##>   },
  ##>   {
  ##>     "emptyFieldText": "No Type",
  ##>     "project": {
  ##>       "name": "test",
  ##>       "id": "0-7",
  ##>       "$type": "Project"
  ##>     },
  ##>     "canBeEmpty": false,
  ##>     "field": {
  ##>       "name": "Type",
  ##>       "id": "89-1",
  ##>       "$type": "CustomField"
  ##>     },
  ##>     "id": "110-37",
  ##>     "$type": "EnumProjectCustomField"
  ##>   },
  ##>   {
  ##>     "emptyFieldText": "No State",
  ##>     "project": {
  ##>       "name": "test",
  ##>       "id": "0-7",
  ##>       "$type": "Project"
  ##>     },
  ##>     "canBeEmpty": true,
  ##>     "field": {
  ##>       "name": "State",
  ##>       "id": "89-2",
  ##>       "$type": "CustomField"
  ##>     },
  ##>     "id": "110-38",
  ##>     "$type": "StateProjectCustomField"
  ##>   },
  ##>   {
  ##>     "emptyFieldText": "Unscheduled",
  ##>     "project": {
  ##>       "name": "test",
  ##>       "id": "0-7",
  ##>       "$type": "Project"
  ##>     },
  ##>     "canBeEmpty": true,
  ##>     "field": {
  ##>       "name": "Sprint",
  ##>       "id": "89-9",
  ##>       "$type": "CustomField"
  ##>     },
  ##>     "id": "110-44",
  ##>     "$type": "VersionProjectCustomField"
  ##>   },
  ##>   {
  ##>     "emptyFieldText": "No build number",
  ##>     "project": {
  ##>       "name": "test",
  ##>       "id": "0-7",
  ##>       "$type": "Project"
  ##>     },
  ##>     "canBeEmpty": true,
  ##>     "field": {
  ##>       "name": "Fixed Version",
  ##>       "id": "89-31",
  ##>       "$type": "CustomField"
  ##>     },
  ##>     "id": "110-98",
  ##>     "$type": "BuildProjectCustomField"
  ##>   },
  ##>   {
  ##>     "emptyFieldText": "No epic",
  ##>     "project": {
  ##>       "name": "test",
  ##>       "id": "0-7",
  ##>       "$type": "Project"
  ##>     },
  ##>     "canBeEmpty": true,
  ##>     "field": {
  ##>       "name": "Epic",
  ##>       "id": "89-30",
  ##>       "$type": "CustomField"
  ##>     },
  ##>     "id": "110-99",
  ##>     "$type": "EnumProjectCustomField"
  ##>   },
  ##>   {
  ##>     "emptyFieldText": "No subproject",
  ##>     "project": {
  ##>       "name": "test",
  ##>       "id": "0-7",
  ##>       "$type": "Project"
  ##>     },
  ##>     "canBeEmpty": true,
  ##>     "field": {
  ##>       "name": "SubProject",
  ##>       "id": "89-12",
  ##>       "$type": "CustomField"
  ##>     },
  ##>     "id": "110-100",
  ##>     "$type": "EnumProjectCustomField"
  ##>   },
  ##>   {
  ##>     "emptyFieldText": "S",
  ##>     "project": {
  ##>       "name": "test",
  ##>       "id": "0-7",
  ##>       "$type": "Project"
  ##>     },
  ##>     "canBeEmpty": true,
  ##>     "field": {
  ##>       "name": "Volume",
  ##>       "id": "89-43",
  ##>       "$type": "CustomField"
  ##>     },
  ##>     "id": "110-119",
  ##>     "$type": "EnumProjectCustomField"
  ##>   },
  ##>   {
  ##>     "emptyFieldText": "Very Low",
  ##>     "project": {
  ##>       "name": "test",
  ##>       "id": "0-7",
  ##>       "$type": "Project"
  ##>     },
  ##>     "canBeEmpty": true,
  ##>     "field": {
  ##>       "name": "Uncertainty",
  ##>       "id": "89-44",
  ##>       "$type": "CustomField"
  ##>     },
  ##>     "id": "110-120",
  ##>     "$type": "EnumProjectCustomField"
  ##>   },
  ##>   {
  ##>     "emptyFieldText": "Very Low",
  ##>     "project": {
  ##>       "name": "test",
  ##>       "id": "0-7",
  ##>       "$type": "Project"
  ##>     },
  ##>     "canBeEmpty": true,
  ##>     "field": {
  ##>       "name": "Complexity",
  ##>       "id": "89-45",
  ##>       "$type": "CustomField"
  ##>     },
  ##>     "id": "110-121",
  ##>     "$type": "EnumProjectCustomField"
  ##>   },
  ##>   {
  ##>     "emptyFieldText": "No timer time",
  ##>     "project": {
  ##>       "name": "test",
  ##>       "id": "0-7",
  ##>       "$type": "Project"
  ##>     },
  ##>     "canBeEmpty": true,
  ##>     "field": {
  ##>       "name": "Timer time",
  ##>       "id": "89-23",
  ##>       "$type": "CustomField"
  ##>     },
  ##>     "id": "111-22",
  ##>     "$type": "SimpleProjectCustomField"
  ##>   },
  ##>   {
  ##>     "emptyFieldText": "No story points",
  ##>     "project": {
  ##>       "name": "test",
  ##>       "id": "0-7",
  ##>       "$type": "Project"
  ##>     },
  ##>     "canBeEmpty": true,
  ##>     "field": {
  ##>       "name": "Story points",
  ##>       "id": "89-11",
  ##>       "$type": "CustomField"
  ##>     },
  ##>     "id": "111-32",
  ##>     "$type": "SimpleProjectCustomField"
  ##>   },
  ##>   {
  ##>     "emptyFieldText": "No requirement id",
  ##>     "project": {
  ##>       "name": "test",
  ##>       "id": "0-7",
  ##>       "$type": "Project"
  ##>     },
  ##>     "canBeEmpty": true,
  ##>     "field": {
  ##>       "name": "Requirement ID",
  ##>       "id": "89-39",
  ##>       "$type": "CustomField"
  ##>     },
  ##>     "id": "111-42",
  ##>     "$type": "SimpleProjectCustomField"
  ##>   },
  ##>   {
  ##>     "emptyFieldText": "No due date",
  ##>     "project": {
  ##>       "name": "test",
  ##>       "id": "0-7",
  ##>       "$type": "Project"
  ##>     },
  ##>     "canBeEmpty": true,
  ##>     "field": {
  ##>       "name": "Due Date",
  ##>       "id": "89-47",
  ##>       "$type": "CustomField"
  ##>     },
  ##>     "id": "111-47",
  ##>     "$type": "SimpleProjectCustomField"
  ##>   },
  ##>   {
  ##>     "emptyFieldText": "Unassigned",
  ##>     "project": {
  ##>       "name": "test",
  ##>       "id": "0-7",
  ##>       "$type": "Project"
  ##>     },
  ##>     "canBeEmpty": true,
  ##>     "field": {
  ##>       "name": "Assignee",
  ##>       "id": "89-3",
  ##>       "$type": "CustomField"
  ##>     },
  ##>     "id": "121-7",
  ##>     "$type": "UserProjectCustomField"
  ##>   },
  ##>   {
  ##>     "emptyFieldText": "No verifier",
  ##>     "project": {
  ##>       "name": "test",
  ##>       "id": "0-7",
  ##>       "$type": "Project"
  ##>     },
  ##>     "canBeEmpty": true,
  ##>     "field": {
  ##>       "name": "Verifier",
  ##>       "id": "89-35",
  ##>       "$type": "CustomField"
  ##>     },
  ##>     "id": "121-22",
  ##>     "$type": "UserProjectCustomField"
  ##>   },
  ##>   {
  ##>     "emptyFieldText": "-",
  ##>     "project": {
  ##>       "name": "test",
  ##>       "id": "0-7",
  ##>       "$type": "Project"
  ##>     },
  ##>     "canBeEmpty": true,
  ##>     "field": {
  ##>       "name": "Spent time",
  ##>       "id": "89-20",
  ##>       "$type": "CustomField"
  ##>     },
  ##>     "id": "148-6",
  ##>     "$type": "PeriodProjectCustomField"
  ##>   },
  ##>   {
  ##>     "emptyFieldText": "?",
  ##>     "project": {
  ##>       "name": "test",
  ##>       "id": "0-7",
  ##>       "$type": "Project"
  ##>     },
  ##>     "canBeEmpty": true,
  ##>     "field": {
  ##>       "name": "Estimation",
  ##>       "id": "89-19",
  ##>       "$type": "CustomField"
  ##>     },
  ##>     "id": "148-7",
  ##>     "$type": "PeriodProjectCustomField"
  ##>   }
  ##> ]

