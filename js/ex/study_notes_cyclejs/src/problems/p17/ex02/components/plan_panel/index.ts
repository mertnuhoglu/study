import { a, article, button, div, form, h3, input, label, li, nav, option, select, span, table, tbody, td, th, thead, tr, ul } from '@cycle/dom';
import { style } from 'typestyle/lib';
import xs from 'xstream';
import { Plan, PurchaseOrder } from '../../interfaces';
import { State as AppState } from '../app';

export interface State {
  plans: Array<Plan>
  purchase_orders: Array<PurchaseOrder>
  firstPlan: Plan | {}
}

export type Reducer = (prev?: State) => State | undefined

export const lens = {
  get: (state: AppState): State => ({
    ...state,
    firstPlan: state.plans.filter(plan => plan.plan_id === 1),
  }),
  set: (state: AppState, childState: State) => ({
    ...state,
    firstPlan: childState.firstPlan,
  })
}
const styles = style({
});

export function addListenerStream(stream$, key) {
  stream$.addListener({
    next: data => {
      key && console.log(key)
      console.log(data)
    }})
}
function intent(dom$) {
  const getPlanDetails$ = dom$.select('.get_plan_details').events('click')
    .map((e) => ({type: "GET_PLAN_DETAILS", payload: e.target.dataset.plan_id}))
    .debug(x => { console.log("PlanPanel getPlanDetails$"); console.log(x); })
  addListenerStream(getPlanDetails$, "getPlanDetails$")
  const requests$ = xs.from(
    [
      {
        url: 'http://localhost:8080/rest/purchase_order?select=purchase_order_id,company_id,order_extid,company_extid',
        method: 'GET',
        headers: {
          "Authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJyb2xlIjoid2VidXNlciJ9.uSsS2cukBlM6QXe4Y0H90fsdkJSGcle9b7p_kMV1Ymk"
        },
        category: 'purchase_order',
      },
    ]
  )
    .debug(x => { console.log("PlanPanel intent requests$"); console.log(x); })
  return {actions: getPlanDetails$, requests$: requests$}
}

