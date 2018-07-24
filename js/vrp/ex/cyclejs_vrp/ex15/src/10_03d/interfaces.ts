import { DOMSource, VNode } from '@cycle/dom';
import { HTTPSource } from '@cycle/http';
import { StorageSource } from '@cycle/storage';
import { TimeSource } from '@cycle/time';
import { StateSource } from 'cycle-onionify';
import xs from 'xstream';
import { State } from './components/app';

export type Sources = {
  DOM: DOMSource;
  onion: StateSource<State>;
  HTTP: HTTPSource;
  Time: TimeSource;
  storage: StorageSource;
  props$: any;
};

export type Sinks = {
  DOM: xs<VNode>;
  onion: xs<(s: any) => any>;
  HTTP: xs<any>;
  Hot: xs<any>;
};

export type Component = (s: Sources) => Sinks;

export type Plan = {
  plan_id: number
  usr: string
  depot_id: number
}

export type PurchaseOrder = {
  purchase_order_id: number
  company_id: number
  order_extid: string
  company_exti: string
}