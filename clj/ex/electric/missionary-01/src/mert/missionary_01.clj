(ns mert.missionary-01)

; missionary_02

;; spcs: study: Missionary for dummies, Timur Latypoff || ((5a875957-3aae-44f3-a914-08958dbe7909))
;; src: https://nextjournal.com/N-litened/missionary-for-dummies
;; deps: ~/prj/study/clj/ex/electric/missionary-01/deps.edn

(require '[missionary.core :as m])
(import 'missionary.Cancelled)
(add-tap clojure.pprint/pprint) ; Pretty-print tapped values

(tap> {:debug "value"})

;; Define a tap handler that stores tapped values in an atom
(def debug-a (atom nil))
(add-tap #(reset! debug-a %))

;; Send a value via tap>
(tap> {:key "value"})

;; Inspect the tapped value
@debug-a ; => {:key "value"}

(println "Now we have everything we need")

;; Running tasks
;;   id:: 84fe55ad-1199-42fa-b763-bc3bb985ba0d

(defn some-operation1 [] "RESULT")
(let [result (some-operation1)]
  (println result))

(defn some-operation2 [] (throw (ex-info "FAILURE" {})))
(try (some-operation2)
  (catch Exception ex (println (ex-message ex))))

;; exmp 04: Thread
;;   id:: c1b46c6c-4910-489a-a796-d5a3bd061354
;; Here's some CPU-heavy operation written in a way that (I think) prevents 
;; Clojure's compiler and JVM from optimizing it away - we _want_ it to be CPU-intensive!
(defn some-slow-operation3 [] (reduce + (range 0.0 10000000.0 0.1)))

;; We start a thread that only does CPU computations — can't .interrupt it!
(let [result (atom "Never updated result")
      thread (Thread. (fn [] (reset! result (some-slow-operation3))))]
  (.start thread)
  (Thread/sleep 50) ;; Giving it some time to start
  (.interrupt thread)
  ;; Yeah, we can .stop it, but it might leave our real-life system in a "dirty" state,
  ;; better not do it. That's why this function was deprecated
  ;; (.stop thread)
  (.join thread)
  (println @result)) ;; In the end, we print numeric result, 
                     ;; because we were not able to interrupt the thread, it finished its job.

;; exmp 05: Task
;;   id:: 84557a64-9a42-4e4d-8a5a-4495c770daa8
(defn some-task [success failure]
  (do ;; Maybe even do stuff in another thread, if needed
    (if "Do stuff successfully?"
      (success "RESULT")
      (failure (ex-info "FAILURE" {}))))
  ;; But 
  (fn cancel-me [] "Cancel the operation if possible. The return value is to be ignored"))

;; exmp 06: Calling task:
;;   id:: d14beb95-2c45-4b1f-a8a2-30a4d71b7eaf
(let [cancel-task (some-task
                    (fn [result] (println "Succeeded with value" result))
                    (fn [ex] (println "Caught exception" (type ex)
                                      "- feeling cute, might throw later")))]
  (Thread/sleep 50)
  ;; Hey, it's taking too long, I don't like it anymore, might as well try cancel it
  (cancel-task))

;; exmp 07: Run a task with: `?`
;;   id:: 8c562ca3-c521-43ef-96eb-96f5090b5452
(defn task-that-returns-five [success _]
  (success 5)
  #(do "Can't cancel, sorry!"))

(identity (m/? task-that-returns-five)) ; 5

(defn task-that-always-fails [_ failure]
  (failure (ex-info "I've failed" {:who 'task-that-always-fails}))
  #())

(try
  (identity (m/? task-that-always-fails))
  (catch Exception ex
    (println "EXCEPTION:" (ex-message ex) (ex-data ex))))
; (out) EXCEPTION: I've failed {:who task-that-always-fails}

;; exmp 08: Task with args: Task factory
;;   id:: 54bbe88b-e4e4-4c8d-bc43-b088afeab01a
(defn add-two-numbers [x y]
  (fn add-two-numbers-inner-task [success _failure]
    (success (+ x y))
    #(do "Can't cancel addition, too fast anyway :(")))

(m/? (add-two-numbers 3 7)) ; 10

;; exmp 09: Cancelable task: Requests data via API
;;   id:: bc74cb52-0583-47e9-a484-92cb67b76b69
(defn request-user-info [user-id]
  (fn [success failure]
    (let [fut (future
                (try
                  ;; Pretending to request data via HTTP
                  (Thread/sleep 3000)
                  ;; Passing result
                  (success {:user-id user-id
                            :user-name "Timur"})
                  (catch Throwable th
                    (failure th))))]
      ;; Returning a canceling function
      (fn request-canceler []
        (future-cancel fut)))))

(println "User 1234:" (m/? (request-user-info 1234)))
; (out) User 1234: {:user-id 1234, :user-name Timur}

;; exmp 10: Cancel tasks 3 times: idempotency
;;   id:: 9f7cfcfe-8fc4-4eae-a30e-95c13429f4ac
(let [task-about-to-be-run (request-user-info 4567)
      cancel-this-task (task-about-to-be-run
                         #(println "User 4567:" %)
                         #(println "Got exception:" (type %)))]
  (Thread/sleep 100) ;; Now we wait a bit
  (cancel-this-task)
  (cancel-this-task)
  (cancel-this-task)) ; false
; (out) Got exception: java.lang.InterruptedException

(def our-simple-task (m/sp
                       (let [v (+ 1 2)]
                         (do
                           "whatever")
                         (str "Our result " v))))
(identity (m/? our-simple-task)) ; "Our result 3"

;; exmp 11: Magicness of sp:
;;   id:: 98aab4c0-d5ee-4ab1-896f-0dd0df4f92f1
(def our-simple-task-that-fails (m/sp
                                  (throw (ex-info "We've failed" {}))
                                  "Our result that is never reached"))

;; let's run the task it in a "raw" way to see that the exception is not actually 
;; thrown up our call stack (good tasks don't throw exceptions, remember?), 
;; but instead properly passed to the failure handler
(our-simple-task-that-fails
  (fn [result] (println "Success:" result))
  (fn [ex] (println "Here's our exception:" (ex-message ex))))
; (out) Here's our exception: We've failed
;
; Dikkat: Bu fn iki arg alıyor.
; İlki success fn, ikincisi failure fn.
; Yani eğer task başarılı dönerse, success callback çağrılır.
; Eğer exception atarsa, failure callback çağrılır.
; Bu çağırma işlemini missionary yapar. Task kendisi yapmaz.
; Task sadece bir işin nasıl yapılacağının tarifidir.
; Başka bir deyişle herhangi bir expression dır.
; Ancak dikkat: Task ı çağırmak bizim görevimiz.
; Bir task definition var.
; Bir de task run var.
; Task run sırasında callback leri aktarırız.

;; exmp 12: via: running task in a separate thread
;;   id:: 12a8b97c-42c4-40fe-87d4-4ac1bbab1bb0
(defn request-from-rest-api [id]
  (m/via m/blk
    (Thread/sleep 3000) ;; we simulate some (http/get ...) that takes a long time
    {:id id
     :name "Timur"}))

(defn compute-value-on-cpu [input]
  (m/via m/cpu
    ;; we re-use our slow CPU-bound function from one of the examples above
    (let [result (some-slow-operation3)]
      (* input result))))

(def task-with-subthreads
  (m/sp
    (let [entity (m/? (request-from-rest-api 100))
          new-entity (assoc entity
                       :computed-value (m/? (compute-value-on-cpu -1)))]
      new-entity)))

(println "Result:" (m/? task-with-subthreads))
; (out) Result: {:id 100, :name Timur, :computed-value -5.000000046218244E14}

;; exmp 13: sp magic: Internal sub-task are canceled
;;   id:: 8c8538c2-f19a-4b38-b28f-3f84bde7f4e2
;; Running the task in a "raw" way just to show cancellation
(let [cancel-it (task-with-subthreads
                  (bound-fn [result] (println "Result:" result))
                  (bound-fn [ex] (println "Exception:" (type ex))))]
  (Thread/sleep 100)
  (cancel-it)
  (Thread/sleep 300))
; (out) Exception: java.lang.InterruptedException

;; exmp 14: Canceling too early 
;;   id:: ade6c0b5-7508-4d26-b7d2-0fde1d0206e8
;; Running the task in a "raw" way just to show cancellation
(let [cancel-it (task-with-subthreads
                  (bound-fn [result] (println "Result:" result))
                  (bound-fn [ex] (println "Exception:" (type ex))))]
  ;; wait enough for our I/O-bound task to complete, and CPU-bound task to start
  (Thread/sleep 3300)
  (cancel-it)
  ;; then wait enough to see the result
  (Thread/sleep 4000))
; (out) Result: {:id 100, :name Timur, :computed-value -5.000000046218244E14}

;; exmp 15: join: multiple tasks in parallel
;;   id:: c3f4d69b-08e1-4db0-8e36-40efca5e0310
(identity (m/? (m/join vector
                 (m/sleep 1000 :hi)
                 (m/sleep 1500 :there)
                 (m/sleep 500 :fellow)
                 (m/sleep 0 :curious-reader)))) ; [:hi :there :fellow :curious-reader]
(identity (m/? (m/join +
                 (m/sleep 1000 3)
                 (m/sleep 1500 4)))) ; 7
(try
  (str "Result is"
    (m/? (m/join vector
           ;; first task returns :success after 100 ms
           (m/sleep 100 :success)
           ;; second task signals faillure after 400 ms
           (m/sleep 400 (m/? (m/sp
                               (throw (ex-info "Failure in one of the branches" {}))))))))
  (catch Exception ex (println "Exception:" (ex-message ex)))) ; nil
; (out) Exception: Failure in one of the branches

;; exmp 16: Parking tasks with `m/?`
;;   id:: 1e44e190-e1c7-43a6-a56a-c8d147d96a2f
(m/? ;; The outer m/? is here just to force the m/sp to actually run. This one blocks.
  (m/join vector
    ;; all five question marks below run "in parallel" on a single thread, they park their
    ;; parent tasks (generated by m/sp) instead of blocking our thread from doing anything
    (m/sp (m/? (m/sleep 1000 :how)))
    (m/sp (m/? (m/sleep 1000 :do)))
    (m/sp (m/? (m/sleep 1000 :you)))
    (m/sp (m/? (m/sleep 1000 :like)))
    (m/sp (m/? (m/sleep 1000 :this?))))) ; [:how :do :you :like :this?]

;; exmp 17: Inside `sp` you can throw exceptions
;;   id:: a2f19a4c-1ba9-4e0d-9dd0-6a86b20cae35
(m/? (m/sp
       (try
         (m/? (m/sleep 1000))
         (throw (ex-info "Thrown after parking" {}))
         (m/? (m/sleep 1000)) ;; this sleep is not reached
         (catch Exception ex
           (tap> (str "Exception caught in m/sp across parking sites: " (ex-message ex)))
           :successful-value-of-the-task)))) ; :successful-value-of-the-task

;; exmp 18: race: Return fastest task
;;   id:: 4b8c4567-cba0-4f3b-93c9-a82cd32d67d4
(defn request-result-via-api-from-region [region]
  (m/sp ;; wrapping in m/sp so that exception is thrown in task-oriented way and when task is run,
        ;; not when the task is being created (before being run)
    (let [network-delay (case region
                          :frankfurt 30 ;; Frankfurt's data center is 30ms away
                          :sydney 800 ;; Australian one is 800 ms away
                          :boston 250 ;; Boston is 250 ms away
                          (throw (ex-info (str "No data center in " region) {:region region})))]
      (m/? (m/sleep
             network-delay
             (str "Result from " region " after " network-delay " ms delay"))))))

(str "What we have: "
  (m/?
    (m/race
      (request-result-via-api-from-region :sydney)
      (request-result-via-api-from-region :boston)
      ;; request to Dubai fails, we don't have a data center there
      (request-result-via-api-from-region :dubai)))) 
; "What we have: Result from :boston after 250 ms delay"

;; exmp 19: Run a task with a timeout:
;;   id:: 2eaa1bad-0b32-44cd-adbf-4a532b0cf659
(defn with-timeout [some-task timeout-ms timeout-value]
  (m/race
    some-task
    (m/sleep timeout-ms timeout-value)))

(let [result (m/? (with-timeout (request-result-via-api-from-region :sydney)
                   100 "Timeout limit of 100 ms has been reached"))]
  (str "Result: " result)) 
; "Result: Timeout limit of 100 ms has been reached"

;; exmp 20: Timeout aslında çalışmıyor:
;;   id:: 6ea1fb15-64f3-450f-b6e2-747c0cf03702
(try
  (let [result (m/? (with-timeout (request-result-via-api-from-region :jakarta)
                      100 "Timeout limit of 100 ms has been reached"))]
    (str "Result: " result))
  (catch Exception ex (println "Error:" (ex-message ex)))) ; "Result: Timeout limit of 100 ms has been reached"

;; exmp 21: Missionary timeout fn
;;   id:: 24fc9bbd-7961-4695-ab74-19ee5583071e
(try
  (let [result (m/? (m/timeout (request-result-via-api-from-region :jakarta)
                      100 "Timeout limit of 100 ms has been reached"))]
    (str "Result: " result))
  (catch Exception ex (str "Error: " (ex-message ex)))) 
; "Error: No data center in :jakarta"

;; exmp 22: compel: make a task uncancelable
;;   id:: 0a0f9d5c-7c27-43c6-b728-c5530da61c6f
(defn register-business-value-event [& whatever]
  (m/sp
    ;; I've recalled the way to print-debug 
    ;; in a threading-agnostic way, phew
    (println "Attempting to deliver value...")
    (m/? (m/sleep 100))
    (println "Stakeholders have been aligned. Good job.")))

(try
  (println "Result:"
    (m/? (m/join vector
           (request-result-via-api-from-region :sydney)
           (m/compel
             (register-business-value-event {:who? :user :did-what? :click :with-what? :button})))))
  (catch Exception ex (println "Error:" (ex-message ex))))

;; exmp 23: memo: memoize task
;;   id:: c23e25c9-d2dc-4a64-a07d-6ab35c93400c
(defn request-application-settings [central-server]
  (m/sp
    (tap> (str "Requesting settings from " central-server "..."))
    (m/? (m/sleep 1000))
    (tap> (str "Received settings from " central-server "."))
    {:should-start? true}))

(def startup-app-settings-the-wrong-way (request-application-settings "example.com"))

(println "1) Settings are:" (m/? startup-app-settings-the-wrong-way))
(println "2) Settings are:" (m/? startup-app-settings-the-wrong-way))
(println "3) Settings are:" (m/? startup-app-settings-the-wrong-way))

(def startup-app-settings (m/memo (request-application-settings "dont-ddos-me.com")))

(println "1) Memorized settings are" (m/? startup-app-settings))
(println "2) Memorized settings are" (m/? startup-app-settings))
(println "3) Memorized settings are" (m/? startup-app-settings))
;; 1) Settings are: {:should-start? true}
;; 2) Settings are: {:should-start? true}
;; 3) Settings are: {:should-start? true}
;; 1) Memorized settings are {:should-start? true}
;; 2) Memorized settings are {:should-start? true}
;; 3) Memorized settings are {:should-start? true}

;; "Requesting settings from example.com..."
;; "Received settings from example.com."
;; "Requesting settings from example.com..."
;; "Received settings from example.com."
;; "Requesting settings from example.com..."
;; "Received settings from example.com."
;; "Requesting settings from dont-ddos-me.com..."
;; "Received settings from dont-ddos-me.com."

;; exmp 24: never: asla succeed etmeyen bir singleton task
;;   id:: 79d3b87c-15ed-46de-aad9-43c9d1f2ce4e
(println
  (m/?
    (m/timeout m/never ;; NOTE: not (m/never) here, m/never does not MAKE a task, it IS a task
      1000
      "We don't have time to wait forever in this guide, we have to start learning flows")))
; (out) We don't have time to wait forever in this guide, we have to start learning flows

;; exmp 25: Flow: Produces no values and stops
;;   id:: 5eef3a39-8e07-4aea-a6ae-240aec12d8f2
(defn flow-producing-no-values-before-running-out [_value-is-ready! no-more-values!]
  (no-more-values!)
  (reify
    clojure.lang.IFn
    ;; Cancellation function. Note that we're idempotent here
    (invoke [_this] (println "They tried to cancel me :("))
    
    clojure.lang.IDeref
    ;; Value retrieval function. In this case, the value can never be ready, so we crash
    ;; those who tried to retrieve it
    (deref [_this] (throw (ex-info "C'mon man" {})))))

(let [flow-control-panel (flow-producing-no-values-before-running-out
                           #(println "NEW VALUE READY!")
                           #(println "NO MORE NEW VALUES!"))]
  ;; getting a value from the flow:
  (try
    (println "Current value:" @flow-control-panel)
    (catch Throwable th (println "What did I do? Why throw this:" (ex-message th))))
  (println "After try-catch")
  (flow-control-panel)
  (println "After cancel"))
; (out) NO MORE NEW VALUES!
; (out) What did I do? Why throw this: C'mon man
; (out) After try-catch
; (out) They tried to cancel me :(
; (out) After cancel

(let [flow-control-panel (flow-producing-no-values-before-running-out
                           #(println "NEW VALUE READY!")
                           #(println "NO MORE NEW VALUES!"))]
  ;; getting a value from the flow:
  (try
    (println "Current value:" @flow-control-panel))
  (flow-control-panel))
; (out) NO MORE NEW VALUES!
; (err) Execution error (ExceptionInfo) at mert.missionary_01$flow_producing_no_values_before_running_out$reify__8064/deref (form-init12063697500813807062.clj:371).
; (err) C'mon man

;; exmp 26: Flow: single value
;;   id:: 4e7b0810-c8c7-40ca-a6fa-7a52650c6de5
(defn make-a-flow-from-a-single-value [the-single-value]
  (fn flow-producing-no-values-before-running-out [value-is-ready! no-more-values!]
    (value-is-ready!)
    (reify
      clojure.lang.IFn
      (invoke [_]
        (println "Already done by the time you attempted to cancel :)"))
      
      clojure.lang.IDeref
      ;; We already know what value we will produce, so our job here is simple
      (deref [_this]
        (no-more-values!) ;; we indicate that no more values will follow
        the-single-value))))

(let [the-flow (make-a-flow-from-a-single-value :some-valuable-thing)
      flow-control-panel (the-flow
                           #(println "NEW VALUE READY!")
                           #(println "NO MORE NEW VALUES!"))]
  ;; getting a value from the flow:
  (println "Current value:" @flow-control-panel)
  ;; canceling the flow:
  (flow-control-panel))
; (out) NEW VALUE READY!
; (out) NO MORE NEW VALUES!
; (out) Current value: :some-valuable-thing
; (out) Already done by the time you attempted to cancel :)

(let [the-flow (make-a-flow-from-a-single-value :some-valuable-thing)
      flow-control-panel (the-flow
                           #(println "NEW VALUE READY!")
                           #(println "NO MORE NEW VALUES!"))]
  ;; canceling the flow:
  (flow-control-panel))
; (out) NEW VALUE READY!
; (out) Already done by the time you attempted to cancel :)

(let [the-flow (make-a-flow-from-a-single-value :some-valuable-thing)
      flow-control-panel (the-flow
                           #(println "NEW VALUE READY!")
                           #(println "NO MORE NEW VALUES!"))]
  (println "no call to flow"))
; (out) NEW VALUE READY!
; (out) no call to flow

;; exmp 27: none: runs out immediately
;;   id:: 40a97bca-51b1-409b-aac1-f3cd60751cc6
(def running-flow-state (m/none ;; NOTE: not (m/none) here, m/none does not MAKE a flow, it IS a flow
                          #(println "The flow said a value is ready")
                          #(println "The flow said it has run out")))
; (out) The flow said it has run out

;; exmp 28: reduce
;;   id:: a21acbe9-d596-414a-8115-655251814ef3
(println "Reduce of `none` with conj into a list:"
   (m/? (m/reduce conj '() m/none)))
; (out) Reduce of `none` with conj into a list: ()

(println "Reduce of `none` with conj with no initial value:"
  (m/? (m/reduce conj m/none))) ;; conj will be called as a zero-arity function to get an initial value
; (out) Reduce of `none` with conj with no initial value: []

(println "Reduce of flow of [1 2 3 2 1] with conj into a set:"
  (m/? (m/reduce conj #{} (m/seed [1 2 3 2 1]))))
; (out) Reduce of flow of [1 2 3 2 1] with conj into a set: #{1 3 2}

(println "Sum of a finite flow of numbers 0 1 2 3 4 5:"
  (m/? (m/reduce + (m/seed (range 6)))))
; (out) Sum of a finite flow of numbers 0 1 2 3 4 5: 15

;; Print each successive value from a flow
(m/? (m/reduce
       (fn [_ x] (prn x))
       :whatever ;; our reducing function doesn't have a zero-arity, so we have to supply an initial value
       (m/seed (take 6 (cycle (reverse (range 4)))))))
; (out) 3
; (out) 2
; (out) 1
; (out) 0
; (out) 3
; (out) 2

;; exmp 29: zip: map gibi
;;   id:: 7283c683-e6b1-4e35-9068-7d7da8f083a6
(prn (m/? (m/reduce conj {} (m/zip vector
                              (m/seed [:key1 :key2 :key3])
                              (m/seed ["value1" "value2" "value3"])))))
; (out) {:key1 "value1", :key2 "value2", :key3 "value3"}

;; exmp 30: monitoring flows
;;   id:: b9d73220-f035-4bd6-877c-d91f3353bd06
;; This is a helper function that takes a flow and returns the same flow,
;; but also reports whether the flow is initialized
(defn report-whether-flow-initialized [name wrapped-flow]
  (fn [value-is-ready! no-more-values!] ;; we're raw dogging a flow like pros
    (let [called-ready-during-startup? (atom true)
          check-where-we-are-once (delay
                                    (if @called-ready-during-startup?
                                      (tap> (str "> Flow '" name "' is initialized"))
                                      (tap> (str "> Flow '" name "' is UN-initialized")))
                                    :done)
          
          _ (tap> (str "> Flow '" name "' is starting"))
          ;; we're running the flow we're wrapping
          wrapped-flow-control-panel
          #_=> (wrapped-flow
                 (fn wrapped-value-is-ready! []
                   (force check-where-we-are-once) ;; do our check before notifying downstream
                   (value-is-ready!))
                 no-more-values!)]
      (reset! called-ready-during-startup? false)
      (force check-where-we-are-once)
      ;; then we return the canceler/retriever of our original flow
      wrapped-flow-control-panel)))

;; This is also a helper function that packs each value of a flow within [index value],
;; just like you'd do (map-indexed vector some-sequence) — so that when printing each value,
;; we could also print the index of the value, which is very informative.
;; We could have just used (m/zip vector (m/seed (range)) the-flow), but m/zip currently 
;; has an issue with not working properly with some empty or infinite flows — 
;; https://github.com/leonoel/missionary/issues/74 — so we work around that for our
;; educational purposes
(defn mark-each-subsequent-value-with-an-index [wrapped-flow]
  (fn [value-is-ready! no-more-values!] ;; we're going raw again, you like to see it!
    (let [current-index (atom 0)
          wrapped-flow-control-panel (wrapped-flow value-is-ready! no-more-values!)]
      (reify
        clojure.lang.IFn
        (invoke [_]
          (wrapped-flow-control-panel))
        clojure.lang.IDeref
        (deref [_this]
          [(first (swap-vals! current-index inc))
           (deref wrapped-flow-control-panel)])))))

;; I'm using exclamation mark in the name to remind myself
;; in the future that this function blocks
(defn inspect-flow! [name flow]
  (let [MAX_NUMBER_OF_RESULTS_WE_WANT 10
        MILLISECONDS_WE_ARE_READY_TO_WAIT 5000
        result (m/? (m/timeout
                      (m/reduce
                        (fn [last-timestamp-ms [index new-value]]
                          (if (= index MAX_NUMBER_OF_RESULTS_WE_WANT)
                            ;; if we don't want any more values, stop the reduction
                            (reduced ::flow-hasn't-yet-run-out)
                            ;; otherwise report a new value
                            (let [current-timestamp-ms (System/currentTimeMillis)
                                  delay-ms (- current-timestamp-ms last-timestamp-ms)]
                              ;; we use tap> instead of printing, so that we can inspect in any thread
                              ;; in NextJournal environment
                              (tap>
                                (str "> " name "[" index "] after "
                                  delay-ms "ms: " (pr-str new-value)))
                              current-timestamp-ms)))
                        (System/currentTimeMillis) ;; start the clock
                        (->> flow
                          ;; report flow's initial properties
                          (report-whether-flow-initialized name)
                          ;; mark each new value with its index for displaying
                          (mark-each-subsequent-value-with-an-index)))
                      MILLISECONDS_WE_ARE_READY_TO_WAIT
                      ::flow-hasn't-yet-run-out))]
    (if (= ::flow-hasn't-yet-run-out result)
      (tap> (str "> Flow '" name "' continues afterwards..."))
      (tap> (str "> Flow '" name "' has ran out completely.")))))

(inspect-flow! "none" m/none) 
;; "> Flow 'none' is starting"
;; "> Flow 'none' is UN-initialized"
;; "> Flow 'none' has ran out completely."

(inspect-flow! "seed-5-values" (m/seed (range 40 45)))
;; "> Flow 'seed-5-values' is starting"
;; "> Flow 'seed-5-values' is initialized"
;; "> seed-5-values[0] after 0ms: 40"
;; "> seed-5-values[1] after 1ms: 41"
;; "> seed-5-values[2] after 0ms: 42"
;; "> seed-5-values[3] after 0ms: 43"
;; "> seed-5-values[4] after 0ms: 44"
;; "> Flow 'seed-5-values' has ran out completely."

(inspect-flow! "seed-15-values" (m/seed (range 50 65)))
;; "> Flow 'seed-15-values' is starting"
;; "> Flow 'seed-15-values' is initialized"
;; "> seed-15-values[0] after 0ms: 50"
;; "> seed-15-values[1] after 0ms: 51"
;; "> seed-15-values[2] after 0ms: 52"
;; "> seed-15-values[3] after 0ms: 53"
;; "> seed-15-values[4] after 0ms: 54"
;; "> seed-15-values[5] after 0ms: 55"
;; "> seed-15-values[6] after 1ms: 56"
;; "> seed-15-values[7] after 0ms: 57"
;; "> seed-15-values[8] after 0ms: 58"
;; "> seed-15-values[9] after 0ms: 59"
;; "> Flow 'seed-15-values' continues afterwards..."

(inspect-flow! "seed-infinite" (m/seed (range)))

;; exmp 31: ap
;;   id:: 81aa8f89-bc77-47d4-af9a-422e09641dc1
(inspect-flow! "empty-body" (m/ap))
;; "> empty-body[0] after 19ms: nil"
;; "> Flow 'empty-body' has ran out completely."

(inspect-flow! "body-of-just-nil" (m/ap nil))
;; "> Flow 'body-of-just-nil' is starting"
;; "> Flow 'body-of-just-nil' is initialized"
;; "> body-of-just-nil[0] after 0ms: nil"
;; "> Flow 'body-of-just-nil' has ran out completely."

;; exmp 32: ap: uninitialized 
;;   id:: 8f47ffcb-e5d9-46fe-b539-754e5d7e2fb8
(inspect-flow! "produce-delayed-value"
  (m/ap
    (m/? (m/sleep 100 :my-delayed-value))))
;; "> Flow 'produce-delayed-value' is starting"
;; "> Flow 'produce-delayed-value' is UN-initialized"
;; "> produce-delayed-value[0] after 108ms: :my-delayed-value"
;; "> Flow 'produce-delayed-value' has ran out completely.

;; exmp 33: ap: why ambiguous?
;;   id:: 7c182a0b-1631-4807-8ee3-86ff819f6518
(inspect-flow! "empty-flow"
  (m/ap
    (m/amb)))
;; "> Flow 'empty-flow' is starting"
;; "> Flow 'empty-flow' is UN-initialized"
;; "> Flow 'empty-flow' has ran out completely."

(def should-be-empty?)
(def the-ambiguous-flow
  (m/ap
    (if should-be-empty?
      (m/amb)
      (m/amb :result))))

(alter-var-root #'should-be-empty? (constantly true))
(inspect-flow! "empty-or-not" the-ambiguous-flow)
;; "> Flow 'empty-or-not' is starting"
;; "> Flow 'empty-or-not' is UN-initialized"
;; "> Flow 'empty-or-not' has ran out completely."

(alter-var-root #'should-be-empty? (constantly false))
(inspect-flow! "empty-or-not" the-ambiguous-flow)
;; "> Flow 'empty-or-not' is starting"
;; "> Flow 'empty-or-not' is initialized"
;; "> empty-or-not[0] after 1ms: :result"
;; "> Flow 'empty-or-not' has ran out completely."

(inspect-flow! "two-values"
  (m/ap
    (m/amb :value1 :value2)))
;; "> Flow 'two-values' is starting"
;; "> Flow 'two-values' is initialized"
;; "> two-values[0] after 1ms: :value1"
;; "> two-values[1] after 0ms: :value2"
;; "> Flow 'two-values' has ran out completely."

;; exmp 34: `amb` forks flows
;;   id:: 3b3e1d18-fd61-4f9e-bbcc-7b1eb8bc181e
(def our-collection [:value1 :value2])

(inspect-flow! "values-from-a-collection"
  (m/ap
    (println "Let's start")
    (loop [coll (seq our-collection)]
      (if (nil? coll)
        (m/amb) ;; if we've finished with our collection, produce no more elements
        (m/amb ;; if not:
          (first coll) ;; produce the first element
          (recur (next coll))))))) ;; WTF??
; (out) Let's start
;; "> Flow 'values-from-a-collection' is starting"
;; "> Flow 'values-from-a-collection' is initialized"
;; "> values-from-a-collection[0] after 1ms: :value1"
;; "> values-from-a-collection[1] after 0ms: :value2"
;; "> Flow 'values-from-a-collection' has ran out completely."

;; exmp 35: `amb=` executes flows in parallel
;;   id:: cfc95abb-7583-4def-9ac7-12ed9f017eb3
(inspect-flow! "amb"
  (m/ap
    (m/amb
      (m/? (m/sleep 400 :delay400))
      (m/? (m/sleep 300 :delay300))
      (m/? (m/sleep 200 :delay200))
      (m/? (m/sleep 100 :delay100)))))

(inspect-flow! "amb="
  (m/ap
    (m/amb=
      (m/? (m/sleep 400 :delay400))
      (m/? (m/sleep 300 :delay300))
      (m/? (m/sleep 200 :delay200))
      (m/? (m/sleep 100 :delay100)))))
;; "> Flow 'amb' is starting"
;; "> Flow 'amb' is UN-initialized"
;; "> amb[0] after 404ms: :delay400"
;; "> amb[1] after 301ms: :delay300"
;; "> amb[2] after 200ms: :delay200"
;; "> amb[3] after 100ms: :delay100"
;; "> Flow 'amb' has ran out completely."
;; "> Flow 'amb=' is starting"
;; "> Flow 'amb=' is UN-initialized"
;; "> amb=[0] after 106ms: :delay100"
;; "> amb=[1] after 103ms: :delay200"
;; "> amb=[2] after 101ms: :delay300"
;; "> amb=[3] after 99ms: :delay400"
;; "> Flow 'amb=' has ran out completely."

;; exmp 36: `?>` forks for each new value
;;   id:: 2043c072-b0b7-4679-b8f7-27e6e9651dde
(def a-collection-of-numbers [7 6 5 4 3 2 1])
(def delayed-numbers-flow ;; we will be using it in further examples
  (m/ap
    (let [the-number (m/?> (m/seed a-collection-of-numbers))]
      ;; we play our delay first
      (m/? (m/sleep 80))
      ;; only after the delay we produce a number in this scenario
      the-number)))

(inspect-flow! "delayed-numbers" delayed-numbers-flow)
;; "> Flow 'delayed-numbers' is starting"
;; "> Flow 'delayed-numbers' is UN-initialized"
;; "> delayed-numbers[0] after 83ms: 7"
;; "> delayed-numbers[1] after 81ms: 6"
;; "> delayed-numbers[2] after 80ms: 5"
;; "> delayed-numbers[3] after 81ms: 4"
;; "> delayed-numbers[4] after 80ms: 3"
;; "> delayed-numbers[5] after 80ms: 2"
;; "> delayed-numbers[6] after 80ms: 1"
;; "> Flow 'delayed-numbers' has ran out completely."

;; exmp 37: make a continuous flow from discrete flow
;;   id:: 80ed1ebb-f7c9-45b6-83c6-f3b83d41d9d5
;; the time logic is kinda buggy (might skip a second or emit the same second twice), 
;; but not a big deal for our example, let's not overcomplicate the code
(def discrete-flow-of-unix-timestamps
  (m/ap
    (tap> "Starting our clock...") ;; we'll need this later
    (loop []
      (let [now-ms (System/currentTimeMillis)
            now-s (quot now-ms 1000)
            next-s (inc now-s)
            ms-until-next-s (- (* next-s 1000) now-ms)]
        (m/? (m/sleep ms-until-next-s))
        (m/amb
          next-s
          (recur))))))

(inspect-flow! "discrete" discrete-flow-of-unix-timestamps)
;; "> Flow 'discrete' is starting"
;; "Starting our clock..."
;; "> Flow 'discrete' is UN-initialized"
;; "> discrete[0] after 112ms: 1739274959"
;; "> discrete[1] after 1005ms: 1739274960"
;; "> discrete[2] after 991ms: 1739274961"
;; "> discrete[3] after 1002ms: 1739274962"
;; "> discrete[4] after 1001ms: 1739274963"
;; "> Flow 'discrete' continues afterwards..."

(def prepared-flow-of-unix-timestamps
  (->> discrete-flow-of-unix-timestamps
    (m/reductions {} :unknown) ;; initializing here
    (m/relieve {}))) ;; decoupling here

(def continuous-flow-of-unix-timestamps (m/signal prepared-flow-of-unix-timestamps))

(inspect-flow! "continuous" continuous-flow-of-unix-timestamps)
;; "> Flow 'continuous' is starting"
;; "Starting our clock..."
;; "> Flow 'continuous' is initialized"
;; "> continuous[0] after 17ms: :unknown"
;; "> continuous[1] after 955ms: 1739275059"
;; "> continuous[2] after 995ms: 1739275060"
;; "> continuous[3] after 1002ms: 1739275061"
;; "> continuous[4] after 997ms: 1739275062"
;; "> continuous[5] after 1003ms: 1739275063"
;; "> Flow 'continuous' continues afterwards..."

