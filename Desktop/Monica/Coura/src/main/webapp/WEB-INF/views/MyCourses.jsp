<html lang="en" >
   <head>
   	  <title>${message}</title>
   	  <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
      <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/angular_material/1.0.0/angular-material.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-animate.min.js"></script>
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-aria.min.js"></script>
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-messages.min.js"></script>
      <script src="https://ajax.googleapis.com/ajax/libs/angular_material/1.0.0/angular-material.min.js"></script>
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-cookies.js"></script>
  	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  	  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  	  <link rel="stylesheet" type="text/css" href="resources/css/MyCourses.css">
  	  <script type="text/javascript" src="resources/js/MyCourses.js"></script>
   </head>
    
   <body data-ng-app="couraApplication">
   		<div class="jumbotron">
  			<div class="container text-center">
    			<h1>Coura</h1>      
    			<p>Course Recommender</p>
  			</div>
		</div>
 		<div data-ng-controller="MyCourseController" data-ng-init="listMyCourses()">
   			<ul class="nav nav-tabs nav-justified">
      			<li></li>
      			<li><a href="/app/studenthome">Courses</a></li>
      			<li></li>
      			<li><a href ="/app/studentviewinstructors">Instructors</a></li>
      			<li></li>
      			<li class="active"><a type="button" data-ng-click="listMyCourses()">My Courses</a></li>
      			<li></li>
      			<li><a type="button" data-ng-click="logout()"><span class="glyphicon glyphicon-log-out"></span> Log out</a></li>
    		</ul>
      		<div class="frameContainer" layout="row" layout-padding layout-wrap layout-fill style="padding-bottom: 32px;" ng-cloak>    		
      			<div class="panel panel-primary">	
      				<br>
      				<br>
      				<div data-ng-show="error" class="alert alert-danger"><strong>Error: </strong>{{errorMessage}}</div>
    				<div class="course-grids">
   						<div class="container">
   							<div class="row">
      							<ul class="thumbnails list-unstyled">
        							<li class="col-md-3" data-ng-repeat="course in coursesList">
          								<div class="thumbnail" style="padding: 0">
            								<div style="padding:5px">
              									<img alt="courses" style="width: 100%" src="resources/images/course.jpg">
            								</div>
            								<div class="caption">
              									<h6><b>{{course.courseName}}</b></h6>
              									<p>{{course.courseNumber}}</p>
              									<button type="button" class="btn btn-success" data-ng-click="unEnrollCourse(course.id)">Unenroll</button>
            								</div>
          								</div>
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