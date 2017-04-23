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
  	  <link rel="stylesheet" type="text/css" href="resources/css/InstructorDetails.css">
  	  <script type="text/javascript" src="resources/js/InstructorDetails.js"></script>
   </head>
   <body data-ng-app="couraApplication">
   		<div class="jumbotron">
  			<div class="container text-center">
    			<h1>Coura</h1>      
    			<p>Course Recommender</p>
  			</div>
		</div>
		<div data-ng-controller="InstructorDisplayController" data-ng-init="listInstructorDetails()">
			<div class="panel panel-primary">
      			<div class="panel-body">
      				<a class="btn btn-success btn-md" data-ng-href="/app/studentviewinstructors">{{Previous}}</a>
      				
      				<h3><strong>Instructor Details : {{instructor[0].firstName}} {{instructor[0].lastName}}</strong> </h3>
      				<div class="well well-lg">
      					<div class="panel-heading" id="div-left">
      						<h4><strong>First Name: <small>{{instructor[0].firstName}}</small></strong></h4>
      						<h4><strong>Last Name: </strong> <small>{{instructor[0].lastName}}</small></h4>
      						<h4><strong>Email ID: </strong> <small>{{instructor[0].emailId}}</small></h4>
      						<h4><strong>Research Interests: </strong><h5>&nbsp;&nbsp;&nbsp;{{instructor[0].researchInterest}}</h5></h4>
      						<h4><strong>Courses taken by the instructor :</strong></h4>
      						<ul data-ng-repeat="course in courseList">
      							<li>{{course.courseNumber}} {{course.courseName}}</li>
      							<button type="button" class="btn btn-danger btn-sm" data-ng-click="getCourseId(course.courseNumber)" data-toggle="modal" data-target="#myModal">Rate Instructor</button>
      						</ul>
      					</div>
      					<div class="panel-heading" id="div-right">
      						<h4><strong>Overall Instructor Rating :</strong></h4>
      						<ul data-ng-repeat="course in courseList">
      						<h5><b>{{course.courseName}} :</b></h5>
      							<li>Quality of Teaching: 
      							<ul class="rating">
            					  	<li data-ng-repeat="n in [].constructor(5) track by $index">
                				  		<span data-ng-show="getQualityOfTeaching(course.id) > $index" class="filled">&#9733;</span>
                						<span data-ng-show="getQualityOfTeaching(course.id) <= $index">&#9733;</span>
            						</li>
        						</ul>
        						<br>
      							</li>
      							<li>Grading Style: 
      							<ul class="rating">
            					  	<li data-ng-repeat="n in [].constructor(5) track by $index">
                				  		<span data-ng-show="getGradingStyle(course.id) > $index" class="filled">&#9733;</span>
                						<span data-ng-show="getGradingStyle(course.id) <= $index">&#9733;</span>
            						</li>
        						</ul>
        						<br>
      							</li>
      							<li>Leniency: 
      							<ul class="rating">
            					  	<li data-ng-repeat="n in [].constructor(5) track by $index">
                				  		<span data-ng-show="getLeniency(course.id) > $index" class="filled">&#9733;</span>
                						<span data-ng-show="getLeniency(course.id) <= $index">&#9733;</span>
            						</li>
        						</ul>
        						<br>
      							</li>
      						</ul>
      					</div>
      				</div>
      				<div id="review-div">
      					<md-toolbar>
    						<div class="md-toolbar-tools">Instructor Reviews</div>
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
        							<h4 class="modal-title">Rate {{instructor[0].firstName}} {{instructor[0].lastName}}</h4>
      							</div>
      							<div class="modal-body">
      								Quality of Teaching :
        							<ul class="rating">
            							 <li data-ng-repeat="n in [].constructor(maxQualityOfTeachingRating) track by $index">
                						 	<span data-ng-click="rateQualityOfTeaching($index+1)" data-ng-show="ratedQualityOfTeaching > $index" class="filled">&#9733;</span>
                							<span data-ng-click="rateQualityOfTeaching($index+1)" data-ng-show="ratedQualityOfTeaching <= $index">&#9733;</span>
            							</li>
        							</ul>
        							<br>
        							Grading Style :
        							<ul class="rating">
            							<li data-ng-repeat="n in [].constructor(maxGradingStyleRating) track by $index">
                					 	    <span data-ng-click="rateGradingStyle($index+1)" data-ng-show="ratedGradingStyle > $index" class="filled">&#9733;</span>
                						    <span data-ng-click="rateGradingStyle($index+1)" data-ng-show="ratedGradingStyle <= $index">&#9733;</span>
            							</li>
        							</ul>
        							<br>
        							Leniency :
        							<ul class="rating">
            							<li data-ng-repeat="n in [].constructor(maxLeniencyRating) track by $index">
                					 	    <span data-ng-click="rateLeniency($index+1)" data-ng-show="ratedLeniency > $index" class="filled">&#9733;</span>
                						    <span data-ng-click="rateLeniency($index+1)" data-ng-show="ratedLeniency <= $index">&#9733;</span>
            							</li>
        							</ul>
      							</div>
      							<div class="modal-footer">
        							<button type="button" class="btn btn-success btn-sm" data-dismiss="modal" data-ng-click="insertRating()">Submit Rating</button>
      							</div>
   	 						</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>