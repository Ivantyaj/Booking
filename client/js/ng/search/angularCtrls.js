var appSearch = angular.module("ngSearch", []);

appSearch.controller("searchCtrl", function ($scope, $http) {
    console.log($scope)
    $('#cardPayment').fadeOut();

    $scope.booking = function (id) {
        console.log("id = ", id);
        $('#roomId').val(id);
    }

    $scope.payment = function () {
        var val = event.target.value;
        console.log(val);
        if (val === "card") {
            $('#cardPayment').fadeIn(200);
        } else {
            $('#cardPayment').fadeOut(200);
        }
    };
    // $scope.websites = [];
    //
    // $http.get('xxxx').then(function (response) {
    //     $scope.websites=response.data;
    // });
    var startDate = getUrlParameter('startDate');
    var endDate = getUrlParameter('endDate');
    var clients = getUrlParameter('clients');
    var price = "";

    $http.get("http://localhost:8080/hotel/booking/searchFree?startDate=" + startDate + "&endDate=" + endDate + "&clients=" + clients + "&price=" + price)
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

        $http.get("http://localhost:8080/hotel/booking/searchFree?startDate=" + startDate + "&endDate=" + endDate + "&clients=" + clients + "&price=" + price)
            .then(function (result) {
                    console.log('success', result);
                    $scope.findRooms = result.data;
                    console.log('rooms', $scope.findRooms);
                },
                function (result) {
                    console.log('error')
                })
    });

    $scope.sendBooking = function (){
        event.preventDefault();

        var dataToSend = [];
        dataToSend.push($('#roomId').val());
        dataToSend.push($('#name').val());
        dataToSend.push($('#email').val());
        dataToSend.push($('#phone').val());
        dataToSend.push($('#message').val());

        var isCheckCard = $('#checkCard').is(':checked');
        console.log("CHECK", isCheckCard);
        dataToSend.push($('#checkCard').is(':checked'));

        dataToSend.push($('#owner').val());
        dataToSend.push($('#cvv').val());
        dataToSend.push($('#exp').val());

        console.log("CHECK email", $('#check').is(':checked'));
        dataToSend.push($('#check').is(':checked'));

        console.log("Form data", dataToSend);
        $.ajax({
            type: 'POST',
            url: 'zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz',
            data: dataToSend,
            success: function(data) {
                console.log("Succc", data);
            },
            error:  function(data){
                console.log("Err", data);
            }
        });

    }

});



