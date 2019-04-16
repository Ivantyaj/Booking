var appSearch = angular.module("ngSearch", []);

appSearch.controller("searchCtrl", function ($scope, $http) {
    console.log($scope)
    // $scope.websites = [];
    //
    // $http.get('xxxx').then(function (response) {
    //     $scope.websites=response.data;
    // });
    var startDate = getUrlParameter('startDate');
    var endDate = getUrlParameter('endDate');
    var clients = getUrlParameter('clients');


    $http.get("http://localhost:8080/hotel/booking/?startDate="+startDate+"&endDate="+endDate+"&clients="+clients)
        .then(function (result) {
            console.log('success', result)
        },
        function (result) {
            console.log('error')
        })
});