  <url:file:///~/Dropbox (Personal)/projects/study/study/stackoverflow_questions.md>

## sof q 20171027 

I have two tables: A and B with a one-to-many relationship between them.

  create table A (
    a_id serial primary key
  )
  create table B ()

## sof q1 20170527

  https://stackoverflow.com/questions/44218060/capacity-assignment-without-for-loops-and-conditional-branches

I have the following data frame:

    items = data_frame( id = 1:4, due = c(1,1,1,2), size = c(5,4,3,2), day = NA )
    capacity = 8
    items
    # # A tibble: 4 × 4
    #      id   due  size   day
    #   <int> <dbl> <dbl> <lgl>
    # 1     1     1     5    NA
    # 2     2     1     4    NA
    # 3     3     1     3    NA
    # 4     4     2     2    NA

My goal is to assign a `day` to each item such that the daily capacity `8` is not exceeded. The items are sorted and assigned by their `due` date. 

So the target data frame that I want to obtain for this example is this:

    goal = data_frame( id = 1:4, due = c(1,1,1,2), size = c(5,4,3,2), day = c(1,2,1,2) )
    goal
    # # A tibble: 4 × 4
    #      id   due  size   day
    #   <int> <dbl> <dbl> <dbl>
    # 1     1     1     5     1
    # 2     2     1     4     2
    # 3     3     1     3     1
    # 4     4     2     2     2

I can solve this problem using two for loops and one if condition like that:

    result = data_frame( id = integer(), due = integer(), size = integer(), day = integer() )
    df = items
    will_drop = integer()
    for (day in 1:2) {
      cum_size = 0
      for (i in 1:nrow(df)) {
        item = df[i, ]
        if (cum_size + item$size <= capacity) {
          cum_size = cum_size + item$size
          item$day = day
          result = rbind(result, item)
          will_drop = c(will_drop, i)
        }
      }
      df = df[-will_drop, ]
    }

But I don't like this solution because it is error prone and hard to modify. 

I would like to ask if you might come up with a solution without using any for loop and if branching using dplyr or data.table or higher order functions such as lapply or Reduce?

### sof q 20170530 

  https://stackoverflow.com/questions/44265783/remove-rows-from-data-frame-using-row-indices-where-row-indices-might-be-zero-le

I want to drop some rows from some dataframe using numeric indices of the rows. But sometimes the indices vector that I am going to drop becomes zero length vector. In this case, I expect that nothing should be dropped from the original data frame. But instead of nothing everything is dropped. 

For example, here `drop` works as expected

    df = data_frame( a = 10:12 )
    drop = c(1,2)
    df[ -drop, ]
    # # A tibble: 1 × 1
    # a
    # <int>
    # 1    12

But when `drop` is zero length vector, then removing those rows doesn't work as I expect.

    drop = integer()
    df[ -drop, ]
    # A tibble: 0 × 1
    # ... with 1 variables: a <int>

How to remove rows from a data frame using row indices safely?

Solutions:

  df %>%
     filter(!row_number() %in% drop)

  df[!seq_len(nrow(df)) %in% drop, ]

  df[ setdiff(1:nrow(df), drop), ]

### sof 20170601 

  https://stackoverflow.com/questions/44304547/mutate-an-empty-data-frames-column-safely

I have an empty data frame. I try to assign some value to a column of this data frame. Since the data frame is empty, I expect to obtain an empty data frame in return as follows:

    df = data_frame(a = integer())
    df %>%
      mutate(a = 1)
    # A tibble: 0 × 1
    # ... with 1 variables: a <dbl>

The problem occurs when assigned value is a vector. Then I get the following error:

    df %>%
      mutate(a = 1:2)
    # Error: wrong result size (2), expected 0 or 1

When I try to assign a value using `$` operator, then even assigning a single value produces error:

    df$a = 1
    # Error in `$<-.data.frame`(`*tmp*`, "a", value = 1) :
    # replacement has 1 row, data has 0

What is the correct and safe way to try to mutate an empty dataframe's column? 

## sof 20170606 

I have a data frame of some items to produce with certain sizes. There is a capacity limit for total size of items to produce. I want to obtain list of items such that they just surpass the capacity limit.

For example, this is the data frame of items:

    df = data_frame( id = 1:4, size = 100 ) %>%
      mutate( cum_size = cumsum(size) )
    # # A tibble: 4 × 3
    # id  size cum_size
    # <int> <dbl>    <dbl>
    # 1     1   100      100
    # 2     2   100      200
    # 3     3   100      300
    # 4     4   100      400
    capacity = 250

I can obtain the list of items such that they don't exceed the capacity limit
as such:

    df2 = df %>%
      filter( cum_size < capacity )
    df2
    #      id  size cum_size
    # <int> <dbl>    <dbl>
    # 1     1   100      100
    # 2     2   100      200

But I want to include the row that can be partially produced as well. To find that row, I use the following code:

    partial_item = df %>%
      mutate( remains = cum_size - capacity ) %>%
      filter( remains < size & remains > 0 ) %>%
      select( -remains )
    target = bind_rows(df2, partial_item)
    target
    #      id  size cum_size
    # <int> <dbl>    <dbl>
    # 1     1   100      100
    # 2     2   100      200
    # 3     3   100      300


Solution:

    df3 = df %>%
      filter( cum_size < capacity + size)
