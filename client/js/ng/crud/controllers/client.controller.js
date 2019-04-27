angular.module("crudApp").controller("ClientController", ClientController);

ClientController.inject = [ '$scope', 'Clients' ];

function ClientController($scope, Clients) {

    $scope.clientss = Clients.query();

    console.log("clientss", $scope.clientss);

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
        console.log(clients);
        $scope.buttonText="Update";
        $scope.clients = clients;
    }

    $scope.updateClientsPhone = function(clients) {
        clients.needCall = false;
        $scope.clients = clients;
        $scope.saveClients();
    }

    $scope.deleteClients = function(clients) {
        clients.$delete({id: clients.id}, function() {
            $scope.clientss = Clients.query();
        });
    }

    $scope.isNeedCall = function (clients) {
        if(clients.needCall === true){
            return true;
        } else {
            return false;
        }
    }




}
