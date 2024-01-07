tags:: study
date:: 20231224

# ndx/json
.
- [[f/ndx]]
	- rfr: [[json.otl]]
	- rfr: [[study_xsv]]
  - Visidata
		- ex: Read json array || ((4968aebd-5b4b-4cb8-9167-15b860db960d))
	- jet: edn || ((c110c47c-6ee4-489c-9d08-a92fb639b924))
	- tabl: convert json/edn to table || ((83ad5aa8-2967-4650-8e86-18af19f33f6c))
  - jsonvisio: navigator and visualizer || ((99f2d69c-ea29-406e-ada8-eefe85eada66))
.
# f/pnt
.

- ## jsonvisio: navigator and visualizer
  id:: 99f2d69c-ea29-406e-ada8-eefe85eada66

[JSON Visio - Directly onto graphs](https://jsonvisio.com/)

![JsonVisio](./assets/scs20231225_215251.png)

- zsv

[liquidaty/zsv: zsv+lib: world's fastest (simd) CSV parser, bare metal or wasm, with an extensible CLI for SQL querying, format conversion and more](https://github.com/liquidaty/zsv)

xsv, miller alternative.

* json support: json<->csv
* jq filter
* 2db: convert json to sqlite3 db
* sql on csv

- SQL on CSV:

[Q – Run SQL Directly on CSV or TSV Files | Hacker News](https://news.ycombinator.com/item?id=27423276)

```
cat data.csv | xsv select 1,3 | q 'select * from - where col1 != "foo"'
```

```
cat data.csv | sqlite3 -csv ':memory:' '.import /dev/stdin data' 'select col1, col3 from data where col1 != "foo"'
```

```
r -e "data.table::fread(data.csv)[col1 != "foo", .(1,3)]"
```

- Miller: join

[Questions about joins - Miller 6.10.0 Documentation](https://miller.readthedocs.io/en/latest/questions-about-joins/)

```
mlr --csv join -j id -f data/color-codes.csv data/color-names.csv
```

- sq

[sq | wrangle data](https://sq.io/)

Imports: SQL, XLSX, CSV, JSON

- columnq-cli

[roapi/columnq-cli/README.md at main · roapi/roapi](https://github.com/roapi/roapi/blob/main/columnq-cli/README.md)

SQL on JSON:

```
$ columnq sql --table test_data/spacex_launches.json \
  "SELECT COUNT(id), DATE_TRUNC('year', CAST(date_utc AS TIMESTAMP)) as d FROM spacex_launches WHERE success = true GROUP BY d ORDER BY d DESC"

```

- qsv: xsv fork

[jqnatividad/qsv: CSVs sliced, diced & analyzed.](https://github.com/jqnatividad/qsv)

- dsq

[multiprocessio/dsq: Commandline tool for running SQL queries against JSON, CSV, Excel, Parquet, and more.](https://github.com/multiprocessio/dsq)

Nested arrays

Multiple files and joins:

```
$ dsq testdata/join/users.csv testdata/join/ages.json \
  "select {0}.name, {1}.age from {0} join {1} on {0}.id = {1}.id"
[{"age":88,"name":"Ted"},
{"age":56,"name":"Marjory"},
{"age":33,"name":"Micah"}]
```



