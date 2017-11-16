CREATE TABLE Book(  --df: mult=100.0
  bid SERIAL PRIMARY KEY,
  title TEXT NOT NULL, 
  -- df English: word=/Users/mertnuhoglu/projects/test_data/google-10000-english-no-swears.txt
  -- df: text=English length=4 lenvar=3
  isbn ISBN13 NOT NULL 
);
CREATE TABLE Reader( 
  rid SERIAL PRIMARY KEY,
  firstname TEXT NOT NULL, 
  -- df first: word=/Users/mertnuhoglu/projects/test_data/first_names.txt
  -- df: text=first length=1 lenvar=0
  lastname TEXT NOT NULL, 
  -- df last: word=/Users/mertnuhoglu/projects/test_data/last_names.txt
  -- df: text=last length=1 lenvar=0
  born DATE NOT NULL, -- df: start=1950-01-01 end=2005-01-01
  gender BOOLEAN NOT NULL, 
  phone TEXT 
  -- df: chars='0-9' length=10 lenvar=0
  -- df: null=0.01 size=1000000
);
CREATE TABLE Borrow( --df: mult=1.5
  borrowed TIMESTAMP NOT NULL, 
  rid INTEGER NOT NULL REFERENCES Reader,
  bid INTEGER NOT NULL REFERENCES Book, 
  PRIMARY KEY(bid) -- a book is borrowed once at a time!
);


