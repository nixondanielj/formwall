formwallApp
  .directive('formwallNav', function () {
      return {
          restrict: "E",
          controller: "FormwallNavCtrl",
          templateUrl: '/app/views/directives/formwallnav.html'
      };
  });

formwallCtrls.controller('FormwallNavCtrl', ['$scope', 'AuthSvc', function ($scope, authSvc) {
    $scope.login = function () {
        authSvc.signOut();
        authSvc.silentGoogleSignin(function () {
            if (!authSvc.isAuthed()) {
                authSvc.fireLoginModal();
            }
        });
    }
    $scope.isAuthed = function(){
        return authSvc.isAuthed();
    }
}]);