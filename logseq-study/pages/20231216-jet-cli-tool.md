tags:: study, prg/terminal, prg/data-analysis, f/tll, clj
date:: 20231216

# 20231216-jet-cli-tool

- [[f/ndx]]

# f/pnt

- [borkdude/jet: CLI to transform between JSON, EDN, YAML and Transit using Clojure](https://github.com/borkdude/jet)

```
$ echo '{:a 1}' | jet --to json
{"a":1}
```

```
$ echo '["^ ","~:a",1]' | jet --from transit --to edn
{:a 1}
```

Use as a clj tool:

```
$ clojure -Ttools install-latest :lib io.github.borkdude/jet :as jet

$ echo '[1 2 3]' | clj -Tjet exec :colors true :func '"#(-> % first inc)"'
2
```

Specter:

```
$ echo '{:a {:a 1}}' | jet -t '(s/transform [s/MAP-VALS s/MAP-VALS] inc)'
{:a {:a 2}}
```

- Examples:

```
cd ~/prj/study/logseq-study/pages/ex/20231216-jet-cli-tool
```

```
❯ \cat d01.transit| jet -i transit -o edn
{:a 1}
```

