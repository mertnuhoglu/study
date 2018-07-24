import xs from 'xstream';
import { DOMSource, VNode } from '@cycle/dom';
import { Intent, Action, RequestInfo } from '../../interfaces';
import { addListenerStream } from '../../interfaces';

export function intent(dom$: DOMSource): Intent {
    const getPlanDetails$: xs<Action> = dom$.select('.get_plan_details').events('click')
        .map((e) => ({ type: "GET_PLAN_DETAILS", payload: e.target.dataset.plan_id }))
        .debug(x => { console.log("PlanPanel getPlanDetails$"); console.log(x); })
    addListenerStream(getPlanDetails$, "getPlanDetails$")
    const request: RequestInfo = {
        url: 'http://localhost:8080/rest/purchase_order?select=purchase_order_id,company_id,order_extid,company_extid',
        method: 'GET',
        headers: {
            "Authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJyb2xlIjoid2VidXNlciJ9.uSsS2cukBlM6QXe4Y0H90fsdkJSGcle9b7p_kMV1Ymk"
        },
        category: 'purchase_order',
    }
    const requests$: xs<RequestInfo> = xs.from([
            request,
        ])
        .debug(x => { console.log("PlanPanel intent requests$"); console.log(x); })
    return { actions: getPlanDetails$, requests$: requests$ }
}
