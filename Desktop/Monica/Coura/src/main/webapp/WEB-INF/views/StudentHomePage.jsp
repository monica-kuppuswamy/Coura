<html lang="en">
<head>
<title>${message}</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/angular_material/1.0.0/angular-material.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-animate.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-aria.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-messages.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angular_material/1.0.0/angular-material.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-cookies.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="resources/css/StudentHomePage.css">
<script type="text/javascript" src="resources/js/StudentHome.js"></script>
</head>

<body data-ng-app="couraApplication">
	<div class="jumbotron">
		<div class="container text-center">
			<h1>Coura</h1>
			<p>Course Recommender</p>
		</div>
	</div>
	<div data-ng-controller="CourseController" data-ng-init="listCourses()">
		<div><center>Welcome {{welcomeUserName}}</center></div>
		<ul class="nav nav-tabs nav-justified">
			<li></li>
			<li class="active"><a type="button"
				data-ng-click="listCourses()">Courses</a></li>
			<li></li>
			<li><a href="/app/studentviewinstructors">Instructors</a></li>
			<li></li>
			<li><a href="/app/mycourses">My Courses</a></li>
			<li></li>
			<li><a type="button" data-ng-click="logout()"><span
					class="glyphicon glyphicon-log-out"></span> Log out</a></li>
		</ul>
		<div class="frameContainer" layout="row" layout-padding layout-wrap
			layout-fill style="padding-bottom: 32px;" ng-cloak>
			<div class="panel panel-primary">
				<div class="input-group-btn">
					<div class="btn-group pull-right" role="group">
						<div class="dropdown dropdown-lg">
							<button class="btn btn-primary dropdown-toggle"
								data-toggle="dropdown" aria-expanded="false">
								<span class="glyphicon glyphicon-search"></span>
							</button>
							<div id="myDIV" class="dropdown-menu dropdown-menu-right"
								role="menu">
								<form class="form-horizontal" role="form">
									<div class="form-group">
										<label for="contain">Course Name</label> <input
											class="form-control" type="text" data-ng-model="courseName"
											value="" />
									</div>
									<div class="form-group">
										<label for="contain">Course Number</label> <input
											class="form-control" type="text" data-ng-model="courseNumber"
											value="" />
									</div>
									<div class="form-group">
										<label for="contain">Area of Interest</label> <input
											class="form-control" type="text"
											data-ng-model="areaOfInterest" value="" />
									</div>
									<div class="form-group">
										<label for="contain">Instructor Name</label> <input
											class="form-control" type="text"
											data-ng-model="instructorName" value="" />
									</div>
									<button type="submit" class="btn btn-primary"
										data-ng-click="searchCourses()">
										<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
									</button>
								</form>
							</div>
						</div>
					</div>
				</div>
				<br> <br>
				<div data-ng-show="error" class="alert alert-danger">
					<strong>Error: </strong>{{errorMessage}}
				</div>
				<div class="course-grids">
					<div class="container">
						<div class="row">
							<ul class="thumbnails list-unstyled">
								<li class="col-md-3" data-ng-repeat="course in coursesList | filter: searchFor | startFrom:currentPage*pageSize | limitTo:pageSize">
									<a type="button" data-ng-click="getCourseDetails(course.courseNumber)">
										<div class="thumbnail" style="padding: 0">
											<div style="padding: 5px">
												<img alt="courses" style="width: 100%"
													src="resources/images/course.jpg">
											</div>
											<div class="caption">
												<h6>
													<b>{{course.courseName}}</b>
												</h6>
												<p>{{course.courseNumber}}</p>
											</div>
										</div>
									</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<br>
				<div class="table-pagination">
					<button class="btn btn-success btn-md" data-ng-disabled="currentPage == 0" data-ng-click="currentPage=currentPage-1">{{Previous}}</button>
    				{{currentPage + 1}}/{{numberOfPages()}}
    				<button class="btn btn-success btn-md" data-ng-disabled="currentPage >= getData().length / pageSize - 1" data-ng-click="currentPage = currentPage + 1">{{Next}}</button>
				</div>
				<br>
			</div>
			<b>Recently Viewed Courses</b>
			<div data-ng-controller="CourseController" data-ng-init="listMostRecentSearchedCourses()">
				<div data-ng-show="error" class="alert alert-danger">
					<strong>Error: </strong>{{errorMessage}}
				</div>
				<div class="mrsccourse-grids">
					<div class="container">
						<div class="row">
							<ul class="thumbnails list-unstyled">
								<li class="col-md-3" data-ng-repeat="course in coursesList">
									<a type="button" data-ng-click="getCourseDetails(course.courseNumber)">
										<div class="thumbnail" style="padding: 0">
											<div style="padding: 5px">
												<img alt="courses" style="width: 100%"
													src="resources/images/course.jpg">
											</div>
											<div class="caption">
												<h4>
													<small><b>{{course.courseName}}</b></small>
												</h4>
												<p>{{course.courseNumber}}</p>
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