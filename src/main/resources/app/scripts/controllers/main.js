'use strict';

/**
 * @ngdoc function
 * @name resourcesApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the resourcesApp
 */
angular.module('resourcesApp')
  .controller('MainCtrl', function ($scope,$resource, ShoppingCart) {
	 $scope.products= $resource('/api/product').query();
	 $scope.cart = ShoppingCart;
    $scope.addProduct= function(product){
//    	$scope.cart.products.push(product);
    	ShoppingCart.addProduct(product);
    }
  });
