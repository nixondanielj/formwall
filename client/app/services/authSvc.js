/// <reference path="../references.js" />
formwallSvcs.factory("AuthSvc", function () {
    return {
        signin: function(now, callback){
            gapi.auth.authorize({
                client_id: '616786009709-f6g6dm3jaj8jbobng8eo5dp3augg2dmj.apps.googleusercontent.com',
                scope: 'https://www.googleapis.com/auth/userinfo.email',
                immediate: now
            }, callback || function () { });
        },
        signout: function () {
            gapi.auth.setToken(null);
        }
    }
});