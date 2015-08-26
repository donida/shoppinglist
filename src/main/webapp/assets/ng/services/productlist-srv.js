'use strict';

angular.module('shoppingListApp').service('ProductList', function ($resource) {

	return $resource('rest/productlist', {}, {
		query: {
			url: 'rest/productlist/query',
			method: 'GET',
			isArray: true
		}
	});
	
});
