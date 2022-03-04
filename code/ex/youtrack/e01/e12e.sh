# [Query Syntax - Help | Developer Portal for YouTrack and Hub](https://www.jetbrains.com/help/youtrack/devportal/api-query-syntax.html#samples)

# Tek bir sorguyla istediğimiz issue'ları ve onların alanlarını çekebiliriz.
# Ayrıca tüm alanları alabiliriz

curl -X GET \
'https://youtrack.layermark.com/api/issues?fields=id,idReadable,summary,customFields($type,id,projectCustomField($type,id,field($type,id,name)),value($type,name))&query=project:+AMS' \
-H 'Accept: application/json' \
-H "Authorization: Bearer perm:${YOUTRACK_AUTH}" \
-H 'Cache-Control: no-cache' \
-H 'Content-Type: application/json'


