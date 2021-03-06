
-- data generated by /usr/local/bin/datafiller version 2.0.0 (r792 on 2014-03-23) for postgresql

-- fill table book (6)
\echo # filling table book (6)
COPY book (bid,title,isbn) FROM STDIN (ENCODING 'utf-8');
1	title_1_1_1_	9784413254182
2	title_4_4_4_4_4	9782571851779
3	title_3_3_3	9787601387877
4	title_2_2_2_2_2_	9788305707527
5	title_5_5_	9782571851779
6	title_4_4_4_4_4	9782571851779
\.

-- fill table reader (2)
\echo # filling table reader (2)
COPY reader (rid,firstname,lastname,born,gender,phone) FROM STDIN (ENCODING 'utf-8');
1	firstname_2_2_2	lastname	2017-12-20	TRUE	phone_2_2
2	firstname_2_2_2	lastname_1	2017-12-21	TRUE	phone_2_2
\.

-- fill table borrow (3)
\echo # filling table borrow (3)
COPY borrow (borrowed,rid,bid) FROM STDIN (ENCODING 'utf-8');
2017-12-21 16:37:28	2	1
2017-12-21 16:37:28	2	2
2017-12-21 16:37:28	2	3
\.

-- restart sequences
ALTER SEQUENCE book_bid_seq RESTART WITH 7;
ALTER SEQUENCE reader_rid_seq RESTART WITH 3;

-- analyze modified tables
ANALYZE book;
ANALYZE reader;
ANALYZE borrow;
