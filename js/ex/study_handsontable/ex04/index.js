var data1 = [
  ['', 'Tesla', 'Nissan', 'Toyota', 'Honda', 'Mazda', 'Ford'],
  ['2017', 10, 11, 12, 13, 15, 16],
],
  container1 = document.getElementById('example1'),
  settings1 = {
    data: data1
  }
var hot1 = new Handsontable(container1, settings1);
hot1.addHook('afterChange', function () {
  console.log(hot1.getSourceData())
})
