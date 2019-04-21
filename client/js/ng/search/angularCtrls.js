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
    var price = "";

    $http.get("http://localhost:8080/hotel/booking/searchFree?startDate="+startDate+"&endDate="+endDate+"&clients="+clients+"&price="+price)
        .then(function (result) {
                console.log('success', result);
                $scope.findRooms = result.data;
                console.log('rooms', $scope.findRooms);
            },
            function (result) {
                console.log('error')
            })

    $('#btnSearch').click(function () {

        startDate = $('#start-date-1').val();
        endDate = $('#end-date-1').val();
        clients = $('#selectPeople').val();
        price = $('#inputPrice').val();

        console.log(startDate, endDate, clients, price);

        $http.get("http://localhost:8080/hotel/booking/searchFree?startDate="+startDate+"&endDate="+endDate+"&clients="+clients+"&price="+price)
            .then(function (result) {
                    console.log('success', result);
                    $scope.findRooms = result.data;
                    console.log('rooms', $scope.findRooms);
                },
                function (result) {
                    console.log('error')
                })
    });
});
