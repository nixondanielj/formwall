/// <reference path="../references.js" />
formwallCtrls.controller('SeedCtrl', ['AuthSvc', function (authSvc) {
    $scope.seed = function () {
        authSvc.signin(false);
    }
}]);