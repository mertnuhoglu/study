# Hexagon, Schmexagon? – Part 1 - codecentric AG Blog

[Hexagon, Schmexagon? – Part 1 - codecentric AG Blog](https://blog.codecentric.de/en/2020/07/hexagon-schmexagon-1/)

Hexagon, Schmexagon? – Part 1
07/29/20 by Tobias GoeschelNo Comments

This understanding of domain-related code as the crucial element of solving business problems is the fundamental principle of Domain Driven Design:
All other aspects of a software system are supporting extensions to this model, and should be treated as such.


“The goal of the most ambitious enterprise system is a tightly integrated system spanning the entire business. Yet the entire business model for almost any such organisation is too large and complex to manage or even understand as a single unit. The system must be broken into smaller parts, in both concept and implementation.”


1. The classic approach: Layered architecture

Presentation layer (UI, view, graphical interactions)
Application layer (a.k.a. service layer, containing APIs and “jobs”, i.e. use cases4)
Domain layer (a.k.a. business layer)
Infrastructure layer (persistence, message passing, architectural frameworks)

2. What’s wrong with layers?

sometimes it’s advisable to build what he calls “Smart UI” applications:

...

But makes sure to clarify:

”Smart UI is an alternate, mutually exclusive fork in the road, incompatible with the approach of domain-driven design.”



Therefore, when circumstances warrant:
Put all the business logic into the user interface. Chop the application into small functions and implement them as separate user interfaces, embedding the business rules into them. Use a relational database as a shared repository of the data. Use the most automated UI building and visual programming tools available.”


3. Enter Hexagonal Architecture

Shortly after the publication of the Blue Book, in 2005, Alistair Cockburn published the original version of what he called the Hexagonal Architecture, a.k.a. the Ports and Adapters pattern
The attempted solution, repeated in many organisations, is to create a new layer in the architecture, with the promise that this time, really and truly, no business logic will be put into the new layer. However, having no mechanism to detect when a violation of that promise occurs, the organisation finds a few years later that the new layer is cluttered with business logic and the old problem has reappeared.”


He also found a symmetry between the GUI and another part of the application, which corresponds to the infrastructure layer:


An interesting similar problem exists on what is normally considered “the other side” of the application, where the application logic gets tied to an external database or other service
Alistair’s solution is as simple as it is radical: He proposes to forego the concept of layers entirely, and instead expose the core of the application — the business — to all external concerns via “ports”, i.e. a public API fit to each individual purpose. Any number of “adapters” can connect to these, and work seamlessly as a whole, leaving the core itself oblivious to technical details and the exact nature of the external parts
