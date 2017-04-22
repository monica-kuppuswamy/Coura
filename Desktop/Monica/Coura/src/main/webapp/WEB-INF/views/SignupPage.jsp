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
    <script src="https://ajax.googleapis.com/ajax/libs/angular_material/1.0.0/angular-material.min.js"></script>
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  	<link rel="stylesheet" type="text/css" href="resources/css/signup.css">
  	<script type="text/javascript" src="resources/js/signup.js"></script>      
</head>
 <body data-ng-app="couraApplication">
 	<div class="jumbotron">
  		<div class="container text-center">
    		<h1>Coura</h1>      
    		<p>Course Recommender</p>
  		</div>
	  </div>
      <div class="frameContainer" layout="row" layout-padding layout-wrap layout-fill ng-cloak>
      	<md-whiteframe class="md-whiteframe-10dp" flex-sm="100" flex-gt-sm="100" flex-gt-md="100" layout="column" layout-align="space-around center">
         	<br>
         	<div data-ng-controller="frameController" class="panel-group">
         		<div data-ng-show="success" class="alert alert-success">{{successMessage}} Click <a data-ng-href="/app/login" class="alert-link"><strong>here</strong></a> to Log In</div>
         		<div data-ng-show="error" class="alert alert-danger"><strong>Error: </strong>{{errorMessage}}</div>
         		<br>
    			<div class="panel panel-primary">
    				<div class="panel-heading">Sign Up</div>
      				<div class="panel-body">
      					<form name="signupForm" class="form-horizontal">
      						<label>First Name:</label>
  							<input type="text" class="form-control" name="firstName" data-ng-model="firstName" placeholder="Enter First Name" required/>
  							<span style="color:red" data-ng-show="signupForm.firstName.$touched && signupForm.firstName.$invalid">First Name is required</span>
  							<br>
  							
      						<label>Last Name:</label>
  							<input type="text" class="form-control" name="lastName" data-ng-model="lastName" placeholder="Enter Last Name" required/>
  							<span style="color:red" data-ng-show="signupForm.lastName.$touched && signupForm.lastName.$invalid">Last Name is required</span>
  							<br>
  							
  							<label>Email Id:</label>
  							<input type="email" class="form-control" name="emailId" data-ng-model="emailId" 
  							data-ng-pattern="/^[a-z0-9](\.?[a-z0-9]){4,}@uncc\.edu$/" placeholder="Enter Email ID" required/>
  							<span style="color:red" data-ng-show="signupForm.emailId.$touched && signupForm.emailId.$invalid">Email id is required</span>
  							<div data-ng-messages="signupForm.emailId.$error">
  								<div style="color:red" data-ng-message="pattern">It should be a valid UNCC email id</div>
  							</div>
  							<br>
  							
  							<label>Password:</label>
  							<input type="password" class="form-control" name="password" data-ng-model="password" 
  							data-ng-minlength=6 placeholder="Enter Password" required/>
  							<div data-ng-messages="signupForm.password.$error">
  								<div style="color:red" data-ng-message="minlength">Password should contain minimum of 6 characters</div>
  							</div>
  							<br>
  							
  							<label>Confirm Password:</label>
  							<input type="password" class="form-control" name="confirmPassword" data-ng-model="confirmPassword" placeholder="Re-enter Password" required/>
  							<br>
  							<button type="submit" class="btn btn-primary" data-ng-click="signup()">Sign Up</button>
  							<br>
      					</form>
      				</div>
    			</div>
    		</div>
    	</md-whiteframe>
      </div>
   </body>
</html>