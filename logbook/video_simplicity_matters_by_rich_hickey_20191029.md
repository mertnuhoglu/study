# Video: Rails Conf 2012 Keynote: Simplicity Matters by Rich Hickey - YouTube

https://www.youtube.com/watch?v=rI8tNMsozo0

"Simplicity is prerequisite for reliability". Dijkstra

## Word origin: Sim-plex vs. complex: one fold

Easy: lie near vs. hard

Simple: One fold

Maybe: one role, one task, one concept

But not: one instance, operation

It is about lack of interleaving (objective), not cardinality

## Easy: near, at hand

It is on our hard drive

It is familiar to me. Near to our skill set.

Easy is relative. It may be hard for someone else.

## Limits

I have to think all of the stuff together when I want to manipulate something complex.

## Change

Everything starts easy. But something grows as time passes. As we move forward, we need to make the elephant change.

We need to challenge to understand the whole system. We will be completely dominated by complexity. It is independent of the process, method, tool.

## Basitlik = Fırsat

Basitlik büyük bir fırsat sağlar. Mimari çeviklik sağlar. 

Mimari çeviklik: Basit bir sistem inşa etmiş olmanın getirdiği çeviklik, diğer her türde çevikliği domine eder. 

Ne tür bir süreç kullandığınızın önemi yok. Eğer ortada devasa bir fil varsa, onu nasıl iterseniz itin. Bu zor bir iştir.

"Mimari" kelimesi eskimiş bir kelime gibi görünüyor. Bunu artık yapmıyoruz deniyor. 

Design is about pulling things apart. 

Dizayn, büyük planlar yapmak gibiymiş anlıyoruz. Fakat aslında iyi tasarım böyle bir şey değil. Parçaları bölmek ve ayrıştırmaktır iyi dizayn. 

Bunu bir kez yapınca, gerçek bir değişim fırsatı ortaya çıkar. Basit bileşenleriniz varsa, bunları farklı bağlamlarda kullanabilirsiniz. Bu işler kolaylaşır. 

Basitlik, kolaylığı getirir. Çevikliği getirir. 

## Fayda odaklıyız, maliyetler

Programmers know the benefits of everything and the tradeoffs of nothing.

Yeni bir araç görünce, hemen bunun faydalarını görmeye odaklanıyoruz.

Aslında kendimize odaklıyız çok fazla. Bunun yerine yazılıma odaklanmalıyız.

Foofighters: Düşünün ki şöyle deselerdi: "Gitar çalmak istemiyorum. Çok zor. Telleri çok zorluyor." Yeni bir grup kuruyorlar. Kazoofighters. Kim bunu dinlemek ister? Ayrıca kim bu grubun üyesi olmak ister?

## Programmers vs Programs

Şu an biz, hep kendi kolaylığımıza odaklanıyoruz. Kendi zararımıza kendi kolaylığımızı hedefliyoruz.

Yöneticiler, farklılıklar istemiyor. Tek bir şey olsun istiyorlar. Böylece sizi kolaylıkla ikame edebileceklerini düşünüyrolar. 

Neye odaklanmalıyız? Yaptığımız şeye. 

Programlamak yerine, havuzda yüzebilirdik. Ama bu bir iş değil. 

Programın kalitesine, doğruluğuna, değiştirilebilirliğine odaklanmalıyız. "Ben bunu seviyorum, çünkü şu an benim için iyi bir şey" dememeliyiz, sırf bu şimdi kolay geldiğinden. Şu an için rahatsın, ama sonra ne olacak.

## Complect

Hairball: saç yumağı

Saç yumağı, güzel bir mecaz. 

Complect: To interleave, entwine.

Complecting your program. They complected my program with other things. 

Don't do it. Karmaşıklık da buradan geliyor.

## Making things easy

Nasıl kolaylaştırabiliriz işleri?

Kolaylığın iki yönü hemen yapılabilir:

Bir şeyleri yanına getir yani araç setine ekle. Onları öğrenerek, onlarla tanışıklık geliştirerek. 

Bu aslında gerçek bir öğrenme değil ama. Bu bağımlılıktan kurtulmalısın.

Gerçekten zor bir şeyle nasıl uğraşırsın? Get smarter in 3 days. Bu mümkün değil. Daha karmaşık bir şeyle uğraşmak istiyorsan, onları kendimize yaklaştırmalıyız, onların implementasyonunu olabildiğince basit hale getirerek. 

