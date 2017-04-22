var app = angular.module('couraApplication', ['ngMaterial', , 'ngCookies']);
app.config(function($mdThemingProvider) {
	  $mdThemingProvider.theme('default')
	    .primaryPalette('red');
})
app.controller('CourseDisplayController', ['$scope', '$http', '$cookies', function ($scope, $http, $cookies) {
	$scope.listCourseDetails = function () {
	    
		$scope.registerStatus = "Enroll Now";
		$scope.buttonStatus = false;
		$scope.Previous = "<< Back"
		var queryString = window.location.search.split('=');
		var courseId = queryString[1];
		
		$http.get('/app/courseservice/getcourse/' + courseId).then(function (response) {
 			$scope.course = response.data;
 			$http.get("/app/courseservice/getinstructorforcourse/" + courseId).then(function (response){
 	 			$scope.instructorList = response.data;
 	 			$http.get("/app/ratingservice/getrating/" + courseId).then(function (response){
 		 			var overallRatings = response.data;
 		 			var td = 0;
 		 			var tu = 0;
 		 			for(var i = 0; i < overallRatings.length; i++) {
 		 				td += overallRatings[i].difficultyRating;
 		 				tu += overallRatings[i].usefulnessRating;
 		 			}
 		 			if (td != 0 ) {
 		 				$scope.difficulty = Math.round(td/overallRatings.length);
 		 			} else {
 		 				$scope.difficulty = 0;
 		 			}
 		 			if (tu != 0) {
 		 				$scope.usefulness = Math.round(tu/overallRatings.length);
 		 			} else {
 		 				$scope.usefulness = 0;
 		 			}
 		 			$http.get('/app/enrollservice/getenrolledcourses/' + $cookies.get('username')).then(function (response) {
 		 				var resultData = response.data;
 		 				for (var i = 0; i < resultData.length; i++) {
 		 					if(resultData[i].id == courseId) {
 		 		 				$scope.registerStatus = "Enrolled";
 		 		 				$scope.buttonStatus = true;
 		 						break;
 		 					}
 		 				}
 		 			});
 		 			$scope.getReviews();
 		 		});
 	 		}); 
 		});
	}
	
	$scope.getReviews = function () {
		$http.get('/app/reviewservice/getreview/' + parseInt(window.location.search.split('=')[1]))
				.then(function (response) {
					$scope.reviewList = response.data;
				});
	}
	
	$scope.postReview = function () {
		var reviewJson = {
		    courseId: parseInt(window.location.search.split('=')[1]),
		    userEmailId: $cookies.get('username'),
		    review: $scope.reviewString
		}
    	var res = $http.post('/app/reviewservice/insertreview', reviewJson);
    	res.success(function(data, status, headers, config) {
        	$scope.getReviews();
        	$scope.reviewString = "";
    	});
        res.error(function(data, status, headers, config) {
        	$scope.error = true;
        	$scope.errorMessage = "Your post was unsuccessful due to server error. Please rate again later.";
        });
	}
}]);

app.controller('RatingController', ['$scope', '$cookies', '$http', function ($scope, $cookies, $http) {
	$scope.setDefaultRating = function () {
		$scope.maxDifficultyRating = 5;
	    $scope.ratedDifficulty = 0;
		$scope.maxUsefulnessRating = 5;
	    $scope.ratedUsefulness = 0;
	}

    $scope.rateDifficulty = function (star) {
        $scope.ratedDifficulty = star;
    }
    
    $scope.rateUsefulness = function (star) {
        $scope.ratedUsefulness= star;
    }
    
    $scope.insertRating = function () {
    	var rating = {
    			userEmailId: $cookies.get('username'),
    			courseId: parseInt(window.location.search.split('=')[1]),
    			difficultyRating: $scope.ratedDifficulty,
    			usefulnessRating: $scope.ratedUsefulness
    	}
    	var res = $http.post('/app/ratingservice/insertrating', rating);
    	res.success(function(data, status, headers, config) {
    		$scope.success = true;
    		$scope.successMessage = "Your rating has been recorded.";
    	});
        res.error(function(data, status, headers, config) {
        	$scope.error = true;
        	$scope.errorMessage = "Rating was unsuccessful due to server error. Please rate again later.";
        });
    	$scope.setDefaultRating();
    }
    
	$scope.registerCourse = function () {
		var registrationdetails = {
			courseId: parseInt(window.location.search.split('=')[1]),
			userEmailId: $cookies.get('username'),
			instructorId: $scope.teacherName 
		}
    	var res = $http.post('/app/enrollservice/savedetails', registrationdetails);
    	res.success(function(data, status, headers, config) {
    		$scope.registerStatus = "Enrolled";
    		$scope.buttonStatus = true;
    		window.location.reload();
    	});
        res.error(function(data, status, headers, config) {
        	$scope.error = true;
        	$scope.errorMessage = "Registration was unsuccessful due to server error. Please rate again later.";
        });
	}
}]);