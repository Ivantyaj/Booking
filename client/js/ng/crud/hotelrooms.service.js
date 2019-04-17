angular.module('crudApp').factory('HotelRooms', HotelRooms);

HotelRooms.$inject = [ '$resource' ];

function HotelRooms($resource) {
	var resourceUrl = 'http://localhost:8080/hotel/rooms/:id';

	return $resource(resourceUrl, {}, {
		'update' : {
			method : 'PUT'
		}
	});
}