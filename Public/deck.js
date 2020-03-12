angular.module('demo', [])
    .controller('Hello', function($scope, $http) {
        $http.get('http://localhost:8080/decklist/1').
        then(function(response) {
            $scope.deck = response.data;
        });
    });