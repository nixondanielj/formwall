/// <reference path="../references.js" />
formwallCtrls.controller('SeedCtrl', ["$scope", 'AuthSvc', function ($scope, authSvc) {
    $scope.seed = function () {
        gapi.client.formwallApi.seedEndpoint.seed({ clear: $scope.clear }).execute(function (resp) {
            console.log(resp);
        });
    }
}]);