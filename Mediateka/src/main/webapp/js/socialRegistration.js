
function checkEmail(){
	
	var email = document.getElementById("email");
	$.ajax(
			{
				url:'checkemail', 
				data : { email : email.value}, 
				success : 
					function(data){
					alert(data);
						if (data == 'true'){
							email.setCustomValidity('');
							email.setAttribute('class', 'validate valid');
							
							
						} else {
							email.setAttribute('class', 'validate invalid');
							email.setCustomValidity('<fmt:message bundle="${msg}" key="registration.email_is_in_use" />');
							
					        
						}
					}
			} 
		);
	
}