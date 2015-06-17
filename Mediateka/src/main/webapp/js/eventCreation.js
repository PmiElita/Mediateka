
function  dateChange(){

	var dateFromString = document.getElementById("dateFrom").value;
	var dateTillString = document.getElementById("dateTill").value;
	if (dateTillString!= ""||!dateFromString!= ""){
	var dateFrom = getDateFromFormat(document.getElementById("dateFrom").value, "yyyy-MM-dd");
	var dateTill = getDateFromFormat(document.getElementById("dateTill").value, "yyyy-MM-dd");
    if (dateFrom>dateTill) {
    	alert(21);
    	document.getElementById("wrongDate").innerHTML="Date till must be equals or greater than date from";
        return false;
    } else {
		return true;
	}
	
	}
}

function submitPressed(){
	alert(2);
	alert(dateChange());
	if (dateChange()){
		alert(1);
		document.createEvent.submit();
	}
}