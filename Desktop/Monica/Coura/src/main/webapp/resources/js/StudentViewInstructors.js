var app = angular.module('couraApplication', ['ngMaterial', 'ngCookies']);
app.controller('InstructorController', ['$scope', '$http', '$cookies', function ($scope, $http, $cookies) {   	
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
		var researchInterest = $scope.researchInterest;
		var courseName = $scope.courseName;
		
		$http.get("/app/instructorservice/searchinstructors?firstName=" + firstName + "&lastName=" + lastName + "&researchInterest=" + researchInterest + "&courseName=" + courseName)
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