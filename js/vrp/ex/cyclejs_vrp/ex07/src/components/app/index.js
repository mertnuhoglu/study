import xs from 'xstream'
import {makeDOMDriver} from '@cycle/dom';
import model from './model';
import view from './view';
import intent, { Actions } from './intent';

export default function main(sources) {
  const json$ = model(sources.HTTP);
  return {
    DOM: view(json$),
    HTTP: intent(sources.DOM),
  };
}

