function submitModify(){

		 $.ajax({
				url:'modifyUserAjax',
				type: 'post',
				dataType: 'json',
				data: $("#modifyUser").serialize(),
			    complete : function(data) {
			    	Materialize.toast(data.responseJSON.message.toString(), 4000);
					 
					   }
			});

	return false;
}