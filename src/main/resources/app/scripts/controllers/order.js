'use strict';

/**
 * @ngdoc function
 * @name resourcesApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the resourcesApp

angular.module('resourcesApp').factory('CartService', function(){
	return {
	    cart: {
	      firstName: '',
	      lastName: ''
	    }
	    // Other methods or objects can go here
	  };

	});
 */
angular.module('resourcesApp')
  .controller('OrderCtrl', function ($scope,$resource,$location, $routeParams) {
	 $scope.id = $routeParams.id;
	 $scope.order = $resource('/api/order/'+$scope.id).get();
	 $scope.confirm= function(){
		 $resource('/api/order').save($scope.order);
		 
	 }
	 $scope.orders = $resource('/api/order').query();
  });
