import xs from 'xstream'
import {run} from '@cycle/run';
import {makeHistoryDriver} from '@cycle/history';

function main(sources){
  return {
    // updates the browser URL every 500ms
    history: xs.periodic(500).map(i => `url-${i}`)
  };
}

const drivers = {
  history: makeHistoryDriver()
};

run(main, drivers);


