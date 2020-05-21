  <url:file:///~/Dropbox/mynotes/content/articles/articles_datascience.md>

_ id=r_lastid art_0011

# ref

  book_implementing_analytics
    <url:file:///Users/mertnuhoglu/Dropbox/mynotes/content/books/data_science/book_implementing_analytics.md>
  book_for_dummies_data_mining
    <url:file:///Users/mertnuhoglu/Dropbox/mynotes/content/books/data_science/book_for_dummies_data_mining.md>
  book_enterprise_anaytics_davenport
    <url:file:///Users/mertnuhoglu/Dropbox/mynotes/content/books/data_science/book_enterprise_anaytics_davenport.md>
  book_developing_analytic_talent
    <url:file:///Users/mertnuhoglu/Dropbox/mynotes/content/books/data_science/book_developing_analytic_talent.md>
  book_data_science_for_business
    <url:file:///Users/mertnuhoglu/Dropbox/mynotes/content/books/data_science/book_data_science_for_business.md>
  book_data_mining_with_weka
    <url:file:///Users/mertnuhoglu/Dropbox/mynotes/content/books/data_science/book_data_mining_with_weka.md>
  book_big_data_revolution
    <url:file:///Users/mertnuhoglu/Dropbox/mynotes/content/books/data_science/book_big_data_revolution.md>
  book_big_data_at_work
    <url:file:///Users/mertnuhoglu/Dropbox/mynotes/content/books/data_science/book_big_data_at_work.md>

# Articles

## Introduction to Association Rules (Market Basket Analysis) in R

  https://blog.exploratory.io/introduction-to-association-rules-market-basket-analysis-in-r-7a0dd900a3e0

## Machine Learning From Streaming Data: Two Problems, Two Solutions, Two Concerns, and Two Lessons

  http://blog.bigml.com/2013/03/12/machine-learning-from-streaming-data-two-problems-two-solutions-two-concerns-and-two-lessons/
  what is machine learning from streaming data?
    alt
      model that takes into account recent history
        ex: weather
      model that is updatable
    central question:
      underlying source generating the data is changing or not?
        weather: no
        business: sometimes
    first case: prediction conditioned on history
      call: time-series prediction
    second case: update model
      dealing with non-sstationarity
    totally different problems
  some options
    approaches
      incremental algorithms
        ml algorithm learns incrementally over data
        incremental versions of 
          support vector machines
          neural networks
          bayesian networks
      periodic retraining with batch algorithm
    issues
      data horizon
        how quickly is the need to update?
      data obsolescence
        how long takes it data becomes irrelevant?
        are older instances more relevant or variable?
        ex: economics
          newer data instances are more relevant
        shorter data horizon => more incremental learning
          don't confuse with time-series prediction
          tsp:
            model should behave based on last 
              few instances
          il:
            model should behave based on last 
              few thousand instances
  summing up
    il great for two cases
      simplicity
        no buffering, explicit retrraining
      speed

## Real time machine learning

  http://www.slideshare.net/VinothKumarKannan/real-time-machine-learning
  non-stationary data distributions
    vs.
      batch algorithm
        retrain algorithm periodically
      incremental algorithms
        algorithm learns incrementally
  hadoop vs. storm
  hadoop + storm = real time big data
    lambda architecture
  lambda architecture
    speed layer
      only new data
      batch layer overrides it
    serving layer
      load and expose batch views for querying
    batch layer
      immutable, growing datasets
    query = function(all data)
    real time big data
      perform some function from real time data 0 to history data n
        f(a_0 ... a_n)
    lambda architecture
      f(a_0 .. a_n .. a_m ) = f( a_0 .. a_n ) + f( a_n+1 ... a_m )
      real time big data      storm process      hadoop process

## Real-Time Machine Learning at Industrial scale

  http://www.slideshare.net/tumra/real-time-machine-learning-at-industrial-scale-9th-oct
  background
    solving problems with big data is hard
      tools are low level (pig, hive ...)
  real time vs. batch
    batch: accumulating, stocks
    real time: process
  key points
    move towards distributed algorithms
    latency is more favorable than accuracy
    trade-offs dependent on use cases
  tumbra labs
    labs.tumra.com
    probabilstic demographics
    hadoop
          

# Excerpt from: Practical Text Mining and Statistical Analysis for Non-Structured Text Data Applications

  http://www.textanalyticsworld.com/wp-content/uploads/2012/03/PracticalTextMining_Excerpt.pdf
  7 different practice areas in text mining
    document classification
    concept extraction
    document clustering
    web mining
    information extraction
    natural language processing
    search and information retrieval
  what is text mining
    text mining or text analytics
      umbrella terms
      analyze unstructured text data
      need
        turn text into numbers
        st algorithms can be applied
  target audience
    nonspecialist practitioners
  venn diagram of text mining and related fields
    _fig 2.1
      database systems
        information retrieval
      data mining
        document clustering
        document classification
      ai and ml
        information extraction
        nlp
      computational linguistics
        nlp
        concept extraction
  7 practice areas
    practice areas
      document classification
        categorizing text using dm classification methods
          based on labeled examples
      concept extraction
        grouping of words 
      document clustering
        categorizing terms using dm clustering methods
      web mining
        mining on internet
        focus on scale and connections
      information extraction
        identification of facts and relationships
      natural language processing
        understanding tasks
      search and information retrieval
        storage and retrieval
        search engines
    5 questions 

