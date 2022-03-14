# [Query Syntax - Help | Developer Portal for YouTrack and Hub](https://www.jetbrains.com/help/youtrack/devportal/api-query-syntax.html#samples)

# Tek bir sorguyla istediğimiz issue'ları ve onların alanlarını çekebiliriz.
# Ayrıca tüm alanları alabiliriz

# https://youtrack.layermark.com/issues?q=project:%20DMB%20Type:%20%7BRequirement%20Implementation%7D%20Requirement%20ID:%20*
curl -X GET \
'https://youtrack.layermark.com/api/issues?fields=id,idReadable,summary,customFields($type,id,projectCustomField($type,id,field($type,id,name)),value($type,name))&query=project:%20DMB%20Type:%20%7BRequirement%20Implementation%7D%20Requirement%20ID:%20*' \
-H 'Accept: application/json' \
-H "Authorization: Bearer perm:${YOUTRACK_AUTH}" \
-H 'Cache-Control: no-cache' \
-H 'Content-Type: application/json'


