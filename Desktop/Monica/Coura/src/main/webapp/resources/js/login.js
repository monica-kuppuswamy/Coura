var app = angular.module('couraApplication', ['ngMaterial', 'ngCookies']);
app.controller('loginController', ['$scope', '$http', '$cookies', function ($scope, $http, $cookies) {
	
	$scope.showMessage = false;
	$scope.login = function () {
        $http({
     		method : 'GET',
     		url : '/app/loginservice/authLogin/' + $scope.emailId + '/' + $scope.password
     	}).success(function(data, status, headers, config) {
     		if (data.includes("Valid User")) {
     			$cookies.put('username', $scope.emailId);
     			if($cookies.get('username').includes('admin')) {
     				window.location.href = "/app/adminmanageusers";
     			} else {
     				window.location.href = "/app/studenthome";
     			}
     		} else {
     			$scope.showMessage = true;
     		}
     	}).error(function(data, status, headers, config) {
     	});
     } 
}]);