# taken from: [Custom Fields in REST API - Help | Developer Portal for YouTrack and Hub](https://www.jetbrains.com/help/youtrack/devportal/api-concept-custom-fields.html#issue-custom-field)
curl -X GET \
'https://example.youtrack.cloud/api/issues/2-7/customFields?fields=id,value(id,name,login,fullName),projectCustomField(id,field(id,name))' \
-H 'Accept: application/json' \
-H 'Authorization: Bearer perm:am9obi5kb2U=.UG9zdG1hbiBKb2huIERvZQ==.jJe0eYhhkV271j1lCpfknNYOEakNk7' \
-H 'Cache-Control: no-cache' \
-H 'Content-Type: application/json'
