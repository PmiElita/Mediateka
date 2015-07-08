<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/club_page" var="msg" />

<div id="modal9" class="modal">
	<div class="modal-content">
		<div id="creation_form">
			<form>

				<div class="container">

					<h3 class="titler"><fmt:message bundle="${msg}" key="add_video" /></h3>
					<button class="btn waves-effect titler" type="submit"
						name="action">
						<fmt:message bundle="${msg}" key="add_video.add" /> 
					</button>

					<div class="row">
						<div class="col s12">
							<div class="row">
								<div class="input-field col s6">
									<p><fmt:message bundle="${msg}" key="add_video.description" /></p>
									<input id="description" name="description" type="text"
										class="validate">
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>

	</div>
</div>