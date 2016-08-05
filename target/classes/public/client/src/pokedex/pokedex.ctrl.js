(function() {
    angular.module('mainApp').controller("pokedexCtrl", pokedexCtrl);

    pokedexCtrl.$inject = ['$scope', 'speciesSvc'];

    function pokedexCtrl($scope, speciesSvc) {
        console.log('pokedexCtrl');

        $scope.speciesList = speciesSvc.getAllSpecies();

        $scope.selectSpecies = function (speciesId) {
            $scope.speciesList.forEach(function(species) {
               if (species.id === speciesId) {
                   species.active = true;
                   $scope.currentSpecies = species;
               }  else {
                   species.active = false;
               }
            });
        }

    }
})();