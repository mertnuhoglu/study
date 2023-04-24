# p03: Clerk Functionalities id=g14239

```clojure
(require '[nextjournal.clerk :as clerk])
```

## Table

rfr: [Tables | 📖 Book of Clerk](https://book.clerk.vision/#tables)

- e01: Düz tablo

```clojure
(clerk/table [[1 2]
              [3 4]]) 
```

- Dokümantasyonu:

> nextjournal.clerk/table
>
> [xs]
>
> [viewer-opts xs]
>
> Displays `xs` using the table viewer.
> Performs normalization on the data, supporting:
> - seqs of maps
> - maps of seqs
> - seqs of seqs  
    > If you want a header for seqs of seqs use `use-headers`.
    > Supports an optional first `viewer-opts` map arg with the following optional keys:
> * `:nextjournal.clerk/width`: set the width to `:full`, `:wide`, `:prose`
> * `:nextjournal.clerk/viewers`: a seq of viewers to use for presentation of this value and its children
> * `:nextjournal.clerk/opts`: a map argument that will be passed to the viewers `:render-fn`

- e02: Header (başlık) ekleme:

```clojure
(clerk/table
  (clerk/use-headers
    [["col1" "col2"]
     [1 2]
     [3 4]])) 
```

- e03: map of seqs

```clojure
(clerk/table {:col1 [1 2]
              :col2 [3 4]}) 
```

- e03: seq of maps

```clojure
(clerk/table [{:col1 1 :col2 2} 
              {:col1 3 :col2 4}]) 
```

- e04: canonical form

```clojure
(clerk/table {:head ["col1" "col2"]
              :rows [[1 2] [3 4]]}) 
```

## Matematiksel ifadeler:

- e01: inline:

$${\frac{d}{d t} \frac{∂ L}{∂ \dot{q}}}-\frac{∂ L}{∂ q}=0.$$

- e02: block:

```clojure
(clerk/tex "
\\begin{alignedat}{2}
\\nabla\\cdot\\vec{E} = \\frac{\\rho}{\\varepsilon_0} & \\qquad \\text{Gauss' Law} \\\\
\\end{alignedat}
")
```

