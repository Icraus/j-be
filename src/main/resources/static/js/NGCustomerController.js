var app = angular.module('customerApp', ['ui.grid','ui.grid.pagination']);
var searchId;
var searchCountry;
var searchName;
var searchPhone;
var searchCountryCode;
app.controller('CustomerController', function($scope, $http, uiGridConstants) {
    function getCustomersBy(newPage, pageSize, endPoint, paramName, paramValue){
        params = {
            "start": newPage,
            "count": pageSize
        };
        if(paramName !== ""){
            params[paramName] = paramValue;
        }
        var genParams = jQuery.param(params);
        $http.get("http://localhost:8080/rest/customers" + endPoint + "?" + genParams).then(function(response) {
            $scope.customerList = response.data;
            $scope.gridOptions.data = $scope.customerList.items;
            $scope.gridOptions.totalItems = $scope.customerList.count;
        })
    }
    function getCustomers(newPage, pageSize){
        return getCustomersBy(newPage, pageSize, "", "", "");
    }
    function getCustomersByName(newPage, pageSize, name){
        return getCustomersBy(newPage, pageSize, "/name", "name", name);

    }
    function getCustomersByPhone(newPage, pageSize, phoneNumber){
        return getCustomersBy(newPage, pageSize, "/phone", "phone", phoneNumber);

    }
    function getCustomersByCountry(newPage, pageSize, country){
        return getCustomersBy(newPage, pageSize, "/country", "country", country);
    }
    function getCustomersByCountryCode(newPage, pageSize, countryCode){
        return getCustomersBy(newPage, pageSize, "/countryCode", "countryCode", countryCode);
    }
    function getCustomersByValid(newPage, pageSize, valid){
        return getCustomersBy(newPage, pageSize, "/valid", "valid", valid);
    }

    function getCustomersById(newPage, pageSize, customerId){
        return getCustomersBy(newPage, pageSize, "/" + customerId, "", "");
    }
    var paginationOptions = {
        pageNumber: 0,
        pageSize: 10,
        sort: null
    };
    $scope.gridOptions = {
        paginationPageSizes: [10, 15, 20],
        paginationPageSize: 10,
        useExternalPagination: true,
        enableFiltering: true,
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
    searchCountry = function (country){
        console.log(country);
        getCustomersByCountry(0, 10, country);
    }
    searchPhone = function (phone){
        getCustomersByPhone(0, 10, phone);
    }
    searchName = function (name){
        getCustomersByName(0, 10, name);
    }
    searchCountryCode = function (countryCode){
        getCustomersByCountryCode(0, 10, countryCode);
    }
    searchId = function (id){
        getCustomersById(0, 10, id);
    }
    getCustomers(0, 10);
});
