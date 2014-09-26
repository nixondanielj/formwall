formwallSvcs.service("FormSvc", [function () {
    this.getForm = function (id, callback) {
        if (!id) {
            getNewForm(callback);
        }
    }
    var getNewForm = function (callback) {
        gapi.client.formwallApi.formEndpoint.getNew().execute(function (resp) {
            if (!resp.code) {
                if (callback) {
                    callback(resp);
                }
            }
        });
    }
}]);