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
        }).when('/formbuilder/:formId?', {
            templateUrl: '/app/views/formbuilder.html',
            controller: 'FormbuilderCtrl'
        }).otherwise({
            redirectTo: '/'
        });
}]);

formwallApp.run(function ($rootScope, $window) {
    // no ending slash on root
    var root = 'http://localhost:8888/_ah/api';
    var apiURL = root + '/formwallApi/v1/';
    // loads api and notifies all scopes that backend is ready
    var apisToLoad = 2, apisLoaded = 0;
    var apiLoadCallback = function () {
        if (++apisLoaded == apisToLoad) {
            $rootScope.is_backend_ready = true;
            $rootScope.$apply();
        }
    }
    $window.init = function () {
        gapi.client.load('formwallApi', 'v1', apiLoadCallback, root);
        gapi.client.load('oauth2', 'v2', apiLoadCallback);
    }
});

function init() {
    window.init();
}