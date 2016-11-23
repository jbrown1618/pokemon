(function () {
    angular.module('mainApp', ['ngRoute', 'ngResource']).config(config);

    function config ($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: '/client/src/home/home.tpl.html',
                controller: 'homeCtrl'
            })
            .when('/pokedex', {
                templateUrl: '/client/src/pokedex/pokedex.tpl.html',
                controller: 'pokedexCtrl'
            })
            .when('/battle', {
                templateUrl: '/client/src/battle/battle.tpl.html',
                controller: 'battleCtrl'
            }).when('/party', {
                templateUrl: '/client/src/party/party.tpl.html',
                controller: 'partyCtrl'
            }).otherwise({
                redirectTo: '/'
            });
    }
})();
