'use strict';

angular.module('resourcesApp').factory('ShoppingCart', function($resource) {
	return {
		cartItems : [],

		// Other methods or objects can go here

		addProduct : function(product) {
			var existingProduct = false;
			this.cartItems.forEach(function(cartItem){
				if(cartItem.product.name == product.name){
					existingProduct=true;
					cartItem.size++;
				}
			});
			if(!existingProduct){
				var cartItem = {};
				cartItem.product = product;
				cartItem.size = 1;
				this.cartItems.push(cartItem);	
			}
		},
		
		removeItem : function(index) { 
			this.cartItems.splice(index, 1);      
		},
		
		size : function() {
			var size =0;
			this.cartItems.forEach(function(cartItem){
				size = size +cartItem.size;
			});
			return size;
		}
	};
	// return $resource('/api/cart').get();
});