# forte consultancy articles id=dat_066

  forte consultancy articles <url:#r=dat_066>
  http://www.forteconsultancy.com/Page/12/1/Enabling_Analytics.aspx
  Real-Time Customer Value Management – Sales and Churn Triggers
    http://www.forteconsultancy.com/Ourideas/547/Real_Time_Customer_Value_Management_–_Sales_and_Churn_Triggers.aspx
    1384
    what?
      current models
        good in prediction
        bad in: understanding human behavior
          events that trigger sales or churn
    why?
      people are not rational
        ex: 
          incorrectly overbill and not resolve it immediately
          regardless of excellent value proposition
          no warning sign
        ex - sales:
          customer not pitched when in the zone
      event driven actions 
        5 times higher response rates 
        than traditional marketing
    how?
      5 steps:
      1. hypothesize potential triggers
        first step: business discovery
        enlist all potential triggers
        ex: telecom churn triggers
          customer billed with wrong amount
          customer experiences 5th dropped call same day
          customer says "cancel" during a complaint
          customer cancels second phone line
          customer calls competitor call center
          customer asks about MNP to employee
        ex: credit card cross sales triggers
          customer uses debit card for 100+ $
          asks credit card benefits
          transfers many to online store
          transfers salary account to bank
          pays final personal loan installment
          loses debit card and reapplies
      2. compile trigger data
        what are the IT changes required?
        ex
          telecom churn trigger
            potential trigger
              customer says "cancel" during complaint
            source
              CRM data
            required change
              "customer talks about cancelling" checkbox should be added to call center screen
          credit card cross-sales trigger
            potential trigger
              customer uses debit card for 100+ $
            source
              POS data
            required change
              no change in existing source
      3. analyze trigger impact
        ex
          trigger 1: tuesday
            customer asks about credit card benefits
          trigger 2: thursday
            customer uses debit card for +100 $
          triggered event: saturday
            45% of such customers receive credit card offer accept
        design test scenarios
        measure their impacts
      4. define trigger actions
        define corrective or proactive actions
          ex:
            promotion of relevant offer
            courtesy action to resolve dissatisfaction
        study timing of impacts
          ex:
            sms within 5 minutes after critical complaint
            or 15 mins
      5. automate event management
                      "customer talks about cancelling" checkbox should be added to call center screen
  Customer Analytics Gone Wrong – Ten Common Mistakes To Avoid When Designing Customer Analytics Models
    http://www.forteconsultancy.com/Ourideas/543/Customer_Analytics_Gone_Wrong_%E2%80%93_Ten_Common_Mistakes_To_Avoid_When_Designing_Customer_Analytics_Models.aspx
    3265
    10 design mistakes in customer analytics models
    65% of models are not deployed
    areas of mistakes
      designing models
      deploying models
    designing mistakes
      1. customer segmentation models
        most common model
          but least actionable
        customers classified into macro-segments
          around value
            sometimes around behavior
        problem:
          does not drive tactical ongoing actions
          redesign companies around segments
        mistake:
          macro-level segments
            micro level allows
              actions on little behaviors
              much more actionable and clear
            ex
              telecom:
                subscriber acts around
                  paying
                  recharging
                  time of day to talk
                  week of day to use
                  inbound subscribers receiving calls
                  call makers
          retention models
            most incorrectly designed model
            around valuation of customers
              limited scope: very recent 
                ex
                  model tags someone
                    low value with high churn risk
                  low offer is made
                  problem
                    looks at recent value of customer
                    not all history
              social network value is ignored
                customer has social network value
            churn triggers ignored
              churn models run at beginning of each month
            seasonality ignored
              using one single retention model year round
              customers act differently in different seasons
              ex
                vacation mode
                  decreased spending
                  more outside
                  abroad vacation
          channel migration models
            which customers shift to which channels
              ex: retail, dealer, online, mobile
            benefit:
              reducing operational costs
              cost per transaction type per channel per customer
            ignores level of service/effectiveness in sales a channel has
              decision is on cost only
            ex
              bank: shift loan applicants to website
                lost revenues through lost cross-sales
          next best activity models
            which action to take on customer
            focuses on revenue generating actions
              which product likely to buy
              what revenue to expect
            ignores every type of action
              migrating customer to online channel
              collecting personal information
              seeking permission from customer (sms)
              seeking referral
              taking no action
          across the board mistakes
            not getting proper insight from units/end users
              consult all departments
                limitations to consider
                needs
                definitions on core concepts
            ignoring new customers
              churn rate much higher
              traditional approach: exclude new clients
            not refreshing models
            not testing stability
              ex:
                20% of customers in Segment A
                next month: 45%
  Five New Opportunities in Credit Card Analytics
    http://www.forteconsultancy.com/Ourideas/541/Five_New_Opportunities_in_Credit_Card_Analytics.aspx
    1682
    basic credit card analytics
      ex: value, behavior, needs/lifestyle segmentation, churn prediction, credit risk analysis
      5 niche ways of data
    what?
      look from retailer's perspective
        location, product, competitive intelligence aspects
    why?
      more revenues from
        direct financial benefits
          Visa selling reports on credit card data
            for better targeting customers
          banks sell reports to merchants
            of POS and customer data
        indirect financial benefits
          more transaction for credit cards through merchants
    how?
      targeted merchant ads for google dollars
        credit card providers equivalent to google
          in targeted advertising
        google: target customers based on keywords
        providers: target based on what they purchase
        ex:
          citibank
          ads under credit card statement lines
            ex:
              customers buys a blu-ray
              netflix wants to target them
              customer receives targeted offer below his statement
      real time competitive intelligence for merchants
        ex: notify Bosh when
            price of Siemens regrigerator decreases
            based on POS transactions
      cross brand partnership opportunities for merchants
        ex
          customer buys a bag from Jimmy Choo
          customer buys a necklace from Tiffany
          partnership opportunity identified for both
        ex
          a tv brand
          customers buy a furniture brand
          joint value proposition to increase sales
      tapping into location based marketing
        ex
          customer eats at Pizza Hut
          customer location and purchase analyzed
          customer receives an SMS from Starbucks near
      sneak peak into competitor customers
        ex
          competitor card used at own POS
          card owner identified to favor Nike
          POS slip offers for Nike on card uptake
    Improving the Quality of Data Acquired from Customer Touchpoints
      http://www.forteconsultancy.com/Ourideas/538/improving_the_Quality_of_Data_Acquired_from_Customer_Touchpoints.aspx
      1488
      how to increase quality of data from touch points
      what?
        garbage in garbage out
        touch points
          stores, call centers, websites
        milestones
          defining data quality
            attributes
              accuracy, reliability, timeliness, relevance, completeness, consistency, sufficiency, understandability
          assigning ownership
            data quality roadmap
            governance structure
          monitoring systems development
            monitoring tools
              employee/agent data quality scorecards
              channel data quality report
              data quality excellence
      why?
        revenue increase
          cross-sell/up-sell
          optimized retention
          targeted pricing
        decrease in costs
          poorly targeted mails
          crm system failure
          88% of data integration projects fail or over-run budgets
      how?
        data quality management 
          is company-wide matter
        improvement roadmap
          understand business needs, expectations, priorities
          define basics, definitions, quality rules
          asses current data quality level
          understand data collection process
          establish shared data quality vision and set quality targets
          define ideal state data quality management enablers
          conduct gap analysis
          prepare data quality improvement roadmap
        address these
          define data quality and share it across company
            levels of data quality definitions
              1: data field level
              2: customer level
              3: customer touch point/channel level
          introduce data quality index
          set data quality index targets
          measure and report data quality at every touch point
          incentivize front-line employees
          demonstrate bottom-line impact
          establish data quality governance structure
          prioritize data collection efforts
  Channel Migration Strategies – Matching Customers to the Optimum Channels
    http://www.forteconsultancy.com/Ourideas/537/Channel_Migration_Strategies_%E2%80%93_Matching_Customers_to_the_Optimum_Channels_.aspx
    1780
    shifting customers to certain channels
      to optimize cost
      vs. the level of service 
    before:
      contact center
      shop/dealer
      mail
      ATM
    new channels
      twitter, sms
      email, self-care websites
      service providers
    issues
      consisten level of service across all channels
        quality, speed to service, respond, offering of types of services
      differentiated level of service
        to high value customers
      control channels customers use
    consequences
      lov-value customers serviced through high-cost channels
      high-value customers serviced through low-quality channels
    steps
      1. define cost per transaction per channel
        by channel
          shops, over phone, IVR, facebook ...
        by type of transaction
          handling complaint, processing order, enabling service, general inquiry ...
        considering all costs
          cost of employee resources, physical resources, system costs ...
      2. define existing service levels by type of transaction per channel
        efficiency
          how quicly can be serviced
        quality
          error rates
        satisfaction
      3. define customer segments based on their value/cost to serve
        by
          existing value
          potential value
          volume of service related interactions
          cost to serve
        goal
          which customers
            to leave alone
            to migrate/shift
            to fire
        @fig
      4. define migration paths
      5. design migration-driving initiatives
  One Size Does Not Fit All – Customizing Retail Chain Sales Points
    http://www.forteconsultancy.com/Ourideas/532/One_Size_Does_Not_Fit_All_%E2%80%93_Customizing_Retail_Chain_Sales_Points.aspx
    1520
    retail chains
      standard service
        level of quality
        similar products
    customization
      to make it better
      not radical change
    objective
      identify what is different about
        sales point
          locale, customers/prospects, weather ...
    three areas
      1. product location/position customization
        where the products placed
          ex: display window, store entrance
            analysis of sales data
          ex: Best Buy
            ipads at entrance
            dell at entrance
          ex: products purchased together
            Economist purchased together with Businessweek
            put them together
          ex: cross-sales
      2. advertising customization
      3. local touch customization
        ex: providing complementary umbrellas
        ex: honolulu: old people
  Business Intelligence 2.0 – The Hidden Treasures in Cell Tower Data
    http://www.forteconsultancy.com/Ourideas/525/Business_intelligence_20_%E2%80%93_The_Hidden_Treasures_in_Cell_Tower_Data.aspx
    1571
    traditional information
      BI
        predict churn
        segment customers
        identify sales opportunities
      usage
        vas, sms, calls
      volume
        frequency, length
      value
        ARPU, profitability
    new areas
      data from cell towers
        where to open new dealer
        how to allocate marketing budget
    data collected
      subscriber phone number
      type of connection (voice, sms, data)
      time of connection
      duration
      direction
    mix them with
      nationality
      age
      gender
      value-added-service usage
      arpu
      handset
      churn risk
      customer segment
      neighborhood population
    examples of use
      1. neighborhood market share
        usage:
          where to open new dealer
          send sales representatives
      2. neighborhood connection types/time etc.
        local sms is popular on weekends
          new tariffs down to neighborhood
      3. value/churn/location
        poor network coverage => churn
      4. neighborhood vas usage
        high data => new gaming application to launch
      5. cell tower segmentation
        score based on
          subscribers
          churn risks, segments
        which ones to upgrade
  Retail Loyalty Programs – Tapping Into Their Hidden Benefits
    http://www.forteconsultancy.com/Ourideas/521/Retail_Loyalty_Programs_%E2%80%93_Tapping_into_Their_Hidden_Benefits.aspx
    1488
    analyze data for:
      1. identify customers spending less than in the past
        potential to churn
      2. identify customers that can spend more
      4. identify high value customers
      4. identify customers that churned
    underused reports:
      1. store-by-store customer mix profile report
        customer mix
          number of customers
          age, nation, gender, marital, location, travel
        use
          in messages
          medium of advertesing
          location and timing
      2. store-by-store spending profile report
        ex
          when they spend
          how much
          categories
          freuency
          profitability?
        use
          high level of staff?
          categories of products to stock
            location?
          who is valuable customer?
        empower store managers
      3. store based product mix profile report
        ex
          they buy 
            most
            unique
            together
            high-margin?
            low-margin
        use
          product location
          safety stock
          near what?
          campaigns?
  Outlier Analytics – Learning From Those on the Fringe
    http://www.forteconsultancy.com/Ourideas/519/Outlier_Analytics_%E2%80%93_Learning_From_Those_on_the_Fringe.aspx
    1477
    examining outliers
      hidden gems of information
    what?
      unexpected events common
      gain insights
        new offerings
        alternative treatment
        future of customers
      why?
        ex
          3M
          products form outlier consumer segments
        ex
          Bharti Airtel, India
            farmers low voice traffic
            weather forecast
      how?
        4 steps
          1. identify outlier
            exceptional behavior
              ex: calls 30 times
              ex: purchase 100 tickets
            unexpected behavior
              cross-analysis of product usage
              low value customer that buys expensive contract
            untriggered behavior
              an old subscriber that comes back years later
          2. analyze outlier comprehensive behaviors
            before and after observation
          3. understand root cause
            talking directly
          4. create new offerings
            pricing, product, channel, benefit by unique offering
  Why Recreate the Wheel? Using Best Practices in the Workplace
    http://www.forteconsultancy.com/Ourideas/517/Why_Recreate_the_Wheel_Using_Best_Practices_in_the_Workplace.aspx
    1502
    ex
      Danaher
        from Toyoto Production System
        goal
          all employees find ways to improve every day
      Bank Atlantic
        from Commerce Bank
        open 7 days until 24
      Woolworth's
        from Tesco
        sell fuel vial retail network
      Ryanair
        from Southwest
      Ikea
        from Caesars Palace
    5 perspectives to
      1. recency
      2. relevancy
      3. measurability
      4. feasibility
      5. compatibility
  Text Mining - Going Way Beyond Just Listening to the Voice of the Customer
    http://www.forteconsultancy.com/Ourideas/511/Text_Mining_Going_Way_Beyond_Just_Listening_to_the_Voice_of_the_Customer.aspx
    1804
    what?
      80% of data in unstructured format
        call center notes
        emails 
        survey responses
    why?
      1. converting qualitative info into quanntitative
        reasons, risk, levels of contacts
        enables data mining
      2. identifying repeating complaint and inquiry reasons automatically
        root cause
      3. automatic categorization of complaints, inquiries
        use: indicator of operational problems
      4. extract competitor intelligence
        by: competitor prices, campaigns, products from contact center records
    how?
      channel analysis identification
        what are the channels customers interact?
      lexicon customization
        common words and categorization
          ex: location, numbers
          ex: custom dictionary phrases
            keywords | category
            day and night, weekend special | company tariff
            dropped call, slow connection | negative industry term
      text mining
      analytics integration
        ex
          text: 
            your website keeps crashing
          extract:
            field | value | from
            complaint | yes | crashing
            website | yes | website
          conclusion
            churn risk | high
      reporting integration
        ex:
          chart
            slow blackberry email
          axis:
            sun, mon, tue ...
      operational integration
        real time integration
          forward requests to right department
        ex:
          message =>
          contact center agent alert:
            customer has high churn risk
            waive all online banking for next 6 months
  Wondering What Lies Ahead? The Power of Predictive Modeling
    http://www.forteconsultancy.com/Ourideas/510/Wondering_What_Lies_Ahead_The_Power_of_Predictive_Modeling.aspx
    2407
    phases of predictive modeling
      training window
        analyze data from training window to develop model
      control window
        check results
      future
        predict
    telecom
      estimating demand
        ex: at&t
          predict customer demand for data and voice
          use
            what if analysis by simulating
            right product features and pricing
      predicting churn
        which customers are at-risk
        use
          tailor-made benefits to them
    financial services
      estimating customer lifetime value
        use
          resources allocated accordingly
      estimating credit risk
        input:
          income, credit history, outstanding load balances
      targeting customers
        identify most appropriate prospects
      predicting startup success
    ecommerce
      next best offer
        amazon, netflix
    technology
      filtering spam
    health
      improving care services
        identify high risk groups
          to take preventive actions
    government
      predicting equipment failure
        us army
    transportation
      optimizing customer service levels
        additional service to which customers
          to prevent cancellation
      organizing air traffic
    principles in building predictive modeling practice
      1. increase awareness: what can be achieved with analytics
        need to be owned by business
      2. define data strategy to clarify which data to collect
      3. manage data quality
      4. ensure data driven decision making culture
        performance reviews
        job descriptions
        roles
      5. establish a test and learn environment
      6. conduct pilots and spread the results
  Enabling Cross-Selling Across Group Companies By Centralizing Data
    http://www.forteconsultancy.com/Ourideas/473/Bi_on_a_$099_Budget.aspx
    2402
    what?
      central data hubs
    why?
      ex
        Hilton
        impacts:
          cross-sell to group customers across companies
          better understanding risks
            ex
              customer that defaulted or delayed payments
            availabilityof most uptodate info
          recognize high value customers 
          overwhelming communication customers get
    how?
      design of data model
        understanding business requirements
      data and customer governance
        customer data feed
        customer data access
        cusomer communications
        permissions
        data ownership
      preparation of data
      data improvements across group
      group level customer analytics
      business use of data
        prioritizing high value customers in call center
  Matchmaking to Optimize Call Center Sales Performance
    http://www.forteconsultancy.com/Ourideas/487/Matchmaking_to_Optimize_Call_Center_Sales_Performance.aspx
    953
    matchmaking between
      callcenter
      customer
    what
      direct marketing
      human factor
    how
      data preparation
        data on personnel's performance
          who offered to whom and when
          customer segments information
            demographis, needs, behavior, value
      identification of factors
        that affect performance
        ex
          age, marital, income level, needs
      optimization of allocation
        agent A best in youth prospects
        agent B in old
  BI on a $0.99 Budget
    http://www.forteconsultancy.com/Ourideas/489/Quintupling_Your_Churn_Prediction_Performance.aspx
    1043
    reporting
      features
        retrieve data from database
        reporting cubes
        design reports
        customize reports
        develop graphs
    processing
      data mining
      ETL
        most time consuming
        sql editors
      scheduling
    back end
      database
      olap server
  Quintupling Your Churn Prediction Performance
    http://www.forteconsultancy.com/Ourideas/498/Eyes_Wide_Open_Competitor_intelligence_Practices.aspx
    1337
    put together
      past behavior
      real time interactions
    what?
    why?
      ex
  Eyes Wide Open - Competitor Intelligence Practices
    http://www.forteconsultancy.com/Ourideas/509/Enabling_Cross_Selling_Across_Group_Companies_By_Centralizing_Data.aspx
    1959
    key success factor
    why?
    how?
      identification of competitors
        not only usual rivals
          ex: xerox - canon
      set up operations to follow news
        data items
          promotions
          prices
          products
          channels
          customer experience
          service levels
          operational model
          human resources
          strategies
        data sources
          web research
          observation
            visits, calls
          case studies, conferences, interviews
          competitor hr
            open positions
          third parties
            consultants, suppliers, partners
          simulation
          modeling
  Context Mining in Telecom
    http://www.forteconsultancy.com/Ourideas/536/‘Context_Mining’_To_Go_Beyond_Customer_Needs_in_Telecommunications.aspx
    what
      in which context to the customers use services
      to understand
        the lifestyles
        their brands and loyalties
        mobile marketing data
    example data
      customer 
        is a business man
        likes to party
        is in A SES group
        visits university campus regularly
        frequently works with Bank A
      data content
        visits news pages
        watches baby tv channels => has baby
  Product Network Analysis
    http://www.forteconsultancy.com/Ourideas/551/Product_Network_Analysis_–_The_Next_Big_Thing_in_Retail_Data_Mining.aspx
    SNA (social network analysis)
      identifying social relations between individuals
        based on finance, social media, telecom interactions
    PNA (product network analysis)
      application of SNA in category management
      to identify
        which products 
          belong to the same category
          are most important in category loyalty
          are most likely to cross-category sales
    comparison to Market Basket Analysis
      shortcomings of MBA
        is not complete
          does not categorize products
        biased against substitutes
          competitor products are rarely seen in same basket
    how?
      convert POS data into category optimization
        1. compile pos data
          pairwise frequencies of products: A and B: x % together
        2. run Network Clustering Algorithm
          ex
            healthy baby products: together
        3. review Networks and Key Products
          iterations of clustering to tune parameters
          typical product types:
            core: most purchased
            hook: category crossers/connectors. first product in a category to buy.
            expandable: don't create synergy
            staples: very frequent but independent of micro categories. basic need
            add-on: ice cream cones together with ice cream.
        4. strategy design: micro category and key product strategies
          sourcing: focusing on lucrative
          layout and shelf
          pricing: 
          targeted promotions
        5. pilot strategies
  Power of Predictive Modeling
    http://www.forteconsultancy.com/Ourideas/510/Wondering_What_Lies_Ahead_The_Power_of_Predictive_Modeling.aspx
    telecom
      estimating demand
        conduct what-if analysis
          change price, what will happen
          change features
      predict churn
        custom benefits to the customers at-risk
    finance
      estimating customer lifetime value
        if high value => provide better service
      estimating credit risk
      targeting customers
        which customers to target in a campaign
      predicting startup success
        youoodle
    ecommerce
      recommendation
        what will they buy next
        next best offer
    technology
      filtering spam
    health care
      improving care services
  Hype or the Real Deal? The Big Data Conundrum
    http://www.forteconsultancy.com/Ourideas/1552/Hype_or_the_Real_Deal_The_Big_Data_Conundrum.aspx
    what is?
      12 year old: 1999
      3 v:
      volume
      velocity
      variety
    what is wrong?
      remarks to consider
        Forrester: user adoption of BI: 10% in 2/3
      ability to tackle unstructured data from social media
        no need for big data 
          if less than 10K messages/day
      real time decisions and actions
        requires other enablers
          campaign management system
          customized operational systems
        not immediate need for many
    use cases
      unknown or changing requirements
        faster report implementation
          me: doubtful
      scalability
      unknown structure
        aproach: dump and ask questions later
          mongodb like document db
      real time analytics
    first perform a big data needs assessment 
  Grading Performance – The Automotive Industry BI Maturity Map
    http://www.forteconsultancy.com/Ourideas/554/Grading_Performance_%E2%80%93_The_Automotive_industry_Bi_Maturity_Map.aspx
    what?
      crm investments not effective
        dozens of apparent or hidden failures
      how to improve?
        effective BI practices required
      recommendation:
        assess how mature their practices in BI areas
    why?
      ways to assess maturity in BI
        clear definition of baseline
        identify current problem/improvement areas
        define paths to align employees
        define list of milestones/targets
        set objective scale for benchmarking
    how?
      Automotive Industry BI Maturity Map
        maturity levels
          barely basic
          on the way
          ahead of the curve
          best-in-class
        BI areas
          data management
          technology
          reporting
          analytics
          governance
    maturity levels
      level 1: barely basic
        lack of awarenes about potential benefits
        absence of mandatory enablers
          capable workforce
          proper data system
        data management
          to log sales and service data
          not related to each other
        basic demographic information
          logged
          significant data quality issues
        customer uniqueness: questionable
        reference values not up to date
          car configuration
          accessories reference table
        no reporting beyond basic financials
        basic segmentation
        governance of BI: non-existent
          BI efforts managed haphazardly
            no formal business unit to oversee
            roles not defined
              no full-time employee
      level 2: on the way
        realized the importance of BI
        absences in
          design issues
            data management structure design
            analytical models design
            roles/responsibilities design
          scale up BI
            on its own as a business unit
        data management
          data stored in proper manner
            data warehouse
            but not in complete manner
              lacking prospect data
          data is deeper
            not just customer name, also occupation, education etc.
            richer: not only dealer visit information, also contact center interactions
          customer uniqueness assured
            unified view of sales and service dat
          information can be historically tracked
          data inventory and data quality
            monitored reqularly
            only from company perspective
        technology
          basic reporting tools
          possible a data mining tool
          alternative technologies known and reviewed regularly
          vendor evaluation scorecards in place
            SLA management structures established
          reporting automation
            standard and basic
            monthly reports around key KPIs
              across business units
            one view of truth assured
              all reported data reviewed
                free of discrepancies and inaccuracies
              ad-hoc reporting at minimal level
          customer analytics
            customer differentiation beyond value
              differentiation based on service usage behavior
            basic models developed
              churn prediction
            performance and accuracy: questionable
          governance
            formal BI function
            assigned roles and responsibilities
              marketing and sales interaction common
                but not systematic
            ad hoc analysis performed frequently
            data dictionary developed
              as reference
              entire set of customer definitions 
                such as: active service customer, sports car savvy
      level 3: ahead of the curve
        view of data: 
          main competitive advantage
          critical asset
          assesed for completeness and accuracy
          compiled and shared across all parts
        data management
          all data-related processes working smoothly
            ex: measurement methods, controls
          proper data strategy
            which data to collect
            where
            when
          colected across internal channels
            ie.: even around prospective customers
            data quality controls 
          data privacy guidelines documented
        technology
          advanced level
          experts of tools in BI unit
          power users in marketing-sales
          examining alternative solutions
            rather than particular technology
            ex: outsourcing, open source, cloud
          demand and efficiency
            constantly increasing
            turnaround time: decreasing
        reporting
          fully automated
          customized for various stakeholder groups
          advanced concepts in play
            ex: dashboards, scorecards ...
            KPIs reported daily or real time
          business users: power users
            can develop ad-hoc reports
        analytics
          developed models
            in a spectrum
            acquiring, retaining customer base
          segment management
            understanding prospects and customers
          predictive sales propensity models
          stock levels optimized
            sales/demand forecast models
          accuracy of models
          automation of models
        governance
          collaboration between stakeholders
          BI: viewed as peer not a support unit
      level 4: best-in-class
        decisions 
          triggered by data
          validated by data
        utilization of intelligence
          is a KPI
          expected of all employees
        data management
          collected from
            internal
            interfacing with external parties
              ex: social media, web behaviors
              tracked and logged
            no interaction exists that is not monitored
          deep data
            > 10 years
          accurate
          granular
            down to hour
            down to conversation details level
        technology
          everything in real-time
          opportunities in new developments
        reporting
          proactive
          alert built
            real time
            ex: stock levels hit certain level => replenishment
          accessed through mobile
          tailored reports
            shared with 3rd parties
              ex: dealers, suppliers
          report usage monitored
            to understand reports' fit-for-need
        analytics
          concepts like
            customer lifetime value
              for long term strategies
          gis based analyses
            ex: potential analysis, new dealer/service location selection
        governance

