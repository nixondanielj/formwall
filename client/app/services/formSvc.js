formwallSvcs.service("FormSvc", ['$http', function ($http) {
    this.getForm = function (id, callback) {
        if (!id) {
            getNewForm(callback);
        }
    }
    var getNewForm = function (callback) {
        gapi.client.formwallApi.formEndpoint.getNew().execute(function (resp) {
            if (resp.code) {
                console.log(resp);
            } else if (callback) {
                callback(resp);
            }
        });
    }

    this.saveForm = function (form, callback) {
        gapi.client.formwallApi.formEndpoint.post(form).execute(function (resp) {
            if (resp.code) {
                console.log(resp);
            }else if (callback) {
                callback(resp);
            }
        });
    }
}]);