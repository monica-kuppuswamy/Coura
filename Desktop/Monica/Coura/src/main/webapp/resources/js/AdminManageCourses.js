var app = angular.module('couraApplication', ['ngMaterial', 'ngCookies']);
app.controller('CourseController', ['$scope', '$http', '$filter', '$cookies', function ($scope, $http, $filter, $cookies) {
	
	$scope.Previous = "<<";
	$scope.Next = ">>";
	$scope.currentPage = 0;
	$scope.pageSize = 7;
	$scope.searchFor = '';
        	 
	$scope.getData = function () {
		return $filter('filter')($scope.courseList, $scope.searchFor);
	}
        	 
	$scope.numberOfPages = function () {
		return Math.ceil($scope.getData().length/$scope.pageSize);                
	}
	
	$scope.addCourse = function () {
		
 				var courseId = Math.floor((Math.random() * 1000) + 1);
 				var course = {
 						id: courseId,
 						courseName: $scope.courseName,
 						courseNumber: $scope.courseNumber,
 						prerequisite: $scope.prerequisite,
 						description: $scope.courseDescription
 				};
 				var res = $http.post('/app/courseservice/savecourse', course);
 		    	res.success(function(data, status, headers, config) {
 		    		if (data.includes("Course added successfully.")) {
 		    			$scope.success = true;
 		    			$scope.successMessage = data;
 		    		} else {
 		    			$scope.error = true;
 		    			$scope.errorMessage = data;
 		    		}
 		    	});
	}
	
	$scope.listCourses = function () {
		$http.get("/app/courseservice/getcourses")
     		.then(function (response) {
     			$scope.courseList = response.data;
     		}); 
	}
	
	var courseId;
	$scope.deleteCourse = function (id) {
		courseId = id;
	}
        	 
	$scope.confirmDeletion = function () {
		$http({
			method : 'DELETE',
			url : '/app/courseservice/deletecourse/' + courseId
		}).success(function(data, status, headers, config) {
			$scope.listCourses();
		}).error(function(data, status, headers, config) {
		});
	}
	
	$scope.editCourse = function (id, courseNumber, courseName, prerequisite, description) {
		
		courseId = id;
		$scope.courseNumber = courseNumber;
		$scope.courseName = courseName;
		$scope.prerequisite = prerequisite;
		$scope.description = description;
	}
	
	$scope.confirmEdition = function() {
		
		
		// Hide failure or success message
       	$scope.error = false;
       	$scope.success = false;
            	
    	var courseDetails = {
    		id: courseId,
    		courseNumber: $scope.courseNumber,
    		courseName: $scope.courseName,
    		prerequisite: $scope.prerequisite,
    		description: $scope.description
    	};
    	
    	
    	
    	var res = $http.post('/app/courseservice/updatecourse', courseDetails);
    	res.success(function(data, status, headers, config) {
    		
    		if (data.includes("successfully updated.")) {
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
        start = +start;
        return input.slice(start);
    }
});