import xs from 'xstream';

export default function model(http$) {
  const response$$ = http$.select();
  const response$ = response$$.flatten();
  const responseReducer$ = response$.map(
    response => (prevState) => ({
      body: response.body
    })
  );
  return xs.merge(responseReducer$);
}


