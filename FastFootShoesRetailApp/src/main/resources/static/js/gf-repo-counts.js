var timeoutdelay = 3000;
var previousVals = {};


function requestMessageCount() {
	$.ajax({
		url : '/alertCount',
		success : function(messages) {
			$("#missedOrders").html(messages);
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
		returnVal += "<p><span>" + key + "</span>" + " : " + val;
		if (previousVals[key] < val) {
			returnVal += " <i class='fa fa-arrow-up'></i>";
		} else if (previousVals[key] > val) {
			returnVal += " <i class='fa fa-arrow-down'></i>";
		}
		returnVal += "</p>";
		previousVals[key] = val;
	});
	divId = $("#"+divId);
	divId.html(returnVal);
	if (returnVal.length == 0) {
		divId.html("N/A");
	}
	else {
		divId.html(returnVal);
	}
}