# [Query Syntax - Help | Developer Portal for YouTrack and Hub](https://www.jetbrains.com/help/youtrack/devportal/api-query-syntax.html#samples)

# Tek bir sorguyla istediğimiz issue'ları ve onların alanlarını çekebiliriz.

curl -X GET \
'https://example.youtrack.cloud/api/issues?fields=id,summary,project(name)&query=for:+john.doe+%23Unresolved+summary' \
-H 'Accept: application/json' \
-H 'Authorization: Bearer perm:amFuZS5kb2U=.UkVTVCBBUEk=.wcKuAok8cHmAtzjA6xlc4BrB4hleaX' \
-H 'Cache-Control: no-cache' \
-H 'Content-Type: application/json'
