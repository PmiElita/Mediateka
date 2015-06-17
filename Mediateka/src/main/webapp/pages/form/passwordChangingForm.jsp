<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>



<form class="form-horizontal" action="changePassword" method="post">
<fieldset>

<!-- Form Name -->
<legend>Form Name</legend>

<!-- Text input-->
<input type="hidden" name="token" id="token" value="${token}"/>

<!-- Text input-->
<div class="control-group">
  <label class="control-label" for="password">new password</label>
  <div class="controls">
    <input id="password" name="password" type="text" placeholder="password" class="input-xlarge" required="">
    <p class="help-block">passworrd</p>
  </div>
</div>



<!-- Text input-->
<div class="control-group">
  <label class="control-label" for="password">confirm password</label>
  <div class="controls">
    <input id="password" name="confirmPassword" type="text" placeholder="password" class="input-xlarge" required="">
    <p class="help-block">confirmPassword</p>
  </div>
</div>



<!-- Button -->
<div class="control-group">
  <label class="control-label" for="button"></label>
  <div class="controls">
    <button id="button" name="button" class="btn btn-primary">Send</button>
  </div>
</div>

</fieldset>
</form>






</body>
</html>