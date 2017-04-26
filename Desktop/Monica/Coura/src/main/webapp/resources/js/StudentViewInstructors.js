var app = angular.module('couraApplication', ['ngMaterial', 'ngCookies']);
app.controller('InstructorController', ['$scope', '$http', '$filter', '$cookies', function ($scope, $http, $filter, $cookies) {
	
	$scope.Previous = "<<";
	$scope.Next = ">>";
	$scope.currentPage = 0;
	$scope.pageSize = 8;
	
	$scope.getData = function () {
		return $filter('filter')($scope.instructorsList, '');
	}
        	 
	$scope.numberOfPages = function () {
		return Math.ceil($scope.getData().length/$scope.pageSize);                
	}
	
	$scope.error = false;
	$scope.listInstructors = function () {
		$scope.error = false;
		$http.get("/app/instructorservice/getinstructors")
     		.then(function (response){
     			$scope.instructorsList = response.data;
     		}); 
	}
	
	$scope.getInstructorDetails = function (emailId) {
		$http.get("/app/instructorservice/getinstructorId/" + emailId)
 		.then(function (response){
 			var instructorId = response.data;
 			window.location.href = "/app/instructordetails?id=" + instructorId;
 		});
	}
	
	$scope.searchInstructors = function () {
		$scope.error = false;
		var firstName = $scope.firstName;
		var lastName = $scope.lastName;
		var areaOfInterest = $scope.areaOfInterest;
		
		
		$http.get("/app/courseservice/searchInstructors?firstName=" + firstName + "&lastName=" + lastName + "&areaOfInterest=" + areaOfInterest)
 		.then(function (response) {
 			if (response.data.length == 0) {
 				$scope.instructorsList = response.data;
 				$scope.error = true;
 				$scope.errorMessage = "No instructors to display.";
 			} else {
 				$scope.error = false;
 				$scope.instructorsList = response.data;
 			}
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