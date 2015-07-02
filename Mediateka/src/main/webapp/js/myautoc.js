
$(function () {
$('#userQuery').autocomplete({
    serviceUrl: 'get_users_by_regexp', // Страница для обработки запросов автозаполнения
    minChars: 1,
    maxHeight: 400,
    width: 300,
    onSelect: function(suggestion){ 
    	
    	 } // Callback функция, срабатывающая на выбор одного из предложенных вариантов,
 
});
});

$(function () {
	$('#query').autocomplete({
	    serviceUrl: 'get_users_by_regexp', // Страница для обработки запросов автозаполнения
	    minChars: 1,
	    maxHeight: 400,
	    width: 300,
	    onSelect: function(suggestion){ 
	    	
	    	 } // Callback функция, срабатывающая на выбор одного из предложенных вариантов,
	 
	});
	});

function clearMessage(){
	if (event.keyCode != 13){
	document.getElementById("message").innerHTML="";
	}
}

function submitUserForm(){

	var query = document.getElementById("userQuery");
	var message = document.getElementById("message");
	if (query.value!=""){
	   $.ajax({
			url:'ckeckUsers',
			type: 'post',
			dataType: 'json',
			data: {"query" : query.value},
		    complete : function(data) {
		    	if (!(data.responseJSON.message.toString()==="success")){
				 message.innerHTML=data.responseJSON.message.toString();
		    	} else {
		    		document.getElementById("searchUsers").submit();
		    	}
				 
				   }
		});
	
	}
	return false;
}

function reloadUsers(){
	var queryArray = document.getElementById("query").value.split(" ");
	var query =queryArray[0] ;
	for	(index = 1; index < queryArray.length; index++) {
	    query +="+"+queryArray[index];
	}
	if (query!=""){
	$('#users').load("users?query="+query +  ' #users');
	}
	return false;
}

function blockUser(button){
	var userId=button.value;
	  $.ajax({
			url:'blockUser',
			type: 'post',
			dataType: 'json',
			data: {"userId" : userId},
		    complete : function(data) {
		    	if ((data.responseJSON.message.toString()==="success")){
		    		button.innerHTML = data.responseJSON.button.toString();
		    	} 
				   }
		});
}


$(function () {
$('#bookQuery').autocomplete({
    serviceUrl: 'get_books_by_regexp', // Страница для обработки запросов автозаполнения
    minChars: 1,
    maxHeight: 400,
    width: 300,
    onSelect: function(suggestion){ 
    	
    	 } // Callback функция, срабатывающая на выбор одного из предложенных вариантов,
 
});
});