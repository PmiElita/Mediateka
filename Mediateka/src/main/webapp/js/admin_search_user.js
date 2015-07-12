

var index;
$(document).ready(function(){
	index=1;
});
function bodyScroll(){
						
							var queryArray = document.getElementById("query").value.split(" ");
							var query =queryArray[0] ;
							for	(index = 1; index < queryArray.length; index++) {
							    query +="+"+queryArray[index];
							}
							var emptyDiv = document.getElementById("empty");
							emptyDiv.id = "empty"+index;
							
							$('#'+emptyDiv.id).load("getMoreUsersForAdmin?query="+query +"&index="+index);
											index = index + 1;
								
						
					
}

function makeAdmin(button){
	var userId=button.value;
	  $.ajax({
			url:'makeAdmin',
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
