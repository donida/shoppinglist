'use strict';

angular.module('shoppingListApp').controller('CreateCategoryCtrl', function($scope, $window, $modalInstance, Category, $timeout) { 
	
	$scope.id = null;
	$scope.name = '';
	$scope.description = '';
	$scope.messages = [];
	
	$scope.saveCategory = function () {
		$scope.messages = [];
		if (!$scope.name || $scope.name == '') {
			$scope.messages.push({danger:true, text: 'O Campo "Nome" é obrigatório.'});
			return;
		}
		Category.save({
	    	'id': $scope.id,
	    	'name': $scope.name,
	    	'description': $scope.description
		}, function(category) {
			$modalInstance.close(category);
		}, function(errorData) {
			$scope.messages.push({danger:true, text: 'Ocorreu um erro inesperado ao savar a lista, por favor tente novamente.'});
		});
	};

	$scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	};
	
});
