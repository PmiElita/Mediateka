$(function () {
	$('#bookName').autocomplete({
	    serviceUrl: 'get_books_by_regexp', // Страница для обработки запросов автозаполнения
	    minChars: 1,
	    maxHeight: 400,
	    width: 300,
	    onSelect: function(suggestion){ 
	    	document.getElementById("bookId").value = suggestion.data;
	    	 } // Callback функция, срабатывающая на выбор одного из предложенных вариантов,
	 
	});
	});

$(function () {
	$('#bookId').autocomplete({
	    serviceUrl: 'get_books_by_library_book_id_regexp', // Страница для обработки запросов автозаполнения
	    minChars: 1,
	    maxHeight: 400,
	    width: 300,
	    onSelect: function(suggestion){ 
	    	document.getElementById("bookName").value = suggestion.data;
	    	 } // Callback функция, срабатывающая на выбор одного из предложенных вариантов,
	 
	});
	});
$(function(){
	$('#bookId').keypress(function(e){
		document.getElementById("bookName").value="";
	});
});

$(function(){
	$('#bookName').keypress(function(e){
		var input =document.getElementById("bookId");

	});

});
$(function(){
$('#bookId').change(function(e){
	var input =document.getElementById("bookId");
	input.setCustomValidity('');
	input.setAttribute('class', 'validate valid');
});
});

function checkFormRecordValidity(){
	var form = document.getElementById("create_form_record");
	var input = document.getElementById("bookId");
	if (document.getElementById("bookRadio").checked){
		$.ajax({
			url : 'checkLibraryBookId',
			type : 'get',
			dataType : 'json',
			data : {
				"libraryBookId" : input.value
			},
			complete : function(data) {
				
				if (data.responseJSON.libraryBookIdState == "free"
						&& input.value != document
								.getElementById("oldLibraryBookId")) {
					setInvalid(input);
					
				} else {
					input.setCustomValidity('');
					input.setAttribute('class', 'validate valid');
					form.submit();
				}
			}
		});
	} else if (document.getElementById("eventRadio").checked){
		var option = getSelectedOption(document.getElementById("event"));
		if (option.value!=null&&option.value!=""){
			
			form.submit()
		}else {
			Materialize.toast("Select event!",2000);
		}
	} else {
		form.submit();
	}
	
	return false;	
}

function getSelectedOption(select){
	for (var i =0; i<select.options.length; i++){
		if (select.options[i].selected){
			return select.options[i];
		}
	}
}