---
title: "Book: Business Dynamics - John Sterman"
date: 2022-07-28T12:49:02+03:00 
draft: false
description: ""
tags:
categories: 
type: post
url:
author: "Mert Nuhoglu"
output:
  html_document:

---

# Business Dynamics - John Sterman id=g13175

## Outline

	PART I PERSPECTIVEAND PROCESS 1 
		1 Learning in and about Complex Systems
			1.1 Introduction 3
				notes
					"Experience is an expensive school." Benjamin Franklin
					"Experience is something you get just after you need it." Anonymous
					iyi niyetli eforlar politika direnci (policy resistance) üretebilir
					Law of Acceleration. Henry Adams
						Geçmişe dair büyüme kuralları artık geçerli olmayacak yeni dönemde.
					Sistem dinamiği karmaşık sistemlerde öğrenmeyi kolaylaştıran bir yöntemdir. Pilotların, uçak simülatörlerinde eğitim görmesi gibi, sistem dinamiği de yöneticiler için uçuş simülatörleri geliştirmeyi sağlar.
				1.1.1 Policy Resistance, the Law of Unintended Consequences, and the Counterintuitive Behavior of Social Systems 5
					notes
						"And it will fall out as in a complication of diseases, that by applying a remedy to one sore, you will provoke another; and that which removes the one ill symptom produces others . . ." Sir Thomas More
						"Anything that can go wrong willgo wrong." Murphy
						"We have met the enemy and he is us." Pogo
						Problemleri çözen insanlar birçok zaman onları büyütürler. 
						Çünkü politikalar (problemlere uygulanan çözümler) beklenmedik yan etkiler üretir.
						Forrester bu tip durumlara, "sosyal sistemlerin karşıtsezgisel davranışı" der.
						Beklenmeyen gelişmeler şunlara sebep olur: (Meadows 1982)
							1. Politikaya direnç (policy resistance)
							2. Çözümlerin gecikmesi, dağılması
							3. Sistemin çözüme karşı tepki vermesi
						Örnek: Romanya'da nüfus artışını sağlamak için, kürtaj ve benzeri yöntemler ya yasaklandı, ya da zorlaştırılmış 1966 yılında. Bunun sonucunda hızlı bir doğum artışı oldu. Ama bir süre sonra doğum oranları eskisinin bile altına indi.
						/Users/mertnuhoglu/gdrive/keynote_resimler/screencapture/scs20220730_153603.jpg
						Makyavel: Bir problemle karşılaştığında en güvenli yöntem, sorunu çözmeyi geciktirmektir. Soruna doğrudan müdahele etmek, çoğu zaman onu güçlendirir. 
				1.1.2 Causes of Policy Resistance 10
					Örnek: Fiyat rekabeti. Sen fiyatı düşürünce, satışların artar. Bunu gören rakiplerin de fiyatlarını düşürürler. Sonunda aynı satış miktarına geri dönersin ama gelirlerin azalmış olur.
					Politikaya direncin oluşmasının sebebi, sistemin geribesleme döngülerini dikkate almayışımızdır. Bizim eylemlerimizle, sistemin durumu değişir. Diğer aktörler buna tepki verir. Yeni bir denge oluşur.
					Event-oriented view of the world
						Problem -> Decision -> Result
					Yan etkiler (side effect) aslında yan etki değildir. Normal etkidir. Fakat biz onları öngörmediğimiz için yan etki deriz. Yan etkilerin varlığı, aslında sisteme dair anlayışımızın sığ ve hatalı olduğunu gösterir.
						{Goals, Environment} -> Decisions -> Environment
					Kararlarımız çevreyi değiştirir. Bu da yeni kararlara neden olur.
					Ama bu arada yan etkiler de üretir, kimisi gecikmeli olan. Bunlar da hem bizim hem başkalarının amaçlarını değiştirir
					Decisions -> Side Effects -> Environment
					Environment -> {Goals, Goals of other Agents} 
					{Environment, Goals of other Agents}-> Actions of Others
					Politika direncinden kaçınmak ve yüksek etkili (high leverage) politikalar tasarlamak için, zihinsel modellerimizin sınırlarını genişletmeliyiz. Kararlarımızın neden olduğu geribesleme döngülerini dikkate almalıyız.
				1.1.3 Feedback 12
					SD modellemezinin özünde geribesleme süreçlerini keşfetmek yatar. Bunlar sistemin dinamiğini oluşturur.
					Karmaşık davranışlar, bileşenlerin karmaşıklığından değil, bileşenlerin arasındaki etkileşimlerden doğar.
					İki tür geribesleme döngüsü bulunur: pozitif (güçlendirici) ve negatif (dengeleyici).
					Örn: 
						Wintel mimarisi (pozitif)
						Sigaradaki nikotin azaldıkça insanlar daha çok sigara içer.
						Şehir güzelse, göç artar, şehir kalabalıklaşır.
						Emtianın fiyatı artarsa, talep azalır, fiyat düşer.
						Firmaların pazar payı büyüdükçe, hükümetlerin müdahelesi artar.
				1.1.4 Process Point: The Meaning of Feedback 14
				Challenge: Dynamics of Multiple-Loop Systems
			1.2 Learning Is a Feedback Process 14
			1.3 Barriers to Learning 19
				1.3.1 Dynamic Complexity 21
				1.3.2 Limited Information 23
				1.3.3 Confounding Variablesand Ambiguity 25
				1.3.4 Bounded Rationality and the Misperceptions of Feedback 26
				1.3.5 Flawed Cognitive Maps 28
				1.3.6 Erroneous Inferences about Dynamics 29
				1.3.7 Unscientific Reasoning: Judgmental Errors and Biases 30
				Challenge: Hypothesis Testing 30
				1.3.8 Defensive Routines and Interpersonal Impediments to Learning 32
				1.3.9 Implementation Failure 33
			1.4 Requirements for Successful Learning in Complex Systems 33
				1.4.1 Improving the Learning Process: Virtues of Virtual Worlds 34
				1.4.2 Pitfalls of Virtual Worlds 35
				1.4.3 Why Simulation Is Essential 37
			1.5 Summary 39
		2 System Dynamics in Action 41
			2.1 Applications of System Dynamics 41
			2.2 Automobile Leasing Strategy: Gone Today, Here Tomorrow 42
				2.2.1 Dynamic Hypothesis 44
				2.2.2 Elaborating the Model 48
				2.2.3 Policy Analysis 51
				2.2.4 Impact and Follow-up 54
			2.3 On Time and Under Budget: The Dynamics of Project Management 55
				2.3.1 The Claim 56
				2.3.2 Initial Model Development 57
				2.3.3 Dynamic Hypothesis 58
		3 The Modeling Process 83
			3.1 The Purpose of Modeling: Managers as Organization Designers
			3.2 The Client and the Modeler 84
			3.3 Steps of the Modeling Process 85
			3.4 Modeling Is Iterative 87
			3.5 Overview of the Modeling Process 89
				3.5.1 Problem Articulation: The Importance of Purpose 89
				3.5.2 Formulating a Dynamic Hypothesis 94
				3.5.3 Formulating a Simulation Model 102
				3.5.4 Testing 103
				3.5.5 Policy Design and Evaluation 103
			3.6 Summary 104
		4 Structure and Behavior of Dynamic Systems 107
				2.3.4 The Modeling Process
				2.3.5 Continuing Impact 64
			2.4 Playing the Maintenance Game 66
				2.4.1 Dynamic Hypothesis 67
				2.4.2 The Implementation Challenge 74
				2.4.3 Results 76
				2.4.4 Transferring the Learning: The Lima Experience 77
			2.5 Summary: Principles for Successful Use of System Dynamics 79
			4.1 Fundamental Modes of Dynamic Behavior
				4.1.1 Exponential Growth 108
				4.1.2 Goal Seeking 111
				4.1.3 Oscillation 114
				4.1.4 Process Point 116
				Challenge: Identifying Feedback Structure from System Behavior 117
			4.2 Interactions of the Fundamental Modes 118
				4.2.1 S-Shaped Growth 118
				4.2.2 S-Shaped Growth with Overshoot 121
				Challenge: Identifying the Limits to Growth 121 
				4.2.3 Overshoot and Collapse 123
			4.3 Other Modes of Behavior 127
				4.3.1 Stasis, or Equilibrium 127
				4.3.2 Randomness 127 
				4.3.3 Chaos 129
			4.4 Summary
	PART II TOOLS FOR SYSTEMS THINKING 135
		5 Causal Loop Diagrams 137
			5.1 Causal Diagram Notation 137
			5.2 Guidelines for Causal Loop Diagrams 141
				5.2.1 Causation versus Correlation 141
				5.2.2 Labeling Link Polarity 142
				Challenge: Assigning Link Polarities 143
				5.2.3 Determining Loop Polarity 143 
				Challenge: Identifying Link and Loop Polarity 145 
				Challenge: Employee Motivation 147
				5.2.4 Name YourLoops 148
				5.2.5 Indicate Important Delays in Causal Links 150
				5.2.6 VariableNames 152
				5.2.7 Tipsfor Causal Loop Diagram Layout 153
				5.2.8 Choose the Right Level ofAggregation 154
				5.2.9 Don’t Put All the Loops into One Large Diagram 154
				5.2.10 Make the Goals of Negative Loops Explicit 155
				5.2.11 Distinguish between Actual and Perceived Conditions 156
			5.3 Process Point: Developing Causal Diagrams from Interview Data 157
				Challenge: Process Improvement 158
			5.4 Conceptualization Case Study: Managing Your Workload 159
				5.4.1 Problem Definition 159
				5.4.2 IdentiJLing Key Variables 160
				5.4.3 Developing the Reference Mode 160
				5.4.4 Developing the Causal Diagrams
				5.4.5 Limitations of the Causal Diagram 166
				Challenge: Policy Analysis with Causal Diagrams 168
			5.5 Adam Smith’s Invisible Hand and the Feedback Structure of Markets 169
				Challenge: The Oil Crises of the 1970s 172 
				Challenge: Speculative Bubbles 173
				Challenge: The Thoroughbred Horse Market 174 
				5.5.1 Market Failure, Adverse Selection, and the Death Spiral 174 
				Challenge: The Medigap Death Spiral 176
			5.6 Explaining Policy Resistance: Traffic Congestion
				5.6.1 Mental Models of the Traffic Problem 178
				5.6.2 Compensating Feedback: The Response to Decreased Congestion 181
				5.6.3 The Mass Transit Death Spiral 185
				5.6.4 Policy Analysis: The Impact of Technology 188
				5.6.5 Compensating Feedback: The Source of Policy Resistance 189
				Challenge: Identifying the Feedback Structure of Policy Resistance 190 
			5.7 Summary 190
		6 Stocks and Flows 191
			6.1 Stocks, Flows, and Accumulation 191
				6.1.1 Diagramming Notationfor Stocks and Flows 192
				6.1.2 Mathematical Representation of Stocks and Flows 193
				6.1.3 The Contribution of Stocks to Dynamics 195
			6.2 Identifying Stocks and Flows 197
				6.2.1 Units of Measure in Stock and Flow Networks 198
				6.2.2 The Snapshot Test 199
				Challenge: Identifying Stocks and Flows 201
				6.2.3 Conservation of Material in Stock and Flow Networks 201
				6.2.4 State-Determined Systems 202
				6.2.5 Auxiliary Variables 202
				6.2.6 Stocks Change Only through Their Rates 204
				6.2.7 Continuous Time and Instantaneous Flows 206
				6.2.8 Continuously Divisible versus Quantized Flows 207
				6.2.9 Which Modeling Approach Should You Use? 208
				6.2.10 Process Point: Portraying Stocks and Flows in Practice 209
			6.3 Mapping Stocks and Flows 210
				6.3.1 When Should Causal Loop Diagrams Show Stock and Flow Structure? 210
				Challenge:Adding Stock and Flow Structure to Causal Diagrams 211
				Challenge: Linking Stock and Flow Structure with Feedback 212 
				6.3.2 Aggregation in Stock and Flow Mapping 213 
				Challenge: Modifying Stock and Flow Maps 213
				Challenge: Disaggregation 214
				6.3.3 Guidelines for Aggregation 216
				6.3.4 System Dynamics in Action: Modeling Large-Scale Construction Projects 218
				6.3.5 Setting the Model Boundary: "Challenging the Clouds" 222
				6.3.6 System Dynamics in Action: Automobile Recycling 225
			6.4 Summary 229
		7 Dynamics of Stocks and Flows 231
			7.1 Relationship between Stocks and Flows 232
				7.1.1 Static and Dynamic Equilibrium 232
				7.1.2 Calculus without Mathematics 232
				7.1.3 Graphical Integration 234
				Challenge: Graphical Integration
				7.1.4 Graphical Diflerentiation 
				Challenge:Graphical Differentiation 241
			7.2 System Dynamics in Action: Global Warming 241
			7.3 System Dynamics in Action: The War on Drugs 250 
				7.3.1 The Cocaine Epidemic after 1990 258
			7.4 Summary
		8 Closing the Loop: Dynamics of Simple Structures 263
			8.1 First-Order Systems 263
			8.2 Positive Feedback and Exponential Growth 264
				8.2.1 Analytic Solutionfor the Linear First-Order System 265 
				8.2.2 Graphical Solution of the Linear First-Order Positive Feedback System 266
				8.2.3 The Power of Positive Feedback: Doubling Times 268
				Challenge: Paper Folding 268
				8.2.4 Misperceptions of Exponential Growth 269
				8.2.5 Process Point: Overcoming Overconfidence 272
			8.3 Negative Feedback and Exponential Decay 274
				8.3.1 Time Constants and Half-Lives 279 
				Challenge: Goal-Seeking Behavior 281 
			8.4 Multiple-Loop Systems 282
			8.5 Nonlinear First-Order Systems: S-Shaped Growth 285 
				Challenge: Nonlinear Birth and Death Rates 286
				8.5.1 Formal Definition of Loop Dominance
				8.5.2 First-Order Systems Cannot Oscillate 290
			8.6 Summary 290
	PART III THE DYNAMICS OF GROWTH 293
		9 S-Shaped Growth: Epidemics, Innovation Diffusion, and the Growth of New Products 295
			9.1 Modeling S-Shaped Growth 296
				9.1.1 Logistic Growth 296
				9.1.2 Analytic Solution of the Logistic Equation 297
				9.1.3 Other Common Growth Models 299
				9.1.4 Testing the Logistic Model 300
			9.2 Dynamics of Disease: Modeling Epidemics 300
				9.2.1 A Simple Model of Infectious Disease 300
				9.2.2 Modeling Acute Infection: The SIR Model 303
				9.2.3 Model Behavior: The Tipping Point 305
				Challenge: Exploring the SIR Model 308
				9.2.4 Immunization and the Eradication of Smallpox 309 
				Challenge: The Efficacy of Immunization Programs 3SO
				9.2.5 Herd Immunity 312
				9.2.6 Moving Past the Tipping Point: Mad Cow Disease 314
				Challenge: Extending the SIR Model 316
				9.2.7 Modeling the HIV/AIDS Epidemic 319 
				Challenge: Modeling HIV/AIDS 321
			9.3 Innovation Diffusion as Infection: Modeling New Ideas and New Products 323
				9.3.1 The Logistic Model of Innovation DiJj’usion: Examples 325
				9.3.2 Process Point: Historical Fit and Model Validity 328
				9.3.3 The Bass Dijfusion Model 332
				Challenge: Phase Space of the Bass Diffusion Model 333 
				9.3.4 Behavior of the Bass Model 334
				Challenge: Critiquing the Bass Diffusion Model 334 
				Challenge: Extending the Bass Model 335
				9.3.5 Fad and Fashion: Modeling the Abandonment of an Innovation 
				Challenge: Modeling Fads 341
				9.3.6 Replacement Purchases 342
				Challenge: Modeling the Life Cycle of Durable Products 345 
			9.4 Summary 346
		10 Path Dependence and Positive Feedback 349
			10.1 Path Dependence 349
				Challenge: Identifying Path Dependence 353
			10.2 A Simple Model of Path Dependence: The Polya Process 354 
				10.2.1 Generalizing the Model: Nonlinear Polya Processes 357 
			10.3 Path Dependence in the Economy: VHS versus Betamax 359 
				Challenge: Formulating a Dynamic Hypothesis for the VCR Industry 364
			10.4 Positive Feedback: The Engine of Corporate Growth 364
				10.4.1 Product Awareness 365
				10.4.2 UnitDevelopment Costs 367
				10.4.3 Price and Production Cost 368
				10.4.4 Network Effects and Complementary Goods
				10.4.5 Product Differentiation 371
				10.4.6 New Product Development 373
				10.4.7 Market Power 374
				10.4.8 Mergers and Acquisitions 375
				10.4.9 Workforce Quality and Loyalty 376
				10.4.10 The Cost of Capital 378
				10.4.11 The Rules of the Game 380
				10.4.12 Ambition and Aspirations 380
				10.4.13 Creating Synergyfor Corporate Growth 382
			10.5 Positive Feedback, Increasing Returns, and Economic Growth 385 
			10.6 Does the Economy Lock in to Inferior Technologies? 387
			10.7 Limits to Lock In 389
			10.8 Modeling Path Dependence and Standards Formation 391
				10.8.1 Model Structure 392
				10.8.2 Model Behavior 396
				10.8.3 Policy Implications 402
				Challenge: Policy Analysis 403 
				Challenge: Extending the Model 404 
			10.9 Summary 406
	PART IV TOOLS FOR MODELING DYNAMIC SYSTEMS 407
		11 Delays 409
			11.1 Delays: An Introduction 409
			11.2 Material Delays: Structure and Behavior 412
				Challenge: Duration and Dynamics of Delays 409 
				11.1.1 Defining Delays 411
				11.2.1 What Is the Average Length of the Delay? 413
				11.2.2 What Is the Distribution of the Output around the Average Delay Time? 413
				11.2.3 Pipeline Delay 415
				11.2.4 First-Order Material Delay 415
				11.2.5 Higher-Order Material Delays 417
				11.2.6 How Much Is in the Delay? Little’s Law 421
				Challenge: Response of Material Delays to Steps, Ramps, and Cycles 425
			11.3 Information Delays: Structure and Behavior 426
				11.3.1 Modeling Perceptions: Adaptive Expectations and Exponential Smoothing 428
				11.3.2 Higher-Order Information Delays 432
			11.4 Response to Variable Delay Times 434
				Challenge: Response of Delays to Changing Delay Times 435 
				11.4.1 Nonlinear Adjustment Times: Modeling Ratchet Effects 436
			11.5 Estimating the Duration and Distribution of Delays 437
				11.5.1 Estimating Delays WhenNumerical Data Are Available 437
				11.5.2 Estimating Delays WhenNumerical Data Are Not Available 445
				11.5.3 Process Point: Walk the Line 449
			11.6 System Dynamics in Action: Forecasting Semiconductor Demand 449
			11.7 Mathematics of Delays: Koyck Lags and Erlang Distributions 462
				11.7.1 General Formulationfor Delays 462
				11.7.2 First-Order Delay 464
				11.7.3 Higher-Order Delays 465
				11.7.4 Relation of Material and Information Delays 466
			11.8 Summary 466
		12 Coflows and Aging Chains 469
			12.1 Aging Chains 470
				12.1.1 General Structure of Aging Chains 470
				12.1.2 Example: Population and Infrastructure in Urban Dynamics 472
				12.1.3 Example: The Population Pyramid and the Demographic Transition 474
				12.1.4 Aging Chains and Population Inertia 480
				12.1.5 System Dynamics in Action: World Population and Economic Development 481
				12.1.6 Case Study: Growth and the Age Structure of Organizations 12.1.7 Promotion Chains and the Learning Curve 490
				12.1.8 Mentoring and On-The-Job Training 493
				Challenge: The Interactions of Training Delays and Growth 495 
			12.2 Coflows: Modeling the Attributes of a Stock 497
				Challenge: Coflows 503
				12.2.1 Cofzows with Nonconsewed Flows 504 
				Challenge: The Dynamics of Experience and Learning 508 
				12.2.2 Integrating CofzowsandAging Chains 509 
				Challenge: Modeling Design Wins in the Semiconductor Industry 511
			12.3 Summary 511
		13 Modeling Decision Making 513
			13.1 Principles for Modeling Decision Making
				13.1.1 Decisions and Decision Rules
				13.1.2 Five Formulation Fundamentals 516 
				Challenge: Finding Formulation Flaws 520
			13.2 Formulating Rate Equations 522
				13.2.1 Fractional Increase Rate 522
				13.2.2 Fractional Decrease Rate
				13.2.3 Adjustment to a Goal 523
				13.2.4 The Stock Management Structure: Rate = Normal Rate +Adjustments 524
				13.2.5 Flow = Resource * Productivity 524
				13.2.6 Y = Y**Effect of XI on Y *Effect of X2on Y * ...*Effect
				13.2.7 Y = Y*+Effect ofX, on Y +Effect 0fX2on Y +...+ EffectofX,onY ofX,on Y 525
				Challenge: Multiple Nonlinear Effects 529
				13.2.8 Fuzzy MIN Function 529
				13.2.9 Fuzzy MAX Function 530
				13.2.10 Floating Goals 532
				Challenge:Floating Goals 533
				Challenge: Goal Formation with Internal and External Inputs 535
				13.2.11 Nonlinear WeightedAverage 535
				13.2.12 Modeling Search: Hill-Climbing Optimization 537
				Challenge: Finding the Optimal Mix of Capital and Labor
				13.2.13 Resource Allocation 544 
			13.3 Common Pitfalls 545
				13.3.1 All Outflows Require First-Order Control 545 
				Challenge: Preventing Negative Stocks 547
				13.3.2 Avoid I F . . . THEN. . . ELSE Formulations 547
				13.3.3 Disaggregate Net Flows 547
			13.4 Summary 549
		14 Formulating Nonlinear Relationships 551
			14.1 Table Functions 552
				14.1.1 SpeciJLing Table Functions 552
				14.1.2 Example: Building a Nonlinear Function 552
				14.1.3 Process Point: Table Functions versus Analytic Functions 562
			14.2 Case Study: Cutting Corners versus Overtime 563 
				Challenge: Formulating Nonlinear Functions 566
				14.2.1 Working Overtime: The Effect of Schedule Pressure on Workweek
				14.2.2 Cutting Corners: The Effect of Schedule Pressure on Timeper Task 568
			14.3 Case Study: Estimating Nonlinear Functions with Qualitative and Numerical Data 569
				Challenge: Refining Table Functions with Qualitative Data 569
			14.4 Common Pitfalls 573
				14.4.1 Using the Wrong Input 573 
				Challenge: Critiquing Nonlinear Functions 575
				14.4.2 Improper Normalization 576
				14.4.3 Avoid Hump-Shaped Functions
				Challenge: Formulating the Error Rate 583 
				Challenge: Testing the Full Model 585
			14.5 Eliciting Model Relationships Interactively 585
				14.5.1 Case Study: Estimating Precedence Relationships in Product Development 587 14.6 Summary 595
		15 Modeling Human Behavior: Bounded Rationality or Rational Expectations? 597
			15.1 Human Decision Making: Bounded Rationality or Rational Expectations? 598
			15.2 Cognitive Limitations 599
			15.3 Individual and Organizational Responses to Bounded Rationality 601
				15.3.1 Habit, Routines, and Rules of Thumb 601
				15.3.2 Managing Attention 601
				15.3.3 Goal Formation and Satisficing 601
				15.3.4 Problem Decomposition and Decentralized Decision Making 602
			15.4 Intended Rationality 603
				15.4.1 Testingfor Intended Rationality: Partial Model Tests 605 15.5 Case Study: Modeling High-Tech Growth Firms 605
				15.5.1 Model Structure: Overview 606
				15.5.2 Order Fulfillment 607
				15.5.3 Capacity Acquisition 609
				Challenge: Hill Climbing 615
				15.5.4 The Sales Force 615
				15.5.5 The Market 619
				15.5.6 Behavior of the Full System 621
				Challenge: Policy Design in the Market Growth Model 624
			15.6 Summary 629
		16 Forecasts and Fudge Factors: Modeling Expectation Formation 631
			16.1 Modeling Expectation Formation 631
				16.1.1 Modeling Growth Expectations: The TREND Function 634
				16.1.2 Behavior of the TREND Function 638
			16.2 Case Study: Energy Consumption 638 
			16.3 Case Study: Commodity Prices 643 
			16.4 Case Study: Inflation 645
			16.5 Implications for Forecast Consumers 655 
				Challenge: Extrapolation and Stability 656 
			16.6 Initialization and Steady State Response of the TREND Function 658
			16.7 Summary 660
	PART V INSTABILITY AND OSCILLATION 661
		17 Supply Chains and the Origin of Oscillations 663
			17.1 Supply Chains in Business and Beyond 664
		17.1,l Oscillation, Amplification, and Phase Lag 664
			17.2 The Stock Management Problem 666
				17.2.1 Managing a Stock: Structure 668
				17.2.2 Steady State Error 671
				17.2.3 Managing a Stock: Behavior 672
				Challenge: Exploring Amplification 674
			17.3 The Stock Management Structure 675
				17.3.1 Behavior of the Stock Management Structure 680 
				Challenge: Exploring the Stock Management Structure 683
			17.4 The Origin of Oscillations 684
				17.4.1 Mismanaging the Supply Line: The Beer Distribution Game 684
				17.4.2 Why Do We Ignore the Supply Line? 695
				17.4.3 Case Study: Boom and Bust in Real Estate Markets 698
				Challenge: Expanding the Real Estate Model 707
			17.5 Summary 707
		18 The Manufacturing Supply Chain 709
			18.1 The Policy Structure of Inventory and Production 710
				18.1.1 Order Fulfillment 711
				18.1.2 Production 7 13
				18.1.3 Production Starts 714
				18.1.4 Demand Forecasting 716
				18.1.5 Process Point: Initializing a Model in Equilibrium 716
				Challenge: Simultaneous Initial Conditions 7 18
				18.1.6 Behavior of the Production Model 720
				18.1.7 Enriching the Model: Adding Order Backlogs 723
				18.1.8 Behavior of the Firm with Order Backlogs 725
				18.1.9 Adding Raw Materials Inventory 725
			18.2 Interactions among Supply Chain Partners 729
				18.2.1 Instability and Trust in Supply Chains 735
				18.2.2 From Functional Silos to Integrated Supply Chain Management 740
				Challenge: Reengineering the Supply Chain 741
			18.3 System Dynamics in Action: Reengineering the Supply Chain in a High-Velocity Industry 743
				18.3.1 Initial Problem Definition 743
				18.3.2 Reference Mode and Dynamic Hypothesis 746
				18.3.3 Model Formulation 749
		19 The Labor Supply Chain and the Origin of Business Cycles 757
				18.3.4 Testing the Model
				18.3.5 Policy Analysis 751
				18.3.6 Implementation: Sequential Debottlenecking 753
				18.3.7 Results 755
			18.4 Summary 755
			19.1 The Labor Supply Chain 758
				19.1.1 Structure of Labor and Hiring 758
				19.1.2 Behavior of the Labor Supply Chain 760
			19.2 Interactions of Labor and Inventory Management 764 
				Challenge: Mental Simulation of Inventory Management with Labor 766
				19.2.1 Inventory- WorkforceInteractions: Behavior
				19.2.2 Process Point: Explaining Model Behavior
				Challenge: Explaining Oscillations 767
				19.2.3 Understanding the Sources of Oscillation 771 
				Challenge: Policy Design to Enhance Stability 773
				19.2.4 Adding Overtime 774
				19.2.5 Response to Flexible Workweeks 776
				Challenge: Reengineering a Manufacturing Firm for Enhanced Stability 778
				19.2.6 The Costs of Instability 779 
				Challenge: The Costs of Instability 780 
				Challenge:Adding Training and Experience 780
			19.3 Inventory- WorkforceInteractions and the Business Cycle 782 19.3.1 Is the Business Cycle Dead? 785
			19.4 Summary 788
		20 The Invisible Hand Sometimes Shakes: Commodity Cycles 791
			20.1 Commodity Cycles: From Aircraft to Zinc 792
			20.2 A Generic Commodity Market Model 798
				20.2.1 Production and Inventory 801
				20.2.2 Capacity Utilization 802
				20.2.3 Production Capacity 805
				20.2.4 Desired Capacity 807
				Challenge: Intended Rationality of the Investment Process 810
				20.2.5 Demand 8 11
				20.2.6 The Price-Setting Process 813
			20.3 Application: Cycles in the Pulp and Paper Industry 824
				Challenge: Sensitivity to Uncertainty in Parameters 828
				Challenge: Sensitivity to Structural Changes 831 
				Challenge: Implementing Structural Changes- Modeling Livestock Markets 836
				Challenge: Policy Analysis 840
			20.4 Summary 841
	PART VI MODEL TESTING 843
		21 Truth and Beauty: Validation and Model Testing
			21.1 Validation and Verification Are Impossible 846
			21.2 Questions Model Users Should Ask But Usually Don’t 851
			21.3 Pragmatics and Politics of Model Use 851
				21.3.1 Types ofData 853
				21.3.2 Documentation 855
				21.3.3 Replicability 855
				21.3.4 Protective versus Reflective Modeling 858
			21.4 Model Testing in Practice 858
				21.4.1 Boundary Adequacy Tests 861 
				21.4.2 Structure Assessment Tests 863 
				21.4.3 Dimensional Consistency 866 
				21.4.4 Parameter Assessment 866
				Challenge: Extreme Condition Tests 871
				21.4.6 Integration Error Tests 872
				21.4.7 Behavior Reproduction Tests 874
				21.4.8 Behavior Anomaly Tests 880
				21.4.9 Family Member Tests 881
	PART VII COMMENCEMENT
		22 Challenges for the Future 895
			22.1 Theory 895
			22.2 Technology 896
			22.3 Implementation 899
			22.4 Education 900
			22.5 Applications 901
				Challenge: Putting System Dynamics into Action 901 
		APPENDIX A NUMERICAL INTEGRATION 903
				Challenge: Choosing a Time Step 910 
		APPENDIX B NOISE 913
				Challenge: Exploring Noise 922 
		REFERENCES 925
		INDEX 947
