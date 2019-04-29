angular.module("crudApp").controller("BookingController", BookingController);

BookingController.inject = [ '$scope', 'Booking' ];

function BookingController($scope, Booking) {

    $scope.bookings = Booking.query();

    console.log($scope.bookings);

    $scope.booking = {};

    $scope.buttonText="Submit";

    $scope.saveBooking = function() {
        if ($scope.booking.id !== undefined) {
            Booking.update($scope.booking, function() {
                $scope.bookings = Booking.query();
                $scope.booking = {};
                $scope.buttonText="Submit";
            });
        } else {
            Booking.save($scope.booking, function() {
                $scope.bookings = Booking.query();
                $scope.booking = {};
            });
        }
    }

    $scope.updateBookingInit = function(booking) {
        $scope.buttonText="Update";
        $scope.booking = booking;
    }

    $scope.deleteBooking = function(booking) {
        booking.$delete({id: booking.id}, function() {
            $scope.bookings = Booking.query();
        });
    }


    $scope.cancelBooking = function (booking) {
        console.log("cancel booking", booking);

        let cancelData = {};
        cancelData.id = booking.id;
        cancelData.message = $('#messageBooking').val();

        console.log("cancel data", cancelData);

        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/hotel/booking/cancel',
            data: JSON.stringify(cancelData),
            contentType: 'application/json',
            dataType: 'json',
            success: function(data) {
                console.log("Succc bbouk", data);
                $scope.bookings = Booking.query();
                location.reload();
                alert("возможно стоит обновить страницу!");
            },
            error:  function(data){
                console.log("Err book", data);
                $scope.bookings = Booking.query();
                location.reload();
                alert("возможно стоит обновить страницу!");
            }
        });

    }



}