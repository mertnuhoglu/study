    <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md>

_ id=r_lastid adb_016

ref

    ~/projects/study/py/jupyter/study_sql_01.ipynb
		<url:file:///~/Dropbox/mynotes/content/code/cdb.md>

# Articles DB

## OReilly.Designing.Data-Intensive.Applications.1449373321.pdf

    Preface
      data intensive app
        data is its primary challenge
          quantity, complexity, speed of data
        as opposed to compute-intensive
          where processing speed is bottleneck
    CHAPTER 1 Reliable, Scalable, and Maintainable Applications
      common functionality of data-intensive applications
        store and find data (databases)
        remember the result of expensive operations (caches)
        search by keyword (search indexes)
        send message to another process (stream processing)
        crunch large amount of accumulated data (batch processing)
      Thinking About Data Systems
        data systems
          why umbrella term data systems?
          any new tools emerged
            datastore used as message queues (redis)
            message queues with database-like durability (kafka)
          wide-ranging requirements not fulfilled by a single tool
            ex: application managed caching layer (memcached)
              full text search server (elasticsearch, solr)
              application's responsibility: to keep them in sync with main database
            when you combine several tools
              new special-purpose data system
            then you are both application developer + data system designer
        3 critical concerns:
          reliability: synstem should work correctly even in the face of adversity (errors)
          scalability: as system (volume, speed, complexity) grows, ways to deal with it
          maintainability: 
      Reliability
        reliability: continuing to work correctly, even when things go wrong
        faults: the things that can go wrong
        fault-tolerant or resilient: to cope with faults
        failure: system as a whole stops providing service
          fault: component of a system deviates from its spec
        goal: to prevent faults from causing failures
        Hardware Faults
          MTTF (mean time to failure): 10-50 years
          redundancy
        Software Errors
        Human Errors
          make it easy to do right thing
          sandbox environments
          test thoroughly
          allow easy recovery
          monitoring: telemetry
        How Important is Reliability?
      Scalability
        describing Load
          load parameters:
            ex: requests per second to a web server
              ration of reads to writes in a database
              number of simultaneously active users in 
              hit rate on a cache
            ex: twitter
              post tweet: 12k req/sec
              home timeline: 300k req/sec
              2 approaches
                classical relational
                write to memcached home timelines
              /Users/mertnuhoglu/Dropbox/public/img/ss-240.png
              better: to do more work at write time and less at read time
                write 312k tweet/sec to each timeline cache
                makes reading simpler
        describing performance
          what happens when load increases
            two ways:
            1. increase load and keep resources unchanged. how is performance?
            2. increase load. how much to increase resources to keep performance unchanged?
          hadoop: batch processing system
            we care about throughput
              number of records to process per second
          online systems: 
            we care: response time
              time between a request sending and response receiving
          latency and response time:
            response time: time to process request (service time). includes network and queuing delays
            latency: time a request waits to be handled (awaiting service)
          percentile: 99.9 percentile
            99.9% of users have response time of ...
          SLO: service level objectives
          SLA: service level agreements
            service is considered to be up if it has a median response time of less than 200 ms and a 99th percentile under 1 s
            service is required to be up at least 99.9% of the time
          queueing delays

## GraphQL

    Explaining GraphQL Connections
      https://dev-blog.apollodata.com/explaining-graphql-connections-c48b7c3d6976

