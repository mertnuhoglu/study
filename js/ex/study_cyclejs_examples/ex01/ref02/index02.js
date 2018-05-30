import {run} from '@cycle/run';
import {div, makeDOMDriver} from '@cycle/dom';
import storageDriver from '@cycle/storage';
import {captureClicks, makeHistoryDriver} from '@cycle/history'
//import onionify from 'cycle-onionify';
import onionify from './cycle-onionify';
import storageify from "cycle-storageify";
import xs from 'xstream';
import TaskList from './components/TaskList/index';

function getSpread() {
  var c = [1,2,3]
  var d = [...c]
  var e = {f: 3, g: 5}
  var h = {...e}
  return {
    d,
    a: "a"
  }
}
function main(sources) {
  const vdom$ = xs.of(
    div("planet earth")
  )
  var a = [1,2,3]
  var b = [...a]
  console.log(a)
  console.log(getSpread())

  return {
    DOM: vdom$,
  };
}

run(main, {
  DOM: makeDOMDriver('.todoapp')
});

