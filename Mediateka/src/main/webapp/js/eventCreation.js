function dateChange() {

	var dateFromString = document.getElementById("dateFrom").value;
	var dateTillString = document.getElementById("dateTill").value;
	alert(dateTillString);
	alert(dateFromString);
	if (dateTillString != "" && dateFromString != "") {
		alert("1*");
		var dateFrom = getDateFromFormat(
				document.getElementById("dateFrom").value, "yyyy-MM-dd");
		var dateTill = getDateFromFormat(
				document.getElementById("dateTill").value, "yyyy-MM-dd");
		if (dateFrom <= 0 || dateTill <= 0) {
			document.getElementById("wrongDate").innerHTML = "Date format is wrong";
			return false;
			alert(2);
		} else {
			if (dateFrom > dateTill) {
				document.getElementById("wrongDate").innerHTML = "Date till must be equals or greater than date from";
				document.getElementById("submit").disabled = true;
				alert(3);
				return false;
			} else {
				document.getElementById("wrongDate").innerHTML = "";
				document.getElementById("submit").disabled = false;
				alert(4)
				return true;
			}
		}
	}
}