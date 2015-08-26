'use strict';

angular.module('shoppingListApp').controller('SelectProductsCtrl', function($scope, $window, $modalInstance, items, selectedItems, selectedPoductIds, Product, $timeout) {
	
    $scope.loadFile = function() {
    	if (window.File && window.FileReader && window.FileList && window.Blob) {
			var inputFileElement = $window.document.getElementById('input-file-select');
			if (!inputFileElement) {
				var inputFile = document.createElement('input');
				inputFile.type = 'file';
				inputFile.id = 'input-file-select';
				inputFile.style.position = 'absolute';
				inputFile.style.top = '-100px';
				$window.document.body.appendChild(inputFile);
				inputFileElement = $window.document.getElementById('input-file-select');		
				inputFile.onchange = function(e) {
					if (e.target.files.length == 1) {
						var file = e.target.files[0];
						if (file.size > 999999) {
							return $scope.$apply(function() {
								$scope.productForm.messages.push({text: 'Este arquivo é muito grande para ser enviado. Por favor tente outro', danger: true});
				        	});
						}
						var reader = new FileReader();
						reader.onload = (function(file) {
					        return function(e) {
					        	$scope.$apply(function() {
						        	$scope.productForm.filename = e.target.result;
					        	});
					        };
					    })(file);
						reader.readAsDataURL(file);
					}
				};
			}
			inputFileElement.accept = 'image/*';
			inputFileElement.multiple = false;
			inputFileElement.click();
    	} else {
			$scope.productForm.messages.push({text: 'Seu navegador não suporta essa funcionalidade.', danger: true});
    	}
    };
    
	$scope.items = items;

	$scope.productForm = {};
	$scope.productForm.enabled = false;
	$scope.productForm.id = -1;
	$scope.productForm.messages = [];
	
	$scope.refreshItems = function() {
		Product.query({'notin': selectedPoductIds}, function(products) {
			$scope.items.splice(0, $scope.items.length);
			angular.forEach(products, function(product) {
				$scope.items.push(product);
			}, $scope.items);
		});
	};
	
	angular.forEach(selectedItems, function(selectedItem) {
		  this.filter(function(item){
			  if(selectedItem.id == item.id) {
				  item.selected = true;
				  item.value = selectedItem.value;
				  item.purchased = selectedItem.purchased;
			  }
			  return item.selected
		  });
		}, $scope.items);

	$scope.toogleItem = function(item) {
		if (item.selected)
			item.selected = false;
		else
			item.selected = true;
	}

	$scope.selectAll = function() {
		angular.forEach($scope.items, function(item) {
			if (!item.selected)
				this.toogleItem(item);
			}, $scope);
	};
	
	$scope.selectedItensFilter = function(item) {
		return item.selected;
	};

	$scope.ok = function () {
		$scope.productForm.enabled = false;
		$scope.productForm.id = -1;
		$scope.productForm.messages = [];
	    $modalInstance.close($scope.items.filter($scope.selectedItensFilter));
	};

	$scope.cancel = function () {
		$scope.productForm.enabled = false;
		$scope.productForm.id = -1;
		$scope.productForm.messages = [];
	    $modalInstance.dismiss('cancel');
	};
	
	$scope.deleteProduct = function(product) {
		Product['delete']({'id': product.id}, function(product) {
			$scope.productForm.messages.push({text: 'Produto "'+product.id+'" excluí­do com sucesso.', success: true});
			$scope.refreshItems();
		});
	};
	
	$scope.editProduct = function(product) {
		$scope.productForm.id = product.id;
		$scope.productForm.name = product.name;
		$scope.productForm.description = product.description;
		$scope.productForm.value = product.value;
		$scope.productForm.filename = product.filename;
		$scope.productForm.messages = [];
		$scope.productForm.enabled = true;
	};
	
	$scope.saveProduct = function() {
		if (!$scope.productForm.name || $scope.productForm.name == '') {
			$scope.productForm.messages.push({danger:true, text: 'O Campo "Nome" é obrigatório.'});
			return;
		}
		Product.save($scope.productForm, function(product) {
			$scope.productForm.messages.push({text: 'Produto "'+product.id+'" salvo com sucesso.', success: true});
			$scope.productForm.enabled = false;
			$scope.productForm.id = -1;
			$scope.refreshItems();
		}, function(errorData) {
			$scope.productForm.messages.push({danger:true, text: 'Ocorreu um erro inesperado no sistema, por favor, tente novamente.'});
		});
	}

});
