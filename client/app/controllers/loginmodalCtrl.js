formwallCtrls.controller("LoginModalCtrl", ["$scope", "AuthSvc", "$modalInstance", function ($scope, authSvc, $modalInstance) {
    $scope.credentials = {};
    $scope.register = {};
    $scope.customSignin = function () {
        authSvc.customSignin({
            username: $scope.credentials.email,
            password: $scope.credentials.password
        }, function () {
            if (authSvc.isAuthed()) {
                $modalInstance.dismiss();
            } else {
                // TODO authentication failed...
                alert('auth failed');
            }
        });
    }
    $scope.googleLogin = function () {
        authSvc.loudGoogleSignin(function () {
            $modalInstance.dismiss();
        });
    }
    $scope.customRegister = function () {
        authSvc.customRegister($scope.register.email);
    }
}]);