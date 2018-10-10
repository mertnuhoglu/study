import { DOMSource, VNode } from '@cycle/dom';
import { HTTPSource } from '@cycle/http';
import { StorageSource } from '@cycle/storage';
import { TimeSource } from '@cycle/time';
// import { StateSource } from 'cycle-onionify';
import { StateSource } from './cycle-onionify/StateSource';

import xs from 'xstream';
import { State as AppState } from './components/app';
import { State as PlanPanelState } from './components/plan_panel';
import Stream from 'xstream';

export type State = AppState | PlanPanelState
export type Omit<T, K extends keyof T> = Pick<T, Exclude<keyof T, K>> 
// @description: Reducer type safe olsun
// export type Component = (s: Sources) => Sinks; // @essence
export type Reducer<T extends State> = (prev?: T) => T  

export type Sources = {
  DOM: DOMSource
  onion: StateSource<State>
}
export type SoHTTP = { HTTP: HTTPSource }
export type SoTime = { Time: TimeSource }
export type SoStorage = {storage: StorageSource }
export type Sinks = {
  DOM: Stream<VNode>
  onion: Stream<Reducer<State>>
}
export type SiHTTP = { HTTP: Stream<RequestInfo> }
export type SiHot = { Hot: Stream<{}> }

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

export type RequestInfo = { 
  url: string,
  method: 'GET' | 'POST',
  headers?: {
    Authorization?: string,
  }
  category?: 'plan' | 'purchase_order',
}

export type ActionPayload = {
  type: string, 
  payload: {},
}

export type DictStream = {
  [key: string]: Stream<any>;
}

export type Intent = {
  actions?: DictStream, 
  requests$: Stream<RequestInfo>,
}

export function addListenerStream(stream$, key) {
    stream$.addListener({
        next: data => {
            key && console.log(key)
            console.log(data)
        }
    })
}
