function checkSubmit(){	
	$.ajax({
		url:'checkLogin',
		type: 'get',
		dataType: 'json',
		data: {email : document.getElementById("login_log").value, password :  document.getElementById("password_log").value},
	    complete : function(data) {
	    	if (data.responseJSON.message.toString()!=="success"){
			document.getElementById("message").innerHTML= data.responseJSON.message.toString();
	    	} else {
	    		document.getElementById("loginForm").submit();
	    	}
			   }
	});
	return false;
}