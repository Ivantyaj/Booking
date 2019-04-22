angular.module("crudApp").controller("SubscribeController", SubscribeController);

SubscribeController.inject = [ '$scope', 'Subscribes' ];

function SubscribeController($scope, Subscribes) {

    $scope.subscribess = Subscribes.query();

    console.log($scope.subscribess);

    $scope.subscribes = {};

    $scope.buttonText2="Submit";

    $scope.saveSubscribes = function() {
        if ($scope.subscribes.id !== undefined) {
            Subscribes.update($scope.subscribes, function() {
                $scope.subscribess = Subscribes.query();
                $scope.subscribes = {};
                $scope.buttonText2="Submit";
            });
        } else {
            Subscribes.save($scope.subscribes, function() {
                $scope.subscribess = Subscribes.query();
                $scope.subscribes = {};
            });
        }
    }

    $scope.updateSubscribesInit = function(subscribes) {
        $scope.buttonText2="Update";
        $scope.subscribes = subscribes;
    }

    $scope.deleteSubscribes = function(subscribes) {
        subscribes.$delete({id: subscribes.id}, function() {
            $scope.subscribess = Subscribes.query();
        });
    }




}