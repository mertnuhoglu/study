	<url:file:///~/Dropbox/mynotes/content/articles/articles_scala.md>

# Unclassified

	sealed trait
		http://stackoverflow.com/questions/11203268/what-is-a-sealed-trait
			alternative to enums
			can only be extended in a single file
			ex:
				sealed trait Answer
				case object Yes extends Answer
				case object No extends Answer
			ex using match:
				x match {
					case No => ..
					case Yes => ..
		http://stackoverflow.com/questions/32199989/what-are-the-differences-between-final-class-and-sealed-class-in-scala
			sealed vs. final
				final: cannot be extended
				sealed: can be extended in the same source file
	trait
		http://docs.scala-lang.org/tutorials/tour/traits.html
			similar to interfaces in  java
			specify signature of methods
			can have default implementations
		http://stackoverflow.com/questions/1991042/what-is-the-advantage-of-using-abstract-classes-instead-of-traits
			abstract class vs. trait
				a class can extend one abstract class
				a class can extend multiple traits
		http://stackoverflow.com/questions/1229743/what-are-the-pros-of-using-traits-over-abstract-classes
			you can use multiple traits (stackable)
			cannot have constructor parameters
		http://joelabrahamsson.com/learning-scala-part-seven-traits/
			trait Swimming
			trait Flying
			abstract class Bird
			class Pigeon extends Bird with Swimming
			class Hawk extends Bird with Swimming with Flying


