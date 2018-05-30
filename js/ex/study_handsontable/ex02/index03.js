var data1 = [
  ['', 'Tesla', 'Nissan', 'Toyota', 'Honda', 'Mazda', 'Ford'],
  ['2017', 10, 11, 12, 13, 15, 16],
  ['2018', 10, 11, 12, 13, 15, 16],
  ['2019', 10, 11, 12, 13, 15, 16],
  ['2020', 10, 11, 12, 13, 15, 16],
  ['2021', 10, 11, 12, 13, 15, 16]
],
  container1 = document.getElementById('example1'),
  settings1 = {
    data: data1
  },
  hot1;

hot1 = new Handsontable(container1, settings1);
data1[0][1] = 'Ford'; // change "Kia" to "Ford" programmatically
hot1.render();
console.log(JSON.stringify(data1))
// [["","Ford","Nissan","Toyota","Honda","Mazda","Ford"],["2017",10,11,12,13,15,16],["2018",10,11,12,13,15,16],["2019",10,11,12,13,15,16],["2020",10,11,12,13,15,16],["2021",10,11,12,13,15,16]]
var data2 = [
  ['', 'Mercedes', 'Opel', 'Toyota', 'Honda', 'Mazda', 'Ford'],
  ['2017', 10, 11, 12, 13, 15, 16],
  ['2018', 10, 11, 12, 13, 15, 16],
  ['2019', 10, 11, 12, 13, 15, 16],
  ['2020', 10, 11, 12, 13, 15, 16],
  ['2021', 10, 11, 12, 13, 15, 16]
]
hot1.loadData(data2)
