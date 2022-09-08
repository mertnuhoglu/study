(ns sims.ppfs-sim01)

;; PPFS Simulation Script id=g13262

;; Use Case 02: Yeni görev tanımlama scripti id=g13264

;; rfr: Use Case 02: Yeni görev tanımlama <url:file:///~/prj/myrepo/prj/ppfs/use-cases-ppfs.md#r=g13258>

(def db (atom nil))

(defn submit-repeat-frequency
  [:keys [repeat-unit
          repeat-amt
          expiry-date
          max-job-count
          which-year
          which-month
          which-week
          scheduler
          which-time
          constraint-day-type]]
  (swap! db assoc :b 2))


