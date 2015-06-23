function valid() {
	var typeString = document.getElementById("type").value;
	var meaningString = document.getElementById("meaning").value;
	var languageString = document.getElementById("language").value;
	if (typeString == 0 || meaningString == 0 || languageString == 0) {
		Materialize.toast('Fill all fields!', 2000);
	} else {
		document.getElementById("submit").disabled = false;
	}
}