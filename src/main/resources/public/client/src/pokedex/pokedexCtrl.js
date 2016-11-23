(function() {
    'use strict';

    angular.module('mainApp').controller('pokedexCtrl', pokedexCtrl);

    function pokedexCtrl ($scope, speciesService) {
        speciesService.getAllSpecies().then(function (allSpecies) {
            $scope.speciesList = allSpecies;
        });
    }
})();