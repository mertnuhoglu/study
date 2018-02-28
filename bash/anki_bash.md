
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

