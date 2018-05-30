import { run } from '@cycle/run';
import xs from 'xstream'
import fromEvent from 'xstream/extra/fromEvent'

var data1 = [
  ['', 'Tesla', 'Nissan', 'Toyota', 'Honda', 'Mazda', 'Ford'],
  ['2017', 10, 11, 12, 13, 15, 16],
];
var container = document.getElementById('example');
var hot = new Handsontable(container, {
  data: data1,
});

function main(sources) {
  const click$ = sources.DOM;
  const sinks = {
    DOM: click$
      .startWith(null)
      .map(() =>
        xs.periodic(1000)
        .map(i => `Seconds ${i}`)
      ).flatten(),
    Log: sources.Hot,
    Hot: xs.periodic(2000)
      .map(i => {
        console.log(data1)
        data1[0][i % 7] = i; 
        return data1
      })
  };
  return sinks;
}

function DOMDriver(text$) {
  text$.addListener({
    next: text => {
      const container = document.querySelector('#app');
      container.textContent = text;
    }}
  );
  const DOMSource = fromEvent(document, 'click');
  return DOMSource;
}

function consoleLogDriver(msg$) {
  msg$.addListener({next: msg => console.log(msg)});
}

function HotDriver(data$) {
  data$.addListener({
    next: data => {
      hot.loadData(data)
    }}
  )
  var producer = {
    start: function(observer) {
      hot.addHook('afterChange', function () {
        observer.next(hot.getSourceData())
      })
    },
    stop: function () {
      console.log("stopped")
    }
  }
  const HotSource = xs.create(producer)
  return HotSource
}

const drivers = {
  DOM: DOMDriver,
  Log: consoleLogDriver,
  Hot: HotDriver,
}

run(main, drivers);
