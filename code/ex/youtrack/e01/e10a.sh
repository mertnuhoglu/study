# Bir issue'ya command apply etmek:
curl -X POST 'https://example.youtrack.cloud/api/commands' \
	-H 'Authorization: Bearer perm:am9obi5kb2U=.UG9zdG1hbiBKb2huIERvZQ==.jJe0eYhhkV271j1lCpfknNYOEakNk7' \
	-H 'Content-Type: application/json' \
	-d '{
	"query": "Fixed",
	"issues": [ { "id": "2-17" } ] }'
# id yerine insanlara özel id'yi de kullanabilirsin:
# "issues":[{"idReadable":"SP-3967"},{"idReadable":"SP-4032"},{"idReadable":"SP-3990"} ]

# response olarak bu hiçbir şöy dönmez, çünkü fields parametresini göndermedik

