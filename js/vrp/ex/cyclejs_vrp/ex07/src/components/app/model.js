import xs from 'xstream';

export default function model(http$) {
  const response$$ = http$.select();
  const response$ = response$$.flatten();
  const json$ = response$.map(response => response.body);
  return json$;
}