## Tutorial G: Text Mining: Automobile Brand Review

  source
    Handbook of Statistical Analysis and Data Mining Applications id=art_0003
  input documents 
    crawling
  stop lists, synonyms, phrases
    phrase
      eiffel tower
    stop list
  stemming and support
    travel = traveled
  Car Review Example
    Unstructured data
      summary
      strengths
      weaknesses
    structured data
      overall rating
      price paid
      car type
    /Users/mertnuhoglu/Dropbox/public/img/ss-11.png
  SVD: singular value decomposition
    to determine underlying dimensions that account for most of common contents
    ex
      /Users/mertnuhoglu/Dropbox/public/img/ss-12.png
      first component explains 18% of total variance
        of 295 words
    svd word coefficients
      /Users/mertnuhoglu/Dropbox/public/img/ss-13.png
    scatter plot of svd word coefficients
      /Users/mertnuhoglu/Dropbox/public/img/ss-14.png
      ellipses:
      first ellipse: contains negative words
      second ellipse: positive words
  Feature Selection Tool
    ex
      transmission is one word that appeared in negative group
      how to predict find words related to transmission
      find k best predictors for "transmission"
      /Users/mertnuhoglu/Dropbox/public/img/ss-14.png
  Interactive Trees
    goal1
      classify car type
      use words as predictors for car type
      /Users/mertnuhoglu/Dropbox/public/img/ss-15.png
    goal2
      recode a new indicator variable
      from negative connotation words
      which brand accumulated most number of negative words
      /Users/mertnuhoglu/Dropbox/public/img/ss-16.png
  Other Applications
    1. extracting information reflecting opinions, needs, interest
    2. filtering unwanted documents
    3. predicting customer satisfaction levels
    4. clustering similar words/documents
    5. classifying documents
    6. predicting/routing new documents

