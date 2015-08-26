'use strict';

angular.module('shoppingListApp').config(function($routeProvider) {
    $routeProvider
    .when('/criar-nova-lista', {
        templateUrl: 'assets/ng/views/create-list.html',
        controller: 'CreateListCtrl'
      })
      .when('/criar-nova-lista/:id', {
        templateUrl: 'assets/ng/views/create-list.html',
        controller: 'CreateListCtrl'
      })
      .otherwise({
		templateUrl: 'assets/ng/views/index.html',
		controller: 'MainController'
      });
  });