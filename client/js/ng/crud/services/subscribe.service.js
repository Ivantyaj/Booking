angular.module('crudApp').factory('Subscribes', Subscribes);

Subscribes.$inject = [ '$resource' ];

function Subscribes($resource) {
	var resourceUrl = 'http://localhost:8080/zzzzzzzzzzzzz:id';
	console.log("hi serv sub");
	return $resource(resourceUrl, {}, {
		'update' : {
			method : 'PUT'
		}
	});
}