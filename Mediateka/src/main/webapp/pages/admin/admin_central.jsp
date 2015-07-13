<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/admin" var="msg" />



<div class="admin-info" style="margin-top: 2em; margin-bottom: 2.3em">
	<div class="section">
		<div class="container">

			<div class="row">
				<div class="col s4">
					<a href="" data-target="modal7" class="modal-trigger">
						<div class="col s12 m8 offset-m2 l6 offset-l3 my-card">
							<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
								<div style="margin-top: -1em">
									<h4 style="color: black" class="center">
										<fmt:message bundle="${msg}" key="users" />
									</h4>
									<div class="row valign-wrapper">
										<div class="col s10">
											<img src="images/admin/users.png" alt=""
												class="circle responsive-img" />
										</div>
										<div class="my-badge">${usersCount }</div>
									</div>
								</div>
							</div>
						</div>
					</a>
				</div>

				<div class="col s4">
					<a href="clubs">
						<div class="col s12 m8 offset-m2 l6 offset-l3 my-card">
							<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
								<div style="margin-top: -1em">
									<h4 style="color: black" class="center">
										<fmt:message bundle="${msg}" key="clubs" />
									</h4>
									<div class="row valign-wrapper">
										<div class="col s10">
											<img src="images/admin/clubs.png" alt=""
												class="circle responsive-img">
										</div>
										<div class="my-badge">${clubsCount}
											<c:if test="${requestedClubCount > 0}">
													(+<c:out value="${requestedClubCount}" />)
												<p>
											</c:if>
										</div>
									</div>
								</div>
							</div>
						</div>
					</a>
				</div>

				<div class="col s4">
					<a href="events">
						<div class="col s12 m8 offset-m2 l6 offset-l3 my-card">
							<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
								<div style="margin-top: -1em">
									<h4 style="color: black" class="center">
										<fmt:message bundle="${msg}" key="events" />
									</h4>
									<div class="row valign-wrapper">
										<div class="col s8">
											<img style="margin-top: 0em" src="images/admin/events.png"
												alt="" class="circle responsive-img">
										</div>
										<div class="my-badge" style="margin-left: 0.75em"> ${eventsCount}
											<c:if test="${requestedEventCount > 0}">
													(+<c:out value="${requestedEventCount}" />)
												<p>
											</c:if>
										</div>
									</div>
								</div>
							</div>
						</div>
					</a>
				</div>



				<div class="col s4">
					<a href="info">
						<div class="col s12 m8 offset-m2 l6 offset-l3 my-card">
							<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
								<div style="margin-top: -1em">
									<h4 style="color: black" class="center">
										<fmt:message bundle="${msg}" key="info" />
									</h4>
									<div class="row valign-wrapper">
										<div class="col s9">
											<img src="images/admin/info.png" alt=""
												class="circle responsive-img">
										</div>
										<div class="my-badge" style="margin-left: 0.4em"></div>
									</div>
								</div>
							</div>
						</div>
					</a>
				</div>
				<c:if test="${!(good==null)}">
					<div class="section white">
						<h6 style="color: green">
							<c:out value="Changes saved." />
						</h6>
					</div>
				</c:if>

				<div class="col s4">
					<a href="books">
						<div class="col s12 m8 offset-m2 l6 offset-l3 my-card">
							<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
								<div style="margin-top: -1em">
									<h4 style="color: black" class="center">
										<fmt:message bundle="${msg}" key="books" />
									</h4>
									<div class="row valign-wrapper">
										<div class="col s9">
											<img src="images/admin/books.png" alt=""
												class="circle responsive-img">
										</div>
										<div class="my-badge" style="margin-left: 0.2em">${booksCount }</div>
									</div>
								</div>
							</div>
						</div>
					</a>
				</div>

				<div class="col s4">
					<a href="showResponsesPage?offset=0&limit=5">
						<div class="col s12 m8 offset-m2 l6 offset-l3 my-card">
							<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
								<div style="margin-top: -1em">
									<h4 style="color: black" class="center">
										<fmt:message bundle="${msg}" key="reports" />
									</h4>
									<div class="row valign-wrapper">
										<div class="col s8">
											<img style="margin-top: 0em" src="images/admin/reports.png"
												alt="" class="circle responsive-img">
										</div>
										<div class="my-badge" style="margin-left: 0.75em">

											<c:out value="${totalReports }" />
											<c:if test="${newReports > 0}">
												<c:out value="(${newReports})" />
												<p>
											</c:if>

										</div>
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