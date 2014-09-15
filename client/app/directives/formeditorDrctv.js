formwallApp
  .directive('formEditor', function () {
      return {
          restrict: "E",
          scope: {
              form: '='
          },
          templateUrl: '/app/views/directives/formeditor.html'
      };
  });