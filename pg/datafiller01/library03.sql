-- df English: word=/Users/mertnuhoglu/projects/test_data/google-10000-english-no-swears.txt
-- df first: word=/Users/mertnuhoglu/projects/test_data/first_names.txt
-- df last: word=/Users/mertnuhoglu/projects/test_data/last_names.txt
CREATE TABLE Book(  --df: mult=2.0
  bid SERIAL PRIMARY KEY,
  title TEXT NOT NULL, 
  -- df: text=English length=4 lenvar=3
  isbn INTEGER NOT NULL 
);
CREATE TABLE Reader( 
  rid SERIAL PRIMARY KEY,
  firstname TEXT NOT NULL, 
  -- df: text=first length=1 lenvar=0
  lastname TEXT NOT NULL, 
  -- df: text=last length=1 lenvar=0
  born DATE NOT NULL, -- df: start=1950-01-01 end=2005-01-01
  gender BOOLEAN NOT NULL, 
  -- df: rate=0.25
  phone TEXT 
  -- df: chars='0-9' length=10 lenvar=0
  -- df: null=0.01 size=1000000
);
CREATE TABLE Borrow( --df: mult=1.5
  borrowed TIMESTAMP NOT NULL, 
  -- df: size=72000 prec=60
  rid INTEGER NOT NULL REFERENCES Reader,
  bid INTEGER NOT NULL REFERENCES Book, 
  note TEXT,
  -- df: sub=power prefix=note size=1000 rate=0.03
  PRIMARY KEY(bid) -- a book is borrowed once at a time!
);



