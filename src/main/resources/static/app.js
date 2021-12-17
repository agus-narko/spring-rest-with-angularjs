var app = angular.module('app', ['ui.grid', 'ui.grid.pagination']);

app.controller('ProductCtrl', ['$scope', 'ProductService', function($scope, ProductService) {
	var paginationOptions = {
		pageNumber: 1,
		pageSize: 10,
		sort: null
	};

	ProductService.getProducts(paginationOptions.pageNumber,
		paginationOptions.pageSize).success(function(data) {
			$scope.gridOptions.data = data.content;
			$scope.gridOptions.totalItems = data.totalElements;
		});

	$scope.gridOptions = {
		paginationPageSizes: [10, 30, 50, 80, 100],
		paginationPageSize: paginationOptions.pageSize,
		enableColumnMenus: false,
		useExternalPagination: true,
		enableFiltering: true,
		columnDefs: [
			{ name: 'productCode' },
			{ name: 'productName' },
			{ name: 'category' },
			{ name: 'brand' },
			{ name: 'price' },
			{ name: 'status' },
		],
		onRegisterApi: function(gridApi) {
			$scope.gridApi = gridApi;
			gridApi.pagination.on.paginationChanged($scope, function(newPage, pageSize) {
				paginationOptions.pageNumber = newPage;
				paginationOptions.pageSize = pageSize;
				ProductService.getProducts(newPage, pageSize).success(function(data) {
					$scope.gridOptions.data = data.content;
					$scope.gridOptions.totalItems = data.totalElements;
				});
			});
		}
	};

}]);

app.service('ProductService', ['$http', function($http) {

	function getProducts(pageNumber, size) {
		pageNumber = pageNumber > 0 ? pageNumber - 1 : 0;
		return $http({
			method: 'GET',
			url: '/product?page=' + pageNumber + '&size=' + size
		});
	}

	return {
		getProducts: getProducts
	};

}]);
