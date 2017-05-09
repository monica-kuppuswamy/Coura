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
  	  <link rel="stylesheet" type="text/css" href="resources/css/AdminManageInstructors.css">
  	  <script type="text/javascript" src="resources/js/AdminManageInstructors.js"></script>     
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
      		<li><a href ="/app/adminmanageusers">Manage Users</a></li>
      		<li></li>
      		<li class="active"><a type="button" data-ng-click="listInstructors()">Manage Instructors</a></li>
      		<li></li>
      		<li><a href ="/app/adminmanagecourses">Manage Courses</a></li>
      		<li></li>
      		<li><a type="button" data-ng-click="logout()"><span class="glyphicon glyphicon-log-out"></span> Log out</a></li>
    	</ul>
    	<div data-ng-show="success" class="alert alert-success alert-dismissable">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			{{successMessage}}
		</div>
        <div data-ng-show="error" class="alert alert-danger alert-dismissable">
        	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
        	<strong>Error: </strong>{{errorMessage}}
        </div>
      	<div class="frameContainer" layout="row" layout-padding layout-wrap layout-fill style="padding-bottom: 32px;" ng-cloak>
         	<div class="panel panel-primary" id="panel-one">
    			<div class="panel-heading">List Of Instructors</div>
    	    	<div class="form-group">
    				<input type="text" data-ng-model="searchFor" class="search form-control" placeholder="What you looking for?">
				</div>
				<span class="counter pull-right"></span>
				<br>
				<div class="panel-heading">Records per page</div>
    	    	<div class="form-group">
    				<select data-ng-model="pageSize" id="pageSize" class="form-control">
        				<option value="5">5</option>
        				<option value="6">6</option>
        				<option value="7">7</option>
        				<option value="8">8</option>
     				</select>
				</div>
				<span class="counter pull-right"></span>
			</div>
			<br>
			<div class="container">
				<div class="row">
        			<div class="col-md-12">
        				<div class="table-responsive">
             		 		<table id="mytable" class="table table-bordred table-striped">
                   				<thead>
                   					<tr>
                   						<th>Instructor Name</th>
                    					<th>Email ID</th>
                     					<th>Research Interests</th>
                      					<th>Edit</th>
                       					<th>Delete</th>
                       				</tr>
                   				</thead>
    							<tbody>
    								<tr data-ng-repeat="instructor in instructorList | filter: searchFor | startFrom:currentPage*pageSize | limitTo:pageSize">
    									<td>{{instructor.firstName}} {{instructor.lastName}}</td>
    									<td>{{instructor.emailId}}</td>
    									<td>{{instructor.researchInterest}}</td>
    									<td><p data-placement="top" data-toggle="tooltip" title="Edit"><button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" data-target="#edit" data-ng-click="editInstructor(instructor.id, instructor.firstName, instructor.lastName, instructor.emailId, instructor.researchInterest)"><span class="glyphicon glyphicon-pencil"></span></button></p></td>
   	 									<td><p data-placement="top" data-toggle="tooltip" title="Delete"><button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" data-ng-click="deleteInstructor(instructor.id)"><span class="glyphicon glyphicon-trash"></span></button></p></td>
    								</tr> 
    							</tbody>
							</table>
                		</div>
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
			<div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
      			<div class="modal-dialog">
    				<div class="modal-content">
          				<div class="modal-header">
        					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
        					<h4 class="modal-title custom_align" id="Heading">Edit Instructor Detail</h4>
     					 </div>
          				 <div class="modal-body">
          				 	<div class="form-group">
        						<input class="form-control " type="text" data-ng-model="firstName" placeholder="Instructor First Name">
        					</div>
        					<div class="form-group">
        						<input class="form-control " type="text" data-ng-model="lastName" placeholder="Instructor First Name">
        					</div>
        						<div class="form-group">
        						<input class="form-control " type="text" data-ng-model="emailId" placeholder="Email ID">
        					</div>
        	 				<div class="form-group">
        						<input class="form-control " type="text" data-ng-model="researchInterest" placeholder="Reasearch Interests">
        					</div>
      					</div>
          				<div class="modal-footer ">
        					<button type="button" class="btn btn-warning btn-lg" style="width: 100%;" data-dismiss="modal" data-ng-click="confirmEdition()"><span class="glyphicon glyphicon-ok-sign"></span>Update</button>
      					</div>
        			</div>
  				</div>
    		</div>
    
    		<div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
      			<div class="modal-dialog">
    				<div class="modal-content">
          				<div class="modal-header">
        					<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
        					<h4 class="modal-title custom_align" id="Heading">Delete this entry?</h4>
      					</div>
          				<div class="modal-body"> 
       						<div class="alert alert-danger"><span class="glyphicon glyphicon-warning-sign"></span> Are you sure you want to delete this Instructor?</div>
       					</div>
        				<div class="modal-footer ">
        					<button type="button" class="btn btn-success" data-dismiss="modal" data-ng-click="confirmDeletion()"><span class="glyphicon glyphicon-ok-sign"></span> Yes</button>
        					<button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> No</button>
      					</div>
        			</div>
  				</div>
    		</div>
      		
      		<div class="add-form">
      		<div class="panel panel-primary" id="panel-two">
    			<div class="panel-heading">Add A New Instructor</div>
      			<div class="panel-body">
      				<form name="addInstructorForm" class="form-horizontal">
      					<label>First Name:</label>
  						<input type="text" class="form-control" name="firstName" data-ng-model="firstName" placeholder="First Name" required/>
  						<span style="color:red" data-ng-show="addInstructorForm.firstName.$touched && addInstructorForm.firstName.$invalid">This is a required field</span>
  						<br>
  						
  						<label>Last Name:</label>
  						<input type="text" class="form-control" name="lastName" data-ng-model="lastName" placeholder="Last Name" required/>
  						<span style="color:red" data-ng-show="addInstructorForm.lastName.$touched && addInstructorForm.lastName.$invalid">This is a required field</span>
  						<br>
  						
  						<label>Email ID:</label>
  						<input type="text" class="form-control" name="emailId" data-ng-model="emailId" placeholder="Email ID" required/>
  						<span style="color:red" data-ng-show="addInstructorForm.emailId.$touched && addInstructorForm.emailId.$invalid">This is a required field</span>
  						<br>
  						
  						<label>Course Taken:</label>
  						<input type="text" class="form-control" name="coursesTaught" data-ng-model="coursesTaught" placeholder="Enter course number of the course" required/>
  						<span style="color:red" data-ng-show="addInstructorForm.coursesTaught.$touched && addInstructorForm.coursesTaught.$invalid">This is a required field</span>
  						<br>
  						
  						<label>Research Interests:</label>
  						<input type="text" class="form-control" name="interests" data-ng-model="interests" placeholder="Research Interests" required/>
  						<span style="color:red" data-ng-show="addInstructorForm.interests.$touched && addInstructorForm.interests.$invalid">This is a required field</span>
  						<br>
  						
  						<button type="submit" class="btn btn-primary" data-ng-click="addInstructor()">Submit</button>
      				</form>
      			</div>
    		</div>
    		</div>
      	</div>
      	</div>
   </body>
</html>