# The Text Mining Handbook id=art_0006

  # The Text Mining Handbook <url:#r=art_0006>

## ch 12 - Text Mining Applications

  intro
    three areas especially fertile
      corporote finance
        trend extraction
        identifying correlations
        researching references to 
          specific transactions, corporate entities, persons
      patent research
        exploit existing patent assets
      life sciences
        identiy patterns of interactivities between proteins
  general considerations
    background knowledge
      taxonomies, lexicons, ontologies
        wordnet
        dictionaries
        fact books
        thesauri
    specialized background knowledge
      medicine controlled vocabulary
        mesh
        to create taxonomies or refinement constraints
      public domain ontologies
        Reed Elsevier
        BioWisdom
        Go Consortium
  corporate finance
    collecting business intelligence
      manual reviews of industry literature
      companies, executives, products, transactions, trends
    an example system: Industry Analyzer
      life science business sector
      business analysts need
        sift through news stories
          how well is a company doing
          what partners/competitors are there
          corporate action announcements
          products pushed in interviews
          which products are getting coverage
        specific goals
          potential merger pairings
          find smaller companies that will go public
          asset sales or spinouts
      functional architecture
        docs -> 
          preprocessing (term extraction, info extraction) ->
          core mining operations (pattern discovery)
        inputs
          background knowledge
          refinement filters
      background knowledge sources
        124 articles from BioWorld
          news relating M&A activities in life sciences sector
        specialized knowledge implementation
          taxonomies of
            drug names, genes, diseases
          from NCI Thesaurus (part of UMLS Metathesaurus)
          /Users/mertnuhoglu/Dropbox/public/img/ss-17.png
      term extraction
        /Users/mertnuhoglu/Dropbox/public/img/ss-18.png
        ex
          extracting Biogen Idec Inc.
            single term
            mergeer of Biogen and Idec company names
        construct basic facts
          ex  
            Biogen Idec is world's largest biotech company
            events
              Glaxo and Smith Kline merger
          uses
            DIAL language
              rule based information extraction
            /Users/mertnuhoglu/Dropbox/public/img/ss-19.png
      core mining operations and refinement constraints
        generating a query
          ex
            2015-09-29_13-57-04.png
            entity "Company" must be together with entity "DiseaseNCI"
      presentation layer
        events and facts selection
          2015-09-29_14-00-07
          ex
            Acqusition, Alliance, BusinessRelation
        connections between persons and companies
          in context of management changes
            2015-09-29_13-59-05.png
    Application Usage Scenarios
      examining industry press for merger activities
        trends in merger activity
          if there is a sudden uptick
            it may be overall industry consolidation taking place
          if mergers clustered around some product types
            it may be hot area for new products
          if leveling off of valuations
            thara may be maturation of market
        ex
          1. search for all merger events
            2015-09-29_14-06-58.png
          2. constrain with parameters
          3. get result
            2015-09-29_14-07-32.png
      exploring corporate earnings announcements
        finding a company that has earned 130M
          1. search for "CompanyEarningsAnnouncement"
          2. constrain attributes: Metric
            2015-09-29_14-11-06.png
          3. get result 
            2015-09-29_14-12-07.png
      exploring information about drugs in clinical trials
        ex
          finding connections between drugs and particular diseases
            -> quick sense of drugs that might be related to cancer treatment
            2015-09-29_19-13-02.png
          focus on cancer related drugs
            context of carcinoma drugs and a proximity constraint of "within same sentence"
            2015-09-29_19-19-00.png
          what are the cross-relationships between these drugs?
            2015-09-29_19-19-40.png
          what are related concepts to drug: cisplatin?
            2015-09-29_19-20-36.png
    Citations and Notes
      references
        Spenke Beilken
        IntertekGroup
        Kloptchenko et al.
        ClearForest Text Analytics Suite
        NCI Metathesaurus
  horizontal text mining application: patent analysis solution
    patent research encompasses
      investigation of registration
      ownership rights
      usage
    who does it
      IP dept of law firms and consultancies
    patent strategy
      what is patented
      people creating patents
      relationships 
    basic architecture
      data and background knowledge
      preprocessing operations
        categorization functions
          to create a taxonomy automatically
            to organize
              patent registrations
              claim details
              assignees, inventors, examiners
              corporate information
      core mining
        distribution of patents by assignee
        finding relevant patents
      exploring trends
        how patterns of pattent application evolve
          critical to decide whether to develop its own technology or license
          indicates current strength of interest in innovation
  Life sciences research: mining biological pathway information
    GeneWays
    preprocessing
      extract information using nlp
      literature culled from web sources
      1. operation: Term Identifier
        extract significant concepts
          ex: names of proteins, genes, processes, molecules, diseases
      2. Synonym/Homonym Resolver
        resolve meaning of an entity by assigning a single canonical name to each concept's aliases
      3. Term Classifier
        disambiguation operations
      output
        semantic trees
          nested relationships with normalized forms of verbs (eg. bind, binding, binder)
    core mining
      query interactions
        2015-09-30_10-31-37.png
    usage
      ex
        exploring interactions involving the protein collagen
          enter a query for all statements
      use refinement filters
        ex
          interactions that appeared in at least 15 different sentences
            2015-09-30_10-33-25.png
  
