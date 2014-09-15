/// <reference path="../references.js" />
formwallCtrls.controller("HomeCtrl", ['$scope', '$window', 'AuthSvc', function ($scope, $window, authSvc) {
    $scope.slides = [
        {
            img: '/app/img/default.jpg',
            header: 'Stop Losing Leads!',
            body: "Your potential customers are already clicking on your content - get the info you need to make the sale!",
            active: true
        },
        {
            img: '/app/img/default.jpg',
            header: 'Start Collecting Data in Minutes!',
            body: 'Using our form builder and simple interface, anyone can create data collection tools',
            active: false
        },
        {
            img: '/app/img/default.jpg',
            header: 'Register Now!',
            body: 'Sign up using your email or existing Google Account and get started in seconds',
            active: false
        }
    ];
    $scope.carouselInterval = 5000;
    

    $scope.register = function () {
        authSvc.registerCustom($scope.email);
    }
    $scope.signinWithGoogle = function () {
        authSvc.signin(false);
    }
}]);