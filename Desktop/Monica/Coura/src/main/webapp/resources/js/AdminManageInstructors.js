var app = angular.module('couraApplication', ['ngMaterial', 'ngCookies']);
app.controller('InstructorController', ['$scope', '$http', '$filter', '$cookies', function  ($scope, $http, $filter, $cookies) {
	
	$scope.Previous = "<<";
	$scope.Next = ">>";
	$scope.currentPage = 0;
	$scope.pageSize = 7;
	$scope.searchFor = '';
        	 
	$scope.getData = function () {
		return $filter('filter')($scope.instructorList, $scope.searchFor);
	}
        	 
	$scope.numberOfPages = function () {
		return Math.ceil($scope.getData().length/$scope.pageSize);                
	}
	
	$scope.addInstructor = function () {
		
		var result = $http.get("/app/courseservice/getcourseId/" + $scope.coursesTaught);
 		result.then(function (response) {
 			if(response.data != null) {
 				var instructorId = Math.floor((Math.random() * 1000) + 1);
 				var instructorDetails = {
 					instructor : {
 						id: instructorId,
 						firstName: $scope.firstName,
 						lastName: $scope.lastName,
 						emailId: $scope.emailId,
 						researchInterest: $scope.interests
 					},
 						
 					courseInstructor : {
 						courseId: response.data,
 						instructorId: instructorId
 					}
 				};
 				var res = $http.post('/app/instructorservice/addinstructor', instructorDetails);
 		    	res.success(function(data, status, headers, config) {
 		    		alert(data);
 		    		if (data.includes("Instructor Added Successfully")) {
 		    			$scope.success = true;
 		    			$scope.successMessage = data;
 		    		} else {
 		    			$scope.error = true;
 		    			$scope.errorMessage = data;
 		    		}
 		    	});
 			} else {
	    		$scope.error = true;
		    	$scope.errorMessage = data;
 			}
 		});
	}
	
	$scope.listInstructors = function () {
		$http.get("/app/instructorservice/getinstructors")
     		.then(function (response){
     			$scope.instructorList = response.data;
     		}); 
	}
	
	var instructorId;
	$scope.deleteInstructor = function (id) {
		instructorId = id;
	}
        	 
	$scope.confirmDeletion = function () {
		$http({
			method : 'DELETE',
			url : '/app/instructorservice/deleteinstructor/' + instructorId
		}).success(function(data, status, headers, config) {
			$scope.listInstructors();
		}).error(function(data, status, headers, config) {
		});
	}
	
	$scope.editInstructor = function (id, firstName, lastName, emailId, researchInterest) {
		
		instructorId = id;
		$scope.firstName = firstName;
		$scope.lastName = lastName;
		$scope.emailId = emailId;
		$scope.researchInterest = researchInterest;
	}
	
	$scope.confirmEdition = function() {
		
		// Hide failure or success message
       	$scope.error = false;
       	$scope.success = false;
            	
    	var instructorDetails = {
    		id: instructorId,
    		firstName: $scope.firstName,
    		lastName: $scope.lastName,
    		emailId: $scope.emailId,
    		researchInterest: $scope.researchInterest
    	};
    	
    	var res = $http.post('/app/instructorservice/updateinstructor', instructorDetails);
    	res.success(function(data, status, headers, config) {
    		if (data.includes("successfully updated")) {
    			$scope.success = true;
    			$scope.successMessage = data;
    		} else {
    			$scope.error = true;
    			$scope.errorMessage = data;
    		}
    	});
        res.error(function(data, status, headers, config) {
        	$scope.error = true;
        	$scope.errorMessage = "All required fields should be filled as per the criteria";
        });
	}
	
	$scope.logout = function () {
		$http.get("/app/loginservice/logout").then(function (response) {
			window.location.href = "/app/login";
		});
	}
}]);

app.filter('startFrom', function() {
	return function(input, start) {
        start = +start; //parse to int
        return input.slice(start);
    }
});