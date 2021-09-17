var app = angular.module('customerApp', ['ui.grid','ui.grid.pagination']);

app.controller('CustomerController', function($scope, $http, uiGridConstants) {
    function getCustomers(newPage, pageSize){
        var params = "start=" + newPage;
        params += "&count=" + pageSize;
        $http.get("http://localhost:8080/rest/customers?" + params).then(function(response) {
            $scope.customerList = response.data;
            $scope.gridOptions.data = $scope.customerList.items;
            $scope.gridOptions.totalItems = $scope.customerList.count;

        })

    };
    var paginationOptions = {
        pageNumber: 0,
        pageSize: 10,
        sort: null
    };
    $scope.gridOptions = {
        paginationPageSizes: [10, 15],
        paginationPageSize: 10,
        useExternalPagination: true,
        columnDefs: [
            { name: 'id' },
            { name: 'name' },
            { name: 'phone' },
            { name: 'country' },
            { name: 'countryCode' },
            { name: 'valid' }
        ],
        enablePaginationControls: true,
        onRegisterApi: function(gridApi) {
            $scope.gridApi = gridApi;
            gridApi.pagination.on.paginationChanged(
                $scope,
                function (newPage, pageSize) {
                    paginationOptions.pageNumber = newPage;
                    paginationOptions.pageSize = pageSize;
                    getCustomers(parseInt(newPage) - 1, pageSize);

                }
            );
        }

    };
    getCustomers(0, 10);
});
