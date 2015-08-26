'use strict';

angular.module('shoppingListApp').service('Product', function ($resource) {

	return $resource('rest/product', {}, {
		query: {
			url: 'rest/product/query',
			method: 'GET',
			isArray: true
		},
		notin: {
			url: 'rest/product/notin',
			method: 'GET',
			isArray: true
		}
	});
	
});
