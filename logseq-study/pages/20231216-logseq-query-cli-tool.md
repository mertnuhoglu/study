tags:: study, t/logseq
date:: 20231216

- # 20231216-logseq-query-cli-tool
.
- [[f/ndx]]
	- rfr: related: [[logseq/query]]
	- rfr: related: [[20231221-Logseq-Query-Example]]
  - rfr: chunk: cldwalker/logseq-query || ((90ae0608-0878-4d4a-8615-83e3c81d58ec))
  - Configuration || ((4adeb9de-3b9c-470b-a624-e8b1b4439851))
  - Examples || ((2154921a-0bd9-42b8-88bb-52a3002b4544))
  - Set default graph || ((8bda6979-e698-470c-8788-0b9acf832a57))
  - Export query expression || ((aac1866e-0654-483c-bf9d-08f0ebe3accc))
  - Error: lq property querying || ((4ceebc39-a098-4fe7-b6cb-9e52f034f5dc))
.
- # f/pnt
.
- ## [cldwalker/logseq-query](https://github.com/cldwalker/logseq-query) #f/chunk 
  id:: 90ae0608-0878-4d4a-8615-83e3c81d58ec
  - Configuration || ((4adeb9de-3b9c-470b-a624-e8b1b4439851))
  - Examples || ((2154921a-0bd9-42b8-88bb-52a3002b4544))
  - Set default graph || ((8bda6979-e698-470c-8788-0b9acf832a57))
  - Export query expression || ((aac1866e-0654-483c-bf9d-08f0ebe3accc))
.
- Test Block 02
  type:: testing
- Test Block 03 Test03
  type:: test
.
- ## Configuration:
  id:: 4adeb9de-3b9c-470b-a624-e8b1b4439851

  rfr: `~/.lq/config.edn`
.
- ## Examples
  id:: 2154921a-0bd9-42b8-88bb-52a3002b4544

Source: [cldwalker/logseq-query](https://github.com/cldwalker/logseq-query)

List existing Logseq repos (graphs)

```
$ lq graphs
##> 
##> |         :name |                                                                                                                                                                   :path |
##> |---------------+-------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
##> |     docs-grsm | /Users/mertnuhoglu/.logseq/graphs/logseq_local_++Users++mertnuhoglu++Library++CloudStorage++GoogleDrive-mert.nuhoglu@gmail.com++My Drive++grsm++opal++docs-grsm.transit |
##> | logseq-myrepo |                                                                 /Users/mertnuhoglu/.logseq/graphs/logseq_local_++Users++mertnuhoglu++prj++myrepo++logseq-myrepo.transit |
##> |  logseq-study |                                                                   /Users/mertnuhoglu/.logseq/graphs/logseq_local_++Users++mertnuhoglu++prj++study++logseq-study.transit |
```

- ## Set default graph:
	id:: 8bda6979-e698-470c-8788-0b9acf832a57

```
echo '{:default-options {:graph "docs-grsm"}}' > ~/.lq/config.edn
```

List queries:

```
$ lq queries
##> 
##> |           :name | :namespace |          :parent |                                                      :desc |
##> |-----------------+------------+------------------+------------------------------------------------------------|
##> |  content-search |         lq |                  |                         Full text search on :block/content |
##> |    has-property |         lq |                  |                       List blocks that have given property |
##> |        property |         lq |                  | List all blocks that have property equal to property value |
##> |    property-all |         lq |                  |                       List all blocks that have properties |
##> | property-counts |         lq | :lq/property-all |                            Counts for all block properties |
##> | property-search |         lq |                  |                               Full text search on property |
##> |            task |         lq |                  |                      Todos that contain one of the markers |
##> Total: 7

```

Content search:

```
❯ lq q content-search producthunt
##> [{:db/id 73588, :block/content "producthunt.com", :block/format :markdown, :block/left #:db{:id 73611}, :block/page #:db{:id 73419}, :block/parent #:db{:id 73602}, :block/path-refs [#:db{:id 302} #:db{:id 416} #:db{:id 73285} #:db{:id 73419}], :block/uuid #uuid "653f8311-796b-4742-bac5-18b65353a8af"} {:db/id 73775, :block/content "producthunt.com", :block/format :markdown, :block/left #:db{:id 73774}, :block/page #:db{:id 73416}, :block/parent #:db{:id 73771}, :block/path-refs [#:db{:id 4} #:db{:id 180} #:db{:id 198} #:db{:id 73416}], :block/uuid #uuid "653f8771-f909-4ed1-8bc9-7781b39d9aa5"} {:db/id 76086, :block/content "producthunt.com", :block/format :markdown, :block/left #:db{:id 79977}, :block/page #:db{:id 76031}, :block/parent #:db{:id 79977}, :block/path-refs [#:db{:id 76031}], :block/uuid #uuid "654ddfe5-f931-4d15-9baf-e9d428f19567"} {:db/id 76088, :block/content "[Best rated products. | Product Hunt](https://www.producthunt.com/products?order=best_rated&period=all-time)", :block/format :markdown, :block/left #:db{:id 76086}, :block/page #:db{:id 76031}, :block/parent #:db{:id 76086}, :block/path-refs [#:db{:id 76031}], :block/uuid #uuid "654de02a-c884-4fa3-98b9-d44be84d1712"}]
```

Help:

```
❯ lq q content-search -h
##> Usage: lq q [OPTIONS] content-search QUERY
##> Full text search on :block/content
##> 
##> Options:
##>   -h, --help                   Print help
##>   -g, --graph GRAPH            Choose a graph
##>   -e, --export                 Print/export query for use with logseq
##>   -t, --table                  Render results in a table
##>       --table-command COMMAND  Command to run with --table
##>   -p, --puget                  Colorize results with puget
##>   -P, --no-puget
##>   -c, --count                  Print count of results
##>   -C, --block-content          Print only :block/content of result
##>   -T, --tag-counts             Print tag counts for query results
##>   -s, --silence                Silence noisy errors like d/q error

```

Search in a specific graph:

```
❯ lq q content-search -g docs-grsm producthunt
```

Render results in a table:

```
lq q content-search -t appsumo
##> 
##> |   :id |
##> |-------|
##> | 64038 |
##> | 63559 |
```

Print only `:block/content`

```
lq q content-search -C klaviyo
##> - klaviyo, stripe, zapier, pitch, vs.
```

Print tag counts for query results:

```
lq q content-search -T 'f/kybd'
##> (["f" 3] ["f/kybd" 3] ["prg" 2] ["org" 2] ["prg/clj" 2] ["org/orientation" 2])
```

`has-property`:

```
lq q has-property 'type'
lq q has-property -T 'type'
```

Todo blocks: `task`:

```
lq q task -T
```

- ## Export query expression:
	id:: aac1866e-0654-483c-bf9d-08f0ebe3accc

```
lq q content-search -e
##> {:query
##>  [:find (pull ?b [*]) :in $ ?query % :where (block-content ?b ?query)],
##>  :inputs
##>  ["TODO"
##>   [[(block-content ?b ?query)
##>     [?b :block/content ?content]
##>     [(clojure.string/includes? ?content ?query)]]]]}
##> 
```

List rules:

```
lq rules
```

- ## Error: lq property querying
  id:: 4ceebc39-a098-4fe7-b6cb-9e52f034f5dc

rfr: log: prt: Error: lq property querying || ((e67c0906-9225-4df7-aa65-20cb9c645ba0))

rfr: Fix: Reindex or restart logseq || ((93bf5c22-f2a2-4c46-b36d-15612ebf4d17))