# Text Mining_ Predictive Methods for Analyzing Unstructured Information id=art_0007

  # Text Mining_ Predictive Methods for Analyzing Unstructured Information <url:#r=art_0007>

## ch07 Case Studies

  7.1 Market intelligence from web
    data from news agencies: reuters
    goal
      difference between text of a company and its competitors
      ex
        IBM: differences between prev and current quarter
        IBM: differences from MS
        how did news stroies influence prices for IBM vs. Sun
    issues
      what articles are to be obtained
      how to distinguish them among competitors
      how to find patterns in articles
    solution overview
      query specification -> rule based pattern recognition
      documents from crawler -> text analysis
      -> presentation
    methods and procedures
    system deployment
      1. collect stories for ibm, ms, sun
        sample every 15 mins
        clean and convert to xml
        collect share prices att that time
      2. sp
      3. decision rules
        if [chief executive] => class 1
        if [(sharply) or (turn)] => class 1
  7.2 Lightweight Documen tMatching for Digital Libraries
    process
      create local dictionary for each document
      create global dict
      create unique identifiers
      compute table of word weights
  7.3 Model cases for help desk applications
    cluster customer interactions to model cases
  7.4 Assigning topics to news articles
  Email filtreing
  Search engines
    ranking
  extracting named entities from documents
  custom newspaper
    classify news
    cluster news similar to each other
    importance classifier
  historical remarks
  
