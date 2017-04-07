(function () {
    'use strict';

    angular.module('pokemon.common')
        .directive('pkPokemonPanel', pkPokemonPanel)
        .controller('pkPokemonCtrl', pokemonPanelCtrl);

    function pkPokemonPanel () {
        return {
            link : pokemonPanelLink,
            controller : 'pokemonPanelCtrl',
            templateUrl : 'dir/pkPokemonPanel.tpl.html',
            restrict : 'E',
            replace : true,
            scope : {
                pokemon : '=pokemon'
            }
        };

        function pokemonPanelLink (scope, element, attrs) {
            scope.editMode = false;
        }
    }

    function pokemonPanelCtrl ($scope, pokemonSvc) {
        $scope.toggleEdit = function () {
            $scope.editMode = !$scope.editMode;
        };
    }
})();
