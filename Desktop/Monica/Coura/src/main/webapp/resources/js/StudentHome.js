var app = angular.module('couraApplication', ['ngMaterial', 'ngCookies']);
app.controller('CourseController', ['$scope', '$http', '$cookies', function ($scope, $http, $cookies) {   	
	$scope.error = false;
	$scope.listCourses = function () {
		$scope.error = false;
		$http.get("/app/courseservice/getcourses")
     		.then(function (response){
     			$scope.coursesList = response.data;
     		}); 
	}
	
	$scope.getCourseDetails = function (courseNumber) {
		$http.get("/app/courseservice/getcourseId/" + courseNumber)
 		.then(function (response){
 			var courseId = response.data;
 			window.location.href = "/app/coursedetails?id=" + courseId;
 		});
	}
	
	$scope.searchCourses = function () {
		$scope.error = false;
		var courseNumber = $scope.courseNumber;
		var courseName = $scope.courseName;
		var areaOfInterest = $scope.areaOfInterest;
		var instructorName = $scope.instructorName;
		
		$http.get("/app/courseservice/searchcourses?courseNumber=" + courseNumber + "&courseName=" + courseName + "&areaOfInterest=" + areaOfInterest + "&instructorName=" + instructorName)
 		.then(function (response) {
 			if (response.data.length == 0) {
 				$scope.coursesList = response.data;
 				$scope.error = true;
 				$scope.errorMessage = "No courses to display.";
 			} else {
 				$scope.error = false;
 				$scope.coursesList = response.data;
 			}
 		}); 
	}
	
	$scope.logout = function () {
		$http.get("/app/loginservice/logout").then(function (response) {
			window.location.href = "/app/login";
		});
	}
	
	
	$scope.listMostRecentSearchedCourses = function() {
		$http.get("/app/courseservice/getMostRecentlySearchedCourses")
			.then(function(response){
			$scope.coursesList=response.data;
		});
	}

}]);

