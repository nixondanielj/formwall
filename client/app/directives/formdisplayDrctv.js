formwallApp
  .directive('formDisplay', function () {
      return {
          restrict: "E",
          scope: {
              form: '='
          },
          templateUrl: '/app/views/directives/formdisplay.html'
      };
  });