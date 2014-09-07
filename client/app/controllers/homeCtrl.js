/// <reference path="../references.js" />
formwallCtrls.controller("HomeCtrl", ['$scope', '$window', 'AuthSvc', function ($scope, $window, authSvc) {
    $scope.slides = [
        {
            img: '/app/img/default.jpg',
            header: 'Stop Losing Leads!',
            body: "They're already clicking on your content - get the info you need to make the sale!"
        },
        {
            img: '/app/img/default.jpg',
            header: 'Start Collecting Data in Minutes!',
            body: 'Using our form builder and simple interface, anyone can create data collection tools'
        },
        {
            img: '/app/img/default.jpg',
            header: 'Register Now!',
            body: 'Sign up using your email or existing Google Account and get started in seconds'
        }
    ]
    // aggravating init logic...
    $window.init = function () {
        gapi.client.load('formwallApi', 'v1', function () {
            $scope.is_backend_ready = true;
            $scope.$apply();
        }, root);
    }

    $scope.register = function () {
        gapi.client.formwallApi.userEndpoint.register({ email: $scope.email })
            .execute(function(resp){
                console.log(resp);
            });
    }
    $scope.signinWithGoogle = function () {
        authSvc.signin(false);
    }
}]);