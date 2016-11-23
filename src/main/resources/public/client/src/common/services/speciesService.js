(function () {
    'use strict';

    angular.module('mainApp').factory('speciesService', speciesService);

    function speciesService ($resource) {

        var speciesResource = $resource('/api/species', {}, {
            getSpecies: {
                method: 'GET',
                url: '/api/species/:id',
                params: { id: '@id' }
            }
        });

        return {
            getSpecies: getSpecies,
            getAllSpecies: getAllSpecies
        };

        function getSpecies (id) {
            return speciesResource.get({id: id}).$promise;
        }

        function getAllSpecies () {
            return speciesResource.query().$promise;
        }
    }
})();