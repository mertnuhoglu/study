tags:: study

# 20230926-sed-komutlarini-verbose-calistirma id=g14785

sed komutu -i ile inline çalışır. Dosyada gerçekleştirdiği değişiklikleri görmezsin.

Bunları görmek için, `w` opsiyonunu kullanmak lazım.

[Printing verbose progress from sed and awk - Stack Overflow](https://stackoverflow.com/questions/9833948/printing-verbose-progress-from-sed-and-awk)

```
$ echo hello > test
$ sed -e 's/ll/zz/w /dev/stdout' -i .backup test
hezzo
$ cat test
hezzo
$ cat test.backup 
hello
```

