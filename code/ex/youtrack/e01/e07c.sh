# Tüm custom field'ların listesini çeker ve ayrıca tüm issue'ların customField'larını da indirir.
curl -X GET \
	'https://youtrack.layermark.com/api/admin/customFieldSettings/customFields?fields=id,name,aliases,instances(id,project(id,name))' \
	-H 'Accept: application/json' \
  -H "Authorization: Bearer perm:${YOUTRACK_AUTH}"
	-H 'Cache-Control: no-cache' \
	-H 'Content-Type: application/json'
  # {
  #   "aliases": null,
  #   "instances": [
  #     {
  #       "project": {
  #         "name": "EEMS",
  #         "id": "0-17",
  #         "$type": "Project"
  #       },
  #       "id": "111-41",
  #       "$type": "SimpleProjectCustomField"
  #     },
  #     {
  #       "project": {
  #         "name": "test",
  #         "id": "0-7",
  #         "$type": "Project"
  #       },
  #       "id": "111-42",
  #       "$type": "SimpleProjectCustomField"
  #     },
  #     {
  #       "project": {
  #         "name": "LayerNote",
  #         "id": "0-5",
  #         "$type": "Project"
  #       },
  #       "id": "111-50",
  #       "$type": "SimpleProjectCustomField"
  #     },
  #     {
  #       "project": {
  #         "name": "AMS",
  #         "id": "0-21",
  #         "$type": "Project"
  #       },
  #       "id": "111-55",
  #       "$type": "SimpleProjectCustomField"
  #     },
  #     {
  #       "project": {
  #         "name": "Hydrant",
  #         "id": "0-22",
  #         "$type": "Project"
  #       },
  #       "id": "111-59",
  #       "$type": "SimpleProjectCustomField"
  #     }
  #   ],
  #   "name": "Requirement ID",
  #   "id": "89-39",
  #   "$type": "CustomField"
  # },

