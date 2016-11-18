(function () {
    angular.module('mainApp', ['ngRoute']).config(config);

    function config ($routeProvider) {
        $routeProvider
            .when('/home', {
                templateUrl: '/client/app/tpl/home.tpl.html',
                controller: 'homeCtrl'
            })
            .when('/pokedex', {
                templateUrl: '/client/app/tpl/pokedex.tpl.html',
                controller: 'pokedexCtrl'
            })
            .when('/battle', {
                templateUrl: '/client/app/tpl/battle.tpl.html',
                controller: 'battleCtrl'
            }).when('/party', {
                templateUrl: '/client/app/tpl/party.tpl.html',
                controller: 'partyCtrl'
            }).otherwise({
                redirectTo: '/home'
            });
    }
})();
