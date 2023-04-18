(ns user)

(require '[nextjournal.clerk :as clerk])

;; start Clerk's built-in webserver on the default port 7777, opening the browser when done
(clerk/serve! {:browse? true})

(clerk/show! "notebooks/e01.clj")

(comment
  ;; either call `clerk/show!` explicitly
  ;(clerk/show! "notebooks/e02.clj")

  ;; or let Clerk watch the given `:paths` for changes
  (clerk/serve! {:watch-paths ["notebooks" "src"]})

  ;; start with watcher and show filter function to enable notebook pinning
  (clerk/serve! {:watch-paths ["notebooks" "src"] :show-filter-fn #(clojure.string/starts-with? % "notebooks")})

  ;; Build a html file from the given notebook notebooks.
  ;; See the docstring for more options.
  (clerk/build! {:paths ["notebooks/e01.clj"]})

  ; end
  ,)

