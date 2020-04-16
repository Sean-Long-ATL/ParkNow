angular.module('demo', [])
    .controller('Hello', function($scope, $http) {
        $http.get('http://localhost:8080/').
        then(function(response) {
            $scope.deck = response.data;
        });
    });