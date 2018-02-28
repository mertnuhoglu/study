function main() {
  return Rx.Observable.timer(0, 1000)
    .map(i => `Seconds ${i}`);
}

function DOMEffect(text$) {
  text$.subscribe(text => {
    const container = document.querySelector('#app');
    container.textContent = text;
  });
}

DOMEffect(main());
