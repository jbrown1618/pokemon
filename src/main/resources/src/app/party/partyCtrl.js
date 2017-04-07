(function() {
    'use strict';

    angular.module('pokemon.party').controller('partyCtrl', partyCtrl);

    function partyCtrl($scope, partySvc) {
        console.log("partyCtrl");

        $scope.party = partySvc.getParty();
    }
})();