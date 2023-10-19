tags:: study, rtc, tpc/programming

# 20230916-rtc-Basics-of-the-Unix-Philosophy id=g14705
  
[Basics of the Unix Philosophy](http://www.catb.org/~esr/writings/taoup/html/ch01s06.html)

Highlights: https://getupnote.com/share/notes/lBKIPjJNRtftKR05i1PAP18AWwu2/e87ce51e-522c-4571-84c1-ae34ab32235a

> The Unix philosophy (like successful folk traditions in other engineering disciplines) is bottom-up, not top-down.

> Doug McIlroy:

> This is the Unix philosophy: Write programs that do one thing and do it well. Write programs to work together. Write programs to handle text streams, because that is a universal interface.

> Ken Thompson:

> 1. Rule of Modularity: Write simple parts connected by clean interfaces.
> 2. Rule of Clarity: Clarity is better than cleverness.  
> 3. Rule of Composition: Design programs to be connected to other programs.  
> 4. Rule of Separation: Separate policy from mechanism; separate interfaces from engines.  
> 5. Rule of Simplicity: Design for simplicity; add complexity only where you must.  
> 6. Rule of Parsimony: Write a big program only when it is clear by demonstration that nothing else will do.  
> 7. Rule of Transparency: Design for visibility to make inspection and debugging easier.  
> 8. Rule of Robustness: Robustness is the child of transparency and simplicity.  
> 9. Rule of Representation: Fold knowledge into data so program logic can be stupid and robust.  
> 10. Rule of Least Surprise: In interface design, always do the least surprising thing.  
> 11. Rule of Silence: When a program has nothing surprising to say, it should say nothing.  
> 12. Rule of Repair: When you must fail, fail noisily and as soon as possible.  
> 13. Rule of Economy: Programmer time is expensive; conserve it in preference to machine time.  
> 14. Rule of Generation: Avoid hand-hacking; write programs to write programs when you can.  
> 15. Rule of Optimization: Prototype before polishing. Get it working before you optimize it.  
> 16. Rule of Diversity: Distrust all claims for “one true way”.  
> 17. Rule of Extensibility: Design for the future, because it will be here sooner than you think.  

> As Brian Kernighan once observed, “Controlling complexity is the essence of computer programming” \[[Kernighan-Plauger](http://www.catb.org/~esr/writings/taoup/html/apb.html#Kernighan-Plauger "[Kernighan-Plauger]")\]. Debugging dominates development time, and getting a working system out the door is usually less a result of brilliant design than it is of managing not to trip over your own feet too many times.

> All have failed as cures, if only because they ‘succeeded’ by escalating the normal level of program complexity to the point where (once again) human brains could barely cope. As Fred Brooks famously observed \[[Brooks](http://www.catb.org/~esr/writings/taoup/html/apb.html#Brooks "[Brooks]")\], there is no silver bullet.

> The only way to write complex software that won't fall on its face is to hold its global complexity down — to build it out of simple parts connected by well-defined interfaces, so that most problems are local and you can have some hope of upgrading a part without breaking the whole.

> Under classic Unix, as many programs as possible are written as simple _filters_, which take a simple text stream on input and process it into another simple text stream on output.

> Despite popular mythology, this practice is favored not because Unix programmers hate graphical user interfaces. It's because if you don't write programs that accept and emit simple text streams, it's much more difficult to hook the programs together.

> More elaborate forms of inter-process communication, such as remote procedure calls, show a tendency to involve programs with each others' internals too much.

> Consider also Postel's Prescription:\[[10](http://www.catb.org/~esr/writings/taoup/html/ch01s06.html#ftn.id2878578)\] “Be liberal in what you accept, and conservative in what you send”. 

> Kernighan & Plauger's; “90% of the functionality delivered now is better than 100% of it delivered never”.

> Donald Knuth (author of _The Art Of Computer Programming_, one of the field's few true classics) popularized the observation that “Premature optimization is the root of all evil”.\[[11](http://www.catb.org/~esr/writings/taoup/html/ch01s06.html#ftn.id2878872)\] And he was right.

> _Prototype, then polish. Get it working before you optimize it_. Or: Make it work first, then make it work fast. ‘Extreme programming' guru Kent Beck, operating in a different culture, has usefully amplified this to: “Make it run, then make it right, then make it fast”.

> He wouldn't issue long specifications; he'd lash together some combination of shell scripts and awk code that did roughly what was needed, tell the customers to send him some clerks for a few days, and then have the customers come in and look at their clerks using the prototype and tell him whether or not they liked it.
