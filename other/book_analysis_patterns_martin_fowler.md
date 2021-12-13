---
title: "Book: Analysis Patterns - Martin Fowler"
date: 2021-12-06T12:08:08+03:00 
draft: false
description: ""
tags:
categories: 
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:
    css: styles.css
state: wip

---

# Outline

	notation
		KL: knowledge level
		OL: operational level
	Chapter 2. Accountability
		2.1 Party
		2.2 Organization Hierarchies
		2.3 Organization Structure
		2.4 Accountability
		2.5 Accountability Knowledge Level
		2.6 Party Type Generalizations
		2.7 Hierarchic Accountability
		2.8 Operating Scopes
		2.9 Post
	Chapter 3. Observations and Measurements
		3.1 Quantity
			[Quantity| amount: Number; units: Unit; ]
			[Person| height: Quantity; ]
		3.2 Conversion Ratio
			[Unit] 1-n from [ConversionRatio| value: Number; ]
			[Unit] 1-n to [ConversionRatio| value: Number; ]
		3.3 Compound Units
			ex: 1/feet^2
			[CompoundUnit] direct n-n [AtomicUnit]
			[CompoundUnit] indirect n-n [AtomicUnit]
		3.4 Measurement
			[PhenomenonType] 1-n [Measurement]
			[Person] 1-n [Measurement]
			[Quantity] 1-n [Measurement]
			ex: John Smith is 6 feet tall, which can be represented by a measurement whose person is John Smith, phenomenon type is height, and quantity is 6 feet.
		3.5 Observation
			model01:
				[PhenomenonType] -- KL
				[PhenomenonType] 1-n [Observation] 
				[Person] 1-n [Observation]
				[Observation] ^- [CategoryObservation]
				[Observation] ^- [Measurement]
				[Category] 1-n [CategoryObservation]
				[Quantity] 1-n [Measurement]
			Recursive relationship to record evidence and assessment:
				[Observation] assessment n-n evidence [Observation]
			model02:
				[PhenomenonType] 1-n [Phenomenon] -- KL
				Phenomenon: is category in KL
				Putting them in KL allows them to be used in rules
		3.6 Subtyping Observation Concepts
			ex: Diabetes is an observation concept with two subtypes: type I diabetes and type II diabetes. An observation that type I diabetes is present for John Smith implies that diabetes is also present for John Smith.
			model
				[Phenomenon] ^- [ObservationConcept] -- KL
				[ObservationConcept] n-n supertypes [ObservationConcept]
				[CategoryObservation] n-1 [ObservationConcept]
				[CategoryObservation] ^- [Absence]
				[CategoryObservation] ^- [Presence]
		3.7 Protocol
			Protocol: method by which the observations were made
			model
				[Protocol] -- KL
				[Protocol] 1-n [Observation]
		3.8 Dual Time Record
			Observations have a limited time period during which they can be applied
			model
				[TimeRecord| start_time: Timestamp; end_time: Timestamp; ] 
				[TimeRecord] 1-n applicability [Observation]
				[TimeRecord] 1-n recording_time [Observation]
			ex: At a consultation on May 1, 1997, John Smith tells his doctor that he had chest pain six months ago that lasted for a week. The doctor records this as an observation of the presence of the observation concept chest pain. The applicability time record is a time period starting at November 1, 1996 and ending at Novembers, 1996. The recording time is the timepoint May 1,1997. (Note that some way of recording approximate timepoints would be valuable here.)
		3.9 Rejected Observation
			Rejected observations must be linked to the observation that rejects them
			model
				[Observation] 1-n [RejectedObservation]
		3.10 Active Observation, Hypothesis, and Projection
			Active observation vs hypothesis
			Active observation: Clinician runs with it, using it as a basis for treatment
			Hypothesis: leads to further tests. Result of the test indicates whether to confirm the hypothesis or reject it
			model
				[Observation] ^- [Hypothesis]
				[Observation] ^- [Projection]
				[Observation] ^- [ActiveObservation]
			ex:  If a patient has rheumatic fever, or consequent rheumatic valve disease, there is a risk of endocarditis. This risk is recorded as a projection of endocarditis. Treatments will then be based on this projection.
		3.11 Associated Observation
			ex:  Example A clinician observes weight loss, thirst, and polyuria in a patient and makes an associated observation (and hypothesis) of diabetes based on the evidence observations. The associated observation is linked to an associative function whose arguments are the observation concepts—weight loss, thirst, and polyuria—and whose product is diabetes.
			model:
				[AssociativeFunction] arguments n-n [ObservationConcept]
				[AssociativeFunction] product n-1 [ObservationConcept]
				[Observation] ^- [AssociatedObservation]
				[AssociativeFunction] 1-n [AssociatedObservation]
				[Observation] n-n evidence [AssociatedObservation]
		3.12 Process of Observation
	Chapter 4. Observations for Corporate Finance
		4.1 Enterprise Segment
		4.2 Measurement Protocol
		4.3 Phenomenon with Range
		4.4 Range
		4.5 Using the Resulting Framework
	Chapter 5. Referring to Objects
		5.1 Name
		5.2 Identification Scheme
		5.3 Object Merge
		5.4 Object Equivalence
	Chapter 6. Inventory and Accounting
		6.1 Account
			When to use account: You need to record
				Current value of something
				Also details of each change that effects that value
			Account = Quantity attribute + Entry for each change to its value
			Balance = current value of the account = net effect of all entries
			model
				[Account| balance: Quantity; ]
				[Entry| amount: Quantity; whenCharged: Timestamp; whenBooked: Timestamp; ]
				[Account] 1-n [Entry]
		6.2 Transactions
			model: two entries per transaction
				[Account] 1-n [Entry]
				[Entry] 2-1 [Transaction]
			By building the principle of conservation into the model, we make it easier to find any "leaks" in the system.
			model02: multilegged transaction
				[Account] balance n-1 [Quantity]
				[Transaction] amount n-1 [Quantity]
				[Account] withdrawals 1-n from [Transaction]
				[Account] deposit 1-n to [Transaction]
				[Transaction] n-1 [Timepoint]
		6.3 Summary Account
		6.4 Memo Account
		6.5 Posting Rules
		6.6 Individual Instance Method
		6.7 Posting Rule Execution
		6.8 Posting Rules for Many Accounts 
		6.9 Choosing Entries
		6.10 Accounting Practice
		6.11 Sources of an Entry
		6.12 Balance Sheet and Income Statement 
		6.13 Corresponding Account
		6.14 Specialized Account Model
		6.15 Booking Entries to Multiple Accounts 
	Chapter 7. Using the Accounting
		7.1 Models
		7.2 Structural Models
		7.3 Implementing the Structure
		7.4 Setting Up New Phone Services 
		7.5 Setting Up Calls
		7.6 Implementing Account-based Firing
		7.7 Separating Calls into Day and Evening
		7.8 Charging for Time
		7.9 Calculating the Tax 
		7.10 Concluding Thoughts 
	Chapter 8. Planning
		8.1 Proposed and Implemented Action 
		8.2 Completed and Abandoned Actions 
		8.3 Suspension
		8.4 Plan 
		8.5 Protocol
		8.6 Resource Allocation
		8.7 Outcome and Start Functions 
	Chapter 9. Trading
		9.1 Contract
		9.2 Portfolio
		9.3 Quote
		9.4 Scenario 
	Chapter 10. Derivative Contracts
		10.1 Forward Contracts
		10.2 Options
		10.3 Product
		10.4 Subtype State Machines
		10.5 Parallel Application and Domain Hierarchies 
	Chapter 11. Trading Packages
		11.1 Multiple Access Levels to a Package
		11.2 Mutual Visibility
		11.3 Subtyping Packages
		11.4 Concluding Thoughts 
	Chapter 12. Layered Architecture for Information Systems
		12.1 Two-Tier Architecture
		12.2 Three-Tier Architecture
		12.3 Presentation and Application Logic 
		12.4 Database Interaction
		12.5 Concluding Thoughts 
	Chapter 13. Application Facades
		13.1 A Health Care Example
		13.2 Contents of a Facade
		13.3 Common Methods 
		13.4 Operations
		13.5 Type Conversions
		13.6 Multiple Facades 
	Chapter 14. Patterns for Type Model Design Templates
		14.1 Implementing Associations 
		14.2 Implementing Generalization 
		14.3 Object Creation
		14.4 Object Destruction
		14.5 Entry Point
		14.6 Implementing Constraints
		14.7 Design Templates for Other Techniques 
	Chapter 15.
		15.3 Historic Mapping
			model
				setSaralyHistory (Dictionary (key: TimePeriod, value: Money))
			model
				| Applicable Date | Knowledge Date | Result |
				| Feb 25 | Feb 25 | 100 $/day |
				| Feb 25 | Mar 26 | 110 $/day |
				| Feb 25 | Apr 20 | 112 $/day |

