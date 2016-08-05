angular.module('mainApp').config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/home', {
            templateUrl: '/pokemon/client/app/tpl/home.tpl.html',
            controller: 'homeCtrl'
        })
        .when('/pokedex', {
            templateUrl: '/pokemon/client/app/tpl/pokedex.tpl.html',
            controller: 'pokedexCtrl'
        })
        .when('/battle', {
            templateUrl: '/pokemon/client/app/tpl/battle.tpl.html',
            controller: 'battleCtrl'
        }).when('/party', {
            templateUrl: '/pokemon/client/app/tpl/party.tpl.html',
            controller: 'partyCtrl'
        }).otherwise({
            redirectTo: '/home'
        });
}]);