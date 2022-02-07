# `Requirement ID` isimli custom fieldımız var `test` projesinde. Bunun `TEST-91` issue'suna ait değerlerini almak istiyoruz:
curl -X GET \
	'https://youtrack.layermark.com/api/issues/TEST-91/customFields/111-42?fields=id,projectCustomField%28id,field%28id,name%29%29,value%28id,isResolved,localizedName,name%29' \
	-H 'Accept: application/json' \
  -H "Authorization: Bearer perm:${YOUTRACK_AUTH}"
  ##> {
  ##>   "projectCustomField": {
  ##>     "field": {
  ##>       "name": "Requirement ID",
  ##>       "id": "89-39",
  ##>       "$type": "CustomField"
  ##>     },
  ##>     "id": "111-42",
  ##>     "$type": "SimpleProjectCustomField"
  ##>   },
  ##>   "value": 15,
  ##>   "id": "111-42",
  ##>   "$type": "SimpleIssueCustomField"
  ##> }

