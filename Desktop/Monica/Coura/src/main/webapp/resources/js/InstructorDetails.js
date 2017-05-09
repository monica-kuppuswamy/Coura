var app = angular.module('couraApplication', ['ngMaterial', , 'ngCookies'])
.service('sharedProperties', function () {
    var courseId;

    return {
        getCourseId: function () {
            return courseId;
        },
        setCourseId: function(value) {
        	courseId = value;
        }
    };
});

app.config(function($mdThemingProvider) {
	  $mdThemingProvider.theme('default')
	    .primaryPalette('red');
})
app.controller('InstructorDisplayController', ['$scope', '$http', '$cookies', 'sharedProperties', function ($scope, $http, $cookies, sharedProperties) {
	
	var OverallRatings;
	
	$scope.listInstructorDetails = function () {
		$scope.Previous = "<< Back"
		var queryString = window.location.search.split('=');
		var instructorId = queryString[1];
		
		$http.get("/app/instructorservice/getinstructor/" + instructorId)
 		.then(function (response){
 			$scope.instructor = response.data;
 			$http.get("/app/instructorratingservice/getrating/" + instructorId)
 	 		.then(function (response){
 	 			OverallRatings = response.data;
 	 			$http.get("/app/instructorservice/getcourseforinstructor/" + instructorId)
 		 		.then(function (response){
 		 			$scope.courseList = response.data;
 		 			$scope.getReviews();
 		 		});
 	 		}); 
 		});
	}
	
	$scope.getQualityOfTeaching = function(cId) {
		
		var tq = 0;
		var qualityOfTeaching;
		var length = 0;
		
		for(var i = 0; i < OverallRatings.length; i++) {
			if(cId == OverallRatings[i].courseId) {
				tq += OverallRatings[i].qualityOfTeachingRating;
				length++;
			}
		}
		if (tq != 0 ) {
			qualityOfTeaching = Math.round(tq/length);
		} else {
			qualityOfTeaching = 0;
		}
		
		return qualityOfTeaching;
	}
	
	$scope.getGradingStyle = function(cId) {
		
		var tg = 0;
		var gradingStyle;
		var length = 0;
		
		for(var i = 0; i < OverallRatings.length; i++) {
			if(cId == OverallRatings[i].courseId) {
				tg += OverallRatings[i].gradingStyleRating;
				length++;
			}
		}
		if (tg != 0 ) {
			gradingStyle = Math.round(tg/length);
		} else {
			gradingStyle = 0;
		}
		
		return gradingStyle;
	}
	
	$scope.getLeniency = function(cId) {
		
		var tl = 0;
		var leniency;
		var length = 0;
		
		for(var i = 0; i < OverallRatings.length; i++) {
			if(cId == OverallRatings[i].courseId) {
				tl += OverallRatings[i].leniencyRating;
				length++;
			}
		}
		if (tl != 0 ) {
			leniency = Math.round(tl/length);
		} else {
			leniency = 0;
		}
		
		return leniency;
	}
	
	$scope.getReviews = function () {
		$http.get("/app/instructorreviewservice/getreview/" + parseInt(window.location.search.split('=')[1]))
			.then(function (response) {
				$scope.reviewList = response.data;
			});
	}
	
	$scope.getCourseId = function (courseNumber) {
		$http.get("/app/courseservice/getcourseId/" + courseNumber)
			.then(function (response) {
				sharedProperties.setCourseId(response.data);
			});
	}
	
	$scope.postReview = function () {
		var reviewJson = {
		    instructorId: parseInt(window.location.search.split('=')[1]),
		    userEmailId: $cookies.get('username'),
		    review: $scope.reviewString
		}
    	var res = $http.post('/app/instructorreviewservice/insertreview', reviewJson);
    	res.success(function(data, status, headers, config) {
        	$scope.getReviews();
        	$scope.reviewString = "";
    	});
        res.error(function(data, status, headers, config) {
        	$scope.error = true;
        	$scope.errorMessage = "Your post was unsuccessful due to server error. Please review again later.";
        });
	}
}]);

app.controller('RatingController', ['$scope', '$cookies', '$http', 'sharedProperties', function ($scope, $cookies, $http, sharedProperties) {
	$scope.setDefaultRating = function () {
		$scope.maxQualityOfTeachingRating = 5;
	    $scope.ratedQualityOfTeaching = 0;
		$scope.maxGradingStyleRating = 5;
	    $scope.ratedGradingStyle = 0;
	    $scope.maxLeniencyRating = 5;
	    $scope.ratedLeniency = 0;
	}

    $scope.rateQualityOfTeaching = function (star) {
        $scope.ratedQualityOfTeaching = star;
    }
    
    $scope.rateGradingStyle = function (star) {
        $scope.ratedGradingStyle= star;
    }
    
    $scope.rateLeniency = function (star) {
        $scope.ratedLeniency= star;
    }
    
    $scope.insertRating = function () {
    	
    	var rating = {
    			userEmailId: $cookies.get('username'),
    			instructorId: parseInt(window.location.search.split('=')[1]),
    			qualityOfTeachingRating: $scope.ratedQualityOfTeaching,
    			gradingStyleRating: $scope.ratedGradingStyle,
    			leniencyRating: $scope.ratedLeniency,
    			courseId: sharedProperties.getCourseId()
    	}

    	var res = $http.post('/app/instructorratingservice/insertrating', rating);
    	res.success(function(data, status, headers, config) {
    		window.location.reload();
    		$scope.success = true;
    		$scope.successMessage = "Your rating has been recorded.";
    	});
        res.error(function(data, status, headers, config) {
        	$scope.error = true;
        	$scope.errorMessage = "Rating was unsuccessful due to server error. Please rate again later.";
        });
    	$scope.setDefaultRating();
    }
}]);