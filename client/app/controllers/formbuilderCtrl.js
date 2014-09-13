/// <reference path="../references.js" />
formwallCtrls.controller("FormbuilderCtrl", ["$scope", "$window", "$routeParams", "$timeout", function ($scope, $window, $routeParams, $timeout) {
    $window.init = function () {
        gapi.client.load('formwallApi', 'v1', function () {
            $scope.is_backend_ready = true;
            $scope.$apply();
        }, root);
    }
    $scope.form = {
        title: "demo form",
        availableFieldTypes: ['text', 'email', 'phone', 'zip', 'color', 'date'],
        fields: [
            {
                id: 1,
                fieldTypeId: 7,
                label: 'my first field',
                required: true,
                type: 'color',
                placeholder: '#ffff00'
            }
        ]
    };
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
    $scope.displayForm = copyForm($scope.form);
    $scope.$watch('form', function () {
        refreshForm();
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