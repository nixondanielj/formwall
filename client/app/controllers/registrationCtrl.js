/// <reference path="../references.js" />
formwallCtrls.controller("RegistrationCtrl", ['$scope', '$window', function ($scope, $window) {

    // aggravating init logic...
    $window.init = function () {
        gapi.client.load('formwallApi', 'v1', function () {
            $scope.is_backend_ready = true;
            $scope.$apply();
        }, root);
    }

    $scope.register = function () {
        gapi.client.formwallApi.userEndpoint.register({ email: $scope.email })
            .execute(function(resp){
                console.log(resp);
            });
    }
}]);