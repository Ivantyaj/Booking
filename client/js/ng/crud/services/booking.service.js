angular.module('crudApp').factory('Booking', Booking);

Booking.$inject = [ '$resource' ];

function Booking($resource) {
    var resourceUrl = 'http://localhost:8080//hotel/booking/:id';
    console.log("hi serv booking");
    return $resource(resourceUrl, {}, {
        'update' : {
            method : 'PUT'
        }
    });
}