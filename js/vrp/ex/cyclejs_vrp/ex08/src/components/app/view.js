import xs from 'xstream';
import {button, h1, h4, a, div, table, thead, tbody, tr, td, th} from '@cycle/dom';

export default function view(state$) {
  return state$.map(({body}) =>
    table([
      thead(
        tr([
          th('Plan Id'),
          th('Kullanıcı'),
          th('Depot Id')
        ])
      ),
      tbody(
        body.map(e => 
          tr([
            td(e.plan_id),
            td(e.usr),
            td(e.depot_id)
          ])
        )
      )
    ])
  )
}

