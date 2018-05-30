import {run} from '@cycle/run';
import {makeDOMDriver} from '@cycle/dom';
import onionify from 'cycle-onionify';
import MyList from './index'

const main = onionify(MyList);

run(main, {
  DOM: makeDOMDriver('#app'),
});
