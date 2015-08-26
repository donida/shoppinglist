'use strict';

angular.module('shoppingListApp').controller('CreateListCtrl', function($scope, $window, $modal, $log, $routeParams, ProductList, Product, Category) {
	
	$scope.listTitle = 'Nova Lista de Compras';
	$scope.id = $routeParams.id;
	$scope.items = [];
	$scope.messages = [];
	
	$scope.categories = [];
	
	$scope.refreshCategories = function() {
		$scope.categories.splice(0,$scope.categories.length)
		Category.query(function(categories) {
			angular.forEach(categories, function(category) {
				this.push(category);
			}, $scope.categories);
		});
	};
	
	$scope.refreshCategories();
	
	$scope.selectCategory = function(category) {
		$scope.category = category;
	};
	
	$scope.deleteCategory = function(category) {
		Category['delete']({'id':category.id}, function(deleteResult) {
			$scope.messages.push({success:true, text: 'Categoria "'+ category.name +'" apagada com sucesso.'});
			$scope.refreshCategories();
		}, function(errorData) {
			$scope.messages.push({danger:true, text: 'Ocorreu um erro inesperado no sistema, por favor, tente novamente.'});
		});
	};
	
	$scope.addCategory = function() {
		var size, selectedIds, modalInstance;
		size = 'lg';
	    modalInstance = $modal.open({
		      animation: $scope.animationsEnabled,
		      templateUrl: 'assets/ng/views/add-category-modal.html',
		      controller: 'CreateCategoryCtrl',
		      size: size,
		      resolve: {}
		    });
	    modalInstance.result.then(function (category) {
	    	if (category)
	    		$scope.categories.push(category);
	    }, function () {});
	};
	
	$scope.refreshItens = function(updateall) {
		$scope.items.splice(0,$scope.items.length)
		ProductList.get({'id': $scope.id}, function(productlist) {
			if (updateall) {
				$scope.name = productlist.name;
				$scope.description = productlist.description;
				$scope.category = productlist.category;
			}
			angular.forEach(productlist.items, function(item) {
				this.push(item);
			}, $scope.items);	
		});
	};

	if ($scope.id) {
		$scope.listTitle = 'Alterar Lista de Compras';
		$scope.refreshItens(true);
	}
	
	$scope.animationsEnabled = true;
	
	$scope.addItem = function() {
		var size, selectedIds, modalInstance;
		size = 'lg';
		selectedIds = [];
		angular.forEach($scope.items, function(item) {
				this.push(item.product.id);
			}, selectedIds);
		if (selectedIds.length <= 0)
			selectedIds.push(-1);
		Product.query({'notin': selectedIds}, function(products) {
		    modalInstance = $modal.open({
			      animation: $scope.animationsEnabled,
			      templateUrl: 'assets/ng/views/select-products-modal.html',
			      controller: 'SelectProductsCtrl',
			      size: size,
			      resolve: {
			        items: function () {
			          return products;
			        },
			        selectedItems: function () {
			          return $scope.items;
			        },
			        selectedPoductIds: function () {
			        	return selectedIds;
			        }
			      }
			    });
			    modalInstance.result.then(function (selectedItems) {
					angular.forEach(selectedItems, function(item) {
						this.push({
							id: null,
							product: item,
							price: (item.value) ? item.value : 0.00,
							purchased: false
						});
					}, $scope.items);
			    }, function () {
			    	// Do Nothing...
			    });			
        });
	};
	
	$scope.removeProduct = function(item) {
		var index = $scope.items.indexOf(item);
		$scope.items.splice(index, 1);
	};
	
	$scope.tooglePurchased = function(item) {
		item.purchased = !item.purchased;
		if (!item.purchased && !item.product.value)
			item.price = 0.00;
	};
	
	$scope.selectAll = function() {
		angular.forEach($scope.items, function(item) {
			if (!item.purchased)
				this.tooglePurchased(item);
			}, $scope);
	};

	$scope.toogleOnEdition = function(item) {
		if (!item.onEdition) {
			item.editedValue = item.price;
			item.onEdition = !item.onEdition;
		}
	};
	
	$scope.saveEditionValue = function(item) {
		if (item.editedValue)
			item.price = item.editedValue;
		else
			item.price = 0.00;
		item.onEdition = false;
	};
	
	$scope.cancel = function() {
		$scope.items = [];
	};
	
	$scope.save = function() {
		if (!$scope.name || $scope.name == '') {
			$scope.messages.push({danger:true, text: 'O Campo "Nome" � obrigat�rio.'});
			return;
		}
		ProductList.save({
				id: $scope.id,
				name: $scope.name,
				description: $scope.description,
				category: $scope.category,
				items:  $scope.items
			}, function(productlist) {
				$scope.id = productlist.id;
				$scope.messages.push({text: 'Lista de produtos "' + productlist.id + '" salva com sucesso.', success: true});
			}, function(errorData) {
				$scope.messages.push({danger:true, text: 'Ocorreu um erro inesperado ao savar a lista, por favor tente novamente.'});
			});
	};
	
	$scope.cart = function() {
		var vlTotal = {sum: 0};
		angular.forEach($scope.items, function(item) {
			if (item.price && item.purchased)
				this.sum += eval(item.price);
			else if (item.product.value && item.purchased)
				this.sum += eval(item.product.value);
			}, vlTotal);		
		return vlTotal.sum;
	};

	$scope.residual = function() {
		var vlTotal = {sum: 0};
		angular.forEach($scope.items, function(item) {
			if (item.price && !item.purchased)
				this.sum += eval(item.price);
			else if (item.product.value && !item.purchased)
				this.sum += eval(item.product.value);
			}, vlTotal);		
		return vlTotal.sum;
	};

	$scope.total = function() {
		var vlTotal = {sum: 0};
		angular.forEach($scope.items, function(item) {
			if (item.price)
				this.sum += eval(item.price);
			}, vlTotal);		
		return vlTotal.sum;
	};

});
