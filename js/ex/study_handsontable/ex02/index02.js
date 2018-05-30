var
$$ = function(id) {
	return document.getElementById(id);
},
	container = $$('example1'),
	exampleConsole = $$('example1console'),
	autosave = $$('autosave'),
	load = $$('load'),
	save = $$('save'),
	autosaveNotification,
	hot;

hot = new hot(container, {
	startRows: 8,
	startCols: 6,
	rowHeaders: true,
	colHeaders: true,
	afterChange: function (change, source) {
		if (source === 'loadData') {
			return; //don't save this change
		}
		if (!autosave.checked) {
			return;
		}
		clearTimeout(autosaveNotification);
		ajax('scripts/json/save.json', 'GET', JSON.stringify({data: change}), function (data) {
			exampleConsole.innerText  = 'Autosaved (' + change.length + ' ' + 'cell' + (change.length > 1 ? 's' : '') + ')';
			autosaveNotification = setTimeout(function() {
				exampleConsole.innerText ='Changes will be autosaved';
			}, 1000);
		});
	}
});

hot.dom.addEvent(load, 'click', function() {
	ajax('db.json', 'GET', '', function(res) {
		var data = JSON.parse(res.response);

		hot.loadData(data.data);
		exampleConsole.innerText = 'Data loaded';
	});
});

hot.dom.addEvent(save, 'click', function() {
	// save all cell's data
	ajax('scripts/json/save.json', 'GET', JSON.stringify({data: hot.getData()}), function (res) {
		var response = JSON.parse(res.response);

		if (response.result === 'ok') {
			exampleConsole.innerText = 'Data saved';
		}
		else {
			exampleConsole.innerText = 'Save error';
		}
	});
});

hot.dom.addEvent(autosave, 'click', function() {
	if (autosave.checked) {
		exampleConsole.innerText = 'Changes will be autosaved';
	}
	else {
		exampleConsole.innerText ='Changes will not be autosaved';
	}
});
