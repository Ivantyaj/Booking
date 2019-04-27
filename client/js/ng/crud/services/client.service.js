angular.module('crudApp').factory('Clients', Clients);

Clients.$inject = [ '$resource' ];

function Clients($resource) {
    var resourceUrl = 'http://localhost:8080/hotel/clients/:id';
    console.log("hi serv client");
    return $resource(resourceUrl, {}, {
        'update' : {
            method : 'PUT'
        }
    });
}