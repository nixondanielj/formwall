formwallApp
  .directive('formEditor', function () {
      return {
          restrict: "E",
          scope: {
              form: '='
          },
          controller: function($scope){
              $scope.addField = function () {
                  $scope.form.formFields.push({ type: 'text' });
              }
          },
          templateUrl: '/app/views/directives/formeditor.html'
      };
  });