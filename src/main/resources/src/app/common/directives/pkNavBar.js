(function () {
    'use strict';

    angular.module('pokemon.common')
        .directive('pkNavBar', pkNavBar)
        .controller('pkNavBarCtrl', pkNavBarCtrl);

    function pkNavBar () {
        return {
            restrict: 'E',
            templateUrl: 'src/app/common/directives/pkNavBar.tpl.html',
            controller: 'pkNavBarCtrl'
        };
    }

    function pkNavBarCtrl ($scope) {
        $scope.pages = [{
            name: 'Home',
            state: 'home'
        }, {
            name: 'Pokedex',
            state: 'pokedex'
        }, {
            name: 'Party',
            state: 'party'
        }, {
            name: 'Battle',
            state: 'battle'
        }];
    }
})();