function view(state$) {
  const panel_vdom$ = state$.map( (state) =>
    article("#plan_panel.card", [
      ul("#planlama.nav.nav-tabs", {
        "attrs": {
          "role": "tablist",
        },
      }, [
        li(".nav-item", [
          a("#gecmis_planlar-tab.nav-link.active", {
            "attrs": {
              "data-toggle": "tab",
              "href": "#gecmis_planlar",
              "role": "tab",
              "aria-controls": "gecmis_planlar",
              "aria-selected": "true",
            },
          }, ["Geçmiş Planlar"])
        ]),
        li(".nav-item", [
          a("#yeni_plan-tab.nav-link", {
            "attrs": {
              "data-toggle": "tab",
              "href": "#yeni_plan",
              "role": "tab",
              "aria-controls": "yeni_plan",
              "aria-selected": "false",
            },
          }, ["Yeni Plan"])
        ]),
        li(".nav-item", [
          a("#bos_plan-tab.nav-link", {
            "attrs": {
              "data-toggle": "tab",
              "href": "#bos_plan",
              "role": "tab",
              "aria-controls": "bos_plan",
              "aria-selected": "false",
            },
          }, ["Boş Plan"])
        ])
      ]),
      div("#myTabContent.tab-content", [
        div("#gecmis_planlar.tab-pane.fade.show.active", {
          "attrs": {
            "role": "tabpanel",
            "aria-labelledby": "gecmis_planlar-tab",
          },
        }, [
          table(".table.table-striped.table-hover", [
            thead([
              tr([
                th('Plan Id'),
                th('Kullanıcı'),
                th('Depot Id')
              ])
            ]),
            tbody(
              state.plans.map(e => 
                tr(".plan_row", 
                  [
                    td(e.plan_id),
                    td(e.usr),
                    td(e.depot_id),
                    td(
                      button(".get_plan_details", 
                        {dataset: {plan_id: e.plan_id}},
                        "Verileri Getir"
                      )
                    ),
                  ])
              )
            )
          ]),
          nav({
            "attrs": {
              "aria-label": "Sayfalandırma"
            }
          }, [
            ul(".pagination", [
              li(".page-item", [
                a(".page-link", {
                  "attrs": {
                    "href": "#",
                    "aria-label": "Previous",
                  }
                }, [
                  span({
                    "attrs": {
                      "aria-hidden": "true"
                    }
                  }, ["«"]),
                  span(".sr-only", ["Previous"])
                ])
              ]),
              li(".page-item", [
                a(".page-link", {
                  "attrs": {
                    "href": "#",
                  }
                }, ["1"])
              ])
            ])
          ])
        ]),
        div("#yeni_plan.tab-pane.fade", {
          "attrs": {
            "role": "tabpanel",
            "aria-labelledby": "yeni_plan-tab",
          },
        }, [
          form(".yeni_plan", {
            "attrs": {
              "action": "",
            }
          }, [
            div(".yeni_plan__arac_parametreleri", [
              h3(".yeni_plan__heading", ["Araç Parametreleri"]),
              label(".yeni_plan__label_heading", ["Araç Tipi"]),
              label(".yeni_plan__input_heading", ["Efektif Hacim"]),

              label(".yeni_plan__label", {
                "attrs": {
                  "htmlFor": "kamyon"
                }
              }, ["Kamyon"]),
              input("#kamyon.yeni_plan__input.form-control", {
                "attrs": {
                  "type": "text",
                },
              }),
              label(".yeni_plan__unit", ["m³/45m³"]),

              label(".yeni_plan__label", {
                "attrs": {
                  "htmlFor": "b_kamyon"
                }
              }, ["B.Kamyon"]),
              input("#b_kamyon.yeni_plan__input.form-control", {
                "attrs": {
                  "type": "text",
                },
              }),
              label(".yeni_plan__unit", ["m³/68m³"]),

              label(".yeni_plan__label", {
                "attrs": {
                  "htmlFor": "treyler"
                }
              }, ["Treyler"]),
              input("#treyler.yeni_plan__input.form-control", {
                "attrs": {
                  "type": "text",
                },
              }),
              label(".yeni_plan__unit", ["m³/86m³"])
            ]),
            div(".yeni_plan__rota_parametreleri", [
              h3(".yeni_plan__heading", ["Rota Parametreleri"]),
              label(".yeni_plan__label", {
                "attrs": {
                  "htmlFor": "depo"
                }
              }, ["Depo"]),
              select("#depo.yeni_plan__input.form-control", {
                "attrs": {
                  "ng-options": "o.label for o in depotOptions",
                  "ng-model": "plan_depot",
                  "ng-disabled": "!canChangeDepot()",
                },
              }, [
                option({
                  "attrs": {
                    "value": "?",
                    "selected": ""
                  }
                }),
                option({
                  "attrs": {
                    "label": "Adana",
                    "value": "object:18"
                  }
                }, ["Adana"]),
                option({
                  "attrs": {
                    "label": "Çorlu",
                    "value": "object:19"
                  }
                }, ["Çorlu"]),
                option({
                  "attrs": {
                    "label": "Denizli",
                    "value": "object:20"
                  }
                }, ["Denizli"]),
                option({
                  "attrs": {
                    "label": "Eskisehir",
                    "value": "object:21"
                  }
                }, ["Eskisehir"])
              ]),

              label(".yeni_plan__label", {
                "attrs": {
                  "htmlFor": "maks_drop_sayisi"
                }
              }, ["Maks. Drop Sayısı"]),
              input("#maks_drop_sayisi.yeni_plan__input.form-control", {
                "attrs": {
                  "type": "text",
                },
              }),
              label(".yeni_plan__unit", ["adet"]),

              label(".yeni_plan__label", {
                "attrs": {
                  "htmlFor": "efektif_hacim_tolerans"
                }
              }, ["Efektif Hacim Tolerans"]),
              input("#efektif_hacim_tolerans.yeni_plan__input.form-control", {
                "attrs": {
                  "type": "text",
                },
              }),
              label(".yeni_plan__unit", ["%"])
            ]),
            button(".yeni_plan__submit.btn.btn-primary", ["Gönder"])
          ])
        ]),
        div("#bos_plan.tab-pane.fade", {
          "attrs": {
            "role": "tabpanel",
            "aria-labelledby": "bos_plan-tab",
          },
        }, ["Boş Plan"])
      ])
    ])
  )
    .debug(x => { console.log("PlanPanel panel_vdom$"); console.log(x); })
  return panel_vdom$
}
export function PlanPanel(sources) {
  const state$ = sources.onion.state$
    .debug(x => { console.log("PlanPanel state$"); console.log(x); })
  const {actions, requests$} = intent(sources.DOM)
	requests$
    .debug(x => { console.log("PlanPanel requests$"); console.log(x); })
  const sinks = {
    DOM: view(state$),
    HTTP: requests$,
  }
  return sinks
}

