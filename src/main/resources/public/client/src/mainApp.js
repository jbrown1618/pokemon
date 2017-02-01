(function () {
    'use strict';

    angular.module('pokemon', [
        'pokemon.battle',
        'pokemon.home',
        'pokemon.party',
        'pokemon.pokedex'
    ]).config(config);

    function config ($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('home', {
                url: '/home',
                templateUrl: '/client/src/home/home.tpl.html',
                controller: 'homeCtrl'
            })
            .state('pokedex', {
                url: '/pokedex',
                templateUrl: '/client/src/pokedex/pokedex.tpl.html',
                controller: 'pokedexCtrl'
            })
            .state('battle', {
                url: '/battle',
                templateUrl: '/client/src/battle/battle.tpl.html',
                controller: 'battleCtrl'
            })
            .state('party', {
                url: '/party',
                templateUrl: '/client/src/party/party.tpl.html',
                controller: 'partyCtrl'
            });

        $urlRouterProvider.otherwise(function ($injector) {
            var $state = $injector.get('$state');
            return $state.go('home');
        });
    }
})();
