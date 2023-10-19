tags:: study, tll/sed

# 20230914-sed--Replacement-in-specific-lines-only id=g14686

[sed: perform search and replace only on specific lines (not globally) - GoLinuxHub](https://www.golinuxhub.com/2017/09/sed-perform-search-and-replace-only-on/)

a01: Specify line number

Line 4 only:

```
sed -e '4s/one/replaced/g' /tmp/file
```

rfr: [[20230914-sed-Delete-lines-between-matches]]

