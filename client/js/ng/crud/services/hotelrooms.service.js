angular.module('crudApp').factory('HotelRooms', HotelRooms);

HotelRooms.$inject = [ '$resource' ];

function HotelRooms($resource) {
	var resourceUrl = 'http://localhost:8080/hotel/rooms/:id';
	console.log("hi serv hot");
	return $resource(resourceUrl, {}, {
		'update' : {
			method : 'PUT'
		}
	});
}