<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="../general/head.jsp" />
<jsp:include page="report_response_submition_form_modal.jsp" />

</head>
<body>

	<div class="main">
		<jsp:include page="../general/nav.jsp" />

		<div id="responses_div"></div>
		<a href="#" onclick="getMoreResponses()">get more responses</a>
	</div>




	<script type="text/javascript">
		currentResponses = [];
		currentOffset = 0;
		limit = 10;
		noMoreResponses = false;
		getMoreResponses();
		function getMoreResponses() {
			if (noMoreResponses){
				return;
			}
			
			$.ajax({
				url : 'getResponses',
				type : 'get',
				data : {
					'offset' : currentOffset,
					'limit' : limit
				},
				success : function(newResponses) {
					newResponsesArray = $.parseJSON(newResponses);

					if (newResponsesArray.length == 0){
						noMoreResponses = true;
						return;
					}
					currentResponses = currentResponses.concat(newResponsesArray);
					currentOffset += newResponsesArray.length;

					newResponsesArray.forEach(markAsReaded);

					redrawResponses();

				}
			});

		}

		function markAsReaded(element, index, array) {
			if (element.newFlag == false) {
				return;
			}

			$.ajax({
				url : 'markResponseAsReaded',
				type : 'get',
				data : {
					'responseId' : element.id
				},
				success : function(newResponses) {
				}
			});

		}

		function redrawResponses() {
			console.log('redrawing responses');
			responsesDiv = document.getElementById("responses_div");
			responses = currentResponses.map(drawResponse);
			responsesDiv.innerHTML = responses.join("");

		}

	      
		function drawResponse(response) {
			
		      return "<div class='row'>\
		        <div class='col s12 m6'>\
		          <div class='card blue-grey darken-1'>\
		            <div class='card-content white-text'><p>" +
		            $("<div>").text(response.text).html().replace(/\r?\n/g, "<br />") +
		              "</p>\
		            </div>\
		            <div class='card-action'>\
		              <a href='#' onclick='deleteResponse("+response.id+")'>Delete</a>\
		              <a href="" data-target="moda26" class="btn modal-trigger waves-effect">Answer</a>\
		            </div>\
		          </div>\
		        </div>\
		      </div>";
		}
		
		
		function deleteResponse(responseId) {

			console.log('deleting response # ' + responseId);
			$.ajax({
				url : 'deleteResponse',
				type : 'get',
				data : {
					'responseId' : responseId
				},
				success : function(newResponses) {
				}
			});

			console.log('currentResponses before:');
			console.log(currentResponses);
			currentResponses = currentResponses.filter(
				function(response){
					console.log('compare ' + response.id + 'and' + responseId);
					console.log(response.id != responseId);
					return response.id != responseId;
				}
			);
			
			console.log('currentResponses after:');
			console.log(currentResponses);

			redrawResponses();
		}

	</script>

</body>
</html>