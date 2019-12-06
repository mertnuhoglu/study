
## clj: function argument

··  `` (defn hello [{{c1::name}}] (str "Hello, " name)) `` <br>

%

%

clozeq
active
clojure

---

## clj: calling java static method

··  `` System.getProperties() `` <br>

-&gt;

··  `` (System/{{c1::getProperties}}) `` <br>

%

%

clozeq
active
clojure

---

## clj: java class

··  `` class Account { `` <br>
····  `` String id; `` <br>
····  `` Double balance; `` <br>
··  `` } `` <br>

-&gt;

··  `` ({{c1::defrecord}} Account [id balance]) `` <br>

%

%

clozeq
active
clojure

---

