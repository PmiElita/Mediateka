
function valid(){
	var typeString = document.getElementById("type").value;
	var meaningString = document.getElementById("meaning").value;
	var languageString = document.getElementById("language").value;
	var isValid=true;
	if (typeString == 0){
		Materialize.toast('Fill book type!', 2000);
		isValid = false;
	}
	if (meaningString == 0){
		Materialize.toast('Fill book meaning!', 2000);
		isValid = false;
	}
	
	if (languageString == 0){
		Materialize.toast('Fill book language!', 2000);
		isValid=false;
	}
	return isValid;
	
}

$(function () {
	$('#name').autocomplete({
	    serviceUrl: 'getBookNameByRegexp', // Страница для обработки запросов автозаполнения
	    minChars: 1,
	    maxHeight: 400,
	    width: 300,
	    onSelect: function(suggestion){ 
	    	
	    	 } // Callback функция, срабатывающая на выбор одного из предложенных вариантов,
	 
	});
	$('#author').autocomplete({
	    serviceUrl: 'getAuthorsByRegexp', // Страница для обработки запросов автозаполнения
	    minChars: 1,
	    maxHeight: 400,
	    width: 300,
	    onSelect: function(suggestion){ 
	    	 } // Callback функция, срабатывающая на выбор одного из предложенных вариантов,
	 
	});
	});

function checkLibraryBookId(input){
	$.ajax({
		url: 'checkLibraryBookId',
		type: 'get',
		dataType: 'json',
		data: {"libraryBookId": input.value},
		complete : function(data){
			if (data.responseJSON.libraryBookIdState != "free"){
				setInvalid(input);
			} else {
				input.setCustomValidity('');
				form.email.setAttribute('class', 'validate valid');
			}
		}
	});
}