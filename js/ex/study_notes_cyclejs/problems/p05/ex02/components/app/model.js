import xs from 'xstream';

export default function model(sources) {
  const response$$ = sources.HTTP.select();
  const response$ = response$$.flatten();
  const state$ = response$.map(
    response => ({
      body: response.body
    })
  );
  return xs.merge(state$);
}


