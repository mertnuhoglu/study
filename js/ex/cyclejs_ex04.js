function main() {
  return {
    DOM: Rx.Observable.timer(0, 1000)
      .map(i => `Seconds ${i}`),
    Log: Rx.Observable.timer(0, 2000)
      .map(i => 2*i),
  };
}

function DOMEffect(text$) {
  text$.subscribe(text => {
    const container = document.querySelector('#app');
    container.textContent = text;
  });
}

function consoleLogEffect(msg$) {
  msg$.subscribe(msg => console.log(msg));
}

const sink = main();
consoleLogEffect(sink.Log);
DOMEffect(sink.DOM);
