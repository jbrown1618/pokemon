(function () {
    angular.module('mainApp').directive('pkPokemonPanel', function () {
        return {
            link : pokemonPanelLink,
            controller : pokemonPanelCtrl,
            templateUrl : 'dir/pkPokemonPanel.tpl.html',
            restrict : 'E',
            replace : true,
            scope : {
                pokemon : '=pokemon'
            }
        }
    });

    function pokemonPanelLink (scope, element, attrs) {
        scope.editMode = false;
    }

    pokemonPanelCtrl.$inject = ['$scope', 'pokemonSvc'];
    function pokemonPanelCtrl ($scope, pokemonSvc) {
        $scope.toggleEdit = function () {
            $scope.editMode = !$scope.editMode;
        }
    }
})();
