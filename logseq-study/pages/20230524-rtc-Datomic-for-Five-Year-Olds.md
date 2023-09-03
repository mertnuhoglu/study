tags:: study, clj/datomic, rtc

# 20230524-rtc-Datomic-for-Five-Year-Olds id=g14446

rfr: [Datomic for Five Year Olds](https://www.flyingmachinestudios.com/programming/datomic-for-five-year-olds/)

- Information model
	- Fact: entity + attribute + value + time
	- Accrete assertions and retractions
- Architecture
	- Separate querying and transacting
	- read scalability
- Programmability
	- Data structures used for all actions

## Information Model

### Relational Schemas

Entity: tuple within a relation (row in a table)

Attribute: name + data type (column in a table)

Every entity must belong to a rigid relation.

### Schemaless

It limits query power. Data integrity becomes part of your application.

Entity: document

Attribute: name

### Datomic Schemas

Entity: map of attribute/value pairs

Attribute: Name + data type + cardinality

Attributes exist independent from tables.

### Time

Relational: no notion of time. It is always "now".

If something requires multiple trips => in between each trip, no way of knowing whether the database has been altered.

No way to query past states.

Datomic accretes states.

### Summarized

Unit of information: fact

Facts don't go away. 

Fact = datom

## Architecture

Three functionalities of a database:

1. Reading
2. Writing
3. Storage

They are all separate.

Your app communicates with datomic through Peer Library.

Peer Library is within your application.

Peer is a client.

Querying happens in the Peer primarily.

Peer Library pulls the value of the database.

Queries run in your application not in the database server. This gives read scalability.

Datomic has no storage solution. It relies on storage as a service.

## Programmability

Everything is data:

- Queries
- Schema
- Transactions



