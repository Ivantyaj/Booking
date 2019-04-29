angular.module('crudApp').factory('Booking', Booking);

Booking.$inject = [ '$resource' ];

function Booking($resource) {
    var resourceUrl = 'http://localhost:8080/zzzzzzz/:id';
    console.log("hi serv booking");
    return $resource(resourceUrl, {}, {
        'update' : {
            method : 'PUT'
        }
    });
}