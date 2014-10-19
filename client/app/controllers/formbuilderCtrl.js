/// <reference path="../references.js" />
formwallCtrls.controller("FormbuilderCtrl", ["$scope", "$window", "$routeParams", "FormSvc", "AuthSvc", function ($scope, $window, $routeParams, formSvc, authSvc) {
    var ready = false;
    authSvc.callAfterCurrentSignin(function () {
        formSvc.getForm($routeParams.formId, function (newForm) {
            $scope.form = newForm;
            ready = true;
            $scope.displayForm = copyForm($scope.form);
            $scope.$apply();
        });
    });

    $scope.saveForm = function (form) {
        formSvc.saveForm(form);
    }
    $scope.publishForm = function (form) {
        form.active = true;
        saveForm(form);
    }
    var copyForm = function (form) {
        var shallowCopy = function (obj) {
            var copiedObj = {};
            for (var property in obj) {
                copiedObj[property] = obj[property];
            }
            return copiedObj;
        }
        var copiedForm = shallowCopy(form);
        copiedForm.formFields = [];
        for (var i = 0; i < form.formFields.length; i++) {
            copiedForm.formFields.push(shallowCopy(form.formFields[i]));
        }
        return copiedForm;
    }
    $scope.$watch('form', function () {
        if (ready) {
            refreshForm();
        }
    }, true);
    $scope.doNothing = function () {

    }
    var refreshForm = function () {
        $scope.displayForm = copyForm($scope.form);
    }
}]);