İşlerin kolaylaşmasını istiyorum. Ama bunu yeni araçlar ekleyerek yapmak istemiyorum. 

We can make same exact software today with dramatically simpler stuff. Radically simpler than ruby. Why aren't we?

## What's in your Toolkit?

		| Complexity                    | Simplicity              |
		|-------------------------------|-------------------------|
		| Objects, State                | Value                   |
		| Methods                       | Functions, Namespaces   |
		| variables                     | managed references      |
		| Inheritance, switch, matching | polymorphism a la carte |
		| syntax                        | data                    |

syntax is inherently complex. Because it has associated with order. This is adding complexity. We need to make choices constantly.

		| looping, fold | set functions                 |
		| actors        | queues                        |
		| orm           | declarative data manipulation |

orm is one of the most complex things.

		| conditionals  | rules       |
		| inconsistency | consistency |

Eventual inconsistency is incredibly complex.

## Simplicity: wrong definition

"Simplicity --the art of maximizing the amount of work not done-- is essential." Agile Manifesto

This is wrong. Because it says simplicity is about you. It has nothing to do with you. 

Reducing the work to be done. `gem install hairball` reduces work. 
Simplicity is hard work. But there is a huge payoff. 

## Simplicity is not an objective

"Simplicity is not an objective, but one achieves simplicity despite one's self by entering into the real sense of things" Constantin Brancusi

It falls out of trying to pull things apart out of their essential nature.

What is essential nature?

## First Problem: Lists and Order

They are a sequence of things.

Does the order in this list matter?

		[depth width height]

You are going to use it. Which one is first? depth or width?

Complexity is introduced.

Set explicitly says, order is not important.

Order complects each thing with the next.

When do you see negative aspect of that? Everytime you use it.

It inhibits change:

		[name email] -> [name phone email]

You know what happens. 

Everybody says: "we don't do that". But even if you don't do that, it is there. 

Positional arguments to functions is an example of this problem. 

Named arguments or map is simple.

Syntax is essentially complex.

		| Complex       | Simple              |
		| Product types | Associative records |

Maps or hashes can do that.

		| Complex             | Simple               |
		| Imperative programs | Declarative programs |

Change the order of statements, it is broken. 

		| Complex     | Simple  |
		| Prolog      | Datalog |
		| Call chains | Queues  |

Call chains: A calls B calls C. This is ordered list where the order matters. 

Bunu değiştirmek şundan daha zor: A puts its result into queue. B reads from this queue.

Queues are really important.

		| Complex | Simple                 |
		| XML     | json, clojure literals |

XML was designed to support text files where order matters. But is this good for data? No that is terrible. Intuitively people choose json over xml, when they have a map it is inherently simpler. It says what it is. It is data describing protocol.

Maps (aka hashes), Use it all the time

First class associative data structures

Idiiomatic support: literals, accessors, symbolic keys

Generic manipulation

## Second problem: Information is Simple

This is a problem we create ourselves. We ruin it. We wrap it in stupid classes. 90% you use classes that do nothing. Use maps instead. 

One excuse: encapsulation

Encapsulation is for implementation details. Information doesn't have implementation. There is nothing to hide. Because you are not gonna change this aspect of information.

All information has to have some representation. Direct representation or accessors. You have to expose its representation. You don't do encapsulation actually.

### What happens when we wrap information in classes?

Can you move it?

Can I make it a service?

Litmus test: can you move your subsystems?

Verb oriented things. 

Subsystems must have:

- Well defined boundaries
- Abstracted operation interface (verbs)
- General error handling
- Take/return data (biggest point)

We take/return data in REST/RPC but not inside our programs. Why?

They are good inside your programs too.

## Simplicity is a Choice

This needs work. This is the most important work. 

Choose simple constructs. 

Complexity inhibits understanding. And therefore robustness.

Simplicity enables change. It is the primary source of true agility.

Agility means to do something. Not undoing, not redoing.

# (30) The Value of Values with Rich Hickey - YouTube

https://www.youtube.com/watch?v=-6BsiVyC1kM

What is IT? Information technology.

What do we mean with information? Information is facts.

What is a fact? A place for a piece of information. 

This is dead wrong.

What is a place? A particular portion of space. It is delimited. 

We know about places. Memory addresses are places. We can go to them and substitute content with other content. 

We are actually building information systems. 

In memory: Mutable objects are abstractions of places. And they have methods. 

In durable storage: tables/documents/records are places. 

PLOP: place oriented programming: New information replaces old. 

There is a reason we do PLOP. We had small RAM and disks. 

