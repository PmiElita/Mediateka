<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="org.apache.log4j.Logger"%>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="menu" />
<fmt:requestEncoding value="utf-8" />

<div class="admin-info">
	<div class="section white">
		<div class="container">

			<div class="row" style="margin-left: -4em">
				<div class="col s4">
					<a href="" data-target="modal7" class="modal-trigger">
						<div class="col s12 m8 offset-m2 l6 offset-l3 my-card">
							<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
								<div style="margin-top: -2em">
									<h3 style="color: black">Users</h3>
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
									<h3 style="color: black">Clubs</h3>
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
									<h3 style="color: black">Events</h3>
									<div class="row valign-wrapper">
										<div class="col s8">
											<img style="margin-top: 0em" src="images/admin/events.png"
												alt="" class="circle responsive-img">
										</div>
										<div class="my-badge" style="margin-left:0.75em">2</div>
									</div>
								</div>
							</div>
						</div>
					</a>
				</div>
			</div>



			<div class="row" style="margin-left: -4em">
				<div class="col s4">
					<a href="info">
						<div class="col s12 m8 offset-m2 l6 offset-l3 my-card">
							<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
								<div style="margin-top: -2em">
									<h3 style="color: black">Info</h3>
									<div class="row valign-wrapper">
										<div class="col s9">
											<img src="images/admin/info.png" alt=""
												class="circle responsive-img">
										</div>
										<div class="my-badge" style="margin-left:0.4em">1</div>
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
								<h3 style="color: black">Books</h3>
								<div class="row valign-wrapper">
									<div class="col s9">
										<img src="images/admin/books.png" alt=""
											class="circle responsive-img">
									</div>
									<div class="my-badge" style="margin-left:0.2em">88</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="col s4">
					<div class="col s12 m8 offset-m2 l6 offset-l3 my-card">
						<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
							<div style="margin-top: -2em">
								<h3 style="color: black">Reports</h3>
								<div class="row valign-wrapper">
									<div class="col s8">
										<img style="margin-top: 0em" src="images/admin/reports.png"
											alt="" class="circle responsive-img">
									</div>
									<div class="my-badge" style="margin-left:0.75em">9</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>