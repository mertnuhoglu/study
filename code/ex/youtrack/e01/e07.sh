# Get the list of issues in a target project. Also, let's request the list of available custom fields with their names.
curl -X GET 'https://example.youtrack.cloud/api/issues?query=in:SP&fields=id,idReadable,summary,customFields(id,projectCustomField(field(name)))' \
-H 'Accept: application/json' \
-H 'Authorization: Bearer perm:amFuZS5kb2U=.UkVTVCBBUEk=.wcKuAok8cHmAtzjA6xlc4BrB4hleaX'
# [
#   {
#     "summary": "Issue from REST #1",
#     "idReadable": "SP-8",
#     "customFields": [
#       {
#         "projectCustomField": {
#           "field": {
#             "name": "Priority",
#             "$type": "CustomField"
#           },
#           "$type": "EnumProjectCustomField"
#         },
#         "id": "92-1",
#         "$type": "SingleEnumIssueCustomField"
#       },
#       {
#         "projectCustomField": {
#           "field": {
#             "name": "Type",
#             "$type": "CustomField"
#           },
#           "$type": "EnumProjectCustomField"
#         },
#         "id": "92-2",
#         "$type": "SingleEnumIssueCustomField"
#       },
