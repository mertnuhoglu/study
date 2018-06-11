import xs from 'xstream';
import { VNode, DOMSource } from '@cycle/dom';
import { HTTPSource, RequestOptions } from '@cycle/http';
import { TimeSource } from '@cycle/time';
import { StorageSource, StorageRequest } from '@cycle/storage';
import { StateSource } from 'cycle-onionify';
import { State } from './ex08';

export type Sources = {
  DOM: DOMSource;
  onion: StateSource<State>;
  HTTP: HTTPSource;
  Time: TimeSource;
  storage: StorageSource;
  props$: any;
};

export type Sinks = {
  onion: xs<(s: any) => any>;
  HTTP: xs<any>;
};

export type Component = (s: Sources) => Sinks;

