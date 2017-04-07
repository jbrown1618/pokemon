(function() {
    'use strict';

    angular.module('pokemon.pokedex').controller('pokedexCtrl', pokedexCtrl);

    function pokedexCtrl ($scope, speciesService) {
        speciesService.getAllSpecies().then(function (allSpecies) {
            $scope.speciesList = allSpecies;
        });
    }
})();