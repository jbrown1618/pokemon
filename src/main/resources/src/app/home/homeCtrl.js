(function() {
    'use strict';

    angular.module('pokemon.home').controller('homeCtrl', homeCtrl);

    function homeCtrl($scope, $location) {
        $scope.states = [
            {
                name : "Home",
                path : "/home"
            },{
                name : "Pokedex",
                path : "/pokedex"
            },{
                name : "Party",
                path : "/party"
            },{
                name : "Battle",
                path : "/battle"
            }
        ];

        $scope.redirectTo = function(state) {
            $location.path(state.path);
        };
    }
})();