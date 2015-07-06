

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
