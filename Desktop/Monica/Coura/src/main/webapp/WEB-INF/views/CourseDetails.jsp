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
  	  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-cookies.js"></script>
  	  <link rel="stylesheet" type="text/css" href="resources/css/CourseDetails.css">
  	  <script type="text/javascript" src="resources/js/CourseDetails.js"></script>
   </head>
   <body data-ng-app="couraApplication">
   		<div class="jumbotron">
  			<div class="container text-center">
    			<h1>Coura</h1>      
    			<p>Course Recommender</p>
  			</div>
		</div>
		<div data-ng-controller="CourseDisplayController" data-ng-init="listCourseDetails()">
			<div class="panel panel-primary">
      			<div class="panel-body">
      				<a class="btn btn-success btn-md" data-ng-href="/app/studenthome">{{Previous}}</a>
      				
      				<h3><strong>Course Details : {{course[0].courseName}}</strong> </h3>
      				<div class="well well-lg">
      					<div class="panel-heading" id="div-left">
      						<h4><strong>Course Name: <small>{{course[0].courseName}}</small></strong></h4>
      						<h4><strong>Course Number: </strong> <small>{{course[0].courseNumber}}</small></h4>
      						<h4><strong>Course Prerequisite: </strong> <small>{{course[0].prerequisite}}</small></h4>
      						<h4><strong>About this course: </strong><h5>&nbsp;&nbsp;&nbsp;{{course[0].description}}</h5></h4>
      					</div>
      					<div class="panel-heading" id="div-right">
      						<h4><strong>Instructors for the course :</strong></h4>
      						<ul>
      							<li data-ng-repeat="instructor in instructorList">
      							{{instructor.firstName}} {{instructor.lastName}}
      							</li>
      						</ul>
      						<br>
      						<h4><strong>Overall Course Rating</strong></h4>
      						<ul>
      							<li>Difficulty: 
      							<ul class="rating">
            					  	<li data-ng-repeat="n in [].constructor(5) track by $index">
                				  		<span data-ng-show="difficulty > $index" class="filled">&#9733;</span>
                						<span data-ng-show="difficulty <= $index">&#9733;</span>
            						</li>
        						</ul>
        						<br>
      							</li>
      							<li>Usefulness: 
      							<ul class="rating">
            					  	<li data-ng-repeat="n in [].constructor(5) track by $index">
                				  		<span data-ng-show="usefulness > $index" class="filled">&#9733;</span>
                						<span data-ng-show="usefulness <= $index">&#9733;</span>
            						</li>
        						</ul>
        						<br>
      							</li>
      						</ul>
      					</div>
      					<button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#myModal">Rate Course</button>
      					&nbsp;&nbsp;&nbsp;&nbsp;
      					<button type="button" data-ng-disabled="buttonStatus" class="btn btn-success btn-sm" data-toggle="modal" data-target="#myRegisterModal">{{registerStatus}}</button>
      				</div>
      				<div id="review-div">
      					<md-toolbar>
    						<div class="md-toolbar-tools">Course Reviews</div>
  						</md-toolbar>
  						<md-list layout-padding>
  							<md-list-item class="md-3-line">
            					<img data-ng-src="resources/images/user.png" class="md-avatar" alt="user">
            					<div class="md-list-item-text">
            					<textarea data-ng-model="reviewString"></textarea>&nbsp;&nbsp;
            					<button type="button" class="btn btn-success btn-sm" data-ng-click="postReview()">Post Review</button>
            					</div>
        					</md-list-item>
        					<md-list-item class="md-3-line" data-ng-repeat = "review in reviewList">
            					<img data-ng-src="resources/images/user.png" class="md-avatar" alt="user">
            					<div class="md-list-item-text">
             						<h3>{{review.userEmailId}}</h3>
             						<h4>{{review.review}}</h4>
            					</div>
        					</md-list-item>
      					</md-list>	
      				</div>
      			</div>
      			<div data-ng-controller="RatingController" data-ng-init="setDefaultRating()">
      				<div data-ng-show="success" class="alert alert-success">{{successMessage}}</div>
         			<div data-ng-show="error" class="alert alert-danger"><strong>Error: </strong>{{errorMessage}}</div>
      				<div id="myModal" class="modal fade" role="dialog">
  						<div class="modal-dialog">
    						<div class="modal-content">
      							<div class="modal-header">
        							<button type="button" class="close" data-dismiss="modal" data-ng-click="setDefaultRating()">&times;</button>
        							<h4 class="modal-title">Rate for {{course[0].courseNumber}}</h4>
      							</div>
      							<div class="modal-body">
      								Difficulty :
        							<ul class="rating">
            							 <li data-ng-repeat="n in [].constructor(maxDifficultyRating) track by $index">
                						 	<span data-ng-click="rateDifficulty($index+1)" data-ng-show="ratedDifficulty > $index" class="filled">&#9733;</span>
                							<span data-ng-click="rateDifficulty($index+1)" data-ng-show="ratedDifficulty <= $index">&#9733;</span>
            							</li>
        							</ul>
        							<br>
        							Usefulness :
        							<ul class="rating">
            							<li data-ng-repeat="n in [].constructor(maxUsefulnessRating) track by $index">
                					 	    <span data-ng-click="rateUsefulness($index+1)" data-ng-show="ratedUsefulness > $index" class="filled">&#9733;</span>
                						    <span data-ng-click="rateUsefulness($index+1)" data-ng-show="ratedUsefulness <= $index">&#9733;</span>
            							</li>
        							</ul>
      							</div>
      							<div class="modal-footer">
        							<button type="button" class="btn btn-success btn-sm" data-dismiss="modal" data-ng-click="insertRating()">Submit Rating</button>
      							</div>
   	 						</div>
						</div>
					</div>
					<div id="myRegisterModal" class="modal fade" role="dialog">
  						<div class="modal-dialog">
    						<div class="modal-content">
      							<div class="modal-header">
        							<button type="button" class="close" data-dismiss="modal" data-ng-click="setDefaultRating()">&times;</button>
        							<h4 class="modal-title">Enroll for {{course[0].courseNumber}}</h4>
      							</div>
      							<div class="modal-body">
      								<h5>Select your instructor:</h5>
      								<br>
      								<div data-ng-repeat="instructor in instructorList">
  										<input type="radio" name="instructor" data-ng-model="$parent.teacherName" data-ng-value="instructor.id">
  										<label for="radio">{{instructor.firstName}} {{instructor.lastName}}</label>
      								</div>
								</div>
      							<div class="modal-footer">
        							<button type="button" class="btn btn-success btn-sm" data-dismiss="modal" data-ng-click="registerCourse()">Enroll</button>
      							</div>
   	 						</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>