<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="DemoApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Angular Demo</title>
<script src="lib/angular.min.js"></script>
</head>
<body>
	<script type="text/javascript">
		var demoApp = angular.module("DemoApp", []);
	</script>
	<div>
		Enter your name: <input placeholder="Enter your name" ng-model="name"/>
		<br>
		Hello {{name}}
	</div>
</body>
</html>