Those limitations are long gone. But we are retaining the rules of that era.

Why PLOPs still rule?

Memory and records had meaning before computers. 

What is Value? Relative worth. A particular number or amount. Precise meaning. 

Is a String a Value? Is it immutable? Equality, comparability are basis for logic. 

## Value Propositions

Values can be shared.

Values support reproducible results. 

Values aggregate to values. 

Values are easy to convey.

Values make the best interfaces. What do you send over the wires? CORBA is dead for a reason.

## Facts are Values

Not places.

Don't facts change? No, they incorporate time.

Fact is an event known to have happened.

Facts != Recent Facts:

But our current systems know only current facts.

You cannot update a fact.

## Information systems

They are fundamentally about facts.

To give users leverage.

Systems should be value-oriented.

Don't use process constructs for information.

We can use them inside programs but not for keeping information. 

Place has no role in an information model.

## Programmer IT

Source Control:

- Update in place? No
- Timestamps: of course

Logs

- Update in place? No
- Timestamps: of course


## Big Data

Business to programmers: I like your database better than the one you gave me.

## Summary

We continue to use PLOP languages and database

and make new ones!

long after rationale is gone.

# Rich Hickey: Deconstructing the Database - YouTube

https://www.youtube.com/watch?v=Cym4TZwTCNU&t=329s

## Problem of Complexity

The fundamental problem we want to solve: problem of complexity.

Article: "Out of the Tar Pit"

> All complexity caused by state or control.

> By adopting functional programming, declarative programming, and relational model for data we can get rid of the complexity.

But they miss: The data in relational model gets updated. Other than that, everything is functional.

What is most declarative programming language we encounter?

SQL. Declarative programming is much better than both oop and even functional programming.

Problems we have:

1. But with client-server database, declarative programming is alien to us.

2. There is no basis that you can rely on. The database in its entirety is a place.

3. Round-trip fears: 

4. Fear of overloading server. 

Consistency and scale problems:

Servers are monolithic.

Flexibility problems:

Relational model have rectangles. Sparse data, irregular data, hierarchy issues. 

Multi-valued attributes.

Information and time:

We want the database to follow ledger, audit, logging logic.

Perception and reaction:

Reaction is more eventing. How do we see changes in traditional database. We poll. We want to make reactive systems that don't poll.

## Traditional Database

There is transactional service that introduces novelty.

There is query service that accepts questions.

There is indexing service that sorts the data. That is the leverage of the database.

How do you communicate with database? With strings. 

In most applications, questions dominate. Then I am going to store the answers in a cache. 

What goes into cache? What form is it? When does it get validated? These are your problems not database's problems.

And there are other things that databases come with. 

- Most databases have a data model. 

- They contain a state model. Relational algebra is perfect. It is great. How do you get a new state? A miracle occurs. Update is not mathematical. There is not the same model behind it. Update in place occurs.

What is missing is an information model. This is precise. By information model, I mean the ability to store facts, to have immutability. 

Challenges:

- State model. 

There is a great reason why databases work this way. We had a tiny little disk. They had a huge amount of coordination overhead.

## Deconstructing approach

The approach is based on three principles:

1. Move to information model

Using values and information model has architectural implications. This is the real example of that action that adopting information has real implications. 

2. Split process and perception

We are going to use our storage immutably. Once we store stuff we won't change it. 

## A Database of Facts

Information model requires a database of facts.

1. Remove structure a la RDF

That means sucking structure out. In a relational model, there is nothing about when. Granularity of the fact is this composite thing. If I have a row and I change email field, this is the single fact. It dramatically simplifies stuff.

RDF is an attempt to have a universal schema for information. They use triples: subjects, predicates, objects. That is not enough because it doesn't have temporal aspect.

Generally good idea because it is atomic. 

2. Atomic

Datom consists of: entity, attribute, value, transaction. 

It must include time.

## Database State

The database as an expanding value

Database state problemimiz var. Veritabanı temelde bir değer olmalı. Immutable olmalı. Burada bir çelişki var gibi. Çünkü yeni veri işlemi olmak zorunda. Yenilik nasıl girecek?

Değer kavramını nasıl buraya oturtabiliriz. 

Think of database as an expanding value. It grows by accretion of facts. The past doesn't change, it is immutable.

We will get a lot of freedom with this approach.

## Process

The other problem is how are we going to represent change?

Update this place. Go do something there. We won't do this anymore. How are we gonna say this to database?

