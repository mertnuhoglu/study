var data1 = [
  ['', 'Tesla', 'Nissan', 'Toyota', 'Honda', 'Mazda', 'Ford'],
  ['2017', 10, 11, 12, 13, 15, 16],
],
  container1 = document.getElementById('example1'),
  settings1 = {
    data: data1
  }
var hot1 = new Handsontable(container1, settings1);
data1[0][1] = 'Ford'; // change "Kia" to "Ford" programmatically
hot1.render();
console.log(hot1.getSourceData())
