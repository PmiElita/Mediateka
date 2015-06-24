<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="menu" />
<fmt:requestEncoding value="utf-8" />

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/admin" var="msg" />



<div class="admin-info" style="margin-top: 2em; margin-bottom: 2.3em">
	<div class="section white">
		<div class="container">

			<div class="row">
				<div class="col s4">
					<a href="" data-target="modal7" class="modal-trigger">
						<div class="col s12 m8 offset-m2 l6 offset-l3 my-card">
							<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
								<div style="margin-top: -2em">
									<h3 style="color: black">
										<fmt:message bundle="${msg}" key="users" />
									</h3>
									<div class="row valign-wrapper">
										<div class="col s10">
											<img src="images/admin/users.png" alt=""
												class="circle responsive-img" />
										</div>
										<div class="my-badge">4</div>
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
								<div style="margin-top: -2em">
									<h3 style="color: black">
										<fmt:message bundle="${msg}" key="clubs" />
									</h3>
									<div class="row valign-wrapper">
										<div class="col s10">
											<img src="images/admin/clubs.png" alt=""
												class="circle responsive-img">
										</div>
										<div class="my-badge">3</div>
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
								<div style="margin-top: -2em">
									<h3 style="color: black">
										<fmt:message bundle="${msg}" key="events" />
									</h3>
									<div class="row valign-wrapper">
										<div class="col s8">
											<img style="margin-top: 0em" src="images/admin/events.png"
												alt="" class="circle responsive-img">
										</div>
										<div class="my-badge" style="margin-left: 0.75em">2</div>
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
								<div style="margin-top: -2em">
									<h3 style="color: black">
										<fmt:message bundle="${msg}" key="info" />
									</h3>
									<div class="row valign-wrapper">
										<div class="col s9">
											<img src="images/admin/info.png" alt=""
												class="circle responsive-img">
										</div>
										<div class="my-badge" style="margin-left: 0.4em">1</div>
									</div>
								</div>
							</div>
						</div>
					</a>
				</div>

				<div class="col s4">
					<a href="books">
						<div class="col s12 m8 offset-m2 l6 offset-l3 my-card">
							<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
								<div style="margin-top: -2em">
									<h3 style="color: black">
										<fmt:message bundle="${msg}" key="books" />
									</h3>
									<div class="row valign-wrapper">
										<div class="col s9">
											<img src="images/admin/books.png" alt=""
												class="circle responsive-img">
										</div>
										<div class="my-badge" style="margin-left: 0.2em">88</div>
									</div>
								</div>
							</div>
						</div>
					</a>
				</div>

				<div class="col s4">
					<div class="col s12 m8 offset-m2 l6 offset-l3 my-card">
						<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
							<div style="margin-top: -2em">
								<h3 style="color: black">
									<fmt:message bundle="${msg}" key="reports" />
								</h3>
								<div class="row valign-wrapper">
									<div class="col s8">
										<img style="margin-top: 0em" src="images/admin/reports.png"
											alt="" class="circle responsive-img">
									</div>
									<div class="my-badge" style="margin-left: 0.75em">9</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>