Novelty problem. We will represent novelty as assertions or retractions of facts. This new thing is true. This old thing is not true.

This ends up being the minimal model.

The other key thing we want to do with processes is reifying. If you talk about the database, and you want to know what happened. How do you figure this out?

Maybe there is transaction logs. You can replay this. It is really hard to understand by this way.

## Accretion process

It really adds to what already is there. 

You will be able to access the past. 

It is not snapshot from yesterday. 

This is important for information model.

## Deconstruction

## Model

We want to empower independent applications

## State

How do we represent immutable expanding value?

It has to be sorted. 

In functional programming we use persistent trees. 

You can represent a large immutable structure. You can represent almost anything as a tree. And you can represent that immutably by using structural sharing.

# Git is a purely functional data structure - blog. 

https://blog.jayway.com/2013/03/03/git-is-a-purely-functional-data-structure/

Git is a purely functional data structure. Learning how to use git requires learning how to manipulate that data structure.

A functional data structure is immutable. But it supports operations like insertion or delition, they are just not in-place.

A list: `[3,2,1]`

If the list is mutable: inserting 4 at the head: `[4,3,2,1]`

Now the old value `[3,2,1]` is lost to us.

In the functional model, it creates a new value: `[4,3,2,1]` without modifying original list.

Both values exist now. 

But we can share elements in memory. 

``` bash
  +---+    +---+    +---+    +---+
  | 4 +--->+ 3 +--->+ 2 +--->+ 1 |
  +---+    +---+    +---+    +---+
    |        |
new list  original
``` 

In git, the numbers above are commits. Each commit is acopy of your entire working state at point in history.

# Introduction of B+ Tree - GeeksforGeeks

https://www.geeksforgeeks.org/introduction-of-b-tree/

# (30) Data Structures: Heaps - YouTube

https://www.youtube.com/watch?v=t0Cq6tVNRBA

In min heap, the elements are smaller in upper levels.

		2 -> 4, 8
		4 -> 9, 7
		8 -> 10, 9

## Insertion

Two steps:

1. Put the element to the next place

/Users/mertnuhoglu/Pictures/screenshots/Screen Shot 2019-10-29 at 4.38.33 AM.png

2. Bubble it up to the upper levels

/Users/mertnuhoglu/Pictures/screenshots/Screen Shot 2019-10-29 at 4.39.29 AM.png

/Users/mertnuhoglu/Pictures/screenshots/Screen Shot 2019-10-29 at 4.39.55 AM.png

## Removing the minimum element

## Implementation

Note that there are no gaps in the heap. We can use an array to store these values.

/Users/mertnuhoglu/Pictures/screenshots/Screen Shot 2019-10-29 at 4.43.01 AM.png

# (30) 5. Binary Search Trees, BST Sort - YouTube

https://www.youtube.com/watch?v=9Jry5-82I68&list=PLUl4u3cNGP61Oq3tWYp6V_F-5jb5L2iHb&index=5

Brand new data structure. 

## Runway Reservation System

Basic scheduling problem. 

Airport with a single runway. 

Reservations for future landings is what the system is built for. 

### Goal: 

Reserve request for landings specifies landing time t

In particular, add t to the set R of landing times if no other landings are scheduled within k minutes. 

You wouldn't insert if the constraint is violated.

Time is part of the system. Everytime you have a plane that is landing you will remove its landing time. 

Remove from set R after plane lands. 

		|R| = n

O(log n) time

n is the size of the set.

Example:

Now we are at time 37.

`41.2, 49, 56.3` are existing landings.

A new request for 53 comes. 

You say ok assuming k = 3.

44 is not allowed too close to 41.2.

20 is not allowed (past)

### opt01: Unsorted list/array:

What is wrong with this data structure from efficiency point of view.

Everything is here linear. Inserting is constant time. But checking other elements is linear time because it is unsorted. You need to check all the elements. 

Insert in O(1) without check. But check takes O(n) time.

### opt02: Sorted array:

		[20 32 37 45]

Particular time to search: 34. 

Binary search: You find midpoint: 32. 34 > 32. So I am going to move to right. Within logarithmic time you'll find insertion point.

You don't necessarily insert there. Then you do comparison within k minutes to the left and right.

O(log n) for the search

Constant for the comparison

Insertion?

Right now, indices are 1 2 3 4.

When you do insertion, you shift every index to the right.

Find the smallest i such that `R[i] >= t` in O(log n) time.

Compare R[i] and R[i-1] against t is in O(1) time.

