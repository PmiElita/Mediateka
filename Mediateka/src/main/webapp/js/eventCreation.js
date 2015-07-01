function dateChangeExhibition() {
	var dateFromString = document.getElementById("dateFrom").value;
	var dateTillString = document.getElementById("dateTill").value;
	alert(document.getElementById("dateFrom").value);
	var currentTime = new Date();
	currentTime.setHours(0, 0, 0);
	if (dateTillString != "" && dateFromString != "") {
		var dateFrom = getDateFromFormat(dateFromString, "dd.MM.yyyy");
		var dateTill = getDateFromFormat(dateTillString, "dd.MM.yyyy");
		if (dateFrom <= 0 || dateTill <= 0) {
			document.getElementById("wrongDate").innerHTML = "Date format is wrong";
			return false;
		} else {
			if (dateFrom < currentTime.getTime()) {
				document.getElementById("wrongDate").innerHTML = "Date from has gone...";
				document.getElementById("submit").disabled = true;
				return false;
			}
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

function dateChangeMeeting() {
	var dateString = document.getElementById("date").value;
	var currentTime = new Date();
	currentTime.setHours(0, 0, 0);
	if (dateString != "") {
		var date = getDateFromFormat(dateString, "dd.MM.yyyy");
		if (date <= 0) {
			document.getElementById("wrongDate").innerHTML = "Date format is wrong";
			return false;
		} else {
			if (date < currentTime.getTime()) {
				document.getElementById("wrongDate").innerHTML = "Date from has gone...";
				document.getElementById("submit").disabled = true;
				return false;
			} else {
				document.getElementById("wrongDate").innerHTML = "";
				return true;
			}
		}
	}
}

function timeChangeMeeting() {
	var dateString = document.getElementById("date").value;
	var timeFromString = document.getElementById("timeFrom").value;
	var timeTillString = document.getElementById("timeTill").value;
	if (dateString != "" && timeFromString != "" && timeTillString != ""
			&& dateChangeMeeting() == true) {
		var date = getDateFromFormat(dateString, "dd.MM.yyyy");
		var dateNew = new Date();
		dateNew.setTime(date);
		var current = new Date();
		var currentHour = current.getHours();
		var currentMinute = current.getMinutes();
		var timeFromHour = timeFromString.split(":")[0];
		var timeFromMinute = timeFromString.split(":")[1];
		var timeTillHour = timeTillString.split(":")[0];
		var timeTillMinute = timeTillString.split(":")[1];
		if (dateNew.getYear() == current.getYear()
				&& dateNew.getMonth() == current.getMonth()
				&& dateNew.getDate() == current.getDate()) {
			if (timeFromHour < currentHour
					|| (timeFromHour == currentHour && timeFromMinute <= currentMinute)) {
				document.getElementById("wrongTime").innerHTML = "Time from must be biger than current time. ";
				document.getElementById("submit").disabled = true;
				return false;
			} else if (timeFromHour > timeTillHour
					|| (timeFromHour == timeTillHour && timeFromMinute >= timeTillMinute)) {
				document.getElementById("wrongTime").innerHTML = "Time from must be less than time till. ";
				document.getElementById("submit").disabled = true;
				return false;
			} else {
				document.getElementById("wrongTime").innerHTML = "";
				document.getElementById("submit").disabled = false;
				return true;
			}
		} else if (timeFromHour > timeTillHour
				|| (timeFromHour == timeTillHour && timeFromMinute >= timeTillMinute)) {
			document.getElementById("wrongTime").innerHTML = "Time from must be less than time till. ";
			document.getElementById("submit").disabled = true;
			return false;
		} else if (timeFromHour < timeTillHour
				|| (timeFromHour == timeTillHour && timeFromMinute <= timeTillMinute)) {
			document.getElementById("wrongTime").innerHTML = "";
			document.getElementById("submit").disabled = false;
			return true;
		} else {
			document.getElementById("wrongTime").innerHTML = "";
			document.getElementById("wrongDate").innerHTML = "";
			document.getElementById("submit").disabled = true;
			return false;
		}
	} else if (dateString == "" || timeFromString == "" || timeTillString == "") {
		document.getElementById("wrongTime").innerHTML = "";
		document.getElementById("wrongDate").innerHTML = "";
		document.getElementById("submit").disabled = true;
		return false;
	}
}

function meeting() {
	dateChangeMeeting();
	timeChangeMeeting();
}