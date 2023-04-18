(ns user)


(comment
  (require '[nextjournal.clerk :as clerk])

  ;; start Clerk's built-in webserver on the default port 7777, opening the browser when done
  (clerk/serve! {:browse? true
                 :port 7779})

  (clerk/clear-cache!)

  ;; either call `clerk/show!` explicitly
  (clerk/show! "notebooks/e01.clj")
  (clerk/show! "notebooks/e02.clj")

  ;end
  ,)