But actual insertion requires shifting. That takes O(n) time because it is an array.

### opt03: Sorted list

List's elements contain a pointer to the next element. Once you find the insertion point, you can insert in constant time.

What is the problem with list?

You can do binary search in sorted list. But there is no random access in list because there is no index. So you cannot find the insertion point in O(log n) time.

### opt04: Heaps

They are arrays but you can visualize as trees. 

min/max heaps. They have fairly weak invariant. Binary search trees are similar to heaps. Heaps are weak in variant. Look at the min element. If you 

element that is `<= k` or `>= k` from t: O(n) time

What is a heap?

### opt05: Binary search tree (BST): fast insertion into a sorted array

		30 -> 17 40
		17 -> 14 20

node x has key(x)

30 is the key of first node.

Unlike in a heap, the data structure is a little more complicated. Heap is an array visualized as a tree. BST is actually a tree that has pointers unlike a heap. 

Pointers: 

- parent(x)
- left(x)
- right(x)

You have an ordering of key values that satisfy the invariant: 

For all nodes x, if y is in the left subtree of x then `key(y) <= key(x)`

For all nodes x, if y is in the right subtree of x then `key(y) >= key(x)`

#### Insert

Insert 49 

Make a node with key value 49

Insert 79

		Compare 49 to 79. 79 > 49. Go to right.

		49 -> . 79

Insert 46

		49 -> 46 79

Insert 41

		41 < 49. Left. 41 < 46. Left. 

		46 -> 41 .

Insert 42

		42 < 49 . Left. 42 < 46. Left. 42 > 41. 

If you didn't have the k = 3 check, then you would put it there. 

h: height of tree

Insertions with check is in O(h) time.

#### find min()

Keep going to the left till you hit a leaf. O(h) complexity

next_larger(x): next larger value than x: O(h)

### New Requirement

Rank(t): how many planes are scheduled to land at times `<= t`?

Augment the BST structure

		49 -> 46 79
		79 -> 64 83

Insert or delete modify "size" numbers. 

"size" numbers correspond to subtree sizes.

		49,5 -> 46,1 79,3
		79 -> 64,1 83,1

Insert 43

You will increment sizes by one in the path.

		49,6 -> 46,2 79,3
		46 -> 43,1 .
		79 -> 64,1 83,1

How do we calculate Rank(t) now?

What lands before t?

1. Walk down tree to find the desired time.

2. Add in the nodes that are smaller.

3. Add in subtree sizes to the left.

Ex: t = 79

What lands before 79?

		49 < 79. 
		add 1
		since i move to the right, add 2 (corresponding to subtree 46)

		79 <= 79
		add 1
		add f (corresponding to subtree 64)

So you have 5.

Bad news: We haven't quite solved the problem because h can be O(n)

		43 -> . 46
		46 -> . 48
		48 -> . 50

But this looks like a list.

# (30) 6. AVL Trees, AVL Sort - YouTube

https://www.youtube.com/watch?v=FNeL18KsWPc&list=PLUl4u3cNGP61Oq3tWYp6V_F-5jb5L2iHb&index=6

# (30) 10.1 AVL Tree - Insertion and Rotations - YouTube

https://www.youtube.com/watch?v=jDM6_TnYIqE&t=1916s

# B-tree - Wikipedia

https://www.wikizeroo.org/index.php?q=aHR0cHM6Ly9lbi53aWtpcGVkaWEub3JnL3dpa2kvQl90cmVl

B-tree is a self-balancing tree data structure. Search, access, insertion, deletions are in logarithmic time. It is a generalization of binary search tree because a node can have more than two children.

It is commonly used in databases and file systems.

/Users/mertnuhoglu/Pictures/screenshots/20191030154536.png

Internal (non-leaf) nodes can have a variable number of child nodes. This makes B-trees not need rebalancing too much. 

Ex: 2-3 B-tree: an internal node may have 2 or 3 child nodes.

Each internal node contins a number of keys. Keys act as separation values dividing its subtrees. 

Ex: An internal node has 3 child nodes. Then it has 2 keys: a1 and a2. All values in the leftmost subtree will be less than a1. 

## Insertion

Ex: B-tree insertion example:

/Users/mertnuhoglu/Pictures/screenshots/20191030155102.png

1. If the node has space, insert the new element in the node.

2. Other wise the node is full, evenly split it into two nodes so:
  1. A single median is chosen
	2. Values less than the median are put in the new left node. Greater than the median put in right.
	3. Separation value (median) is inserted in the node's parent.


