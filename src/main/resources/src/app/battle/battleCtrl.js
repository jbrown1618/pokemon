(function() {
    'use strict';

    angular.module('pokemon.battle').controller('battleCtrl', battleCtrl);

    function battleCtrl($scope) {
        console.log("battleCtrl");

        $scope.currentOpponent = {
            species : {
                id : 1,
                speciesName : "Bulbasaur",
                type1 : "Grass",
                type2 : "Poison"
            }
        };

        $scope.currentPokemon = {
            species : {
                id : 4,
                speciesName : "Charmander",
                type1 : "Fire",
                type2 : null
            }
        };

        $scope.party = [];

        $scope.opponentParty = [
            {
                species : {
                    id : 1,
                    speciesName : "Bulbasaur",
                    type1 : "Grass",
                    type2 : "Poison"
                }
            }, {
                species : {
                    id : 1,
                    speciesName : "Bulbasaur",
                    type1 : "Grass",
                    type2 : "Poison"
                }
            }, {
                species : {
                    id : 1,
                    speciesName : "Bulbasaur",
                    type1 : "Grass",
                    type2 : "Poison"
                }
            }, {
                species : {
                    id : 1,
                    speciesName : "Bulbasaur",
                    type1 : "Grass",
                    type2 : "Poison"
                }
            }

        ];

    }
})();