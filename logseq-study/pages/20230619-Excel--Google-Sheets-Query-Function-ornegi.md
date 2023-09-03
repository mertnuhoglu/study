tags:: study, prg/excel

# 20230619-Excel--Google-Sheets-Query-Function-ornegi id=g14504

rfr: sndfnn > 20230619-Excel-Query-Function

| id | col1 | col2 |
|----|------|------|
| 1  | 100  | 30   |
| 2  | 200  | 45   |
| 3  | 150  | 50   |
| 4  | 250  | 20   |

```
=query(B367:D371, "select B, C, D where B <= 3", 1)
```

Result:

| id | col1 | col2 |
|----|------|------|
| 1  | 100  | 30   |
| 2  | 200  | 45   |
| 3  | 150  | 50   |

