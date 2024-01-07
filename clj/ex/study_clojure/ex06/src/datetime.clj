(ns datetime)

; datetime.clj id=g13218
;   id:: 0cac4a32-dcd1-44b8-8e53-6f1ca28d8bcc

(require '[tick.core :as t])

(t/time)  ; #time/time "19:31:50.416020"

(t/time "19:45") ; #time/time "19:45"
(t/day-of-week (t/today)) ; #time/day-of-week "SUNDAY"
(t/day-of-week) ; #time/day-of-week "SUNDAY"
(t/day-of-week (t/tomorrow)) ; #time/day-of-week "MONDAY"
(t/date) ; #time/date "2022-08-21"
(t/today) ; #time/date "2022-08-21"
(t/date "2021-06-21") ; #time/date "2021-06-21"
(t/date-time) ; #time/date-time "2022-08-21T19:32:05.736875"
(t/date-time "1918-11-11T11:00") ; #time/date-time "1918-11-11T11:00"
(t/offset-date-time "1918-11-11T11:00:00+01:00") ; #time/offset-date-time "1918-11-11T11:00+01:00"
(t/zoned-date-time "1918-11-11T11:00:00Z[Europe/Paris]") ; #time/zoned-date-time "1918-11-11T11:00Z[Europe/Paris]"
(t/instant (t/offset-date-time "1918-11-11T11:00:00+01:00")) ; #time/instant "1918-11-11T10:00:00Z"
(t/instant) ; #time/instant "2022-08-21T16:32:12.607642Z"
(t/now) ; #time/instant "2022-08-21T16:32:13.726226Z"
(t/inst (t/now)) ; #inst "2022-08-21T16:32:14.861-00:00"
(t/inst) ; #inst "2022-08-21T16:32:15.963-00:00"
(t/new-time 11 0) ; #time/time "11:00"
(t/new-time 11 0 59) ; #time/time "11:00:59"
(t/new-time 11 0 59 123456) ; #time/time "11:00:59.000123456"

(type (t/date)) ; java.time.LocalDate
(type (t/date "2021-06-21")) ; java.time.LocalDate
(type (t/now)) ; java.time.Instant
(type (t/offset-date-time "1918-11-11T11:00:00+01:00")) ; java.time.OffsetDateTime
(type (t/zoned-date-time "1918-11-11T11:00:00Z[Europe/Paris]")) ; java.time.ZonedDateTime
(type (t/instant)) ; java.time.Instant
(type (t/today)) ; java.time.LocalDate
(type (t/day-of-week)) ; java.time.DayOfWeek
(type (t/time)) ; java.time.LocalTime
(type (t/new-time 11 0)) ; java.time.LocalTime
(type (t/inst)) ; java.util.Date

(t/new-date 2013 3 22) ; #time/date "2013-03-22"
(t/new-year-month 2013 3) ; #time/year-month "2013-03"
(-> (t/date "1918-11-11") (t/at "11:00")) ; #time/date-time "1918-11-11T11:00"
(-> (t/time "11:00") (t/on "1918-11-11")) ; #time/date-time "1918-11-11T11:00"
(-> (t/time "11:00") (t/on "1918-11-11") (t/in "Europe/Paris")) ; #time/zoned-date-time "1918-11-11T11:00Z[Europe/Paris]"
(t/time (t/in (t/now) "Australia/Darwin")) ; #time/time "04:34:59.238571"
(t/date (t/in (t/instant "1999-12-31T23:59:59Z") "Australia/Darwin")) ; #time/date "2000-01-01"
(-> (t/time "11:00") (t/on "1918-11-11") (t/offset-by 2)) ; #time/offset-date-time "1918-11-11T11:00+02:00"
(t/zoned-date-time (t/now)) ; #time/zoned-date-time "2022-08-21T22:05:02.269703+03:00[Europe/Istanbul]"
(t/instant (t/zoned-date-time)) ; #time/instant "2022-08-21T19:05:03.513530Z"
(t/inst (t/now)) ; #inst "2022-08-21T19:05:04.577-00:00"
(t/time (t/instant "1999-12-31T00:59:59Z")) ; #time/time "02:59:59"
(t/date (t/instant "1999-12-31T23:59:59Z")) ; #time/date "2000-01-01"
(t/day-of-week (t/date "2018-07-09")) ; #time/day-of-week "MONDAY"
(t/day-of-month (t/instant "1999-12-31T23:59:59Z")) ; 1
(t/month (t/date "2018-07-09")) ; #time/month "JULY"
(t/month (t/instant "1999-12-31T23:59:59Z")) ; #time/month "JANUARY"
(t/year (t/date "2018-07-09")) ; #time/year "2018"
(t/year (t/instant "1999-12-31T23:59:59Z")) ; #time/year "2000"
(t/hour (t/instant "1999-12-31T23:59:59Z")) ; 1
(t/minute (t/instant "1999-12-31T23:59:59Z")) ; 59
(t/second (t/instant "1999-12-31T23:59:59Z")) ; 59
(t/zone (t/zoned-date-time "2000-01-01T00:00:00Z[Europe/Paris]")) ; #time/zone "Europe/Paris"
(= (t/day-of-week (t/date "2018-07-09")) t/MONDAY) ; true
(= (t/month (t/date "2018-07-09")) t/MAY) ; false

(type (t/new-year-month 2013 3)) ; java.time.YearMonth
(type (t/month (t/date "2018-07-09"))) ; java.time.Month
(type (t/day-of-month (t/instant "1999-12-31T23:59:59Z"))) ; java.lang.Integer
(type (t/year (t/date "2018-07-09"))) ; java.time.Year
(type (t/hour (t/instant "1999-12-31T23:59:59Z"))) ; java.lang.Integer
(type (t/zone (t/zoned-date-time "2000-01-01T00:00:00Z[Europe/Paris]"))) ; java.time.ZoneRegion

(t/new-duration 1 :seconds) ; #time/duration "PT1S"
(t/new-duration 100 :days) ; #time/duration "PT2400H"
(t/new-period 100 :days) ; #time/period "P100D"
(t/new-period 2 :months) ; #time/period "P2M"
(t/day-of-week "mon") ; #time/day-of-week "MONDAY"
(t/month "August") ; #time/month "AUGUST"
(t/month 12) ; #time/month "DECEMBER"
(t/year-month "2012-12") ; #time/year-month "2012-12"
(t/year 1999) ; #time/year "1999"
(t/instant (t/new-duration 1531467976048 :millis)) ; #time/instant "2018-07-13T07:46:16.048Z"
(t/inst (t/new-duration 1531468976 :seconds)) ; #inst "2018-07-13T08:02:56.000-00:00"
(t/clock) ; #object[java.time.Clock$SystemClock 0x33941601 "SystemClock[Europe/Istanbul]"]
(t/clock "1999-12-31T23:59:59") ; #object[java.time.Clock$FixedClock 0x7d5de3ef "FixedClock[1999-12-31T21:59:59Z,Europe/Istanbul]"]

(type (t/new-duration 1 :seconds)) ; java.time.Duration
(type (t/new-period 100 :days)) ; java.time.Period
(type (t/clock)) ; java.time.Clock$SystemClock

(require '[tick.alpha.interval :as t.i])
{:tick/beginning "2018-12-31T23:55:00Z"
 :tick/end "2019-01-01T00:00:00Z"}
(t.i/new-interval
  (t/instant "2018-12-31T23:55:00Z")
  (t/instant "2019-01-01T00:00:00Z"))
; #:tick{:beginning #time/instant "2018-12-31T23:55:00Z",
;        :end #time/instant "2019-01-01T00:00:00Z"}
(t.i/bounds (t/today))
; #:tick{:beginning #time/date-time "2022-08-21T00:00",
;        :end #time/date-time "2022-08-22T00:00"}
(t.i/new-interval (t/today) (t/tomorrow))
; #:tick{:beginning #time/date-time "2022-08-21T00:00",
;        :end #time/date-time "2022-08-23T00:00"}
(t.i/relation (t/yesterday) (t/tomorrow)) ; :precedes
(t.i/relation (t/today) (t/tomorrow)) ; :meets

(type (t.i/bounds (t/today))) ; clojure.lang.PersistentArrayMap
(type (t.i/relation (t/today) (t/tomorrow))) ; clojure.lang.Keyword

(t/new-time (t/hour (t/instant)) 30) 
; => 13:30
(t/new-time 13 (t/minute (t/instant))) 
; => 13:10
(t/at (t/date "2018-01-01") (t/time "13:00")) 
; => 2018-01-01T13:00
(t/on (t/time "13:00") (t/date "2018-01-01")) 
; => 2018-01-01T13:00
(-> (t/tomorrow)
    (t/at (t/midnight))) 
; => 2022-08-17T00:00
(-> (t/noon)
    (t/on (t/yesterday))) 
; => 2022-08-15T12:00
(-> (t/tomorrow)
    (t/at (t/midnight))
    (t/in "Europe/Paris")) 
; => 2022-08-17T00:00+02:00[Europe/Paris]
(-> (t/tomorrow)
    (t/at (t/midnight))
    (t/in (t/zone))) 
; => 2022-08-17T00:00+03:00[Europe/Istanbul]
(t/>> (t/date "2000-01-01") (t/new-period 1 :months)) 
; => 2000-02-01
(t/>> (t/date "2000-01-01") (t/new-period 4 :weeks)) 
; => 2000-01-29
(t/>> (t/date "2000-01-01") (t/new-period 30 :days)) 
; => 2000-01-31
(t/>> (t/date "2000-01-01") (t/+ (t/new-period 5 :days)
                                (t/new-period 1 :weeks)
                                (t/new-period 10 :months))) 
; => 2000-11-13
(t/<< (t/date "2000-01-01") (t/new-period 1 :years)) 
; => 1999-01-01
(t/>> (t/time "12:00") (t/new-duration 5 :minutes)) 
; => 12:05
(t/<< (t/time "12:00") (t/new-duration 5 :hours)) 
; => 07:00
(t/>> (t/time "12:00") (t/+ (t/new-duration 5 :seconds)
                           (t/new-duration 5 :millis)
                           (t/new-duration 5 :micros)
                           (t/new-duration 5 :nanos))) 
; => 12:00:05.005005005
(t/>> (t/time "12:00") (t/new-duration 5 :days)) 
; => 12:00
(t/truncate (t/time "10:30:59.99") :minutes) 
; => 10:30
(t/truncate (t/time "10:30:59.99") :minutes) 
; => 10:30
(t/now) 
; => 2022-08-16T10:38:29.487Z
(t/instant "2000-01-01T00:00:00.001Z") 
; => 2000-01-01T00:00:00.001Z
(t/instant (t/inst)) 
; => 2022-08-16T10:38:48.692Z
(t/inst (t/instant)) 
; => Tue Aug 16 2022 13:38:54 GMT+0300 (GMT+03:00)
(t/zone (t/zoned-date-time "2000-01-01T00:00:00Z[Europe/Paris]")) 
; => Europe/Paris
(t/zone) 
; => Europe/Istanbul
(t/in (t/instant "2000-01-01T00:00:00.00Z") "Australia/Darwin") 
; => 2000-01-01T09:30+09:30[Australia/Darwin]
(t/offset-date-time (t/zoned-date-time "2000-01-01T00:00:00Z[Australia/Darwin]")) 
; => 2000-01-01T09:30+09:30
(t/offset-by (t/date-time "2018-01-01T00:00") 9) 
; => 2018-01-01T00:00+09:00
(t.i/new-interval (t/date-time "2000-01-01T00:00")
                (t/date-time "2001-01-01T00:00")) 
; => {:tick/beginning #time/date-time "2000-01-01T00:00", :tick/end #time/date-time "2001-01-01T00:00"}
{:tick/beginning (t/date-time "2000-01-01T00:00")
 :tick/end (t/date-time "2001-01-01T00:00")} 
; => {:tick/beginning #time/date-time "2000-01-01T00:00", :tick/end #time/date-time "2001-01-01T00:00"}
(t.i/bounds (t/year 2000)) 
; => {:tick/beginning #time/date-time "2000-01-01T00:00", :tick/end #time/date-time "2001-01-01T00:00"}
(t.i/extend (t/instant "2000-01-01T00:00:00.00Z")
  (t/new-period 3 :weeks)) 
; => {:tick/beginning #time/instant "2000-01-01T00:00:00Z", :tick/end #time/instant "2000-01-22T00:00:00Z"}
(t.i/extend (t.i/bounds (t/year 2000)) (t/new-period 1 :years)) 
; => {:tick/beginning #time/date-time "2000-01-01T00:00", :tick/end #time/date-time "2002-01-01T00:00"}
(t.i/extend (t.i/bounds (t/year 2000)) (t/new-period -1 :months)) 
; => {:tick/beginning #time/date-time "2000-01-01T00:00", :tick/end #time/date-time "2000-12-01T00:00"}
(t/<< (t.i/bounds (t/year 2000)) (t/new-period 6 :months)) 
; => {:tick/beginning #time/date-time "1999-07-01T00:00", :tick/end #time/date-time "2000-07-01T00:00"}
(t/>> (t.i/bounds (t/today)) (t/new-duration 1 :half-days)) 
; => {:tick/beginning #time/date-time "2022-08-17T12:00", :tick/end #time/date-time "2022-08-18T12:00"}
(t/>> (t/now)
     (t/new-duration 15 :minutes)) 
; => 2022-08-17T10:12:29.074ZEvalClr
(t/<< (t/now)
     (t/new-duration 10 :days)) 
; => 2022-08-07T09:59:21.457Z
(map #(apply t.i/new-interval %)
     (t.i/divide-by 24 {:tick/beginning (t/instant "2000-01-01T00:00:00.00Z")}
                      :tick/end (t/instant "2000-01-02T00:00:00.00Z")))
(t.i/divide-by (t/new-duration 1 :hours
                {:tick/beginning (t/instant "2000-01-01T00:00:00.00Z")
                 :tick/end (t/instant "2000-01-02T00:00:00.00Z")}))
(t/+ (t/new-duration 1 :hours)
     (t/new-duration 10 :minutes)) 
; => PT1H10M
(t/- (t/new-duration 1 :hours)
     (t/new-duration 10 :minutes)) 
; => PT50M
(t.i/divide (t/new-duration 1 :hours)
          (t/new-duration 1 :minutes)) 
; => 60
(t/< (t/instant "2000-01-01T00:00:00.00Z") (t/instant "2018-01-01T00:00:00.00Z")) 
; => true
(t/> (t/instant "2000-01-01T00:00:00.00Z") (t/instant "2018-01-01T00:00:00.00Z")) 
; => false
(t/<= (t/instant "2000-01-01T00:00:00.00Z") (t/instant "2018-01-01T00:00:00.00Z")) 
; => true
(t/ago (t/new-duration 1 :hours)) 
; => 2022-08-17T09:29:40.288Z
(t.i/am (t/date "2018-01-01")) 
; => {:tick/beginning #time/date-time "2018-01-01T00:00", :tick/end #time/date-time "2018-01-01T12:00"}

(t/beginning (t/today)) 
; => 2022-08-17T00:00
(t/between (t/instant "2000-01-01T00:00:00.00Z") (t/instant "2018-01-01T00:00:00.00Z")) 
; => PT157800H
(t.i/bounds (t/yesterday)) 
; => {:tick/beginning #time/date-time "2022-08-16T00:00", :tick/end #time/date-time "2022-08-17T00:00"}
(t/clock) 
; => SystemClock[Europe/Istanbul]
(t/clock (t/instant "2018-01-01T00:00:00.00Z")) 
; => FixedClock[]
(t/coincident? (t/today) (t/today)) 
; => true
(t/dec (t/year)) 
; => 2021
(t.i/difference [(t/yesterday) (t/today) (t/tomorrow)] [(t/today)]) 
; => (#time/date "2022-08-16" #time/date "2022-08-18")
(t/hence (t/new-duration 15 :minutes)) 
; => 2022-08-17T11:49:19.334Z
(t/hour (t/now)) 
; => 14
(t/hours (t/new-duration 2 :days)) 
; => 48
(t/inc (t/year)) 
; => 2023
(t/inst) 
; => Wed Aug 17 2022 14:35:24 GMT+0300 (GMT+03:00)
(t/inst (t/instant "2018-01-01T00:00:00.00Z")) 
; => Mon Jan 01 2018 03:00:00 GMT+0300 (GMT+03:00)
(t/instant (t/inst)) 
; => 2022-08-17T11:36:10.126Z
(t/instant (t/zoned-date-time "2018-01-01T00:00:00.000+09:30[Australia/Darwin]")) 
; => 2017-12-31T14:30:00Z
(t/int (t/year)) 
; => 2022
(t.i/intersection [(t/year)]
                [(t/date "2019-01-01")
                 (t/date "2020-01-01")
                 (t/date "2021-01-01")]) 
; => ()
(t.i/intersects? [(t/year)]
               (t/inc (t/year))) 
; => 
(t.i/intersects? [(t/year)]
                (t/today)) 
; => ({:tick/beginning #time/date-time "2022-08-17T00:00", :tick/end #time/date-time "2022-08-18T00:00"})
(t/long (t/instant)) 
; => 1660736288
(t/max (t/today) (t/tomorrow) (t/yesterday) (t/new-date 2018 11 11)) 
; => 2022-08-18
(t/micros (t/new-duration 5 :minutes)) 
; => 300000000
(t/microsecond (t/now)) 
; => 892000
(t/midnight) 
; => 00:00
(t/midnight? (t/date-time)) 
; => false
(t/millis (t/new-duration 5 :minutes)) 
; => 300000
(t/millisecond (t/now)) 
; => 669
(t/min (t/today) (t/tomorrow) (t/yesterday) (t/new-date 2018 11 11)) 
; => 2018-11-11
(t/minutes (t/new-duration 5 :hours)) 
; => 300
(t/minute (t/now)) 
; => 32
(t/month) 
; => AUGUST
(t/month "2018-11-11") 
; => NOVEMBER
(t/months (t/new-period 10 :months)) 
; => 10
(t/new-date 2000 01 01) 
; => 2000-01-01
(t/new-duration 10 :minutes) 
; => PT10M
(t/new-period 10 :weeks) 
; => P70D
(t/new-time) 
; => 16:36:17.155
(t/new-time 12 00) 
; => 12:00
(t/noon) 
; => 12:00
(t/range (t/date-time "2000-01-01T12:00")
   (t/date-time "2000-01-01T12:05")
   (t/new-duration 1 :minutes)) 
; => (#time/date-time "2000-01-01T12:00" #time/date-time "2000-01-01T12:01" #time/date-time "2000-01-01T12:02" #time/date-time "2000-01-01T12:03" #time/date-time "2000-01-01T12:04")
(t/truncate (t/instant) :days) 
; => 2022-08-17T00:00:00Z
(t.i/union [(t/today)] [(t/yesterday) (t/tomorrow)]) 
; => (#time/date "2022-08-16" #time/date "2022-08-17" #time/date "2022-08-18")
(keys t/unit-map) 
; => (:nanos :forever :months :days :half-days :micros :seconds :centuries :decades :hours :years :minutes :eras :millennia :weeks :millis)
(:minutes t/unit-map) 
; => Minutes
(t/units (t/new-duration 1000000001 :nanos)) 
; => {:seconds 1, :nanos 1}
(t/with (t/today) :day-of-month 1) 
; => 2022-08-01
(t/year-month (t/date "2000-01-01")) 
; => 2000-01
(t/years (t/new-period 10 :years)) 
; => 10
(t/yesterday) 
; => 2022-08-16
(t/zone (t/zoned-date-time "2000-01-01T00:00:00Z[Australia/Darwin]")) 
; => Australia/Darwin
(t/zone-offset 1 30 59) 
; => +01:30:59
(t/zoned-date-time "2000-01-01T00:00:00Z[Australia/Darwin]") 
; => 2000-01-01T09:30+09:30[Australia/Darwin]

