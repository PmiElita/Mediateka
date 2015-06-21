function readURL(input) {
	for (var i = 0; i < input.files.length; i++) {
		if (input.files && input.files[i]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#' + 'photo' + viewFile()).attr('src', e.target.result)
						.width(150).height(200);
			};
			
			reader.readAsDataURL(input.files[i]);
		}
	}
}
function viewFile() {
	if (document.getElementById('photo') != null) {
		return "";
	}
	var numb = document.getElementById('number').innerText;
	document.getElementById('number').innerText++;
	alert(document.getElementById('number').innerText)
	$('<img>').attr('id', 'photo' + numb).appendTo(
			document.getElementById('selectedFiles'));	

	return numb;

}
