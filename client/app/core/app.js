var formwallSvcs = angular.module('formwallSvcs', []);
var formwallCtrls = angular.module('formwallControllers', ['formwallSvcs']);
var formwallApp = angular.module('formwall', ['ngRoute', 'formwallControllers']);

formwallApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'app/views/home.html',
            controller: 'RegistrationCtrl'
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