var app = angular.module('couraApplication', ['ngMaterial', 'ngMessages']);
app.controller('frameController', function  ($scope, $http) {
        	
    // POST request to save user details
    $scope.signup = function() {
        		
       	// Hide failure or success message
       	$scope.error = false;
       	$scope.success = false;
            	
    	var userDetails = {
    		firstName: $scope.firstName,
    		lastName: $scope.lastName,
    		emailId: $scope.emailId,
    		password: $scope.password
    	};
    	var res = $http.post('/app/signupservices/saveuser', userDetails);
    	res.success(function(data, status, headers, config) {
    		if (data.includes("successfully registered")) {
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
};
});