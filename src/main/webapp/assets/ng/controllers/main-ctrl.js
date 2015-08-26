'use strict';

angular.module('shoppingListApp').controller('MainController', function($scope, $location, $route, $routeParams, ProductList) {
	 $scope.$route = $route;
     $scope.$location = $location;
     $scope.$routeParams = $routeParams;
     
     $scope.newContainerData = {};
     $scope.editContainerData = {};
     $scope.searchContainerData = {};
     
     $scope.filter = {};
     $scope.search = false;

     $scope.items = [];
     
     $scope.refreshItems = function() {
    	 $scope.items.splice(0, $scope.items.length);
         ProductList.query(function(productlists) {
        	 angular.forEach(productlists, function(productlist) {
        		 this.push(productlist);
        	 }, $scope.items);
         });
     };
     
     $scope.refreshItems();
     
     $scope.editList = function(productlist) {
    	 $location.path('/criar-nova-lista/'+productlist.id); 
     };
     
     $scope.removeList = function(productlist) {
    	 ProductList['delete']({'id': productlist.id}, function(productlist) {
    		 $scope.refreshItems();
    	 });
     };
     
     $scope.searchProductLists = function() {
    	 $scope.items.splice(0, $scope.items.length);
         ProductList.query({
        	 'name': $scope.filter.name,
        	 'productname': $scope.filter.productname,
        	 'categoryname': $scope.filter.category
         }, function(productlists) {
        	 angular.forEach(productlists, function(productlist) {
        		 this.push(productlist);
        	 }, $scope.items);
         });
     };
     
     $scope.totalValue = function(productlist) {
    	 var value = {sum: 0.00};
    	 angular.forEach(productlist.items, function(item) {
    		 if (item.price)
    			 value.sum += eval(item.price);
    		 else if (item.product.value)
    			 value.sum += eval(item.product.value);
    	 }, value);
    	 return value.sum;
     }
     
});
