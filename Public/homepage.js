angular.module('demo', [])
    .controller('Hello', function($scope, $http) {
        $http.get('http://localhost:8080/').
        then(function(response) {
            let arr = JSON.parse(response.data);
            let x = JSON.parse(arr[0]);
            $scope.deck = x;
        });
    });