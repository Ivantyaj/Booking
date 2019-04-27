angular.module("crudApp").controller("ClientController", ClientController);

ClientController.inject = [ '$scope', 'Clients' ];

function ClientController($scope, Clients) {

    $scope.clientss = Clients.query();

    console.log($scope.clientss);

    $scope.clients = {};

    $scope.buttonText="Submit";

    $scope.saveClients = function() {
        if ($scope.clients.id !== undefined) {
            Clients.update($scope.clients, function() {
                $scope.clientss = Clients.query();
                $scope.clients = {};
                $scope.buttonText="Submit";
            });
        } else {
            Clients.save($scope.clients, function() {
                $scope.clientss = Clients.query();
                $scope.clients = {};
            });
        }
    }

    $scope.updateClientsInit = function(clients) {
        $scope.buttonText="Update";
        $scope.clients = clients;
    }

    $scope.updateClientsPhone = function(clients) {

        $scope.clients; //????????
        Clients.save($scope.clients, function() {
            $scope.clientss = Clients.query();
            $scope.clients = {};
        });
    }

    $scope.deleteClients = function(clients) {
        clients.$delete({id: clients.id}, function() {
            $scope.clientss = Clients.query();
        });
    }




}