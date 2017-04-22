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
 		    		alert(data);
 		    		if (data.includes("Course Added Successfully")) {
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