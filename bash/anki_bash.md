
## cat sed between strings

cat file | sed -n "{{c1::/from/,/to/}} {{c2::p}}"

%

%

clozeq

---

## using tilde inside strings

Which ones work properly? 1 2 3?

··  DATA—MODEL—DIR=~/projects/itr/itr—documentation/data—model <br>
··  DATA—MODEL—DIR="/Users/mertnuhoglu/projects/itr/itr—documentation/data—model" <br>
··  DATA—MODEL—DIR="~/projects/itr/itr—documentation/data—model" <br>

··  cd $DATA—MODEL—DIR <br>


%

1 and 2 work. 3 doesn't work.

%

---

## moving in cli

··  {{c1::A/E}}: start/end  <br>
····  {{c2::ctrl}}: line <br>
··  {{c3::B/F}}: start/end  <br>
····  {{c4::alt}}: word <br>
····  {{c5::ctrl}}: letter <br>

%

%

clozeq

---

## pretty print json with underscore / jq

··  echo '{"a":2}' | underscore {{c1::print}} <br>

··  echo '{"a":2}' | underscore print {{c2::--color}} <br>
··   <br>
··  underscore print {{c3::--in}} data/plan—jobs.json <br>

··  jq {{c4::.}} data/plan—jobs.json <br>
%

%

clozeq

---

## ag options

limit file types

··  ag {{c1::-G}} csv <br>
··  ag --list{{c2::-file-types}} <br>
··  ag -G {{c3::'\.md$'}} search—in—md <br>

%

%

clozeq

---

