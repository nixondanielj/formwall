﻿formwallApp
  .directive('formwallNav', function () {
      return {
          restrict: "E",
          controller: "FormwallNavCtrl",
          templateUrl: '/app/views/directives/formwallnav.html'
      };
  });

formwallCtrls.controller('FormwallNavCtrl', ["$modal", '$scope', 'AuthSvc', '$location', function ($modal, $scope, authSvc, $location) {
    $scope.login = function () {
        authSvc.signOut();
        authSvc.fireLoginModal();
    }
    $scope.getUser = function () {
        return authSvc.getCurrentUser();
    }
    $scope.isAuthed = function(){
        return authSvc.isAuthed();
    }
    $scope.signOut = function () {
        authSvc.signOut();
        $location.path('/');
    }
}]);