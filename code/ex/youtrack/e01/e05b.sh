curl -X POST \
https://example.youtrack.cloud/api/issues \
-H 'Accept: application/json' \
-H 'Authorization: Bearer perm:amFuZS5kb2U=.UkVTVCBBUEk=.wcKuAok8cHmAtzjA6xlc4BrB4hleaX' \
-H 'Content-Type: application/json' \
-d '{
"project":{"id":"0-0"},
"summary":"REST API lets you create issues! Mert",
"description":"Let'\''s create a new issue using YouTrack'\''s REST API."
}'

