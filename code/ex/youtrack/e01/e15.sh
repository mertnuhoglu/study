# json dosyasının veri yapısını görmek için
json2xpath.jq tmp/e12e.json | sort -u | xpath2dot.awk -v ORIENT="UD" | dot -T png > tmp/e12e.png