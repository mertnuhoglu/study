console.clear();

var source = Rx.Observable.interval(400).take(4)
  .map(i => ['1', '2', 'foo', 'bar'][i]);

var result = source;

result.subscribe(x => console.log(x));