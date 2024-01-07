
# Article: mbrainz tutorial id=g12871
  id:: 8953b710-fb40-4ff8-a81b-830ce55b1d31

ref

	~/projects/study/clj/ex/study_datomic/mbrainz-sample/examples/clj/datomic/samples/mbrainz.clj
	[Datomic/mbrainz-sample: Example queries and rules for working with the Datomic mbrainz example database](https://github.com/Datomic/mbrainz-sample)

prerequisite:

	datomic-pro maven setup <url:file:///~/prj/study/clj/datomic-pro-maven-setup.md#r=g13697>
	run datomic pro and console <url:file:///~/projects/study/clj/datomic.otl#r=g12858>

setup

	; import mbrainz data id=g12872
  ;   id:: 30835f00-d0d9-4700-8097-e917c1de133b
	bin/datomic restore-db file:///Users/mertnuhoglu/codes/clj/ex/mbrainz-1968-1973 datomic:dev://localhost:4334/mbrainz-1968-1973

run

	cd /Users/mertnuhoglu/projects/study/clj/ex/study_datomic/mbrainz-sample
	ref: run repl + Rebl + portal <url:file:///~/projects/study/clj/datomic.otl#r=g12891>

repl code

	ref: repl code connection <url:file:///~/projects/study/clj/datomic.otl#r=g12892>
	database-name: mbrainz-1968-1973
	(d/q '[:find ?id ?type ?gender
					:in $ ?name
					:where
					[?e :artist/name ?name]
					[?e :artist/gid ?id]
					[?e :artist/type ?teid]
					[?teid :db/ident ?type]
					[?e :artist/gender ?geid]
					[?geid :db/ident ?gender]]
				db
				"Janis Joplin")

console

	ref: run datomic console <url:file:///~/projects/study/clj/datomic.otl#r=g12858>
	open http://localhost:9000/browse
	DB: mbrainz

query

	ref: [Queries · Datomic/mbrainz-sample Wiki](https://github.com/Datomic/mbrainz-sample/wiki/Queries)
	(d/q '[:find ?title
					:in $ ?artist-name
					:where
					[?a :artist/name ?artist-name]
					[?t :track/artists ?a]
					[?t :track/name ?title]]
				db, "John Lennon")
	; şimdi rebel ile datayı dolaşabilirsin