# Text Mining Techniques for Healthcare Provider Quality Determination id=art_0010

  # Text Mining Techniques for Healthcare Provider Quality Determination <url:#r=art_0010>

## ch08: Text Mining and Patient Severity Clusters

  using patient severity to predict outcomes for intervention
    input
      diagnosis, frequency, procedure, frequency
    find clusters in the data
      to find treatment choices where infection has higher risk

# Natural Language Processing and Text Mining

## ch02: extracting product features from reviews

  intro
    opinions about posts, sites, people, products
    opinion mining
    problems
      taste differs by reviewers
      wade through lots of reviews
    subtasks in review mining
      1. identify product features
        sometimes explicit: "size is too big"
        sometimes implicit: "scanner is slow" refers to "scanner speed"
      2. identify opinions regarding product features
        "the size is too big" -> "too big" corresponds to "size" feature
      3. determine polarity of opinions
        positive, negative
      4. rank opinions based on strength
        "horrible" stronger than "bad"
  terminology
    product class (eg. scanner) is a set of products
    types of product features:
      properties, parts, features of product parts, related concepts, parts and properties of related concepts
      ex
        Explicit Features, Examples, %
        properties, ScannerSize
        Parts, ScannerCover
        Features of Parts, BatteryLife
        Related Concepts, ScannerImage
        Related Concepts' Features, ScannerImageSize
  overview
    inputs
      C: product class
      R: reviews
    R' = parseReviews(R)
    E = findExplicitFeatures(R', C)
    O = findOpinions(R', E)
    CO = clusterOpinions(O)
    I = findImplicitFeatures(CO, E)
    RO = rankOpinions(CO)
    outputTuples(RO, I ∪ E)

# R Graphics Cookbook  id=art_0001

    R Graphics Cookbook  <url:#r=art_0001>
    <url:file:///Users/mertnuhoglu/Documents/yayin/kitap/data/R/graphics_r/R.Graphics.Cookbook.1449316956.pdf>

## Preface

    install.packages(c("ggplot2", "gcookbook"))
    library("ggplot2")
    library("gcookbook")

## ch02 Quickly Exploring Data

### 2.1 Scatter Plot
    opt1
        plot(mtcars$wt, mtcars$mpg)
    opt2
        qplot(mtcars$wt, mtcars$mpg)
    opt3
        qplot(wt, mpg, data = mtcars)
    opt4
        ggplot(mtcars, aes(x=wt, y=mpg)) + geom_point()
### 2.2 Line Graph
    opt1
        plot(pressure$temperature, pressure$pressure, type="l")
        additional graphs
            points(pressure$temperature, pressure$pressure)
            lines(pressure$temperature, pressure$pressure/2, col="r")
    opt2
        qplot(pressure$temperature, pressure$pressure, geom="line")
    opt3
        ggplot(pressure, aes(x=temperature, y=pressure)) + geom_line()
        ggplot(pressure, aes(x=temperature, y=pressure)) + geom_line() + geom_point()
### 2.3 Bar Graph
    ex1
        opt1
            barplot(BOD$demand, names.arg=BOD$Time)
        opt2
            qplot(BOD$Time, BOD$demand, geom="bar", stat="identity")
        opt2 - make Time discrete
            qplot(factor(BOD$Time), BOD$demand, geom="bar", stat="identity")
        opt3
            ggplot(BOD, aes(x=Time, y=demand)) + geom_bar(stat="identity")
    ex2
        opt1
            barplot(table(mtcars$cyl))
        opt2
            qplot(mtcars$cyl)
        opt2 - treat cyl as discrete
            qplot(factor(mtcars$cyl))
### 2.4 Histogram
    opt1
        hist(mtcars$mpg)
    opt2
        qplot(mtcars$mpg)
    opt3
        qplot(mpg, data=mtcars, binwidth=4)
    opt4
        ggplot(mtcars, aes(x=mpg)) + geom_histogram(binwidth=4)
### 2.5 Box plot
    ex1
        opt1
            boxplot(len ~ supp, data = ToothGrowth)
        opt2
            qplot(ToothGrowth$supp, ToothGrowth$len, geom="boxplot")
        opt3
            ggplot(ToothGrowth, aes(x=supp, y=len)) + geom_boxplot()
    ex2: multiple variables
        opt2
            qplot(interaction(ToothGrowth$supp, ToothGrowth$len), ToothGrowth$len, geom="boxplot")
### 2.6 Function Curve
    opt1
        curve(x^3 - 5*x, from = -4, to = 4)
    opt1 - adding new line
        myfun = function(x) 1/(1+exp(-x+10))
        curve(1 - myfun(x), add=T, col = "red")
    opt2
        qplot(c(0,20), fun=myfun, stat="function", geom="line")
    opt3
        ggplot(data.frame(x=c(0,20)), aes(x=x)) + stat_function(fun=myfun, geom="line")
## Bar Graphs

### 3.5 Coloring negative and positive bars
    library(gcookbook)
    csub = subset(climate, Source=="Berkeley" & Year >= 1900)
    csub2 = climate %>%
        filter(Source == "Berkeley") %>%
        filter(Year >= 1900) %>%
        mutate(pos = Anomaly10y >= 0)
    ggplot(csub2, aes(x=Year, y=Anomaly10y, fill=pos)) +
        geom_bar(stat="identity", position="identity")

# Spark

## Refcard Apache Spark

  https://dzone.com/refcardz/apache-spark
  why apache spark
    yahoo open sourced hadoop in 2009
      enabled everybody to enter big data processing
    hadoop provides
      1. hdfs: fault tolerant way to store data
      2. map-reduce
    hadoop data flow:
      [hdfs] read -> [map-reduce] write -> [hdfs] read -> [map-reduce] write -> [hdfs] -> read [query_n] -> result
      intermediate results: stored on disk
        thus jobs are IO bound
      this is a concern for:
        streaming data processing
        interactive querying of large data
      special tools for these use cases
        batch queries: oozie, 
        interactive queries: hive, hbase
        real time streaming: samza
      challenges
        learning lots of tools
        version incompatibility between tools
        data sharing across jobs
      spark solves these needs
        fast in-memory cluster computing
        unifies
          batch, streaming, interactive
  about apache spark
    stack
      spark core
      spark sql: interactive queries
      spark streaming
      mllib: machine learning
      graphx: graph processing
    benefits
      1. speed
        100x faster than hadoop map-reduce
          when all data in memory
      2. apis in java, py, sql
      3. compatible with hadoop v1 (simr) and v2 (yarn)
      4. shell
      5. high level constructs available
      implemented in scala
  how to install
    configuration opts:
      standalone:
      hadoop v1 SIMR
      hadoop 2 yarn/mesos
  resilient distributed dataset rdd
    immutable distributed data
      partitioned across machines
    two types of ops
      transformation: such as filter, map
        returns another rdd
      action: count, first, take, collect
        returns a value or writes to storage
  common transformations
    filter(fun)
      rdd = sc.parallelize(List("Abc","cbc"))
      rdd.filter(_.contains("c"))
        .collect()
        ::List[String] -> rdd -> Array[String]
    map(fun)
      rdd.map(_*2)
        .collect()
    flatMap(fun)
      fm = rdd.flatMap(str => str.split(" "))
      fm.collect()
    reduceByKey(fun)
      wrdCnt = fm.map(word => (word, 1))
        .reduceByKey(_+_)
      wrdCnt.collect()
    groupByKey([numTasks])
      wrdCnt.map{ case (word, count) => (count, word)}
        .groupByKey()
        .collect()
    distinct([numTasks])
      fm.distinct()
        .collect()
  common set operations
    union()
      rdd1.union(rdd2)
        .collect()
    intersection()
    cartesian()
    subtract()
    join()
      ::(K,V)->(K,W)->(K,(V,W))
    cogroup
      ::(K,V)->(K,Iterable<V>)
  common actions
    count()
    collect()
      ::rdd->array
    reduce(fun)
    take(n)
    foreach(fun)
    first()
    saveAsTextFile(path)
  rdd persistence
  shared variables
    accumulators
      scala> val nErrors=sc.accumulator(0.0)
      scala> val logs = sc.textFile(“/Users/akuntamukkala/temp/output.log”)
      scala> logs.filter(_.contains(“error”)).foreach(x=>nErrors+=1)
    broadcast variables
      scala> val map = sc.parallelize(Seq((“ground”,1),(“med”,2), (“priority”,5),(“express”,10))).collect().toMap
      map: scala.collection.immutable.Map[String,Int] = Map(ground -> 1, media -> 2, priority -> 5, express -> 10)
      scala> val bcMailRates = sc.broadcast(map)
      scala> pts.map(shipType=>(shipType,1)).reduceByKey(_+_). map{case (shipType,nPackages)=>(shipType,nPackages*bcMailRates. value(shipType))}.collect()</p>
  spark sql
    to run interactive queries
      using SchemaRDD
    SchemaRDD created from
      RDD, parquet files, json
    SchemaRDD similar to
      table in RDBMS
    two types of contexts:
      SQLContext, HiveContext
        extend SparkContext
      prodvide access to SQL parser or HiveQL parser
    ex:
      data
        John Smith|38|M|201 East Heading Way #2203,Irving, TX,75063 
      scala case class for each row:
        case class Customer(name:String, age:Int, gender:String, address:String)
      query
        val sparkConf = new SparkConf().setAppName(“Customers”)
        val sc = new SparkContext(sparkConf)
        val sqlContext = new SQLContext(sc)
        val r = sc.textFile(“/Users/akuntamukkala/temp/customers.txt”) 
        val records = r.map(_.split(‘|’))
        val c = records.map(r=>Customer(r(0),r(1).trim.toInt,r(2),r(3))) 
        c.registerAsTable(“customers”)
  spark streaming
    
    

      
