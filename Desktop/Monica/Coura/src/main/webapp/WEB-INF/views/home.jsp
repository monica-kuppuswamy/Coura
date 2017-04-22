<html lang="en" >
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
      <script src="https://ajax.googleapis.com/ajax/libs/angular_material/1.0.0/angular-material.min.js"></script>
  	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  	  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  	  <link rel="stylesheet" type="text/css" href="resources/css/home.css">
  	  <script type="text/javascript" src="resources/js/home.js"></script>
   </head>
   <body data-ng-app="couraApplication"> 
      <div class="frameContainer" layout="row" layout-padding layout-wrap layout-fill style="padding-bottom: 32px;" ng-cloak>
         <md-whiteframe class="md-whiteframe-10dp" flex-sm="100" flex-gt-sm="100" flex-gt-md="100" layout="column" layout-align="space-around center">
         	<img src="resources/images/coura.jpg">
         	<div data-ng-controller="panelController" class="panel-group">
    			<div class="panel panel-default">
      				<div class="panel-body">
      					<br><br>
      					<p class="text-center">Are you a new user?</p>
      					<a type="button" data-ng-href="/app/signup" class="btn btn-danger btn-md">Sign Up</a>
      					<br><br><br>
      					<p class="text-center">Already registered user?</p>
      					<a type="button" data-ng-href="/app/login" class="btn btn-success btn-md">Log In</a>
      				</div>
    			</div>
    		</div>
         </md-whiteframe>
      </div>
   </body>
</html>
