/// <reference path="../references.js" />
formwallSvcs.factory("AuthSvc", function () {
    var svc = {
        signInWithGoogle: function (now, callback) {
            gapi.auth.authorize({
                client_id: '616786009709-f6g6dm3jaj8jbobng8eo5dp3augg2dmj.apps.googleusercontent.com',
                scope: 'https://www.googleapis.com/auth/userinfo.email',
                immediate: now
            }, callback);
        },
        signOut: function () {
            gapi.auth.setToken(null);
        },
        isAuthed: false,
        signInCustom: function (email, password) {

        },
        registerCustom: function (email) {
            gapi.client.formwallApi.userEndpoint.register({ email: email })
            .execute(function (resp) {
                console.log(resp);
            });
        }
    };
    return svc;
});