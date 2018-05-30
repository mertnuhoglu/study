import xs from 'xstream';
import {a, article, br, button, div, form, h3, input, label, li, span, sup, table, tbody, td, th, thead, tr, ul} from '@cycle/dom';
import { style } from 'typestyle';
import 'handsontable'

const styles = style({
});

export default function DetailPanel(sources) {
  const state$ = sources.onion.state$
    .debug(x => {
      console.log("state$ in DetailPanel")
      console.log(x)
    })
  var data1 = [
      ["", "Ford", "Volvo", "Toyota", "Honda"],
      ["2016", 10, 11, 12, 13],
      ["2017", 20, 11, 14, 13],
      ["2018", 30, 15, 12, 13]
  ];
  const panel_vdom$ = state$.map( ({body}) =>
    article("#plan_detail_panel.card", [
      ul(".nav.nav-tabs", {
        "attrs": {
          "role": "tablist",
        }
      }, [
        li(".nav-item", [
          a("#stk_siparisler-tab.nav-link.active", {
            "attrs": {
              "data-toggle": "tab",
              "href": "#stk_siparisler",
              "role": "tab",
              "aria-controls": "stk_siparisler",
              "aria-selected": "true",
            },
          }, ["Stk. Siparişler"])
        ]),
        li(".nav-item", [
          a("#urt_siparisler-tab.nav-link", {
            "attrs": {
              "data-toggle": "tab",
              "href": "#urt_siparisler",
              "role": "tab",
              "aria-controls": "urt_siparisler",
              "aria-selected": "false",
            },
          }, ["Ürt. Siparişler"])
        ]),
        li(".nav-item", [
          a("#ozet-tab.nav-link", {
            "attrs": {
              "data-toggle": "tab",
              "href": "#ozet",
              "role": "tab",
              "aria-controls": "ozet",
              "aria-selected": "false",
            },
          }, ["Özet"])
        ]),
        li(".nav-item", [
          a("#adresler-tab.nav-link", {
            "attrs": {
              "data-toggle": "tab",
              "href": "#adresler",
              "role": "tab",
              "aria-controls": "adresler",
              "aria-selected": "false",
            },
          }, ["Adresler"])
        ]),
        li(".nav-item", [
          a("#rotalar-tab.nav-link", {
            "attrs": {
              "data-toggle": "tab",
              "href": "#rotalar",
              "role": "tab",
              "aria-controls": "rotalar",
              "aria-selected": "false",
            },
          }, ["Rotalar"])
        ])
      ]),
      div("#myTabContent.tab-content", [
        div("#stk_siparisler.siparisler.tab-pane.fade.show.active", {
          "attrs": {
            "role": "tabpanel",
            "aria-labelledby": "stk_siparisler-tab",
          },
        }, [
          div(".dropdown.stk_siparisler__col1", [
            button("#dropdownMenuButton.btn.btn-xs.dropdown-toggle", {
              "attrs": {
                "type": "button",
                "data-toggle": "dropdown",
                "aria-haspopup": "true",
                "aria-expanded": "false",
              },
            }, ["Sayı Formatı: Türkçe"]),
            div(".dropdown-menu", {
              "attrs": {
                "aria-labelledby": "dropdownMenuButton",
              }
            }, [
              a(".dropdown-item", {
                "attrs": {
                  "href": "#",
                }
              }, ["İngilizce"]),
              a(".dropdown-item", {
                "attrs": {
                  "href": "#",
                }
              }, ["Türkçe"])
            ])
          ]),
          div(".alert.alert-info.stk_siparisler__col2", [
            "Sayı formatının bilgisayarınızdaki Excel ile aynı olduğundan emin olun. ",
            br(),
            "Gönderilecek siparişleri G ile işaretlemeyi unutmayın. ",
            br(),
            "Stok miktarının sadece bir kısmı gönderilecekse gönderilecek m² girin."
          ])
        ]),
        div("#urt_siparisler.siparisler.tab-pane.fade", {
          "attrs": {
            "role": "tabpanel",
            "aria-labelledby": "urt_siparisler-tab",
          },
        }, [
          div(".dropdown.stk_siparisler__col1", [
            button("#dropdownMenuButton.btn.btn-xs.dropdown-toggle", {
              "attrs": {
                "type": "button",
                "data-toggle": "dropdown",
                "aria-haspopup": "true",
                "aria-expanded": "false",
              },
            }, ["Sayı Formatı: Türkçe"]),
            div(".dropdown-menu", {
              "attrs": {
                "aria-labelledby": "dropdownMenuButton",
              }
            }, [
              a(".dropdown-item", {
                "attrs": {
                  "href": "#",
                }
              }, ["İngilizce"]),
              a(".dropdown-item", {
                "attrs": {
                  "href": "#",
                }
              }, ["Türkçe"])
            ])
          ]),
          div(".alert.alert-info.stk_siparisler__col2", [
            "Sayı formatının bilgisayarınızdaki Excel ile aynı olduğundan emin olun. ",
            br(),
            "Gönderilecek siparişleri G ile işaretlemeyi unutmayın. ",
            br(),
            "Stok miktarının sadece bir kısmı gönderilecekse gönderilecek m² girin."
          ]),
          div("#ordersTableDiv", [
          ])
        ]),
        div("#ozet.tab-pane.fade", {
          "attrs": {
            "role": "tabpanel",
            "aria-labelledby": "ozet-tab",
          },
        }, [
          table(".table.table-condensed.table-striped.table-hover", [
            thead([
              tr([
                th(["Tarih"]),
                th(["Kullanıcı"]),
                th(["Depo"]),
                th(["Maksimum Drop Sayısı"]),
                th(["Efektif Hacim Tolerans"]),
                th([
                  "Kamyon Efektif",
                  br(),
                  "Hacim"
                ]),
                th([
                  "Treyler Efektif",
                  br()
                ]),
                th(["Durum"])
              ])
            ]),
            tbody([
              tr([
                td(["2018-02-01 21:05:46"]),
                td(["manager"]),
                td(["DENIZLI"]),
                td(["4"]),
                td(["10"]),
                td(["35"]),
                td(["55"]),
                td(["TAMAMLANDI"])
              ])
            ])
          ]),
          table(".table.table-condensed.table-striped.table-hover", {
            "attrs": {
              "ng-show": "myDataServ.plan_routes.length > 0",
            }
          }, [
            thead([
              tr([
                th(["Plandaki Sipariş Sayısı"]),
                th(["Plan Dışı Sipariş Sayısı"]),
                th(["Plandaki Müşteri Sayısı"]),
                th(["Plandaki Konum Sayısı"]),
                th(["Plandaki Araç Sayısı"]),
                th(["Planın Toplam Maliyeti"])
              ])
            ]),
            tbody([
              tr([
                td(["1"]),
                td(["273"]),
                td(["65"]),
                td(["1"]),
                td(["1"]),
                td(["1,434.00 TL"])
              ])
            ])
          ])
        ]),
        div("#adresler.tab-pane.fade", {
          "attrs": {
            "role": "tabpanel",
            "aria-labelledby": "adresler-tab",
          },
        }, [
          div("#custDestsTableDiv", [])
        ]),
        div("#rotalar.tab-pane.fade", {
          "attrs": {
            "role": "tabpanel",
            "aria-labelledby": "rotalar-tab",
          },
        }, [
          ul(".mfgpro_indir.nav.nav-pills", [
            li(".btn.btn-xs.pull-right", [
              a({
                "attrs": {
                  "href": "#",
                  "ng-click": "exportSelectedRoutes(myDataServ.plan_job.submitId)",
                  "data-vivaldi-spatnav-clickable": "1"
                }
              }, [
                span(".glyphicon.glyphicon-save"),
                "Seçilenleri MFG Pro İçin İndir"
              ])
            ]),
            li(".btn.btn-xs.dropdown.pull-right", [
              a(".dropdown-toggle", {
                "attrs": {
                  "data-toggle": "dropdown",
                  "href": "#",
                }
              }, [
                "Kalanları İndir",
                span(".caret")
              ]),
              ul(".dropdown-menu", [
                li({
                  "attrs": {
                    "ng-repeat": "lo in loArr"
                  }
                }, [
                  a({
                    "attrs": {
                      "href": "#",
                      "ng-click": "exportLeftoverOrders(myDataServ.plan_job.submitId, lo.isWip)",
                      "data-vivaldi-spatnav-clickable": "1"
                    }
                  }, ["Stok"])
                ]),
                li({
                  "attrs": {
                    "ng-repeat": "lo in loArr"
                  }
                }, [
                  a({
                    "attrs": {
                      "href": "#",
                      "ng-click": "exportLeftoverOrders(myDataServ.plan_job.submitId, lo.isWip)",
                      "data-vivaldi-spatnav-clickable": "1"
                    }
                  }, ["Üretim"])
                ])
              ])
            ])
          ]),
          div(".arac", [
            div(".arac__heading", [
              h3(".arac__title", ["Araç 1"]),
              a("#routeLink_1.arac__harita.btn.btn-xs.btn-primary", {
                "attrs": {
                  "href": "https://www.google.com/maps/dir/37.818221,29.133363/40.778309,29.973301/41.190166,29.204752/41.226262,29.027215/41.1558,27.8137",
                  "target": "_blank",
                },
              }, ["Harita"])
            ]),
            table(".table", [
              tbody([
                tr([
                  td(["Kilometre"]),
                  td(["761.0"]),
                  td(["Liste Fiyatı"]),
                  td(["1,434.00 TL"]),
                  td(["(DENIZLI->TEKIRDAG-CORLU:760km)"])
                ]),
                tr([
                  td(["Konum Sayısı"]),
                  td(["1"]),
                  td(["Topl. Km Farkı"]),
                  td(["0.00 TL"])
                ]),
                tr([
                  td(["Drop Sayısı"]),
                  td(["1"]),
                  td(["Topl. Drop Farkı"]),
                  td(["0.00 TL"])
                ]),
                tr([
                  td(["Müşteri Sayısı"]),
                  td(["1"])
                ]),
                tr([
                  td(["Sipariş Satırı Sayısı"]),
                  td(["1"]),
                  td(["Topl. Maliyet"]),
                  td(["1,434.00 TL"])
                ]),
                tr([
                  td(["Yüklü Ağırlık"]),
                  td(["2,704.0 kg"])
                ]),
                tr([
                  td(["Yüklü Hacim"]),
                  td([
                    "19.740 m",
                    sup(["3"])
                  ])
                ]),
                tr([
                  td(["Efektif Araç Kapasite"]),
                  td([
                    "35 m",
                    sup(["3"])
                  ])
                ]),
                tr([
                  td(["Doluluk Oranı"]),
                  td(["56.40%"])
                ]),
                tr([
                  td(["Araç Tipi"]),
                  td(["KAMYON"])
                ])
              ])
            ]),
            table(".table", [
              thead([
                tr({
                  "style": {
                    "name": "style",
                    "value": "border: 1px solid gray;"
                  }
                }, [
                  th(["Konum"]),
                  th(["Müşteri"]),
                  th(["Sipariş"]),
                  th(["Stok Kodu"]),
                  th([
                    "Sipariş",
                    br(),
                    "Tipi"
                  ]),
                  th([
                    "Drop",
                    br(),
                    "Adet"
                  ]),
                  th([
                    "Drop",
                    br(),
                    "m",
                    sup(["2"])
                  ]),
                  th([
                    "Drop",
                    br(),
                    "kg"
                  ]),
                  th([
                    "Drop",
                    br(),
                    "Palet"
                  ]),
                  th([
                    "Drop",
                    br(),
                    "m",
                    sup(["3"])
                  ]),
                  th([
                    "Araç",
                    br(),
                    "Tercih"
                  ])
                ])
              ])
              /* ngRepeat: drop in route.drops */,
              tbody([
                tr({
                  "attrs": {
                    "data-ng-repeat": "drop in route.drops"
                  },
                  "style": {
                    "name": "style",
                    "value": "border-left: 1px solid gray; border-right: 1px solid gray;"
                  }
                }, [
                  td(["TEKIRDAG-CORLU"]),
                  td(["DENIZLI CAM A."]),
                  td(["54467/1"]),
                  td(["51008-1976"]),
                  td(["Stk."]),
                  td(["7360"]),
                  td(["6,624.0"]),
                  td(["2,704.0"]),
                  td(["19"]),
                  td(["19.74"]),
                  td(["B.KAMYON,KAMYON,TIR"])
                ])
                /* end ngRepeat: drop in route.drops */
              ])
            ]),
            form(".form-inline.alert.alert-info.arac__mfgpro_aktarimi", [
              label(["MFG Pro Aktarımı:"]),
              label(".checkbox-inline.checkbox-primary", [
                input({
                  "attrs": {
                    "type": "checkbox",
                    "disabled": "disabled"
                  }
                }),
                "Uygun"
              ]),
              label(".checkbox-inline.checkbox-primary", [
                input({
                  "attrs": {
                    "type": "checkbox",
                    "disabled": "disabled"
                  }
                }),
                "Kapalı kasa"
              ]),
              label(".checkbox-inline.checkbox-primary", [
                input({
                  "attrs": {
                    "type": "checkbox",
                    "disabled": "disabled"
                  }
                }),
                "Yakın bölge"
              ])
            ])
          ])
        ])
      ])
    ])

  );
  const sinks = {
    DOM: panel_vdom$,
    Hot: state$
    .map( ({body}) => body )
    .map( xs => xs.map( y => Object.keys(y).map( key => y[key] ) )),
  }
  return sinks
}


