var timeoutdelay = 3000;
var previousVals = {};


function requestMessageCount() {
	$.ajax({
		url : '/alertCount',
		success : function(messages) {
			document.getElementById("missedOrders").innerHTML = messages;
			// call it again after one second
			setTimeout(requestMessageCount, timeoutdelay);
		},
		cache : false
	});
}
requestMessageCount();

function requestProductCounts() {
	$.getJSON('/productCounts', function(data) {
		updateUI(data[0], "stockByType");
		updateUI(data[1], "stockByBrand");
	});
	setTimeout(requestProductCounts, timeoutdelay);
}
requestProductCounts();

function updateUI(data, divId) {
	var returnVal = "";
	$.each(data, function(key, val) {
		returnVal += "<span style='color:black;font-weight:normal;'>" + key + "</span>" + " : " + val;
		if (previousVals[key] < val) {
			returnVal += " <i class='fa fa-arrow-up' style='color:green'></i>";
		} else if (previousVals[key] > val) {
			returnVal += " <i class='fa fa-arrow-down' style='color:red'></i>";
		}
		returnVal += "<br/>";
		previousVals[key] = val;
	});
	document.getElementById(divId).innerHTML = returnVal;
	if (returnVal.length == 0) {
		document.getElementById(divId).innerHTML = "<span style='color:red;font-weight:normal;'>N/A</span>";
	}
	else {
		document.getElementById(divId).innerHTML = returnVal;
	}
}