angular.module("crudApp").controller("GeneralController", GeneralController);

GeneralController.inject = [ '$scope', 'HotelRooms' ];

function GeneralController($scope, HotelRooms) {
	
	$scope.hotelRoomss = HotelRooms.query();

	console.log($scope.hotelRoomss);

	$scope.hotelRooms = {};
	
	$scope.buttonText="Submit";
	
	$scope.saveHotelRooms = function() {
		if ($scope.hotelRooms.id !== undefined) {
			HotelRooms.update($scope.hotelRooms, function() {
				$scope.hotelRoomss = HotelRooms.query();
				$scope.hotelRooms = {};
				$scope.buttonText="Submit";
			});
		} else {
			HotelRooms.save($scope.hotelRooms, function() {
				$scope.hotelRoomss = HotelRooms.query();
				$scope.hotelRooms = {};
			});
		}
	}

	$scope.updateHotelRoomsInit = function(hotelRooms) {
		$scope.buttonText="Update";
		$scope.hotelRooms = hotelRooms;
	}

	$scope.deleteHotelRooms = function(hotelRooms) {
		hotelRooms.$delete({id: hotelRooms.id}, function() {
			$scope.hotelRoomss = HotelRooms.query();
		});
	}
	
	

	
}