var data1 = [
  ['', 'Tesla', 'Nissan', 'Toyota', 'Honda', 'Mazda', 'Ford'],
  ['2017', 10, 11, 12, 13, 15, 16],
],
  container1 = document.getElementById('example1'),
  settings1 = {
    data: data1
  }
var hot1 = new Handsontable(container1, settings1);
const t$ = Rx.Observable.timer(0, 2000).map(i => 2*i)
t$.subscribe( i => {
	data1[0][i % 7] = i; 
	hot1.loadData(data1)
})
