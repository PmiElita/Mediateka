<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@page import="com.mediateka.model.enums.Role"%>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="menu" />
<fmt:requestEncoding value="utf-8" />

<html>

<head>
<jsp:include page="../general/head.jsp" />
<jsp:include page="search_book.jsp" />
</head>

	<style>
.image-cover-t {
	color: white;
	position: relative;
	margin-top: 1em;
	z-index: 1000;
	text-shadow: black 1.0px 0.0px, black 1.0px 1.0px, black 0.0px 1.0px,
		black -1.0px 1.0px, black -1.0px 0.0px, black -1.0px -1.0px, black
		0.0px -1.0px, black 1.0px -1.0px, black 0.0 0.0 3.0px, black 0.0 0.0
		3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px,
		black 0.0 0.0 3.0px, black 0.0 0.0 3.0px, black 0.0 0.0 3.0px;
}
</style>


<body>
	<div class="main">
		<jsp:include page="../general/nav.jsp" />

		<div class="parallax-container my-parallax">
			<div class="parallax">
				<img src="images/parallax1.jpg">
			</div>

			<jsp:include page="../admin/admin_side_nav.jsp" />

			<div class="section">
				<div class="container">

					<div style="width: 15em; margin: auto">
						<h2 class="image-cover-t">Books</h2>
					</div>

					<div class="row" style="margin-left: -7em; margin-top: 4em">

						<div class="col s3">
							<a title="Find book" href="" data-target="modal14"
								class="modal-trigger">
								<div class="col s12 m8 offset-m2 l6 offset-l3 my-card">
									<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
										<div style="margin-top: 2em">
											<div class="row valign-wrapper">
												<div class="col s11">
													<img src="images/admin/search_book.png" alt=""
														class="responsive-img" />
												</div>
											</div>
										</div>
									</div>
								</div>
							</a>
						</div>

						<div class="col s3">
							<a title="Create book" href="CreateBook">
								<div class="col s12 m8 offset-m2 l6 offset-l3 my-card">
									<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
										<div style="margin-top: 2em">
											<div class="row valign-wrapper">
												<div class="col s9">
													<img src="images/admin/add_book.png" alt=""
														class="responsive-img" />
												</div>
											</div>
										</div>
									</div>
								</div>
							</a>
						</div>
						<div class="col s3">
							<a title="Update book" href="UpdateBook">
								<div class="col s12 m8 offset-m2 l6 offset-l3 my-card">
									<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
										<div style="margin-top: 2em">
											<div class="row valign-wrapper">
												<div class="col s9">
													<img src="images/admin/book_update.png" alt=""
														class="responsive-img" />
												</div>
											</div>
										</div>
									</div>
								</div>
							</a>
						</div>
						<div class="col s3">
							<a title="Book statistics" href="bookStatistics">
								<div class="col s12 m8 offset-m2 l6 offset-l3 my-card">
									<div class="my-admin-card card-panel grey lighten-5 z-depth-1">
										<div style="margin-top: 2em">
											<div class="row valign-wrapper">
												<div class="col s9">
													<img src="images/admin/book_statistics.png" alt=""
														class="responsive-img" />
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
	</div>

	<jsp:include page="../general/footer.jsp" />
</body>
</html>