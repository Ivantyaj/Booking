angular.module('crudApp').factory('Employee', Employee);

Employee.$inject = [ '$resource' ];

function Employee($resource) {
	var resourceUrl = 'http://localhost:8080/hotel/rooms/:id';

	return $resource(resourceUrl, {}, {
		'update' : {
			method : 'PUT'
		}
	});
}