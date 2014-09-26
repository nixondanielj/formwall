/// <reference path="../references.js" />
formwallCtrls.controller("FormbuilderCtrl", ["$scope", "$window", "$routeParams", "$timeout", "FormSvc", function ($scope, $window, $routeParams, $timeout, formSvc) {
    var ready = false;
    formSvc.getForm($routeParams.formId, function (newForm) {
        $scope.form = newForm;
        ready = true;
        $scope.displayForm = copyForm($scope.form);
        $scope.$apply();
    });
    var copyForm = function (form) {
        var shallowCopy = function (obj) {
            var copiedObj = {};
            for (var property in obj) {
                copiedObj[property] = obj[property];
            }
            return copiedObj;
        }
        var copiedForm = shallowCopy(form);
        copiedForm.fields = [];
        for (var i = 0; i < form.fields.length; i++) {
            copiedForm.fields.push(shallowCopy(form.fields[i]));
        }
        return copiedForm;
    }
    $scope.$watch('form', function () {
        if (ready) {
            refreshForm();
        }
    }, true);
    $scope.addField = function () {
        $scope.form.fields.push({ type: 'text' })
    }
    $scope.doNothing = function () {

    }
    var refreshForm = function () {
        $scope.displayForm = copyForm($scope.form);
    }
}]);