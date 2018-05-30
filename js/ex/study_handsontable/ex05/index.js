var data1 = [
  {a_id: 1, b_id: 101,},
  {a_id: 2, b_id: 102,},
],
  container1 = document.getElementById('example1'),
  settings1 = {
  }
var hot1 = new Handsontable(container1, settings1);
const t$ = Rx.Observable.timer(0, 2000).map(i => 2*i)
t$.subscribe( i => {
	hot1.loadData(data1)
})
