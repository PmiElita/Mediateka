function dateChange() {

	var dateFromString = document.getElementById("dateFrom").value;
	var dateTillString = document.getElementById("dateTill").value;
	if (dateTillString != "" && dateFromString != "") {
		var dateFrom = getDateFromFormat(
				document.getElementById("dateFrom").value, "yyyy-MM-dd");
		var dateTill = getDateFromFormat(
				document.getElementById("dateTill").value, "yyyy-MM-dd");
		if (dateFrom <= 0 || dateTill <= 0) {
			document.getElementById("wrongDate").innerHTML = "Date format is wrong";
			return false;
		} else {
			if (dateFrom > dateTill) {
				document.getElementById("wrongDate").innerHTML = "Date till must be equals or greater than date from";
				return false;
			} else {
				document.getElementById("wrongDate").innerHTML = "";
				return true;
			}
		}
	}
}

function submitPressed() {

	if (dateChange()) {
		document.createEvent.submit();
	}
}