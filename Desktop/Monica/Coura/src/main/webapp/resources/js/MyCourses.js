var app = angular.module('couraApplication', ['ngMaterial', 'ngCookies']);
app.controller('MyCourseController', ['$scope', '$http', '$cookies', function ($scope, $http, $cookies) { 
	$scope.error = false;
	$scope.listMyCourses = function () {
		$scope.error = false;
		$http.get('/app/enrollservice/getenrolledcourses/' + $cookies.get('username')).then(function (response) {
     			$scope.coursesList = response.data;
     			if($scope.courseList.length == 0) {
     				$scope.error = true;
     				$scope.errorMessage = "You are not currently enrolled to any course."
     			}
     	}); 
	}
	
	$scope.unEnrollCourse = function (courseId) {
		$http({
			method : 'DELETE',
			url : '/app/enrollservice/unenrollcourse/' + $cookies.get('username') + '/' + courseId
		}).success(function(data, status, headers, config) {
			$scope.listMyCourses();
		}).error(function(data, status, headers, config) {
		});
	}
	
	$scope.logout = function () {
		$http.get("/app/loginservice/logout").then(function (response) {
			window.location.href = "/app/login";
		});
	}
}]);