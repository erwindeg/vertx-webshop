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
  .controller('CartCtrl', function ($scope,$resource,$location, ShoppingCart) {
	 $scope.cart= ShoppingCart;
	 $scope.removeItem = function(index){
		 ShoppingCart.removeItem(index);
	 }
	 
	 $scope.checkout = function(){
		 var order = {};
		 order.cartItems = ShoppingCart.cartItems;
		 order.date = new Date();
		 if(ShoppingCart.size() > 0){
			  $resource('/api/order').save(order, function(order){
				 $location.path('/checkout/'+order.orderid);	 
			 });
			 
		 }
	 }
  });
