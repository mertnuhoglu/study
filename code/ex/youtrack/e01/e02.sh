curl -X GET \
'https://youtrack.layermark.com/api/users/me' \
-H 'Accept: application/json' \
-H "Authorization: Bearer perm:${YOUTRACK_AUTH}" \
-H 'Cache-Control: no-cache'


