formwallCtrls.controller("LoginModalCtrl", ["$scope", "AuthSvc", "$modalInstance", function ($scope, authSvc, $modalInstance) {
    $scope.credentials = {};
    $scope.register = {};
    var signinCallback = function () {
        if (authSvc.isAuthed()) {
            $modalInstance.dismiss();
            $scope.$apply();
        } else {
            // TODO authentication failed...
            alert('auth failed');
        }
    }

    $scope.customSignin = function () {
        authSvc.callAfterNextSignin(signinCallback);
        authSvc.customSignin({
            username: $scope.credentials.email,
            password: $scope.credentials.password
        });
    }
    $scope.googleLogin = function () {
        authSvc.callAfterNextSignin(signinCallback);
        authSvc.loudGoogleSignin();
    }
    $scope.customRegister = function () {
        authSvc.callAfterNextSignin(signinCallback);
        authSvc.customRegister($scope.register.email);
    }
}]);