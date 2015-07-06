<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/create_event" var="msg" />


<div id="modal18" class="modal">
	<div class="modal-content">
		<div class="container">
			<div class="row" style="margin-top: 0em; margin-left: -4em">

				<div class="col s4">
					<a href="CreateExhibition">
						<div
							class="col s12 m8 offset-m2 l6 offset-l3 my-card my-small-card">
							<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
								<h4 style="color: black" class="center">
									<fmt:message bundle="${msg}" key="exhibition" />
								</h4>
								<div class="row valign-wrapper">
									<div class="col s9">
										<img src="images/events/exhibition.png" alt=""
											class="circle responsive-img">
									</div>
								</div>
							</div>
						</div>
					</a>
				</div>

				<div class="col s4 offset-s2">
					<a href="CreateMeeting">
						<div
							class="col s12 m8 offset-m2 l6 offset-l3 my-card my-small-card">
							<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
								<h4 style="color: black" class="center">
									<fmt:message bundle="${msg}" key="meeting" />
								</h4>
								<div class="row valign-wrapper">
									<div class="col s9">
										<img src="images/events/meeting.png" alt=""
											class="circle responsive-img">
									</div>
								</div>
							</div>
						</div>
					</a>
				</div>
			</div>
		</div>
	</div>
</div>