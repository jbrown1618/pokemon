(function() {
    angular.module("mainApp").controller("partyCtrl", partyCtrl);

    partyCtrl.$inject = ['$scope','partySvc'];

    function partyCtrl($scope, partySvc) {
        console.log("partyCtrl");

        $scope.party = partySvc.getParty();
    }
})();