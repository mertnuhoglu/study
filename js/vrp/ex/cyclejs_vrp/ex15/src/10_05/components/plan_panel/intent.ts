import xs from 'xstream';
import { DOMSource, VNode } from '@cycle/dom';
import { Intent, ActionPayload, RequestInfo } from '../../interfaces';
import { addListenerStream } from '../../interfaces';

export function intent(dom$: DOMSource): Intent {
    const planDetails$: xs<RequestInfo> = dom$.select('.get_plan_details').events('click')
			.map((e) => e.target.dataset.plan_id)
			.debug(x => { console.log("PlanPanel getPlanDetails$"); console.log(x); })
			.map(plan_id => ({
				url: `http://localhost:8080/rest/plan?select=order_line(purchase_order(purchase_order_id,company_id,order_extid,company_extid))&plan_id=eq.${plan_id}`,
				method: 'GET',
				headers: {
						"Authorization": "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJyb2xlIjoid2VidXNlciJ9.uSsS2cukBlM6QXe4Y0H90fsdkJSGcle9b7p_kMV1Ymk"
				},
				category: 'purchase_order',
			}))
    return { requests$: planDetails$ }
}
