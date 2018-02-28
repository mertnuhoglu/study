function main(DOMSource) {
  const click$ = DOMSource;
  return {
    DOM: click$
      .startWith(null)
      .switchMap(() =>
        Rx.Observable.timer(0, 1000)
          .map(i => `Seconds ${i}`)
      ),
    Log: Rx.Observable.timer(0, 2000).map(i => 2*i),
  };
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

function run(main, drivers) {
  const proxyDOMSource = new Rx.Subject();
  const sinks = main(proxyDOMSource);
  const DOMSource = drivers.DOM(sinks.DOM);
  DOMSource.subscribe(click => proxyDOMSource.next(click));
}

const drivers = {
  DOM: DOMDriver,
  Log: consoleLogDriver,
}

run(main, drivers);
