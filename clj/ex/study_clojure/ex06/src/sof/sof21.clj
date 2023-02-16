(ns sof.sof21)

; [Clojure nil vs Java null? - Stack Overflow](https://stackoverflow.com/questions/691925/clojure-nil-vs-java-null)

; The only difference between nil and null is that one is Clojure and the other is Java,
; but they're essentially aliases,
; converted back and forth seamlessly as needed by the reader and printer when going from Clojure to Java to Clojure.

; From the Clojure source code, lang/LispReader.java:
;
; static private Object interpretToken(String s) throws Exception{
;     if(s.equals("nil"))
;         {
;         return null;
;         }
; From lang/RT.java:
;
; static public void print(Object x, Writer w) throws Exception{
;     {
;     ...
;     if(x == null)
;         w.write("nil");
; ;
; ;