## PostgreSQL 9.6.5 Manual id=g_10156

    PostgreSQL 9.6.5 Manual <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10156>
      4.2 Value Expressions id=g_10250
				4.2 Value Expressions <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10250>
        ex: new column values in INSERT/UPDATE
        also called: 
          scalar
          scalar expressions
            to distinguish from table expression = table
          expressions
        value expression is one:
          literal value
          column reference
          positional parameter reference
          subscripted expression
          field selection expression
          operator invocation
          function call
          aggregate expression
          window function call
          type cast
          collation expression
          scalar subquery
          array constructor
          row constructor
          another value expression in parantheses
        column references
          correlation.columnname
          correlation: name of table
        positional parameters id=g_10249
					positional parameters <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10249>
          $number
          ex
            CREATE FUNCTION dept(text) RETURNS dept AS $$ SELECT * FROM dept WHERE name = $1 $$ LANGUAGE SQL;
            $1
        subscripts
          expression[subscript]
          if expression yields array, access specific element by this
          array slice:
            expression[lower:upper]
          ex
            mytable.arraycolumn[4]
            mytable.two_d_column[17][334]
              multidimensional array
            $1[10:42]
            (arrayfunction(a,b))[42]
        field selection
          expression.fieldname
          ex
            mytable.mycolumn
            $1.somecolumn
            (rowfunction(a,b)).col3
            (compositecol).somefield
              parantheses around compositecol: this is a column name not a table name
            (mytable.compositecol).somefield
          all fields of a composite:
            (compositecol).*
        operator invocations
          3 syntaxes:
            expression operator expression
              binary infix
            operator expression
              unary prefix
            expression operator
              unary postfix
          operators
            AND, OR, NOT
            OPERATOR(schema.operatorname)
        function calls
          function_name([expression [, expression ...]])
          ex
            sqrt(2)
          single argument of composite type:
            col(table)
            table.col
        aggregate expressions
          application of a function across the rows selected by a query
          reduces multiple values to a single value
          syntax
            aggregate_name(expression [, ...] [order_by_clause]) [ FILTER ( WHERE filter_clause ) ]
          ex
            SELECT array_agg(a ORDER BY b DESC) FROM table
            SELECT string_agg(a, ',' ORDER BY a) FROM table
          ex
            SELECT count(*) AS unfiltered, count(*) FILTER (WHERE i < 5) AS filtered FROM generate_series(1,10) AS s(i); 
            unfiltered | filtered 
            10 | 4
        window function calls
        type casts
      Chapter 5. Data Definition
        5.7. Row Security Policies id=adb_002 id=g_10251
					5.7. Row Security Policies  id=g_10251 <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10251>
          row level security per user basis:
            which rows can be returned by
              queries, inserts, updates, deletes
          enabled on a table:
            ALTER TABLE ... ENABLE ROW LEVEL SECURITY
          ex: only "managers" role can access rows and only rows of their accounts:
            CREATE TABLE accounts (manager text, company text);
            ALTER TABLE accounts ENABLE ROW LEVEL SECURITY;
            CREATE POLICY account_managers ON accounts TO managers
              USING (manager = current_user);
          ex: allow all users to access their own row:
            CREATE POLICY user_policy ON users
              USING (user_name = current_user);
          ex: allow all users to view all rows, but only modify their own:
            CREATE POLICY user_policy ON users
              USING (true)
              WITH CHECK (user_name = current_user);
          ex:
            -- table: [passwd|user_name, shell]
            -- roles: admin, bob, alice
            -- Be sure to enable row level security on the table
            ALTER TABLE passwd ENABLE ROW LEVEL SECURITY;
            -- Create policies
            -- Administrator can see all rows and add any rows
            CREATE POLICY admin_all ON passwd TO admin USING (true) WITH CHECK (true);
            -- Normal users can view all rows
            CREATE POLICY all_view ON passwd FOR SELECT USING (true);
            -- Normal users can update their own records, but
            -- limit which shells a normal user is allowed to set
            CREATE POLICY user_mod ON passwd FOR UPDATE
              USING (current_user = user_name)
              WITH CHECK (
                current_user = user_name AND
                shell IN ('/bin/bash','/bin/sh','/bin/dash','/bin/zsh','/bin/tcsh')
              );
            -- Allow admin all normal rights
            GRANT SELECT, INSERT, UPDATE, DELETE ON passwd TO admin;
            -- Users only get select access on public columns
            GRANT SELECT
              (user_name, uid, gid, real_name, home_phone, extra_info, home_dir, shell)
              ON passwd TO public;
            -- Allow users to update certain columns
            GRANT UPDATE
              (pwhash, real_name, home_phone, extra_info, shell)
              ON passwd TO public;
          test it: 
            ex: psql
              SET ROLE admin;
              TABLE passwd;
              SET ROLE alice;
              TABLE passwd;
              # ERROR
              SELECT user_name from passwd;
              UPDATE passwd set user_name = 'joe';
              # ERROR
              update passwd set real_name = 'Alice doe';
      Chapter 8. Data Types id=g_10252
				Chapter 8. Data Types <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10252>
        intro
          table: common data types
            bigint
            boolean
            char (n)
            text
            date
            double precision
            interval [ fields  ] [ (p)  ]
            json
            numeric [ (p, s)  ]
            timetz
            timestamptz
          table: data types
            | Name                                    | Aliases            | Description                                        |
            | bigint                                  | int8               | signed eight-byte integer                          |
            | bigserial                               | serial8            | autoincrementing eight-byte integer                |
            | bit [ (n) ]                             |                    | fixed-length bit string                            |
            | bit varying [ (n) ]                     | varbit             | variable-length bit string                         |
            | boolean                                 | bool               | logical Boolean (true/false)                       |
            | box                                     |                    | rectangular box on a plane                         |
            | bytea                                   |                    | binary data (“byte array”)                         |
            | character [ (n) ]                       | char [ (n) ]       | fixed-length character string                      |
            | character varying [ (n) ]               | varchar [ (n) ]    | variable-length character string                   |
            | cidr                                    |                    | IPv4 or IPv6 network address                       |
            | circle                                  |                    | circle on a plane                                  |
            | date                                    |                    | calendar date (year, month, day)                   |
            | double precision                        | float8             | double precision floating-point number (8 bytes)   |
            | inet                                    |                    | IPv4 or IPv6 host address                          |
            | integer                                 | int, int4          | signed four-byte integer                           |
            | interval [ fields ] [ (p) ]             |                    | time span                                          |
            | json                                    |                    | textual JSON data                                  |
            | jsonb                                   |                    | binary JSON data, decomposed                       |
            | line                                    |                    | infinite line on a plane                           |
            | lseg                                    |                    | line segment on a plane                            |
            | macaddr                                 |                    | MAC (Media Access Control) address                 |
            | macaddr8                                |                    | MAC (Media Access Control) address (EUI-64 format) |
            | money                                   |                    | currency amount                                    |
            | numeric [ (p, s) ]                      | decimal [ (p, s) ] | exact numeric of selectable precision              |
            | path                                    |                    | geometric path on a plane                          |
            | pg_lsn                                  |                    | PostgreSQL Log Sequence Number                     |
            | point                                   |                    | geometric point on a plane                         |
            | polygon                                 |                    | closed geometric path on a plane                   |
            | real                                    | float4             | single precision floating-point number (4 bytes)   |
            | smallint                                | int2               | signed two-byte integer                            |
            | smallserial                             | serial2            | autoincrementing two-byte integer                  |
            | serial                                  | serial4            | autoincrementing four-byte integer                 |
            | text                                    |                    | variable-length character string                   |
            | time [ (p) ] [ without time zone ]      |                    | time of day (no time zone)                         |
            | time [ (p) ] with time zone             | timetz             | time of day, including time zone                   |
            | timestamp [ (p) ] [ without time zone ] |                    | date and time (no time zone)                       |
            | timestamp [ (p) ] with time zone        | timestamptz        | date and time, including time zone                 |
            | tsquery                                 |                    | text search query                                  |
            | tsvector                                |                    | text search document                               |
            | txid_snapshot                           |                    | user-level transaction ID snapshot                 |
            | uuid                                    |                    | universally unique identifier                      |
            | xml                                     |                    | XML data                                           |
          sql standard types
            SQL: bigint, bit, bit varying, boolean, char, character varying, character, varchar, date, double precision, integer, interval, numeric, decimal, real, smallint, time (with or without time zone), timestamp (with or without time zone), xml.
        8.1. Numeric Types
        8.1.1. Integer Types
        8.1.2. Arbitrary Precision Numbers
        8.1.3. Floating-Point Types
        8.1.4. Serial Types
        8.2. Monetary Types
        8.3. Character Types
        8.4. Binary Data Types
        8.4.1. bytea Hex Format
        8.4.2. bytea Escape Format
        8.5. Date/Time Types
          Table: Date/Time Types
            timestamp [ (p) ] [ without time zone ]
            timestamp [ (p) ] with time zone
            date
            time [ (p) ] [ without time zone ]
            time [ (p) ] with time zone
            interval [ fields ] [ (p) ]
        8.5.1. Date/Time Input id=g_10253
					8.5.1. Date/Time Input <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10253>
          reasonable input formats
            syntax:
              type [ (p)  ] 'value'
            ex: date
              | Example         | Description                                          |
              | 1999-01-08      | ISO 8601; January 8 in any mode (recommended format) |
              | January 8, 1999 | unambiguous in any datestyle input mode              |
              | 1/8/1999        | January 8 in MDY mode; August 1 in DMY mode          |
              | 1/18/1999       | January 18 in MDY mode; rejected in other modes      |
            ex: time
              | Example                              | Description                             |
              | 04:05:06.789                         | ISO 8601                                |
              | 04:05:06                             | ISO 8601                                |
              | 04:05                                | ISO 8601                                |
              | 040506                               | ISO 8601                                |
              | 04:05 AM                             | same as 04:05; AM does not affect value |
              | 04:05 PM                             | same as 16:05; input hour must be <= 12 |
              | 04:05:06.789-8                       | ISO 8601                                |
              | 04:05:06-08:00                       | ISO 8601                                |
              | 04:05-08:00                          | ISO 8601                                |
              | 040506-08                            | ISO 8601                                |
              | 04:05:06 PST                         | time zone specified by abbreviation     |
              | 2003-04-12 04:05:06 America/New_York | time zone specified by full name        |
            ex: time zone
              | Example          | Description                              |
              | PST              | Abbreviation (for Pacific Standard Time) |
              | America/New_York | Full time zone name                      |
              | PST8PDT          | POSIX-style time zone specification      |
              | -8:00            | ISO-8601 offset for PST                  |
              | -800             | ISO-8601 offset for PST                  |
              | -8               | ISO-8601 offset for PST                  |
              | zulu             | Military abbreviation for UTC            |
              | z                | Short form of zulu                       |
            ex: time stamp
              1999-01-08 04:05:06
              1999-01-08 04:05:06 -8:00
              TIMESTAMP '2004-10-19 10:23:54'
                is a timestamp without time zone, while
              TIMESTAMP '2004-10-19 10:23:54+02'
                is a timestamp with time zone
              TIMESTAMP WITH TIME ZONE '2004-10-19 10:23:54+02'
            ex: special inputs 
              | Input String | Valid Types           | Description                                    |
              | epoch        | date, timestamp       | 1970-01-01 00:00:00+00 (Unix system time zero) |
              | infinity     | date, timestamp       | later than all other time stamps               |
              | -infinity    | date, timestamp       | earlier than all other time stamps             |
              | now          | date, time, timestamp | current transaction's start time               |
              | today        | date, timestamp       | midnight today                                 |
              | tomorrow     | date, timestamp       | midnight tomorrow                              |
              | yesterday    | date, timestamp       | midnight yesterday                             |
              | allballs     | time                  | 00:00:00.00 UTC                                |
        8.5.2. Date/Time Output
          ex: output styles
            | Style Specification | Description            | Example                      |
            | ISO                 | ISO 8601, SQL standard | 1997-12-17 07:37:16-08       |
            | SQL                 | traditional style      | 12/17/1997 07:37:16.00 PST   |
            | Postgres            | original style         | Wed Dec 17 07:37:16 1997 PST |
            | German              | regional style         | 17.12.1997 07:37:16.00 PST   |
        8.5.3. Time Zones
        8.5.4. Interval Input
          syntax:
            [@] quantity unit [quantity unit...] [direction]
            P quantity unit [ quantity unit ...] [ T [ quantity unit ...]]
              ISO 8601 
              T: units smaller than a day appear after T
                meaning of 'M' depends on this
              P [ years-months-days ] [ T hours:minutes:seconds ]
          expl:
            @: optional
            quantity: number
            unit: microsecond | millisecond | second | minute | hour | day | week | month | year | decade | century
              or plurals
            direction: ago or empty (optional)
              ago: negates all fields
          short syntax:
            without explicit unit markings
            ex:
              '1 12:59:10' 
                == '1 day 12 hours 59 min 10 sec'
              '200-10'
                == '200 years 10 months'
          ago == -
            ex:
              '-1 2:03:04'
              '2:03:04 ago'
          ex
            INTERVAL '1' YEAR
              == INTERVAL '1 YEAR'
          table: Interval Unit Abbreviations
            | Abbreviation | Meaning                    |
            | Y            | Years                      |
            | M            | Months (in the date part)  |
            | W            | Weeks                      |
            | D            | Days                       |
            | H            | Hours                      |
            | M            | Minutes (in the time part) |
            | S            | Seconds                    |
          ex: time intervals
            | Example                | Description                                               |
            | 1-2                    | SQL standard format: 1 year 2 months                      |
            | 3 4:05:06              | SQL standard format: 3 days 4 hours 5 minutes 6 seconds   |
            | 1 year 2 months 3 days | Traditional Postgres: 1 year 2 months 3 days              |
            | P1Y2M3DT4H5M6S         | ISO 8601 “format with designators”: same meaning as above |
            | P0001-02-03T04:05:06   | ISO 8601 “alternative format”: same meaning as above      |
        8.5.5. Interval Output
        8.6. Boolean Type
        8.7. Enumerated Types
        8.7.1. Declaration of Enumerated Types
        8.7.2. Ordering
        8.7.3. Type Safety
        8.7.4. Implementation Details
        8.8. Geometric Types
        8.8.1. Points
        8.8.2. Lines
        8.8.3. Line Segments
        8.8.4. Boxes
        8.8.5. Paths
        8.8.6. Polygons
        8.8.7. Circles
        8.9. Network Address Types
        8.9.1. inet
        8.9.2. cidr
        8.9.3. inet vs. cidr
        8.9.4. macaddr
        8.9.5. macaddr8
        8.10. Bit String Types
        8.11. Text Search Types
        8.11.1. tsvector
        8.11.2. tsquery
        8.12. UUID Type
        8.13. XML Type
        8.13.1. Creating XML Values
        8.13.2. Encoding Handling
        8.13.3. Accessing XML Values
        8.14. JSON Types
        8.14.1. JSON Input and Output Syntax
        8.14.2. Designing JSON documents effectively
        8.14.3. jsonb Containment and Existence
        8.14.4. jsonb Indexing
        8.15. Arrays
					8.15.1. Declaration of Array Types
					8.15.2. Array Value Input
					8.15.3. Accessing Arrays
					8.15.4. Modifying Arrays
					8.15.5. Searching in Arrays
					8.15.6. Array Input and Output Syntax
        8.16. Composite Types id=g_10737
					8.16. Composite Types <url:file:///~/gdrive/mynotes/content/articles/articles_db.md#r=g_10737>
						https://www.postgresql.org/docs/9.6/rowtypes.html
						composite type models structure of a row/record
							it is a list of field names and their data types
						a column can be of composite type
						8.16.1. Declaration of Composite Types
							ex
								CREATE TYPE complex AS (
										r       double precision,
										i       double precision
								);
								CREATE TYPE inventory_item AS (
										name            text,
										supplier_id     integer,
										price           numeric
								);
							similar to CREATE TABLE
								except: no constraints
							ex: use them to create tables
								CREATE TABLE on_hand (
										item      inventory_item,
										count     integer
								);
								INSERT INTO on_hand VALUES (ROW('fuzzy dice', 42, 1.99), 1000);
							ex: use in functions
								CREATE FUNCTION price_extension(inventory_item, integer) RETURNS numeric
								AS 'SELECT $1.price * $2' LANGUAGE SQL;
								SELECT price_extension(item, 10) FROM on_hand;
							when a table is created, a composite type is automatically created
								with the same name
								to represent table's row type
						8.16.2. Constructing Composite Values
							how to write a composite value as a literal constant?
								enclose values within parantheses
								wrap with double quotes, if it contains commas or parantheses
							opt01: using parantheses
								ex
									'( val1 , val2 , ... )'
									'("fuzzy dice",42,1.99)'
								ex: NULL as empty
									'("fuzzy dice",42,)'
								ex: empty string not NULL
									'("",42,)'
							opt02: using ROW
								simpler since no worry of multiple quoting
								ex: 
									ROW('fuzzy dice', 42, 1.99)
									ROW('', 42, NULL)
								ex: ROW is optional if you have more than one field
									('fuzzy dice', 42, 1.99)
									('', 42, NULL)
						8.16.3. Accessing Composite Types
							dot notation: typename.fieldname
							use parantheses to keep from confusing
							ex: fails. item is regarded as table name
								SELECT item.name FROM on_hand WHERE item.price > 9.99;
							ex: works
								SELECT (item).name FROM on_hand WHERE (item).price > 9.99;
							ex: works. both table and type are used:
								SELECT (on_hand.item).name FROM on_hand WHERE (on_hand.item).price > 9.99;
						8.16.4. Modifying Composite Types
							ex: first omits ROW.
								INSERT INTO mytab (complex_col) VALUES((1.1,2.2));
								UPDATE mytab SET complex_col = ROW(1.1,2.2) WHERE ...;
							ex: update an individual subfield
								UPDATE mytab SET complex_col.r = (complex_col).r + 1 WHERE ...;
								INSERT INTO mytab (complex_col.r, complex_col.i) VALUES(1.1, 2.2);
						8.16.5. Using Composite Types in Queries
							ex: simple reference to table name = ref to composite value of table's current row
								SELECT c FROM inventory_item c;
								c
								------------------------
								 ("fuzzy dice",42,1.99)
							but: simple names are matched first to column names then table names
								this works because there is no column named c
							ex: c.*
								SELECT c.* FROM inventory_item c;
								name    | supplier_id | price
								------------+-------------+-------
								 fuzzy dice |          42 |  1.99
							ex: equivalent to separate fields expanded
								SELECT c.name, c.supplier_id, c.price FROM inventory_item c;
							ex: any composite-valued expression including functions uses the same expansion behavior
								SELECT (myfunc(x)).* FROM some_table;
								SELECT (myfunc(x)).a, (myfunc(x)).b, (myfunc(x)).c FROM some_table;
							composite_value.* syntax results in column expansion 
								when it appears at the top level of a
									SELECT output list
									a RETURNING list in INSERT/UPDATE/DELETE
									a VALUES clause
									a row constructor
								in other cases .* means "all columns" so the same composite value is produced again
							ex: they are equivalent if arg is of composite type
								SELECT somefunc(c.*) FROM inventory_item c;
								SELECT somefunc(c) FROM inventory_item c;
							c.* is better than c because
								it makes clear that a composite value is intended
							ex: they are equivalent:
								SELECT * FROM inventory_item c ORDER BY c;
								SELECT * FROM inventory_item c ORDER BY c.*;
								SELECT * FROM inventory_item c ORDER BY ROW(c.*);
							if inventory_item contained a column c
								then first case would be different from others
							ex: these are equivalent to above too:
								SELECT * FROM inventory_item c ORDER BY ROW(c.name, c.supplier_id, c.price);
								SELECT * FROM inventory_item c ORDER BY (c.name, c.supplier_id, c.price);
							functional notation = dot notation
								notations field(table) and table.field are interchangeable
									SELECT c.name FROM inventory_item c WHERE c.price > 1000;
									SELECT name(c) FROM inventory_item c WHERE price(c) > 1000;
								ex: if a fnction accepts a single arg of a composite type, they are equivalent:
									SELECT somefunc(c) FROM inventory_item c;
									SELECT somefunc(c.*) FROM inventory_item c;
									SELECT c.somefunc FROM inventory_item c;
						8.16.6. Composite Type Input and Output Syntax
        8.17. Range Types
        8.17.1. Built-in Range Types
        8.17.2. Examples
        8.17.3. Inclusive and Exclusive Bounds
        8.17.4. Infinite (Unbounded) Ranges
        8.17.5. Range Input/Output
        8.17.6. Constructing Ranges
        8.17.7. Discrete Range Types
        8.17.8. Defining New Range Types
        8.17.9. Indexing
        8.17.10. Constraints on Ranges
        8.18. Object Identifier Types
        8.19. pg_lsn Type
        8.20. Pseudo-Types
        8.5. 
      Chapter 9. Functions and Operators id=g_10254
				Chapter 9. Functions and Operators <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10254>
        intro
          list all functions \df
          list all operators \do
        9.1. Logical Operators
          common: AND OR NOT
        9.2. Comparison Operators id=g_10255
					9.2. Comparison Operators <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10255>
          common
            < > <= >= = 
            <> != not equal
          BETWEEN
            a BETWEEN x and y
            a <= x AND a <= y
          NOT BETWEEN
            a NOT BETWEEN x AND y
            a < x OR a > y
          if either input is null
            operators yield null
            ow: use IS [NOT] DISTINCT FROM
              equivalent to <>
              but if null it returns false
          IS NULL
            <expression> IS [NOT] NULL
          IS
            TRUE
            FALSE
            UNKNOWN
            NOT
        9.3. Mathematical Functions and Operators
          + - * /
          % modulo
          ^ exponentiation
          |/ square root
          ||/ cube root
          ! factorial
            5 !
          !!  factorial (prefix)
            !! 5
          @ absolute value
            @ -5.0
          & bitwise AND
            | OR
            # XOR
            ~ NOT
            91 & 15 -> 11
          << bitwise shift left
          abs(x)
          cbrt(dp)  cube root
          ceil(dp)
          ceiling(dp)
          degrees(dp)   radians to degrees
          div(y,x)    integer quotient
          exp(dp)
          floor()
          ln()
          log()   base 10
          log(b, x)
          mod(y x)
          pi()
          power(a,b)
          round(a,b)
          random()
          setseed(dp)
          trigonometric functions
        9.4. String Functions and Operators id=g_10256
					9.4. String Functions and Operators <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10256>
          || concatenation
            'post' || 'gresql'
          char_length(string)
          lower(string)
          overlay(..)
          substring(string, int, int)
          trim()
          upper()
          other
            ascii(string)
            chr(int)
            concat(a,b)
            format('help %s", 'me')
            left('abcde', 2)
            length(string)
            lpad('hi', 5, '#')
            ltrim
            md5
            pg_client_encoding()
            quote_ident('foo bar')
            regexp_matches()
            regexp_replace()
            regexp_split_to_array()
            regexp_split_to_table()
            repeat(text, int)
            replace()
            reverse()
            right()
            rpad()
            split_part()
            strpos()
            substr()
            to_ascii()
        9.5. Binary String Functions and Operators
        9.6. Bit String Functions and Operators
        9.7. Pattern Matching id=g_10257
					9.7. Pattern Matching <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10257>
          LIKE
            'abc' LIKE 'a%'
            'abc' LIKE '_b_'
            ILIKE: case insensitive
            ~~ equivalent LIKE
              !~~
            ~~* equivalent ILIKE
              !~~*
          SIMILAR TO
            uses regex
              | * + ? {m} {m,} () [..] 
              note: . is not a metacharacter
            'abc' SIMILAR TO '%(b|c)%'
          POSIX regex
            operators
              ~ matches
              ~* matches case insensitive
              !~
              !~*
            ex
              'thomas' ~ '.*thomas.*'
            ex: substring
              substring('foobar' from 'o.b')
              regexp_replace('foobar', 'b(..)', E'X\\1Y', 'ig')
        9.8. Data Type Formatting Functions id=g_10258
					9.8. Data Type Formatting Functions <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10258>
          functions
            to_char(timestamp, text)
              to_char(current_timestamp, 'HH12:MI:SS')
            to_date(text, text)
              to_date('05 Dec 2000', 'DD Mon YYYY')
            to_number(text, text)
            to_timestamp(text, text)
          template patterns
            HH
            HH24
            MI
            SS
            ..
        9.9. Date/Time Functions and Operators id=g_10259
					9.9. Date/Time Functions and Operators <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10259>
          operators
            +
              + date '2001-09-28' + integer '7' date '2001-10-05'
              + date '2001-09-28' + interval '1 hour' timestamp '2001-09-28 01:00:00'
          date/time functions
            age(timestamp, timestamp)
            current_date()
            current_time()
            date_part(text, timestamp)
              date_part('hour', timestamp '2011-02-16 20:38:40')
            now()
            OVERLAPS
              (start1, end1) OVERLAPS (start2, end2)
                SELECT (DATE '2001-02-16', DATE '2001-12-21') OVERLAPS
                       (DATE '2001-10-30', DATE '2002-10-30');
                Result: true
            EXTRACT
              EXTRACT(field FROM source)
                SELECT EXTRACT(DAY FROM TIMESTAMP '2001-02-16 20:38:40');
                Result: 16
          date_trunc
            SELECT date_trunc('hour', TIMESTAMP '2001-02-16 20:38:40');
            Result: 2001-02-16 20:00:00
          AT TIME ZONE
            SELECT TIMESTAMP '2001-02-16 20:38:40' AT TIME ZONE 'MST';
            Result: 2001-02-16 19:38:40-08
          Current Date/Time
            CURRENT_DATE
            CURRENT_TIME
            CURRENT_TIMESTAMP
            CURRENT_TIME(precision)
            CURRENT_TIMESTAMP(precision)
            LOCALTIME
            LOCALTIMESTAMP
            LOCALTIME(precision)
            LOCALTIMESTAMP(precision)
            ex
              SELECT CURRENT_TIMESTAMP;
              Result: 2001-12-23 14:39:53.662522-05
            ex
              SELECT CURRENT_TIMESTAMP;
              SELECT now();
          Delaying Execution
            SELECT pg_sleep(1.5);
        9.10. Enum Support Functions id=g_10260
					9.10. Enum Support Functions <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10260>
          ex
            CREATE TYPE rainbow AS ENUM ('red', 'orange', 'yellow', 'green', 'blue', 'purple');
          support functions
            enum_first(null::rainbow) # red
            enum_range(null::rainbow)  # {red,orange,yellow,green,blue,purple}
        9.11. Geometric Functions and Operators
          geometric types
            point, box, lseg, line, path, polygon, circle
          geometric operators
            + Translation 
              box '((0,0),(1,1))' + point '(2.0,0)'
            * Scaling/rotation  
              box '((0,0),(1,1))' * point '(2.0,0)'
            # Point or box of intersection  
              box '((1,-1),(-1,1))' # box '((1,1),(-2,-2))'
          geometric functions
            area(object)
            center
            diameter
            height
          geometric type conversion 
            box(circle)
            lseg(box)
            point(box)
        9.12. Network Address Functions and Operators
        9.13. Text Search Functions and Operators
        9.14. XML Functions
        9.15. Sequence Manipulation Functions
          functions for operating on sequence objects
            also: sequence generators or sequences
          sequence objects:
            single-row tables
            created with CREATE SEQUENCE
          functions
            currval()
            lastval()
            nextval()
            setval()
        9.16. Conditional Expressions id=g_10261
					9.16. Conditional Expressions <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10261>
          9.16.1. CASE
            syntax
              CASE WHEN condition THEN result
                   [WHEN ...]
                   [ELSE result]
              END
            ex
              SELECT a,
                     CASE WHEN a=1 THEN 'one'
                          WHEN a=2 THEN 'two'
                          ELSE 'other'
                     END
                  FROM test;
               a | case
              ---+-------
               1 | one
               2 | two
               3 | other
            ex: simple syntax
              SELECT a,
                 CASE a WHEN 1 THEN 'one'
                        WHEN 2 THEN 'two'
                        ELSE 'other'
                 END
              FROM test;
          9.16.2. COALESCE id=g_10262
						9.16.2. COALESCE <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10262>
            returns first argument that is not null
              used to substitute a default value for null for display
            COALESCE(value [, ...])
            SELECT COALESCE(description, short_description, '(none)') ...
          9.16.3. NULLIF
            reverse of COALESCE
            The NULLIF function returns a null value if value1 equals value2; otherwise it returns value1.
            NULLIF(value1, value2)
          9.16.4. GREATEST and LEAST
            GREATEST(value [, ...])
            LEAST(value [, ...])
        9.17. Array Functions and Operators id=g_10263
					9.17. Array Functions and Operators <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10263>
          operators
            = equal ARRAY[1.1,2.1,3.1]::int[] = ARRAY[1,2,3]  t
            @>  contains  ARRAY[1,4,3] @> ARRAY[3,1]  t
            <@  is contained by ARRAY[2,7] <@ ARRAY[1,7,4,2,6]  t
            &&  overlap (have elements in common) ARRAY[1,4,3] && ARRAY[2,1]  t
            ||  array-to-array concatenation  ARRAY[1,2,3] || ARRAY[4,5,6]  {1,2,3,4,5,6}
          functions
            array_append(ARRAY[1,2], 3) 
            array_cat(ARRAY[1,2,3], ARRAY[4,5]) 
            array_ndims(ARRAY[[1,2,3], [4,5,6]])  
            array_length(array[1,2,3], 1)  # 3
            unnest(ARRAY[1,2])  
              1
              2
              (2 rows)
        9.19. Range Functions and Operators id=g_10264
					9.19. Range Functions and Operators <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10264>
          operators
            = equal int4range(1,5) = '[1,4]'::int4range t
            @>  contains range  int4range(2,4) @> int4range(2,3)  t
            @>  contains element  '[2011-01-01,2011-03-01)'::tsrange @> '2011-01-10'::timestamp t
            <@  range is contained by int4range(2,4) <@ int4range(1,7)  t
            <@  element is contained by 42 <@ int4range(1,7)  f
            &&  overlap (have points in common) int8range(3,7) && int8range(4,12) t
            <<  strictly left of  int8range(1,10) << int8range(100,110) t
        9.18. Aggregate Functions id=g_10265
					9.18. Aggregate Functions <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10265>
          Aggregate functions compute a single result from a set of input values
          functions
            array_agg(expression) any non-array type    
              array of the argument type  
            avg(expr)
            bool_and(expr)
            bool_or(expr)
            count(*)
            count(expr)
              number of input rows for which the value of expression is not null
            json_agg(expression)  
              aggregates values as JSON array
            json_object_agg(name, value)  
              aggregates key/values as JSON object
            max
            min
            string_agg(expr, delimiter)
            sum(expr)
          note: count(*) performance is slow when no WHERE filter is used or
            SELECT COUNT(*) FROM (SELECT DISTINCT column_name FROM table_name) AS temp;
          functions for statistics
            crr(Y,X)
            regr_avgx(Y,X)
            stddev
            variance
          functions for ordered-sets (niverse distribution)
            mode() WITHIN GROUP 
              out: most frequent value
            percentile_cont(fraction)
              continous percentile
        9.19. Window Functions id=g_10266
					9.19. Window Functions <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10266>
          perform calculations across set of rows
          invoked using window syntax
            OVER clause is required
          any aggregate function can be used
            as window function
            with OVER clause
          general purpose functions
            row_number()
              1 2 3
            rank()
              with gaps: 1 1 3
            dense_rank()
              no gaps: 1 1 2
            percent_rank()
            cume_dist()
            ntile
            lag
            lead
            first_value
            last_value
            nth_value
        9.20. Subquery Expressions id=g_10267
					9.20. Subquery Expressions <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10267>
          all return boolean results
          9.20.1. EXISTS
            EXISTS(subquery)
            ex
              SELECT col1
              FROM tab1
              WHERE EXISTS (SELECT 1 FROM tab2 WHERE col2 = tab1.col2);
          9.20.2. IN
            expression IN (subquery)
          9.20.3. NOT IN
          9.20.4. ANY/SOME
            expression operator ANY (subquery)
            expression operator SOME (subquery)
          9.20.5. ALL
            expression operator ALL (subquery)
        9.21. Row and Array Comparisons
        9.22. Set Returning Functions id=g_10268
					9.22. Set Returning Functions <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10268>
          return multiple rows
          series generating functions
            generate_series(start, stop)
            generate_series(start, stop, step)
            generate_series(start, stop, step interval)
          subscript generating
            generates valid subscripts for an array
            ex
              SELECT generate_subscripts('{NULL,1,NULL,2}'::int[], 1) AS s;
               s 
              ---
               1
               2
               3
               4
              (4 rows)
        9.23. System Information Functions id=g_10269
					9.23. System Information Functions <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10269>
          functions
            current_catalog
            current_database()
            current_role
            current_schema
            current_user
            user
            session_user
            version()
          query object access privileges
            has_any_column_privilege(user, table, privilege)
            has_column_privilege(user, table, column, privilege)
            has_function_privilege
            has_schema_privilege
            has_xxx_privilege
              server, table, tablespace, type, language
            pg_has_role(user, role, privilege)
        9.24. System Administration Functions id=g_10270
					9.24. System Administration Functions <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10270>
          functions
            current_setting(setting_name [, missing_ok ]) 
            set_config(setting_name, new_value, is_local) 
            ex
              SELECT current_setting('datestyle');
               current_setting
              -----------------
               ISO, MDY
              (1 row)
              SELECT set_config('log_statement_stats', 'off', false);
          server signaling functions
            pg_cancel_backend(pid int)  
          backup control functions
            pg_create_restore_point(name text)  
            pg_start_backup
              select pg_start_backup('label_goes_here');
          Recovery Control Functions
        9.25. Trigger Functions
			Chapter 20. database Roles and Privileges id=g_10335
				Chapter 20. database Roles and Privileges <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10335>
				20.4. Role Membership id=g_10336
					20.4. Role Membership <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10336>
					logic
						to group users
						privileges can be granted to a group
						create a role that represents a group
						grant membership in the group role to individual user roles
					ex
						CREATE ROLE group_role
						GRANT group_role TO role1, ...
					login role vs group role
						login role:
							the role the user has signed in
						group role
							when the user does `SET ROLE ...`
					how to use privileges of a group role?
						opt1: explicitly SET ROLE
							every member of a group do `SET ROLE` to become the group role
							now the database session has privileges of the group role
							any database objects created are now owned by the group role not the login role
						opt2: member roles have INHERIT attribute
							they can use privileges of group role automatically
						ex:
							CREATE ROLE joe LOGIN INHERIT;
							CREATE ROLE admin NOINHERIT;
							CREATE ROLE wheel NOINHERIT;
							GRANT admin TO joe;
							GRANT wheel TO admin;
							// user logins as `joe`
							// the user can use `admin` privileges
							// but not `wheel` privileges because the membership is via `admin` which has `NOINHERIT`
							SET ROLE admin
							// now the user has previleges granted to `admin` only, not those granted to `joe`
							// to restore orginal privilege state: 3 options:
								SET ROLE joe
								SET ROLE NONE
								RESET ROLE
      Chapter 21. Database Roles id=g_10271
				Chapter 21. Database Roles <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10271>
        21.3 Role Membership id=g_10272
					21.3 Role Membership <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10272>
          create a group role:
            CREATE ROLE name;
          add/remove members:
            GRANT group_role TO role1, ...;
            REVOKE group_role TO role1, ...;
          ex
            CREATE ROLE joe LOGIN INHERIT;
            CREATE ROLE admin NOINHERIT;
            CREATE ROLE wheel NOINHERIT;
            GRANT admin TO joe;
            GRANT wheel TO admin;
          change effective role:
            SET ROLE wheel;
      chapter 35. The Information Schema id=g_10273
				chapter 35. The Information Schema <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10273>
        35.1. The Schema
        35.2. Data Types
        35.3. information_schema_catalog_name
          database name
        35.4. administrable_role_authorizations
          current user has admin option for these roles
        35.5. applicable_roles
          all roles whose privileges current user can use
          columns: grantee, role_name, is_grantable
        35.6. attributes
          attributes of composite data types
        35.7. character_sets
          character sets available
          columns: character_set_name
            UTF8
        35.8. check_constraint_routine_usage
          functions used by a check constraint
        35.9. check_constraints
        35.10. collations
        35.11. collation_character_set_applicability
        35.12. column_domain_usage
        35.13. column_options
        35.14. column_privileges
          kolonlara erişim yetkileri 
        35.15. column_udt_usage
          data types of columns
        35.16. columns
          all table columns in database
        35.17. constraint_column_usage
          columns used by some constraint
        35.18. constraint_table_usage
          tables used by some constraint
        35.19. data_type_privileges
        35.20. domain_constraints
        35.21. domain_udt_usage
        35.22. domains
        35.23. element_types
        35.24. enabled_roles
        35.25. foreign_data_wrapper_options
        35.26. foreign_data_wrappers
        35.27. foreign_server_options
        35.28. foreign_servers
        35.29. foreign_table_options
        35.30. foreign_tables
        35.31. key_column_usage
        35.32. parameters
        35.33. referential_constraints
        35.34. role_column_grants
        35.35. role_routine_grants
        35.36. role_table_grants
        35.37. role_udt_grants
        35.38. role_usage_grants
        35.39. routine_privileges
        35.40. routines
        35.41. schemata
        35.42. sequences
        35.43. sql_features
        35.44. sql_implementation_info
        35.45. sql_languages
        35.46. sql_packages
        35.47. sql_parts
        35.48. sql_sizing
        35.49. sql_sizing_profiles
        35.50. table_constraints
        35.51. table_privileges
        35.52. tables
        35.53. transforms
        35.54. triggered_update_columns
        35.55. triggers
        35.56. udt_privileges
        35.57. usage_privileges
        35.58. user_defined_types
        35.59. user_mapping_options
        35.60. user_mappings
        35.61. view_column_usage
        35.62. view_routine_usage
        35.63. view_table_usage
        35.64. views
			Chapter 36. Extending SQL id=g_10736
				Chapter 36. Extending SQL <url:file:///~/gdrive/mynotes/content/articles/articles_db.md#r=g_10736>
				https://www.postgresql.org/
				36.1. How Extensibility Works
					extensible because its operation is catalog-driven
					they store information about database
					it is known as system catalogs (or data dictionary)
					postgres stores much more information than other rdbs
						information about data types, functions, access methods etc
					36.2. The PostgreSQL Type System
						36.2.1. Base Types
							correspond to abstract data types
							base types: like int4
							subdivided: scalar, array types
						36.2.2. Composite Types
							composite type = row type
							created when user creates a table
							or: CREATE TYPE
							it is: a list of types with associated field names
							value of a composite type = a row or record of field values
						36.2.3. Domains
							~ base type
							however a domain can have constraints that restrict its valid values
							can be created by: CREATE DOMAIN
						36.2.4. Pseudo-Types
							a few only
							for special purposes
								can be used to declare argument and result types of functions
						36.2.5. Polymorphic Types
							5 pseoudo-types of special interest:
								anyelement, anyarray, anynonarray, anyenum, anyrange
				36.3. User-defined Functions
					4 kinds of functions:
						query language functions (in SQL)
						procedural language functions (in pgsql, pl/tcl)
						internal functions
						c-language functions
				36.4. Query Language (SQL) Functions
					return the result of last query
					opt01: simple (non-set) case: first raw is returned
						needs ORDER BY
					opt02: return a set 
						by RETURNS SETOF <sometype>
						or RETURNS TABLE(columns)
					last SQL command must be
						either a SELECT
						or an INSERT, UPDATE, DELETE with RETURNING clause
						or FUNCTION x RETURNS void
							ex
								CREATE FUNCTION clean_emp() RETURNS void AS '
										DELETE FROM emp
												WHERE salary < 0;
								' LANGUAGE SQL;
								SELECT clean_emp();
					36.4.1. Arguments for SQL Functions
						if column name and arg name same, column name takes precedence
							qualify: function_name.argument_name
						numeric reference: $n
						if arg is of composite type, then dot notation: argname.fieldname or $1.fieldname
						arguments can only be used as data values, not as identifiers
							INSERT INTO mytable VALUES ($1);
							but this will not work:
							INSERT INTO $1 VALUES (42);
					36.4.2. SQL Functions on Base Types
						ex
							CREATE FUNCTION one() RETURNS integer AS $$
									SELECT 1 AS result;
							$$ LANGUAGE SQL;
							SELECT one();
							one
							-----
							1
						ex: arguments of base types
							CREATE FUNCTION add_em(x integer, y integer) RETURNS integer AS $$
									SELECT x + y;
							$$ LANGUAGE SQL;
							SELECT add_em(1, 2) AS answer;
							 answer
							--------
										3
						ex: arguments with no names
							CREATE FUNCTION add_em(integer, integer) RETURNS integer AS $$
								SELECT $1 + $2;
							$$ LANGUAGE SQL;
						ex: update
							CREATE FUNCTION tf1 (accountno integer, debit numeric) RETURNS integer AS $$
									UPDATE bank
											SET balance = balance - debit
											WHERE accountno = tf1.accountno;
									SELECT 1;
							$$ LANGUAGE SQL;
							SELECT tf1(17, 100.0);
						ex: update return something useful
							opt01: use SELECT
								CREATE FUNCTION tf1 (accountno integer, debit numeric) RETURNS integer AS $$
										UPDATE bank
												SET balance = balance - debit
												WHERE accountno = tf1.accountno;
										SELECT balance FROM bank WHERE accountno = tf1.accountno;
								$$ LANGUAGE SQL;
							opt02: use RETURNING
								CREATE FUNCTION tf1 (accountno integer, debit numeric) RETURNS integer AS $$
										UPDATE bank
												SET balance = balance - debit
												WHERE accountno = tf1.accountno
										RETURNING balance;
								$$ LANGUAGE SQL;
					36.4.3. SQL Functions on Composite Types
						ex
							CREATE TABLE emp (
									name        text,
									salary      numeric,
									age         integer,
									cubicle     point
							);
							INSERT INTO emp VALUES ('Bill', 4200, 45, '(2,1)');
							CREATE FUNCTION double_salary(emp) RETURNS numeric AS $$
									SELECT $1.salary * 2 AS salary;
							$$ LANGUAGE SQL;
							SELECT name, double_salary(emp.*) AS dream
									FROM emp
									WHERE emp.cubicle ~= point '(2,1)';
							 name | dream
							------+-------
							 Bill |  8400
						ex: opt02: calling (deprecated)
							SELECT name, double_salary(emp) AS dream
							FROM emp
							WHERE emp.cubicle ~= point '(2,1)';
							-- why deprecated
								easy to get confused
						ex: construct a composite argument value on-the-fly using ROW
							SELECT name, double_salary(ROW(name, salary*1.1, age, cubicle)) AS dream
								FROM emp;
						returns a composite type
							ex: opt01: select each column separately
								CREATE FUNCTION new_emp() RETURNS emp AS $$
									SELECT text 'None' AS name,
										1000.0 AS salary,
										25 AS age,
										point '(2,2)' AS cubicle;
								$$ LANGUAGE SQL;
							points:
								select list order must be the same as table/type definition
							ex: opt02: cast to ::emp
								CREATE FUNCTION new_emp() RETURNS emp AS $$
										SELECT ROW('None', 1000.0, 25, '(2,2)')::emp;
								$$ LANGUAGE SQL;
							usage:
								opt01: value expression
									SELECT new_emp();
													 new_emp
									--------------------------
									 (None,1000.0,25,"(2,2)")
								opt02: table function:
									SELECT * FROM new_emp();
									 name | salary | age | cubicle
									------+--------+-----+---------
									 None | 1000.0 |  25 | (2,2)
							ex: composite type, return one field:
								opt01: dot notation
									SELECT (new_emp()).name;
									 name
									------
									 None
								opt02: functional notation to extract an attribute
									SELECT name(new_emp());
										name
									 ------
										None
					36.4.4. SQL Functions with Output Parameters
						opt01: define with output parameters
							ex
								CREATE FUNCTION add_em (IN x int, IN y int, OUT sum int)
								AS 'SELECT x + y'
								LANGUAGE SQL;
								SELECT add_em(3,7);
								 add_em
								--------
										 10
								(1 row)
							ex: useful when multiple columns are returned
								CREATE FUNCTION sum_n_product (x int, y int, OUT sum int, OUT product int)
								AS 'SELECT x + y, x * y'
								LANGUAGE SQL;
								 SELECT * FROM sum_n_product(11,42);
								 sum | product
								-----+---------
									53 |     462
								(1 row)
							this is equivalent to an anonymous composite type for the result of the function:
								CREATE TYPE sum_prod AS (sum int, product int);
								CREATE FUNCTION sum_n_product (int, int) RETURNS sum_prod
								AS 'SELECT $1 + $2, $1 * $2'
								LANGUAGE SQL;
					36.4.5. SQL Functions with Variable Numbers of Arguments
						to accept variable numbers of arguments
							but these optional arguments must be of the same data type
							optional args will be passed as an array
						last parameter marked as VARIADIC
						ex
							CREATE FUNCTION mleast(VARIADIC arr numeric[]) RETURNS numeric AS $$
									SELECT min($1[i]) FROM generate_subscripts($1, 1) g(i);
							$$ LANGUAGE SQL;
							SELECT mleast(10, -1, 5, 4.4);
							 mleast 
							--------
									 -1
							(1 row)
						SELECT mleast(ARRAY[10, -1, 5, 4.4]);    -- doesn't work
						SELECT mleast(VARIADIC ARRAY[10, -1, 5, 4.4]);
					36.4.6. SQL Functions with Default Values for Arguments
						ex
							CREATE FUNCTION foo(a int, b int DEFAULT 2, c int DEFAULT 3)
					36.4.7. SQL Functions as Table Sources
						all functions can be used in `FROM` clause
							useful esp for functions returning composite types
						ex
							CREATE TABLE foo (fooid int, foosubid int, fooname text);
							INSERT INTO foo VALUES (1, 1, 'Joe');
							INSERT INTO foo VALUES (1, 2, 'Ed');
							INSERT INTO foo VALUES (2, 1, 'Mary');
							CREATE FUNCTION getfoo(int) RETURNS foo AS $$
									SELECT * FROM foo WHERE fooid = $1;
							$$ LANGUAGE SQL;
							SELECT *, upper(fooname) FROM getfoo(1) AS t1;
							 fooid | foosubid | fooname | upper
							-------+----------+---------+-------
									 1 |        1 | Joe     | JOE
							(1 row)
						returns only one row
							for multiple rows: use `SETOF`
					36.4.8. SQL Functions Returning Sets
						`SETOF <sometype>`
						ex
							CREATE FUNCTION getfoo(int) RETURNS SETOF foo AS $$
									SELECT * FROM foo WHERE fooid = $1;
							$$ LANGUAGE SQL;
							SELECT * FROM getfoo(1) AS t1;
							 fooid | foosubid | fooname
							-------+----------+---------
									 1 |        1 | Joe
									 1 |        2 | Ed
							(2 rows)
						ex: multiple rows where columns are output parameters
							CREATE TABLE tab (y int, z int);
							INSERT INTO tab VALUES (1, 2), (3, 4), (5, 6), (7, 8);
							CREATE FUNCTION sum_n_product_with_tab (x int, OUT sum int, OUT product int)
							RETURNS SETOF record
							AS $$
									SELECT $1 + tab.y, $1 * tab.y FROM tab;
							$$ LANGUAGE SQL;
							SELECT * FROM sum_n_product_with_tab(10);
							 sum | product
							-----+---------
								11 |      10
								13 |      30
								15 |      50
								17 |      70
							(4 rows)
						note: you must write: `RETURNS SETOF record`
							if multiple rows expected
							record: any row of multiple columns
						ex: LATERAL for invocations from successive rows
							SELECT * FROM nodes;
								 name    | parent
							-----------+--------
							 Top       |
							 Child1    | Top
							 Child2    | Top
							 Child3    | Top
							 SubChild1 | Child1
							 SubChild2 | Child1
							(6 rows)
							CREATE FUNCTION listchildren(text) RETURNS SETOF text AS $$
									SELECT name FROM nodes WHERE parent = $1
							$$ LANGUAGE SQL STABLE;
							SELECT * FROM listchildren('Top');
							 listchildren
							--------------
							 Child1
							 Child2
							 Child3
							(3 rows)
							SELECT name, child FROM nodes, LATERAL listchildren(name) AS child;
								name  |   child
							--------+-----------
							 Top    | Child1
							 Top    | Child2
							 Top    | Child3
							 Child1 | SubChild1
							 Child1 | SubChild2
							(5 rows)
						ex: opt02: LATERAL in SELECT
							SELECT listchildren('Top');
							 listchildren
							--------------
							 Child1
							 Child2
							 Child3
							(3 rows)
							SELECT name, listchildren(name) FROM nodes;
								name  | listchildren
							--------+--------------
							 Top    | Child1
							 Top    | Child2
							 Top    | Child3
							 Child1 | SubChild1
							 Child1 | SubChild2
							(5 rows)
					36.4.9. SQL Functions Returning TABLE
						opt to RETURNS SETOF record with multiple OUT parameters
							RETURNS TABLE(columns)
						this is SQL standard
						ex
							CREATE FUNCTION sum_n_product_with_tab (x int)
							RETURNS TABLE(sum int, product int) AS $$
									SELECT $1 + tab.y, $1 * tab.y FROM tab;
							$$ LANGUAGE SQL;
					36.4.10. Polymorphic SQL Functions
						ex
						CREATE FUNCTION make_array(anyelement, anyelement) RETURNS anyarray AS $$
								SELECT ARRAY[$1, $2];
						$$ LANGUAGE SQL;
						SELECT make_array(1, 2) AS intarray, make_array('a'::text, 'b') AS textarray;
						 intarray | textarray
						----------+-----------
						 {1,2}    | {a,b}
						(1 row)
					36.4.11. SQL Functions with Collations
				36.5. Function Overloading
					ex
						CREATE FUNCTION test(int, real) RETURNS ...
						CREATE FUNCTION test(smallint, double precision) RETURNS ...
				36.6. Function Volatility Categories
					possible volatility classes:
						VOLATILE, STABLE, IMMUTABLE
						VOLATILE is default
					VOLATILE
						can do anything including modify database
					STABLE
						cannot modify database
						return same results for same arguments within a single statement
					IMMUTABLE
						cannot modify database
						return same results for same arguments forever
				36.7. Procedural Language Functions
				36.8. Internal Functions
				36.9. C-Language Functions
					36.9.1. Dynamic Loading
					36.9.2. Base Types in C-Language Functions
					36.9.3. Version 0 Calling Conventions
					36.9.4. Version 1 Calling Conventions
					36.9.5. Writing Code
					36.9.6. Compiling and Linking Dynamically-loaded Functions
					36.9.7. Composite-type Arguments
					36.9.8. Returning Rows (Composite Types)
					36.9.9. Returning Sets
					36.9.10. Polymorphic Arguments and Return Types
					36.9.11. Transform Functions
					36.9.12. Shared Memory and LWLocks
					36.9.13. Using C++ for Extensibility
				36.10. User-defined Aggregates
					defined in terms of state values and state transition functions
					36.10.1. Moving-Aggregate Mode
					36.10.2. Polymorphic and Variadic Aggregates
					36.10.3. Ordered-Set Aggregates
					36.10.4. Partial Aggregation
					36.10.5. Support Functions for Aggregates
				36.11. User-defined Types
				36.12. User-defined Operators
				36.13. Operator Optimization Information
				36.13.1. COMMUTATOR
				36.13.2. NEGATOR
				36.13.3. RESTRICT
				36.13.4. JOIN
				36.13.5. HASHES
				36.13.6. MERGES
				36.14. Interfacing Extensions To Indexes
				36.14.1. Index Methods and Operator Classes
				36.14.2. Index Method Strategies
				36.14.3. Index Method Support Routines
				36.14.4. An Example
				36.14.5. Operator Classes and Operator Families
				36.14.6. System Dependencies on Operator Classes
				36.14.7. Ordering Operators
				36.14.8. Special Features of Operator Classes
				36.15. Packaging Related Objects into an Extension
				36.15.1. Defining Extension Objects
				36.15.2. Extension Files
				36.15.3. Extension Relocatability
				36.15.4. Extension Configuration Tables
				36.15.5. Extension Updates
				36.15.6. Extension Example
				36.16. Extension Building Infrastructuredocs/9.6/extend.html
      Part VI. Reference: SQL Commands id=g_10274
				Part VI. Reference: SQL Commands <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10274>
        intro
          table: sql commands
            ABORT — abort the current transaction
            ALTER AGGREGATE — change the definition of an aggregate function
            ALTER COLLATION — change the definition of a collation
            ALTER CONVERSION — change the definition of a conversion
            ALTER DATABASE — change a database
            ALTER DEFAULT PRIVILEGES — define default access privileges
            ALTER DOMAIN — change the definition of a domain
            ALTER EVENT TRIGGER — change the definition of an event trigger
            ALTER EXTENSION — change the definition of an extension
            ALTER FOREIGN DATA WRAPPER — change the definition of a foreign-data wrapper
            ALTER FOREIGN TABLE — change the definition of a foreign table
            ALTER FUNCTION — change the definition of a function
            ALTER GROUP — change role name or membership
            ALTER INDEX — change the definition of an index
            ALTER LANGUAGE — change the definition of a procedural language
            ALTER LARGE OBJECT — change the definition of a large object
            ALTER MATERIALIZED VIEW — change the definition of a materialized view
            ALTER OPERATOR — change the definition of an operator
            ALTER OPERATOR CLASS — change the definition of an operator class
            ALTER OPERATOR FAMILY — change the definition of an operator family
            ALTER POLICY — change the definition of a row level security policy
            ALTER PUBLICATION — change the definition of a publication
            ALTER ROLE — change a database role
            ALTER RULE — change the definition of a rule
            ALTER SCHEMA — change the definition of a schema
            ALTER SEQUENCE — change the definition of a sequence generator
            ALTER SERVER — change the definition of a foreign server
            ALTER STATISTICS — change the definition of an extended statistics object
            ALTER SUBSCRIPTION — change the definition of a subscription
            ALTER SYSTEM — change a server configuration parameter
            ALTER TABLE — change the definition of a table
            ALTER TABLESPACE — change the definition of a tablespace
            ALTER TEXT SEARCH CONFIGURATION — change the definition of a text search configuration
            ALTER TEXT SEARCH DICTIONARY — change the definition of a text search dictionary
            ALTER TEXT SEARCH PARSER — change the definition of a text search parser
            ALTER TEXT SEARCH TEMPLATE — change the definition of a text search template
            ALTER TRIGGER — change the definition of a trigger
            ALTER TYPE — change the definition of a type
            ALTER USER — change a database role
            ALTER USER MAPPING — change the definition of a user mapping
            ALTER VIEW — change the definition of a view
            ANALYZE — collect statistics about a database
            BEGIN — start a transaction block
            CHECKPOINT — force a write-ahead log checkpoint
            CLOSE — close a cursor
            CLUSTER — cluster a table according to an index
            COMMENT — define or change the comment of an object
            COMMIT — commit the current transaction
            COMMIT PREPARED — commit a transaction that was earlier prepared for two-phase commit
            COPY — copy data between a file and a table
            CREATE ACCESS METHOD — define a new access method
            CREATE AGGREGATE — define a new aggregate function
            CREATE CAST — define a new cast
            CREATE COLLATION — define a new collation
            CREATE CONVERSION — define a new encoding conversion
            CREATE DATABASE — create a new database
            CREATE DOMAIN — define a new domain
            CREATE EVENT TRIGGER — define a new event trigger
            CREATE EXTENSION — install an extension
            CREATE FOREIGN DATA WRAPPER — define a new foreign-data wrapper
            CREATE FOREIGN TABLE — define a new foreign table
            CREATE FUNCTION — define a new function
            CREATE GROUP — define a new database role
            CREATE INDEX — define a new index
            CREATE LANGUAGE — define a new procedural language
            CREATE MATERIALIZED VIEW — define a new materialized view
            CREATE OPERATOR — define a new operator
            CREATE OPERATOR CLASS — define a new operator class
            CREATE OPERATOR FAMILY — define a new operator family
            CREATE POLICY — define a new row level security policy for a table
            CREATE PUBLICATION — define a new publication
            CREATE ROLE — define a new database role
            CREATE RULE — define a new rewrite rule
            CREATE SCHEMA — define a new schema
            CREATE SEQUENCE — define a new sequence generator
            CREATE SERVER — define a new foreign server
            CREATE STATISTICS — define extended statistics
            CREATE SUBSCRIPTION — define a new subscription
            CREATE TABLE — define a new table
            CREATE TABLE AS — define a new table from the results of a query
            CREATE TABLESPACE — define a new tablespace
            CREATE TEXT SEARCH CONFIGURATION — define a new text search configuration
            CREATE TEXT SEARCH DICTIONARY — define a new text search dictionary
            CREATE TEXT SEARCH PARSER — define a new text search parser
            CREATE TEXT SEARCH TEMPLATE — define a new text search template
            CREATE TRANSFORM — define a new transform
            CREATE TRIGGER — define a new trigger
            CREATE TYPE — define a new data type
            CREATE USER — define a new database role
            CREATE USER MAPPING — define a new mapping of a user to a foreign server
            CREATE VIEW — define a new view
            DEALLOCATE — deallocate a prepared statement
            DECLARE — define a cursor
            DELETE — delete rows of a table
            DISCARD — discard session state
            DO — execute an anonymous code block
            DROP ACCESS METHOD — remove an access method
            DROP AGGREGATE — remove an aggregate function
            DROP CAST — remove a cast
            DROP COLLATION — remove a collation
            DROP CONVERSION — remove a conversion
            DROP DATABASE — remove a database
            DROP DOMAIN — remove a domain
            DROP EVENT TRIGGER — remove an event trigger
            DROP EXTENSION — remove an extension
            DROP FOREIGN DATA WRAPPER — remove a foreign-data wrapper
            DROP FOREIGN TABLE — remove a foreign table
            DROP FUNCTION — remove a function
            DROP GROUP — remove a database role
            DROP INDEX — remove an index
            DROP LANGUAGE — remove a procedural language
            DROP MATERIALIZED VIEW — remove a materialized view
            DROP OPERATOR — remove an operator
            DROP OPERATOR CLASS — remove an operator class
            DROP OPERATOR FAMILY — remove an operator family
            DROP OWNED — remove database objects owned by a database role
            DROP POLICY — remove a row level security policy from a table
            DROP PUBLICATION — remove a publication
            DROP ROLE — remove a database role
            DROP RULE — remove a rewrite rule
            DROP SCHEMA — remove a schema
            DROP SEQUENCE — remove a sequence
            DROP SERVER — remove a foreign server descriptor
            DROP STATISTICS — remove extended statistics
            DROP SUBSCRIPTION — remove a subscription
            DROP TABLE — remove a table
            DROP TABLESPACE — remove a tablespace
            DROP TEXT SEARCH CONFIGURATION — remove a text search configuration
            DROP TEXT SEARCH DICTIONARY — remove a text search dictionary
            DROP TEXT SEARCH PARSER — remove a text search parser
            DROP TEXT SEARCH TEMPLATE — remove a text search template
            DROP TRANSFORM — remove a transform
            DROP TRIGGER — remove a trigger
            DROP TYPE — remove a data type
            DROP USER — remove a database role
            DROP USER MAPPING — remove a user mapping for a foreign server
            DROP VIEW — remove a view
            END — commit the current transaction
            EXECUTE — execute a prepared statement
            EXPLAIN — show the execution plan of a statement
            FETCH — retrieve rows from a query using a cursor
            GRANT — define access privileges
            IMPORT FOREIGN SCHEMA — import table definitions from a foreign server
            INSERT — create new rows in a table
            LISTEN — listen for a notification
            LOAD — load a shared library file
            LOCK — lock a table
            MOVE — position a cursor
            NOTIFY — generate a notification
            PREPARE — prepare a statement for execution
            PREPARE TRANSACTION — prepare the current transaction for two-phase commit
            REASSIGN OWNED — change the ownership of database objects owned by a database role
            REFRESH MATERIALIZED VIEW — replace the contents of a materialized view
            REINDEX — rebuild indexes
            RELEASE SAVEPOINT — destroy a previously defined savepoint
            RESET — restore the value of a run-time parameter to the default value
            REVOKE — remove access privileges
            ROLLBACK — abort the current transaction
            ROLLBACK PREPARED — cancel a transaction that was earlier prepared for two-phase commit
            ROLLBACK TO SAVEPOINT — roll back to a savepoint
            SAVEPOINT — define a new savepoint within the current transaction
            SECURITY LABEL — define or change a security label applied to an object
            SELECT — retrieve rows from a table or view
            SELECT INTO — define a new table from the results of a query
            SET — change a run-time parameter
            SET CONSTRAINTS — set constraint check timing for the current transaction
            SET ROLE — set the current user identifier of the current session
            SET SESSION AUTHORIZATION — set the session user identifier and the current user identifier of the current session
            SET TRANSACTION — set the characteristics of the current transaction
            SHOW — show the value of a run-time parameter
            START TRANSACTION — start a transaction block
            TRUNCATE — empty a table or set of tables
            UNLISTEN — stop listening for a notification
            UPDATE — update rows of a table
            VACUUM — garbage-collect and optionally analyze a database
            VALUES — compute a set of rows
				I. SQL Commands
					https://www.postgresql.org/docs/9.5/sql-commands.html
					CREATE TABLE
						https://www.postgresql.org/docs/current/static/sql-createtable.html
						TEMPORARY or TEMP
							Temporary tables exist in a special schema, so a schema name cannot be given when creating a temporary table
							Temporary tables are automatically dropped at the end of a session

## PostgreSQL Up and Running 1491963417 id=g_10157

    PostgreSQL Up and Running 1491963417 <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10157>
    intro
      why postgresql
        not just a db
          also an application platform
        fast
        functions in several pl
          python, r, js
          pgsql
        data types
          custom made
            composite types
          types from tables
            functions that work on them
        don't treat db as dumb storage
          accomplish jobs in seconds that would take hours in coding 
        nonrelational data
          ltree -> graphs
          hstore -> key-value
          json + jsonb
      why not postgresql
        size 100M+
        security might be overkill
        combine pgs with other db types
          ex: redis/memcache to cache query results from pgs
          ex: sqlite for offline. pgs for online
    chapter 1. the basics
      administration tools
        psql, pgAdmin, phpPgAdmin, Adminer
      psql
        unusual features
          import/export csv/tab
          report writer for html
      pgAdmin
        gui tool
        start with pgAdmin
          get birds eye view
      PostgreSQL Database Objects
        databases
        schemas
          a database contains several schemas
          default schema: public
          if lots of tables, organize them into different schemas
        tables
          a schema contains several tables
          have two features
            inheritable
            custom data type for each table
        views
          a view is an abstraction from tables
          views that join multiple tables
            need a trigger to update data
          materialized views
            cache data to speed up queries
            cost: not most up-to-date data
        extensions
          to package functions, data types, casts, indexes etc. for installation
          may depend on other extensions
            CASCADE: to install dependents
        functions
          create functions using pls
          can return: scalar, array, records
          stored procedure = function
        languages
          pl: procedural language
          default: sql, pgSQL, C
          additional: python, v8 (js), r, java
        operators
          aliases for functions
          can create your own
        foreign tables and foreign data wrapper
          foreign tables can link to csv, other servers, other databases, web services
          foreign data wrappers (fdw) makes handshake between pgs and external data source
            follow sql/management of external data standard
          existing fdw for popular data sources
        triggers and trigger functions
          detect data change events
          execute trigger functions
          in response to 
            statements
            changes in particular rows
            before or after data change
          pgadmin > .table > triggers
          trigger functions
            have access to special variables
              that have data before/after event
            can be used for validation 
        catalog
          system schemas that store builtin functions and metadata
          every db has two catalogs
            pg_catalog
              all functions, tables,...
            information_schema
              views of metadata
          pgs has self-replicating structure
            all settings are in system tables
            this gives pgs extensibility (hackability) 
        types
          type = data type
          ex: integer, character, array
          numeric types
            int
            numeric(precision)
            real double float(precision)
            serial
          pgs has composite types
            ex: complex numbers, coordinates, vectors, tensors
          a composite type for each table
        full text search (fts)
          natural language based search
          match based on semantics
            ex: running, ran, runner, jog, sprint
          3 objects for fts
            fts configurations
            fts dictionaries
            fts parsers
        cast
          convert one data type to another
          ex: zip codes: from character to integer
        sequence
          auto incrementation of a serial data type
        rules
          instructions to rewrite sql prior to execution
          fallen out of favor because of triggers
        settings at multiple levels
          server level
          database level
          schema level
          GUC: grand unified configuration = configuration settings in pgs
      What is New
        Features in pgs 10
          parallel query improvements
          logical replication
          full text for json
          fdw aggregates
            count(*) sum(*) on remote queries
          declarative table partitioning
          query execution
          create statistics
          case and coalesce
          identity
        Features in pgs 9.6
          query parallelization
            for multiple cores
          phrase full text search
            distance operator <->
              to indicate how far two words can be apart
          psql \gexec options
          postgres_fdw
          fdw joins
            fdw will perform join remotely
        Features in pgs 9.5
          foreign table architecture improvements
          using unlogged tables to populate new tables 
          arrays in array_agg
            accepts arrays to construct multidim arrays too
          block range indexes (brin)
            new kind of index
              other than btree and gin
          grouping sets, roll up and cube sql predicates
            to return additional sub total rows
          index only scans
            support gist indexes
          insert and update conflict handling
            prior: automatic fail
            now: option to catch exception and skip 
          update lock failures
          row level security
        Features in pgs 9.4
          materialized views enhancements
          new analytic functions to compute percentiles
            percentile_disc (discrete)
            percentile_cont (continous)
            used with: WITHIN GROUP (ORDER BY ...)
          protection against updates in views
            WITH CHECK OPTION
          new data type: jsonb
          improved generalized inverted index (GIN)
            for fts, trigrams, hstores, jsonb
          more json functions
            json_build_array
            json_build_object
            json_to_record
          expedited moves between tablespaces
            move all db objects from/to tablespace
              tablespace is a location on disk where PostgreSQL stores data files containing database objects
          row numbers in returned sets
          using sql to alter system-configuration settings
            ALTER system SET ...
              for global system settings
          triggers on foreign tables
          better unnesting
          ROWS FROM
          dynamic background workers
      Database Drivers
      Notable PGS Forks
    chapter 2. database administration id=g_10276
			chapter 2. database administration <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10276>
      configuration files
        postgresql.conf
          general settings
            ex: memory allocation, default storage location, ip addresses, location of logs
        pg_hba.conf
          access to the server
            which users can log in to which db
            which ip addresses
            which authentication scheme
        pg_ident.conf
          maps os login to pgs user
        note: pgs role = user
          here: user: role with login privilege
        where are config files
          SELECT name, setting FROM pg_settings WHERE category = 'File Locations'
        making configurations take effect
          reload or restart
          reloading
            opt
              pg_ctl reload -D <data_directory>
              service postgresql-9.6 reload
              SELECT pg_reload_conf()
          restarting
            service postgresql-9.5 restart
      Managing Connections
        typical steps:
          1. retrieve list of recent connections:
            SELECT * FROM pg_stat_activity
          2. cancel active queries on a connection
            SELECT pg_cancel_backend(procid)
          3. kill connection
            SELECT pg_terminate_backend(procid)
        kill connections by a user
          SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE usename = 'some_role'
      Roles id=g_10275
				Roles <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10275>
        intro
          login roles
            roles that can login
          group roles
            roles that contain other roles
            usually: DBAs don't grant login right to group roles
          superuser
            have unlimited access
          commands
            old
              CREATE USER
              CREATE GROUP
            CREATE ROLE
        Creating Login Roles
          CREATE ROLE leo LOGIN PASSWORD 'king' CREATEDB VALID UNTIL 'infinity'
            CREATEDB: grants database creation rights
            VALID: optional, default: infinity
          grant superuser rights
            CREATE ROLE regina LOGIN PASSWORD 'queen' SUPERUSER UNTIL '2020-1-1 00:00'
        Creating Group Roles
          CREATE ROLE royalty INHERIT
            INHERIT: any member will inherit rights of royalty role
              except superuser rights
          GRANT royalty TO leo  
            add role "leo" to group "royalty"
            "royalty" is group role
            "leo" is member role
            when we change group role privileges, member roles are updated
        Inheriting rights
          take some role per connection:
            SET ROLE royalty
              impersonation
      Database Creation   
        CREATE DATABASE mydb
          copies "template1" db
      Template Databases
        defn: database that serves as model
        default template db:
          template0
            pure model that you need to copy if you screw up your templates
            never alter it
          template1
            used as template for new db
        CREATE DATABASE mydb TEMPLATE my_template
          create mydb based on my_template
          you can pick any db as template
          you can mark any db as template
        UPDATE pg_database SET datistemplate = TRUE WHERE dataname = 'mydb'
          now mydb becomes immutable (due to dataistemplate = TRUE)
      Using Schemas id=g_10277
				Using Schemas <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10277>
        to organize db into groups
        default: public
        always prepend schema name to table name:
          SELECT * FROM my_schema.dogs
        search_path
          variable to keep schema names
          ex: "public, my_schema"
            searches a table in this list
        user
          variable that lists name of currently logged in user
          SELECT user
          ex: best practice
            search_path = "$user", public
              first look in $user schema then public
        best practice: create schemase to house extensions
          install extensions to custom schema
          ex:
            CREATE SCHEMA my_extensions
            ALTER DATABASE mydb SET search_path = '"$user", public, my_extensions'
              needs reconnect
      Privileges id=g_10278
				Privileges <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10278>
        defn: permissions
        tricky to administer because of fine granular control
      Types of Privileges
        object level privileges:
          SELECT, INSERT, UPDATE, ALTER, EXECUTE, TRUNCATE
          modifier: WITH GRANT
      Getting Started
        have superuser and db
          default: postgres
        create first role
          CREATE ROLE mydb_admin LOGIN PASSWORD 'ss'
        create db and sent owner
          CREATE DATABASE mydb WITH owner = mydb_admin
        login as mydb_admin
        setup additional schemas and tables
      GRANT id=g_10279
				GRANT <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10279>
        assigns privileges to others
        GRANT some_privilege TO some_role
        note:
          you need to be the holder of privilege that you're granting
          some privileges can never be granted away:
            ex: DROP, ALTER 
          owner of object has all privileges
          you can add: WITH GRANT OPTION
            means: grantee can grant onwards
            GRANT ALL ON ALL TABLES IN SCHEMA public TO mydb_admin WITH GRANT OPTION
          all relevant privileges on an object:
            ALL
            GRANT SELECT, REFERENCES, TRIGGER ON ALL TABLES IN SCHEMA my_schema TO PUBLIC
          ALL alias: grant for all objects within a db/schema
            GRANT SELECT, UPDATE ON ALL SEQUENCES IN SCHEMA my_schema TO PUBLIC
          PUBLIC alias: to grant privileges to all roles
            GRANT USAGE ON SCHEMA my_schema TO PUBLIC
        REVOKE
          REVOKE EXECUTE ON ALL FUNCTIONS IN SCHEMA my_schema FROM PUBLIC
        default granted to PUBLIC
          CONNECT, CREATE TEMP TABLE for db
          EXECUTE for functions
          USAGE for languages
      Default Privileges
        ex: all users to have EXECUTE and SELECT to all in schema
          GRANT USAGE ON SCHEMA my_schema TO PUBLIC
          ALTER DEFAULT PRIVILEGES IN SCHEMA my_schema GRANT SELECT, REFERENCES ON TABLES TO PUBLIC
          ALTER DEFAULT PRIVILEGES IN SCHEMA my_schema GRANT ALL ON TABLES TO MYDB_ADMIN WITH GRANT OPTION
          ALTER DEFAULT PRIVILEGES IN SCHEMA my_schema GRANT SELECT, UPDATE ON SEQUENCES TO PUBLIC
          ALTER DEFAULT PRIVILEGES IN SCHEMA my_schema GRANT ALL ON FUNCTIONS TO MYDB_ADMIN WITH GRANT OPTION
          ALTER DEFAULT PRIVILEGES IN SCHEMA my_schema GRANT USAGE ON TYPES TO PUBLIC
      Privilege Idiosyncrasies
        don't forget
          or
            GRANT USAGE ON SCHEMA
            GRANT ALL ON SCHEMA
          you may own schema, but can not use it
      Extensions
        extensions on server
          SELECT name FROM pg_available_extensions WHERE installed_version IS NOT NULL ORDER BY name
        Installing Extensions
          step 1: installing on the server
            apt get postgresql-contrib
            SELECT * FROM pg_available_extensions
          step 2: installing into a database
            opt
              CREATE EXTENSION fuzzystrmatch SCHEMA my_extensions
              CREATE EXTENSION fuzzystrmatch
              psql -p 5432 -d mydb -c "CREATE EXTENSION fuzzystrmatch;"
        Common extensions
          Popular extensions
            postgis
            fuzzystrmatch
              ex: soundex, levenshtein for fuzzy string matching
            hstore
              key-value pair storage
            pg_trgm (trigram)
              fuzzy string search
            dblink
              query pgs on other servers
            pgcrypto
          classic extensions
            tsearch
              indexes, operators, functions to enhance full text searches
            xml
              xml data type, functions
      Backup and Restore id=g_10280
				Backup and Restore <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10280>
        pg_dump
        pg_dumpall
        may use: ~/.pgpass
          to store all paswords
        Selective Backup Using pg_dump
          compressed, single db backup
            pg_dump -h localhost -p 5432 -U someuser -F c -b -v -f mydb.backup mydb
          plain text single db backup
            pg_dump -h localhost -p 5432 -U someuser -C -F p -b -v -f mydb.backup mydb
          compressed tables that start with "pay"
            ... -F c -b -v -t *.pay* -f pay.backup mydb
          compressed all objects in hr and payroll schemas
            ... -F c -b -v -n hr -n payroll -f hr.backup mydb
          compressed all objects in all schemas excluding public schema
            ... -F c -b -v -N public -f all_schema_except_public.backup mydb
          plain text sql of select tables
            ... -F p --column-inserts -f select_tables.backup mydb
            # useful for porting data to any db
          directory format: creates a dir and puts each table as a file
            ... -F d -f /path/dir mydb
        Systemwide Backup Using pg_dumpall
          plain text file
          includes server globals as well
          tip: backup globals such as roles, tablespace definitions on daily basis
          backup roles and tablespaces
            pg_dumpall ... -f myglobals.sql --globals-only
        Restore
          opt
            psql: restore plain text
            pg_restore: compressed, TAR, directory backups
          psql: restore plain text
            restore full backup and ignore errors
              psql -U postgres -f myglobals.sql
            restore, stop if error
              psql -U postgres --set ON_ERROR_STOP=on -f myglobals.sql
            restore to specific database
              psql -U postgres -d mydb -f myglobals.sql
          pg_restore
            restoring
              1. create db
                CREATE DATABASE mydb
              2. restore
                pg_restore --dbname=mydb --jobs=4 --verbose mydb.backup
            restore just structure
              --section=pre-data
      Managing Disk Storage with Tablespaces
        initializing pgs cluster creates two tablespaces
          pg_default
            user data
          pg_global
            system data
          in the same folder
        Creating Tablespaces
          CREATE TABLESPACE secondary LOCATION 'path'
        Moving Objects Between Tablespaces
          move database
            ALTER DATABASE mydb SET TABLESPACE secondary
          move one table
            ALTER TABLE mytable SET TABLESPACE secondary
        Verboten Practices
          logs
            pg_log folder in pgs data folder
          don't delete pgs core system files
            ok: pg_log
              but some files are critical
            never: pg_xlog
              stores transaction logs
        dont Give Full OS Admin Rights
          postgres account should be regular user
        dont set shared_buffers too high
        dont try to start pgs on a port already in use
      postgresql.conf file
        multiple levels: database, role, session, function
        checking settings
          query view: pg_settings
          ex
            SELECT name, context , unit , setting, boot_val, reset_val FROM pg_settings WHERE name IN ('listen_addresses','deadlock_timeout','shared_buffers' 'effective_cache_size','work_mem','maintenance_work_mem') ORDER BY context, name;
    chapter 3. psql id=g_10281
			chapter 3. psql <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10281>
      Environment Variables id=g_10282
				Environment Variables <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10282>
        PSQL_HISTORY  
          default: ~/.psql_history
        PSQLRC
          config file
        PGHOST
        PGPORT
        PGUSER
        PGPASSWORD
          or .password file
      Interactive vs Noninteractive psql id=g_10283
				Interactive vs Noninteractive psql <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10283>
        \?
          list of commands
        \h <command>
        psql -f <some_script_file>
        psql -c <sql_statements>
          psql -d postgresql_book -c "DROP TABLE ..."
        interactive commands inside script files
          ex: build_stage.psql
            \a \t
            SELECT 'CREATE TABLE
            staging.count_to_50(array_to_string(array_agg('x' || i::text ' varchar(10)));' As create_sql
            FROM generate_series(1,9) As i;
            \g create_script.sql
            \i create_script.sql
          expl
            \t: save output remove headers
            \a: remove extra line breaks
            staging...
              create a table with nine varchar columns
            \g: output to a file
            \i: execute script interactively (vs -f)
          run:
            psql -f build_stage.psql -d postgresql_book
      psql Customizations id=g_10284
				psql Customizations <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10284>
        settings: .psqlrc
          runs all commands therein
          ex:
            \pset null 'NULL'
            \encoding latin1
            ...
          expl
            each "set" commond on a single line
        Custom Prompts
          which db server:
            \set PROMPT1 '%n@..'
        Timing Executions
          \timing
            toggle it on and off
          ex:
            SELECT COUNT(*) FROM pg_tables
          out
            count
            ----
            ..
            Time: 18.3 ms
        Autocommit Commands
          \set AUTOCOMMIT off
            now you can roll back
          ex:
            UPDATE census.facts SET short_name = "mistake"
            ROLLBACK
            COMMIT
        Shortcuts
          for typing shortcuts
          ex:
            \set eav 'EXPLAIN ANALYZE VERBOSE'
          psql:
            :eav SELECT COUNT(*) FROM pg_tables
          prepend ":" to resolve variable
          use lowercase
        Retrieving Prior Commands
          \set HISTSIZE 10
            recover 10 comands
      psql Gems
        Executing Shell Commands
          \! ls
        Watching Statements
          \watch
          ex: monitor queries that have not completed
            SELECT datname, waiting, query
            FROM pg_stat_activity
            WHERE state = 'active' AND pid != pg_backend_pid(); \watch 10
          expl: watch traffic every 10 seconds
          ex: run a function (logging) every 5 seconds
            SELECT * INTO log_activity FROM pg_stat_activity;
            INSERT INTO log_activity SELECT * FROM pg_stat_activity; \watch 5
          to kill: ^x ^c
        Lists
          \d: detail
          ex: list tables that start with pg_t: \dt+
            \dt+ pg_catalog.pg_t*
          ex: more details about an object: \d+
            \d+ pg_ts_dict
      Importing and Exporting Data id=g_10285
				Importing and Exporting Data <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10285>
        \copy
          default delimiter: tab
        psql Import
          best practice in denormalized data:
            create a separate staging schema for incoming data
            write explorative queries
            distribute data into normalized production tables
            delete staging schema
          ex: importing
            \connect postgresql_book
            \cd /postgresql_book/ch03
            \copy staging.factfinder_import FROM DEC_..csv CSV
              # CSV not tsv
              NULL AS ''
              DELIMITER '|'
              FROM somefile.txt
        psql Export
          ex
            \connect postgresql_book
            \copy (SELECT * FROM staging.factfinder_import WHERE s01 ~ E'^[0-9]+') TO '/test.tab'
            WITH DELIMITER E'\t' CSV HEADER
          arg
            FORCE QUOTE *
              all columns are double quoted
        Copy from/to Program
          ex: 
            \connect ..
            CREATE TABLE dir_list (filename text) 
            \copy dir_list FROM PROGRAM 'ls <path> /b'
        Basic Reporting
          ex
            psql -d postgresql_book -H -c
            "<sql_query>" -o test.html
    chapter 4. Using pgAdmin id=g_10286
			chapter 4. Using pgAdmin <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10286>
      Overview of Features
        graphical explain your queries
        SQL pane
          see generated SQL
        GUI editor for configi files
          ex: postgresql.conf pg_hba.conf
        Data export and import
          CSV, HTML etc
        Backup and restore 
        Grant wizard
        pgScript engine
        Plugin architecture
          install addons
        pgAgent
          job scheduling agent
      Connecting to a pgs Server
      Navigating pgAdmin
        Tools > Options > Browser
      pgAdmin Features
        access psql
        edit posgresql.conf
        creating database assets and setting privileges
        privilege management
          Tools > Grant Wizard
          .select schema > Grant Wizard
      Import and Export
        .select table > import
        query > run > export
      Backup and Restore
      pgScript
        for running sql tasks
        similar to Transact SQL
        has: conditionals, loops, print, records
        variables: prepended with @
        commands: DECLARE, SET
        control: IF-ELSE, WHILE
        ex
          SET @labels = SELECT ...
        ex: loop through using LINES
          WHILE @I < LINES(@labels)
          BEGIN
            SET @tdef = @tdef + ', ' + @labels[@I][0] + ' numeric(12,3) ';
            SET @I = @I +1;
          END
        ex: print
          PRINT @tdef
        ex: create table
          CREATE TABLE @tdef
      Graphical Explain
      Job Scheduling with pgAgent
    chapter 5. Data Types id=g_10287
			chapter 5. Data Types <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10287>
      Numerics
      Serials
        serial bigserial
          autoincrementing integers
        pgs creates: a sequence object
          table_name_column_name_seq
        you can create sequences independently:
          CREATE SEQUENCE
        to use some sequence in another column:
          nextval(sequence_name)
      Generate Series Function
        generate_series()
          mimics for loop in SQL
        ex:
          select x FROM generate_series(1,20,1) As x
      Characters and Strings
        char varchar text
        char: fixed length
      String Functions
        padding: lpad, rpad
        trimming: rtrim, ltrim, trim, btrim
        extracting: substring
        concat: ||
        splitting
          split_part()
            ex: get nth element
              split_part("text.sub", ".", 1) 
          unnest(string_to_array())
            to convert array to rows
            ex
              SELECT unnest(string_to_array("split.this", ".")) As x
      Regex id=g_10288
				Regex <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10288>
        ex
          regexp_replace('text', 'regex', E'replace')
        backreferences: \\1
        symbols: () [] {3}
        E: treat \ literally
        ex:
          unnest(regexp_matches('text', 'match', 'flags'))
        SIMILAR TO (~) operator
          ex
            SELECT ..
            FROM ..
            WHERE description ~ E'match'
      Temporals
        date: month, day, year
        time: without time zone and date
        timestamp: +date without time zone
        timestamptz: with time zone and date
        timetz: time with time zone
        interval
        tsrange: range of timestamp
        tstzrange
        daterange
        ex
          SELECT '2012-03-11 3:10 AM America/Los_Angeles'::timestamptz
          SELECT '2012-03-11 3:10 AM'::timestamp - '2012-03-11 1:10 AM'::timestampt
          SELECT '2012-03-11 3:10 AM America/Los_Angeles'::timestamptz AT TIME ZONE 'Europe/Paris'
      Datetime Functions
        +: add interval
        -
        ex
          SELECT '2012-03-11 3:10 AM'::timestamp + '1 hour'::interval
          SELECT '23 hours'::interval + '1 hour'::interval
        OVERLAPS: returns true if two ranges overlap
        ex
          SELECT ('2012-03-11 3:10 AM'::timestamp ,'2012-03-11 5:10 AM'::timestamp) OVERLAPS (<timestamp>) 
        generate_series()
          ex
            generate_series(<timestamp>, <end_timestamp>, <interval>)
        extracting/formatting
          date_part('hour', <timestamp>)
          to_char(<timestamp>, 'HH12:MI AM')
      Arrays id=g_10289
				Arrays <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10289>
        useful in 
          building aggregate functions
          IN and ANY clauses
          holding intermediary values
        ex
          integer[]
          character[]
        Array Constructors
          SELECT ARRAY[2001, 2002] As years
          array(): extract from query
            SELECT array(SELECT ...)
          casting
            '{Alex,Sonia}'::text[] As name
          convert delimited strings to array
            string_to_array('ca.ma.tx', '.')
          convert any to array
            SELECT array_agg(log_ts) FROM logs WHERE log_ts BETWEEN ...
        Referencing Elements 
          my_array[1] # first
          my_array[array_upper(my_array,1)] # last
        Slicing and Splicing
          my_array[2:4]
          gluing
            arr[1:2] || arr[5:6]
        Unnesting Arrays to Rows
          unnest('{1,2,3}'::smallint[])
      Range Types id=g_10290
				Range Types <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10290>
        (-2,2]
        (-2,2)
        [-2,2]
        () open ends
        [] closed ends
        Discrete Vs Continuous Ranges
        Defining Tables with Ranges
          ex
            CREATE TABLE employment (id serial PRIMARY KEY, employee varchar(20), period daterange)
            CREATE INDEX idx_emp_period ON employment USING gist (period)
            INSERT INTO employment (employee, period) VALUES ('Alex', '[2012-04-24, infinity)'::daterange)
        Range Operators
          overlap (&&)
          contains (@>)
          ex: overlap: who worked with whom
            SELECT e1.employee, 
              string_agg(DISTINCT e2.employee, ', ' ORDER BY e2.employee) As colleagues
            FROM employment As e1 INNER JOIN employment As e2
            ON e1.period && e2.period
            WHERE e1.employee <> e2.employee GROUP BY e1.employee;
          out:
            employee | colleagues
            ----------+------------------
            Alex     | Leo, Regina, Sonia
            Leo      | Alex, Regina
          ex: contains @>: who is currently working
            SELECT employee FROM employment WHERE period @> CURRENT_DATE GROUP BY employee
          out:
            employee
            ---
            Alex
          ex: contained in <@
            first arg: value
            second arg: range
      JSON
        Inserting JSON Data
          ex: define a column
            CREATE TABLE families_j (id serial PRIMARY KEY, profile json)
          ex: insert
            INSERT INTO families_j (profile) VALUES (
              '{"name":"Gomez", "members": [{"member":{"relation":"padre",...}}]}')
        Querying
          functions
            json_extract_path
            json_array_elements
            json_extract_path_text
          ex: query subelements
            SELECT
              json_extract_path(profile, 'name') As family,
              json_extract_path_text( json_array_elements( json_extract_path(profile, 'members') ), 'member', 'name') As member
              json_array_elements
            FROM families_j
          out:
            family | member
            ---
            Gomez | Alex
            Gomez | Sonia
          shortcut operators
            json_extract_path
            json_array_elements
            ->> #>> json_extract_path_text
          ex: operators ->>
            SELECT
              profile->>'name' As family,
              json_array_elements( (profile->'members')) #>> '{member,name}'::text[] As member
            FROM families_j
          funs
            json_array_length
            -> 
              return specific index element
          ex
            SELECT id,
              json_array_length(profile->'members') As numero,
              profile->'members'->0#>>'{member,name}'::text[] As primero
            FROM ..
          out:
            id|numero|primero
            1|4|Alex
          -> operator
            profile->'members'
            profile->0
          note: arrays in json start at 0
        Outputting JSON
          converting other data to JSON
          funs
            row_to_json
          ex
            SELECT
              row_to_json(f) As x
            FROM (SELECT id, profile->>'name' As name FROM families_j) As f
          out
            x
            {"id":1,"name":"Gomez"}
          ex: output each row
            SELECT row_to_json(f) FROM families_j As f
        Binary JSON: jsonb
          same-named operators
          differences
            no duplicate keys
            better performance
            indexed using GIN
          ex
            CREATE TABLE families_b (id serial PRIMARY KEY, profile jsonb)
          new operators:
            = equality
            @> contains 
            <@ contained
            ? key exists
            ?| any of array of keys exists
            ?& all of array of keys exists
          ex: list all families that have a member named Alex
            SELECT profile->>'name' As family
            FROM families_b
            WHERE profile @> '{"members":[{"member":{"name":"Alex"} }]}'
      XML
      Composite Data Types
        All Tables are Custom Data Types
    chapter 6. Tables, Constraints and Indexes id=g_10291
			chapter 6. Tables, Constraints and Indexes <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10291>
      Tables id=g_10292
				Tables <url:file:///~/Dropbox/mynotes/content/articles/articles_db.md#r=g_10292>
        Basic Table Creation
        Inherited Tables
          ex
            CREATE TABLE logs_2011 (PRIMARY KEY(log_id)) INHERITS (logs)
            ALTER TABLE logs_2011 ADD CONSTRAINT chk_y2011
              CHECK (log_ts >= '2011-1-1'::timestamptz 
                AND log_ts < <timestamp>)
        Unlogged Tables
          ex
            CREATE UNLOGGED TABLE ...
          writing data is much faster
        TYPE OF
          pgs automatically creates a composite data type in background for each table
          reverse: use composite data type as template for creating tables
      Constraints
        Foreign Key Constraints
          opt
            referential integrity
              cascade update
              delete rules to avoid orphaned records
          ex: fk constraints and indexes
            set search_path=census, public;
            ALTER TABLE facts ADD CONSTRAINT fk_facts_f FOREIGN KEY (fact_type_id) REFERENCES lu_fact_types (fact_type_id)
              ON UPDATE CASCADE ON DELETRESTRICT;
            CREATE INDEX fki_facts_1 ON facts (fact_type_id);
          indexes for fk constraints are not created automatically
        Unique Constraints
          automatically creates a unique index
          ex
            ALTER TABLE logs_2011 ADD CONSTRAINT uq UNIQUE (user_name, log_ts);
        Check Constraints
          ex: all user names to be lowercase
            ALTER TABLE logs ADD CONSTRAINT chk CHECK (user_name = lower(user_name));
        Exclusion Constraints
          ex: prevent overlapping bookings
            CREATE TABLE schedules(id .., room smallint, time_slot tstzrange);
            ALTER TABLE schedules ADD CONSTRAINT ex_schedules
              EXCLUDE USING gist (room WITH =, time_slot WITH &&)
      Indexes
        PostgreSQL Stock Indexes
          b-tree
            common index
            default
            only method for PK and unique keys
          GiST
            Generalized Search Tree
            for full text search, spatial data, unstructured data, hierarchical data
            not for uniqueness
          GIN 
            Generalized Inverted Index
            full text, jsonb
            GiST without lossiness
          SP-GiST
          hash
            popular before GIST and GIN
          btree Gist/btree GIN
        next 
    chapter 7. SQL: The PostgreSQL Way
      Views
        purists: never directly query a table
          always query via views
          useful for
            controlling permissions
            abstarction of logic
        Updatable views
          better: use INSTEAD OF triggers
        materialized views
          requery data only with REFRESH command
          benefit: not wasting resources repeatedly
          cost: not most up-to-date data
        Single Table Views
          always include PK if you will write data back to the table
          ex
            CREATE OR REPLACE VIEW census.vw_facts_2011 AS
              SELECT fact_type_id, ... FROM <table> WHERE yr = 2011;
          editing data:
            DELETE FROM <view> WHERE val = 0
            UPDATE <view> SET val = 1 WHERE val = 0;
          updating may empty view due to WHERE filter
            WITH CHECK OPTION
              keep data in view always
            then: error when you empty data
        Using Triggers to Update Views
          ex:
            CREATE OR ... AS
              SELECT ..
              FROM <table> INNER JOIN <table>
              ON <fk1> = <fk2>
            ex: trigger function
            CREATE OR REPLACE FUNCTION census.trig_vw_facts() RETURNS trigger AS $$
            BEGIN
              IF (TG_OP = 'INSERT') THEN
                INSERT INTO <table(col1, col2)>
                SELECT NEW.col1, NEW.col2;
                RETURN NEW;
              END IF;
            END;
            $$
            LANGUAGE plpgsql VOLATILE;
          bind trigger to view
            CREATE TRIGGER census.trig_vw_facts
              INSTEAD OF INSERT OR UPDATE OR DELETE ON <table>
              FOR EACH ROW EXECUTE PROCEDURE census.trig_vw_facts();
        Materialized Views
      Handy Constructions
        DISTINCT ON
          behaves like DISTINCT
            sadece () içine koyduğun kolonlara göre ayrıştırır
        LIMIT and OFFSET
          rows to skip
        Shorthand Casting
          CAST('2011-1-11' AS date)
          ===
          '2011-1-1'::date
        Multirow Insert
          ex
            INSERT INTO <table> (<cols>)
              VALUES
                (<row1>),
                (<row2>);
          ex: multirow constructor
            SELECT *
            FROM (
              VALUES
                (<row1>),
                (<row2>)
              )
        ILIKE for Case insensitive Search
          ex
            SELECT .. WHERE <col1> ILIKE '%duke%'
        Returning Functions
          ex
            SELECT i_type,
              generate_series(<arg1>, <arg2>, i_type) 
            FROM <table>;
        Restricting DELETE, UPDATE, SELECT from Inherited Tables
          child tables
            drill down hierararchy
          ONLY: delete only those records that don't have child
        DELETE USING
          join gibi delete için
          ex
            DELETE FROM census.facts
              USING census.lu_fact_types As ft
              WHERE facts.fact_type_id = ft.fact_type_id AND ft_short_name = 's01'
        Returning Affected Records
          RETURNING
          update ve insert tarafından etkilenen satırlar
          ex
            UPDATE census.lu_fact_types AS f
              SET short_name = ..
              WHERE ..
              RETURNING fact_type_id, short_name
          hangi satırları etkilediyse, onları döner
        Composite Types in Queries
          ex
            SELECT x FROM census.lu_fact_types As x LIMIT 2;
          out
            x
            ---
            (86,Population,"{D001,Total:}",d001)
            (87,Population,"{D002,Total:,""Not Hispanic or Latino:""}",d002)
          used as input for useful functions:
            array_agg
            hstore(): converts a row into key-value hstore object
          ex
            SELECT array_to_json(array_agg(f)) As cat FROM (
              SELECT MAX(fact_type_id) As max_type, category FROM census.lu_fact_types
              GROUP BY category
            ) As f
          out
            [{"max_type":102,"category":"Population"},
            {"max_type":153,"category":"Housing"}]
        DO
          inject procedural code into SQL
            all languages
          ex
            DO language plpgsql
            $$
            DECLARE var_sql text
            BEGIN
              var_sql := string_agg(
                'INSERT INTO ...
                ' || lpad(i::text, 2, '0') || '..
                '
              )
              FROM generate_series(1,51) As i;
              EXECUTE var_sql;
            END
            $$;
        FILTER Clause for Aggregates
          replaced CASE WHEN
          ex
            SELECT student,
              AVG(score) FILTER (WHERE subject ='algebra') As algebra, 
              AVG(score) FILTER (WHERE subject ='physics') As physics
            FROM test_scores 
            GROUP BY student;
      Window Functions
        windows add aggregate information
          to each row
          where aggregation involves other rows in the same window
        ex
          SELECT tract_id, val, AVG(val) OVER () as val_avg
            FROM census.facts
            WHERE fact_type_id = 86;
        OVER: sets boundary of window
        PARTITION BY
          window function over rows containing particular values (like GROUP BY)
          ex
            SELECT tract_id, val, AVG(val) OVER (PARTITION BY left(tract_id,5)) as val_avg_county
              FROM census.facts
              WHERE fact_type_id = 86 ORDER BY tract_id;
        ORDER BY
          window functions allow ORDER BY in OVER clause
          ex
            SELECT ROW_NUMBER() OVER (ORDER BY tract_name) As rnum, tract_name
            FROM census.lu_tracts
            ORDER BY rnum LIMIT 4;
          ex: PARTITION BY and ORDER BY: restarting ordering for each partition
            SELECT tract_id, val, AVG(val) OVER (PARTITION BY left(tract_id,5) ORDER BY val) as sum_county_ordered
            FROM census.facts
            WHERE fact_type_id = 86 
            ORDER BY left(tract_id,5), val;
          ex: LEAD and LAG
            SELECT * FROM (
              SELECT 
                ROW_NUMBER() OVER (wt) As rnum, 
                substring(tract_id,1,5) As county_code,
                tract_id,
                LAG(tract_id,2) OVER wt As tract_2_before,
                LEAD(tract_id) OVER wt As tract_after,
                FROM census.lu_tracts
                WINDOW wt AS (PARTITION BY substring(tract_id,1,5) ORDER BY tract_id)
              ) As x
              ..
          any aggregate function can be used as a window function
      Common Table Expressions (CTE)
        3 ways
          Basic CTE
          Writable CTE
          Recursive CTE
        Basic CTE
          WITH introduces the CTE
          ex:
            WITH 
              cte1 AS (
                SELECT * FROM <table1>
              ), 
              cte2 AS (
                SELECT * FROM <table2>
              )
            SELECT *
            FROM cte1
            WHERE ..
        Writable CTEs
          ex: child table
            CREATE TABLE logs_2011_01 (
              PRIMARY KEY (..),
              CONSTRAINT chk
                CHECK (log_ts >= '2011-01-01')
            )
            INHERITS (logs_2011);
          ex: move data from parent 2011 to childd 2011_01
            WITH t AS (
              DELETE FROM ONLY logs_2011 WHERE log_ts < '2011-03-0' RETURNING *
            )
            INSERT INTO logs_2011_01_02 SELECT * FROM t;
        Recursive CTE
          ref
            PostgreSQL Recursive Query By Example <url:#r=adb_005>
          recursively cascading table relationships
          ex
            WITH RECURSIVE tbls AS (
              SELECT 
                *
              FROM 
                tbls INNER JOIN
                pg_inherits As th ON th.inhparent = tbls.tableoid 
            )
            SELECT * FROM tbls;
        Lateral Joins
          Subqueries appearing in FROM can be preceded by the key word LATERAL. This allows them to reference columns provided by preceding FROM items
          Without LATERAL, each subquery is evaluated independently and so cannot cross-reference any other FROM item
          ex
            SELECT * FROM foo, LATERAL (SELECT * FROM bar WHERE bar.id = foo.bar_id) ss
            === conventional form
            SELECT * FROM foo, bar WHERE bar.id = foo.bar_id
    chapter 8. Writing Functions
      Anatomy of PostgreSQL Functions
        Function Basics
          ex: basic function structure
            CREATE OR REPLACE FUNCTION func_name(arg1 arg1_datatype DEFAULT arg1_default)
            RETURNS some type | set of some type | TABLE (..) AS
            $$ 
            BODY of function
            $$ 
            LANGUAGE languafe_of_function
          list of languages
            SELECT lanname FROM pg_language;
      Triggers and Trigger Functions
        actuate at level:
          statement
          row
        statement triggers
          run once per SQL statement
        row triggers 
          run for each row affected by SQL
        timing
          BEFORE
            prior to execution
            only to tables
          AFTER
            only to tables
          INSTEAD OF
            instead of the statement
            only to views
        WHEN
          which rows will fire trigger
        UPDATE OF columns_list
          fire only if certain columns are updated
        data type: trigger
          trigger function always outputs trigger data type
      Aggregates
        builtin: MIN, MAX, AVG, SUM, COUNT
        custom aggregate functions 
        ex
          CREATE AGGREGATE my_agg (input data type) (
            SFUNC=<state function name>,
            STYPE=<state type>,
            FINALFUNC=<final function name>,
            INITCOND=<initial state value>,
            SORTOP=<sort_operator
            );
      Trusted and Untrusted Languages
        Trusted
          lacks access to file system
            cannot execute OS commands
          SQL, pgSQL, PL/Perl
        Untrusted
      Writing Functions with SQL
        Basic SQL Function
          ex: insert a row and return a scalar value
            CREATE OR REPLACE FUNCTION write_to_log(param_user_name varchar, param_description text)
            RETURNS integer AS
            $$
            INSERT INTO logs(user_name, description) VALUES($1, $2)
            RETURNING log_id;
            $$
            LANGUAGE 'sql' VOLATILE;
          ex: calling it
            SELECT write_to_log('alejandro', 'Woke up') As new_id;
          ex: update a record 
            CREATE OR REPLACE FUNCTION update_logs(log_id int, param_user_name varchar, param_description text)
            RETURNS void AS
            $$
            UPDATE logs
              SET user_name = $2, description = $3 log_ts = CURRENT_TIMESTAMP WHERE log_id = $1;
            $$
            LANGUAGE 'sql' VOLATILE;
          ex: calling it
            SELECT update_logs(12, 'alejandro', 'fell back');
          using named arguments
            param_1 instead of $1
          functions can return sets
            3 approaches:
              ANSI SQL: RETURNS TABLE syntax
              OUT parameters
              composite data types
          ex: RETURNS TABLE
            CREATE OR REPLACE FUNCTION select_logs_rt(param_user_name varchar)
            RETURNS TABLE (log_id int, user_name varchar(50), description text, log_ts timestamptz) AS
            $$
            SELECT log_id, user_name, description, log_ts FROM logs WHERE user_name = $1;
            $$
            LANGUAGE 'sql' STABLE;
          ex: OUT
            CREATE OR REPLACE FUNCTION select_logs_out(param_user_name varchar, OUT log_id int, OUT user_name varchar, OUT description text, OUT log_ts timestamptz)
            RETURNS SETOF record AS
            $$
            SELECT * FROM logs WHERE user_name = $1;
            $$
            LANGUAGE 'sql' STABLE;
          ex: composite type
            CREATE OR REPLACE FUNCTION select_logs_so(param_user_name varchar)
            RETURNS SETOF logs AS
            $$
            SELECT * FROM logs WHERE user_name = $1;
            $$
            LANGUAGE 'sql' STABLE;
          ex: calling them
            SELECT * FROM select_logs_xxx('alejandro');
        Writing SQL Aggregate Functions
          how to create a geometric mean aggregate function with SQL
            geometric mean: nth root of a product of n positive numbers ((x1*x2*x3...xn)^{1/n})
            formula:
              (EXP(SUM(LN(x))/n))
          two subfunctions:
            a state transition function to sum the logs
            a final function to exponentiate the logs
          ex: state function
            CREATE OR REPLACE FUNCTION geom_mean_state(prev numeric[2], next numeric)
            RETURNS numeric[2] AS
            $$
            SELECT
              CASE
                WHEN $2 IS NULL OR $2 = 0 THEN $1
                ELSE ARRAY[COALESCE($1[1],0) + ln($2), $1[2] + 1]
              END;
            $$
            LANGUAGE sql IMMUTABLE;
          bu fonksiyon foldl fonksiyonuna karşılık geliyor
          ex: final function: divide the sum by count
            CREATE OR REPLACE FUNCTION geom_mean_final(numeric[2])
            RETURNS numeric AS
            $$
            SELECT CASE WHEN $1[2] > 0 THEN exp($1[1]/$1[2]) ELSE 0 END;
            $$
            LANGUAGE sql IMMUTABLE;
          ex: assembling pieces
            CREATE AGGREGATE geom_mean (numeric) (
              SFUNC=geom_mean_state,
              STYPE=numeric[],
              FINALFUNC=geom_mean_final,
              INITCOND='{0,0}'
            );
          ex: calling it
            SELECT geom_mean(val) As div_county
            FROM census.vw_facts
            WHERE category = 'Population'
            GROUP BY county
            ORDER BY div_county DESC LIMIT 5;
      Writing PL/pgSQL Functions
        pgSQL surpasses SQL
          declare local variables using DECLARE
          control flow
          Basic PL/pgSQL Function
            ex: return a table
              CREATE FUNCTION select_logs_rt(param_user_name varchar)
              RETURNS TABLE (log_id int, user_name varchar(50), description text, log_ts timestamptz) AS 
              $$
              BEGIN RETURN QUERY
                SELECT log_id, user_name, description, log_ts FROM logs
                WHERE user_name = param_user_name;
              END;
              $$
              LANGUAGE 'plpgsql' STABLE;
          Writing Trigger Functions in pgSQL
            you can't write trigger functions in SQL
            steps
              1. write trigger function
              2. attach it to trigger
            ex: trigger function to timestamp new records
              CREATE OR REPLACE FUNCTION trig_time_stamper() RETURNS trigger AS
              $$
              BEGIN
                NEW.upd_ts := CURRENT_TIMESTAMP;
                RETURN NEW;
              END;
              $$
              LANGUAGE plpgsql VOLATILE;
              CREATE TRIGGER trig_1
              BEFORE INSERT OR UPDATE OF session_state, session_id
              ON web_sessions
              FOR EACH ROW EXECUTE PROCEDURE trig_time_stamper();
            trigger fire only if specific columns have changed
              BEFORE INSERT OR UPDATE OF session_state, session_id
          Writing PL/Python Functions
            install python extension
              # install python on server
              CREATE EXTENSION plpython3u;
            Basic Python Function
              ex: searching docs 
                CREATE OR REPLACE FUNCTION pgs_help_search(param_search text)
                RETURNS text AS
                $$
                import urllib, re
                response = urllib.urlopen('http://...' + param_search)
                raw_html = response.read()
                result = raw_html[raw_html.find("<!-- docbot-->") : raw_html.find("<!--contentWrap-->") -1]
                result = re.sub(..)
                return result
                $$ 
                LANGUAGE plpython2u SECURITY DEFINER STABLE;
              ex: calling it
                SELECT search_term, left(pgs_help_search(search_term), 125) AS result
                FROM (VALUES ('regexp_match'), ('pg_trgm')) As x(search_term);
              ex: list files in directories
                CREATE OR REPLACE FUNCTION list_incoming_files()
                RETURN SETOF text AS
                $$
                import os
                return os.listdir('/niincoming')
                $$ 
                LANGUAGE plpython2u VOLATILE SECURITY DEFINER;
              ex: cllanig
                SELECT filename
                FROM list_incoming_files() AS filename 
                WHERE filename ILIKE '%.csv'
            PL/V8
              Livescript: trusted
                more like python, haskell
              features
                faster than SQL
                window functions (also in R)
    chapter 10. Replication and External Data
      Replication Overview
        availability and scalability
        Replication Jargon
          Master
          Slave
          Write-ahead log (WAL)
          Synchronous
          Asynchronous
          Streaming
          Cascading replication
          Remastering
        Evolution of PosgreSQL Replication
          1. only async warm slaves
          2. async hot slave + streaming replication
          3. sync replication
          4. cascading streaming replication
        Third-Party Replication Options
          Slony, Bucardo
      Setting Up Replication
        Configuring the Master
        Configuring the Slaves
        Initiating the Replication Process
      Foreign Data Wrappers
        other data sources
          foreign tables
        Querying Flat Files
          install
            CREATE EXTENSION file_fdw;
          setup
            CREATE SERVER my_server FOREIGN DATA WRAPPER file_fdw;
            CREATE FOREIGN TABLE staging.devs (developer VARCHAR(150), company VARCHAR(150))
            SERVER my_server
            OPTIONS (format 'csv', header 'true', filename '/postgresql_book/ch10/devs.psv', delimiter '|', null '');
          query
            SELECT * FROM staging.devs WHERE developer like 'T%';
            DROP FOREIGN TABLE staging.devs;
        Querying a Flat File as Jagged Arrays
          jagged array
            flat file that has different number of columns in each line
            and contain multiple header rows and footer rows
          usually orignate from spreadsheets
        Querying Nonconventional Data Sources
          ex: web services
            www_fdw 
            setup
              CREATE EXTENSION www_fdw;
              CREATE SERVER www_fdw_server_google_search
              FOREIGN DATA WRAPPER www_fdw
              OPTIONS (uri 'http://ajax.googleapis.com/...');
            make a table
              CREATE FOREIGN TABLE www_fdw_google_search (
                q text
                GsearchResultClass text,
                ...
              ) SERVER www_fdw_server_google_search;
            query
              SELECT regexp_replace(title, E'..') As title
              FROM www_fdw_server_google_search where q = 'New PostgreSQL'
              LIMIT 2;

