var app = angular.module('couraApplication', ['ngMaterial', 'ngCookies']);
app.controller('UserController', ['$scope', '$http', '$filter', '$cookies', function  ($scope, $http, $filter, $cookies) {
	$scope.Previous = "<<";
	$scope.Next = ">>";
	$scope.currentPage = 0;
	$scope.pageSize = 7;
	$scope.searchFor = '';
        	 
	$scope.getData = function () {
		return $filter('filter')($scope.usersList, $scope.searchFor);
	}
        	 
	$scope.numberOfPages = function () {
		return Math.ceil($scope.getData().length/$scope.pageSize);                
	}
        	 
	$scope.listUsers = function () {
		$http.get("/app/signupservices/getusers")
     		.then(function (response){
     			$scope.usersList = response.data;
     		}); 
	}
        	 
	var emailID;
	$scope.deleteUser = function (userEmail) {
		emailID = userEmail;
	}
        	 
	$scope.confirmDeletion = function () {
		$http({
			method : 'DELETE',
			url : '/app/signupservices/deleteuser/' + emailID
		}).success(function(data, status, headers, config) {
			$scope.listUsers();
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
        start = +start; //parse to int
        return input.slice(start);
    }
});