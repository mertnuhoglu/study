
## exercises for cyclejs

1. timer input. "seconds ${i}"

2. echo your name. input field.

3. increment, decrement buttons. 

4. rest: "get first user" button
  what are read/write for dom/http drivers
  solution
    ref
      <url:/~/codes/js/cycle-examples/http-random-user/src/main.js#tn=function main(sources) {>
    flow of actions
      1. user clicks a button (dom event)
      2. app calls a web service (http request)
      3. web service returns some json (http response)
      4. app returns some html (dom response)
    mapping of actions to sink-source
      | what          | r/w        | source/sink  | concept  | argument |
      |---------------|------------|--------------|----------|----------|
      | dom event     | dom read   | sources.DOM  | intent$  |          |
      | http request  | http write | sinks.HTTP   | getData$ | intent$  |
      | http response | http read  | sources.HTTP | data$    |          |
      | dom response  | dom write  | sinks.DOM    | vtree$   | data$    |
    return
      | r/w        | starts with  | returns to |
      |------------|--------------|------------|
      | dom read   | sources.DOM  |            |
      | http write |              | sinks.HTTP |
      | http read  | sources.HTTP |            |
      | dom write  |              | sinks.DOM  |

## logic of cyclejs

solving cycle using proxy
source-sink: input-output duality
define the followings:
  map effects
    dom read effect
    http write effect
    http read effect
    dom write effect
  actions
    button clicked
    request sent
    response received
    data displayed
  
