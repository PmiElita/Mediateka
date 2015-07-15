<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/create_event" var="msg" />

<script type="text/javascript">

	eventCreationJsTranslations = {
		'date_format_is_wrong' : "<fmt:message bundle="${msg }" key="js.date_format_is_wrong" />",
		'event_start_time_passed' : "<fmt:message bundle="${msg }" key="js.event_start_time_passed" />",
		'event_end_time_must_be_after_start_time' : "<fmt:message bundle="${msg }" key="js.event_end_time_must_be_after_start_time" />",
	};
</script>
	