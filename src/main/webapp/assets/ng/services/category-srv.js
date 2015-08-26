'use strict';

angular.module('shoppingListApp').service('Category', function ($resource) {

	return $resource('rest/category', {}, {
		query: {
			url: 'rest/category/query',
			method: 'GET',
			isArray: true
		}
	});
	
});
