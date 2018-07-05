import xs from 'xstream';

export default function model(http$) {
  const response$$ = http$.select();
  const response$ = response$$.flatten()
    .debug( x => console.log("response: "))
    .debug( x => console.log(x))

  const responseReducer$ = response$.map(
    response => (prevState) => {
      console.log("responseReducer$:")
      console.log(response)
      global.response = response
      return {
        body: response.body
      }
    }
  );
  return xs.merge(responseReducer$);
}
