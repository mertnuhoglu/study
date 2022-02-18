# taken from: [Custom Fields in REST API - Help | Developer Portal for YouTrack and Hub](https://www.jetbrains.com/help/youtrack/devportal/api-concept-custom-fields.html#issue-custom-field)
curl -X GET \
'https://youtrack.layermark.com/api/issues/TEST-514/customFields?fields=id,value(id,name,login,fullName),projectCustomField(id,field(id,name))' \
-H 'Accept: application/json' \
-H "Authorization: Bearer perm:${YOUTRACK_AUTH}" \
-H 'Cache-Control: no-cache' \
-H 'Content-Type: application/json'
