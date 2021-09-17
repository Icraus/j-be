var app = angular.module("customerApp", ["ngTable"]);

app.controller('CustomerController', function($scope, $http, NgTableParams ) {
        $http.get("http://localhost:8080/rest/customers").then(function(response) {
            $scope.customerList = response.data;
            $scope.customerTable = new NgTableParams({}, {
                total: $scope.customerList.total,
                dataset: $scope.customerList

        });

    // $http.get("http://localhost:8080/rest/customers?start=" + page + "&count=" + count).then(function(response) {
    //     $scope.customerList = response.data;
    //     $scope.customerTable = new NgTableParams({page:1, count:10}, {
    //         total: 0,
    //         dataset: $scope.customerList
    //     });
    // });

});
// var x= angular.module('demo', [])
// .controller('cc', function($scope, $http) {
//     $http.get('http://localhost:8080/rest/customers').
//     then(function(response) {
//         $scope.customerList = response.data;
//
//     });
// });
