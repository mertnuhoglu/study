import xs from 'xstream'
const Handsontable = require('handsontable/dist/handsontable.full.min.js')

export default function makeHotDriver(mount_id, initial_data) {
  var hot;
  var container = document.querySelector(mount_id);
  hot = new Handsontable(container, {
    data: initial_data,
    minRows: 1,
    minCols: 7,
    minSpareRows: 1,
    contextMenu: true
  });
  return function HotDriver(data$) {
    data$.addListener({
      next: data => {
        hot.loadData(data)
      }}
    )
    var producer = {
      start: function(observer) {
        hot.addHook('afterChange', function () {
          console.log("changed")
          observer.next(hot.getSourceData())
        })
      },
      stop: function () {
        console.log("stopped")
      }
    }
    const HotSource = xs.create(producer)
    return HotSource
  }
}
