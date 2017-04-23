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
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-cookies.js"></script>
  	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  	  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  	  <link rel="stylesheet" type="text/css" href="resources/css/StudentViewInstructors.css">
  	  <script type="text/javascript" src="resources/js/StudentViewInstructors.js"></script>
   </head>
    
   <body data-ng-app="couraApplication">
   		<div class="jumbotron">
  			<div class="container text-center">
    			<h1>Coura</h1>      
    			<p>Course Recommender</p>
  			</div>
		</div>
 		<div data-ng-controller="InstructorController" data-ng-init="listInstructors()">
   			<ul class="nav nav-tabs nav-justified">
      			<li></li>
      			<li><a href="/app/studenthome">Courses</a></li>
      			<li></li>
      			<li class="active"><a type="button" data-ng-click="listInstructors()">Instructors</a></li>
      			<li></li>
      			<li><a href="/app/mycourses">My Courses</a></li>
				<li></li>
      			<li><a href="/app/home"><span class="glyphicon glyphicon-log-out"></span> Log out</a></li>
    		</ul>
      		<div class="frameContainer" layout="row" layout-padding layout-wrap layout-fill style="padding-bottom: 32px;" ng-cloak>    		
      			<div class="panel panel-primary">	
                	<div class="input-group-btn">
                    	<div class="btn-group pull-right" role="group">
                        	<div class="dropdown dropdown-lg">
                            	<button class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><span class="glyphicon glyphicon-search"></span></button>
                            		<div id="myDIV" class="dropdown-menu dropdown-menu-right" role="menu">
                                		<form class="form-horizontal" role="form">
                                  			<div class="form-group">
                                    			<label for="contain">First Name</label>
                                    			<input class="form-control" type="text" data-ng-model="firstName" value="" />
                                  			</div>
                                  			<div class="form-group">
                                    			<label for="contain">Last Name</label>
                                    			<input class="form-control" type="text" data-ng-model="lastName" value="" />
                                  			</div>
                                  			<div class="form-group">
                                    			<label for="contain">Area of Interest</label>
                                    			<input class="form-control" type="text" data-ng-model="areaOfInterest" value="" />
                                  			</div>
                                  					<button type="submit" class="btn btn-primary" data-ng-click="searchInstructors()"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
                                		</form>
                            		</div>
                        		</div>
                    		</div>
                		</div>
    					<br>
    					<br>
    					<div data-ng-show="error" class="alert alert-danger"><strong>Error: </strong>{{errorMessage}}</div>
    					<div class="course-grids">
   							<div class="container">
   								<div class="row">
      								<ul class="thumbnails list-unstyled">
        								<li class="col-md-3" data-ng-repeat="instructor in instructorsList">
        									<a type="button" data-ng-click="getInstructorDetails(instructor.emailId)">
          										<div class="thumbnail" style="padding: 0">
            										<div style="padding:5px">
              											<img alt="instructors" style="width: 100%" src="resources/images/instructor.jpg">
            										</div>
            										<div class="caption">
              											<h6><b>{{instructor.firstName}}</b> <b>{{instructor.lastName}}</b></h6>
              											
            										</div>
          										</div>
          									</a>
        								</li>
      								</ul>
    							</div>
							</div>
						</div>
					</div>
				</div>
     		</div> 
   		</body>
	</html>