<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<fmt:setLocale value="${cookie.lang.value}" />
<fmt:setBundle basename="translations/record_js" var="msg" />


<script type="text/javascript">
	recordJsTranslations = {};
	recordJsTranslations["click_to_remove"] = "<fmt:message bundle="${msg }" key="click_to_remove" />";
	recordJsTranslations["file_is_uploaded"] = "<fmt:message bundle="${msg }" key="file_is_uploaded" />";
	recordJsTranslations["some_links_are_incorrect"] = "<fmt:message bundle="${msg }" key="some_links_are_incorrect" />";
</script>

