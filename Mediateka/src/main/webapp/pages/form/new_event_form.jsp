<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/forms" var="msg" />


<div id="modal3" class="modal">
	<div class="modal-content">
		<div id="creation_form">
			<form action="CreateEvent" method="post">

				<div class="container">
					<div style="margin-top: -3.5em">
						<button class="btn waves-effect blue titler" type="submit"
							name="action">
							<fmt:message bundle="${msg }" key="new_event_form.create" />
							<i class="mdi-content-send right"></i>
						</button>
						<div class="row" style="margin-top: 0em">
							<div class="input-field col s6">
								<p>
									<fmt:message bundle="${msg }" key="new_event_form.start_date" />
								</p>
								<input id="dateFrom" name="dateFrom" required="required"
									type="date" class="datepicker">
							</div>
							<div class="input-field col s6">
								<p>
									<fmt:message bundle="${msg }" key="new_event_form.end_date" />
								</p>
								<input id="dateTill" name="dateTill" required type="date"
									class="datepicker">
							</div>

						</div>

						<div class="row">
							<div class="col s12">
								<div class="row">
									<div class="input-field col s6">
										<p>
											<fmt:message bundle="${msg }" key="new_event_form.name" />
										</p>
										<input id="name" name="name" required type="text"
											class="validate">
									</div>
									<div class="input-field col s6">
										<p><fmt:message bundle="${msg }" key="new_event_form.type" /></p>
										<select id="type" name="type" class="browser-default"
											style="margin-top: 0.75em">
											<option value="MEETING"><fmt:message bundle="${msg }" key="new_event_form.meeting" /></option>
											<option value="EXHIBITION"><fmt:message bundle="${msg }" key="new_event_form.exhibition" /></option>
										</select>
									</div>
								</div>

								<div class="row">
									<div class="input-field col s12">
										<p><fmt:message bundle="${msg }" key="new_event_form.description" /></p>
										<textarea id="description" name="description"
											class="materialize-textarea" length="100"></textarea>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>

	</div>
</div>