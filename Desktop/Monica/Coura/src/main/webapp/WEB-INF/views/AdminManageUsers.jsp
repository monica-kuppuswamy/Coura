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
  	  <link rel="stylesheet" type="text/css" href="resources/css/AdminManageUsers.css">
  	  <script type="text/javascript" src="resources/js/AdminManageUsers.js"></script>
   </head>
   <body data-ng-app="couraApplication">
   		<div class="jumbotron">
  			<div class="container text-center">
    			<h1>Coura</h1>      
    			<p>Course Recommender</p>
  			</div>
		</div>
 		<div data-ng-controller="UserController" data-ng-init="listUsers()">
   		<ul class="nav nav-tabs nav-justified">
      		<li></li>
      		<li class="active"><a type="button" data-ng-click="listUsers()">Manage Users</a></li>
      		<li></li>
      		<li><a href ="/app/adminmanageinstructors">Manage Instructors</a></li>
      		<li></li>
      		<li><a href ="/app/adminmanagecourses">Manage Courses</a></li>
      		<li></li>
      		<li><a type="button" data-ng-click="logout()"><span class="glyphicon glyphicon-log-out"></span> Log out</a></li>
    	</ul>
      	<div class="frameContainer" layout="row" layout-padding layout-wrap layout-fill style="padding-bottom: 32px;" ng-cloak>
    		<div class="panel panel-primary">
    			<div class="panel-heading">List Of Users</div>
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
                   						<th>First Name</th>
                    					<th>Last Name</th>
                     					<th>Email</th>
                       					<th>Delete</th>
                       				</tr>
                   				</thead>
    							<tbody>
    								<tr data-ng-repeat="user in usersList | filter: searchFor | startFrom:currentPage*pageSize | limitTo:pageSize">
    									<td>{{user.firstName}}</td>
    									<td>{{user.lastName}}</td>
    									<td>{{user.emailId}}</td>
    									<td><p data-placement="top" data-toggle="tooltip" title="Delete"><button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" data-ng-click="deleteUser(user.emailId)"><span class="glyphicon glyphicon-trash"></span></button></p></td>
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
	 
 		<div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
      		<div class="modal-dialog">
    			<div class="modal-content">
          			<div class="modal-header">
        				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
        				<h4 class="modal-title custom_align" id="Heading">Delete this entry?</h4>
      				</div>
          			<div class="modal-body">
       					<div class="alert alert-danger"><span class="glyphicon glyphicon-warning-sign"></span> Are you sure you want to delete this Record?</div>
       				</div>
        			<div class="modal-footer ">
        				<button type="button" class="btn btn-success" data-dismiss="modal" data-ng-click="confirmDeletion()"><span class="glyphicon glyphicon-ok-sign"></span> Yes</button>
        				<button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> No</button>
      				</div>
        		</div>
  			</div>
    	</div>
     </div>
     </div>
   </body>
</html>