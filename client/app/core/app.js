var formwallSvcs = angular.module('formwallSvcs', []);
var formwallCtrls = angular.module('formwallControllers', ['formwallSvcs']);
var formwallApp = angular.module('formwall', ['ngRoute', 'formwallControllers', 'ui.bootstrap']);

formwallApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'app/views/home.html',
            controller: 'HomeCtrl'
        }).when('/seed', {
            templateUrl: 'app/views/seed.html',
            controller: 'SeedCtrl'
        }).otherwise({
            redirectTo: '/'
        });
}]);




// no ending slash
var root = 'http://localhost:8888/_ah/api';
var apiURL = root + '/formwallApi/v1/';
function init() {
    window.init();
}