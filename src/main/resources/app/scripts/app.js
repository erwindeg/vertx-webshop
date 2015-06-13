'use strict';

/**
 * @ngdoc overview
 * @name resourcesApp
 * @description
 * # resourcesApp
 *
 * Main module of the application.
 */
angular
  .module('resourcesApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'app/views/main.html',
        controller: 'MainCtrl'
      })
      .when('/cart', {
        templateUrl: 'app/views/cart.html',
        controller: 'CartCtrl'
      })
      .when('/admin', {
        templateUrl: 'app/views/admin.html',
        controller: 'OrderCtrl'
      })
      .when('/checkout/:id', {
        templateUrl: 'app/views/checkout.html',
        controller: 'OrderCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
