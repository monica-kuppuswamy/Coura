<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html lang="en">
<head>
	<title>${message}</title>
   	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/angular_material/1.0.0/angular-material.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-animate.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-aria.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-messages.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-cookies.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angular_material/1.0.0/angular-material.min.js"></script>
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  	<link rel="stylesheet" type="text/css" href="resources/css/login.css">
  	<script type="text/javascript" src="resources/js/login.js"></script> 
</head>
 <body data-ng-app="couraApplication"> 
      <div class="frameContainer" layout="row" layout-padding layout-wrap layout-fill style="padding-bottom: 32px;" ng-cloak>
         <md-whiteframe class="md-whiteframe-10dp" flex-sm="100" flex-gt-sm="100" flex-gt-md="100" layout="column" layout-align="space-around center">
         	<br>
         	<img data-ng-src="resources/images/coura.jpg">
         	<div data-ng-controller="loginController" class="panel-group">
    			<div class="panel panel-default">
      				<div class="panel-body">
      					<br><br>
      					<form>
      						<div class="input-group">
    							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
    							<input type="text" class="form-control" data-ng-model="emailId" placeholder="Email Address">
  							</div>
  							<br>
  							<div class="input-group">
    							<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
    							<input type="password" class="form-control" data-ng-model="password" placeholder="Enter Password">
  							</div>
  							<br>
  							<input id="submit-button" type="button" data-ng-click="login()" value="Log In" class="btn btn-success btn-md"/>
  							<br>
  							<br>
  							<div data-ng-show="showMessage">
  							<span style="color:red">&nbsp;&nbsp;Either username or password is incorrect.</span>
  							</div>
      					</form>
      				</div>
    			</div>
    		</div>
         </md-whiteframe>
      </div>
   </body>
</html>