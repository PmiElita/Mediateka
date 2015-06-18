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
				document.getElementById("submit").disabled = true;
				return false;
			} else {
				document.getElementById("wrongDate").innerHTML = "";
				document.getElementById("submit").disabled = false;
				return true;
			}
		}
	}
}

function handleClick(myRadio) {
	var exhibition = String("EXHIBITION");
	var meeting = String("MEETING");
	if (String(myRadio.value) == exhibition)
		alert("+exhib");
	else if (String(myRadio.value) == meeting)
		alert("+meet");
}