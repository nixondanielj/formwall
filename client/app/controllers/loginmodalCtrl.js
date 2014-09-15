formwallCtrls.controller("LoginModalCtrl", ["$scope", "AuthSvc", "$modalInstance", function ($scope, authSvc, $modalInstance) {
    $scope.customSignin = function () {
        authSvc.customSignin({
            email: $scope.signinEmail,
            password: $scope.password
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
        authSvc.customRegister($scope.email);
    }
}]);