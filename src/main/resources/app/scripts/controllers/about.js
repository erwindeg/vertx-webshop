'use strict';

/**
 * @ngdoc function
 * @name resourcesApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the resourcesApp
 */
angular.module('resourcesApp')
  .controller('AboutCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
