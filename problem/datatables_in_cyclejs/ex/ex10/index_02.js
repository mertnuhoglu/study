
function main(sources) {
  const click$ = sources.DOM;
  const sinks = {
    DOM: click$
    .startWith(null)
    .flatMapLatest(() =>
      Rx.Observable.timer(0, 1000)
      .map(i => `Seconds ${i}`)
    ),
    Log: Rx.Observable.timer(0, 2000).map(i => 2*i),
    Hot: click$
    .startWith(null)
  };
  return sinks;
}

function DOMDriver(text$) {
  text$.subscribe(text => {
    const container = document.querySelector('#app');
    container.textContent = text;
  });
  const DOMSource = Rx.Observable.fromEvent(document, 'click');
  return DOMSource;
}

function consoleLogDriver(msg$) {
  msg$.subscribe(msg => console.log(msg));
}

function HotDriver(data$) {
  data$.subscribe( temp => {
    var data1 = [
      ['', 'Tesla', 'Nissan', 'Toyota', 'Honda', 'Mazda', 'Ford'],
      ['2017', 10, 11, 12, 13, 15, 16],
    ];
    var container = document.getElementById('example');
    var hot = new Handsontable(container, {
      data: data1,
    });
  })
}

const drivers = {
  DOM: DOMDriver,
  Log: consoleLogDriver,
  Hot: HotDriver,
}

Cycle.run(main, drivers);
