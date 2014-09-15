/// <reference path="../references.js" />
formwallSvcs.service("AuthSvc", ["$modal", function ($modal) {
    var authed = false;
    var signInWithGoogle = function (now, callback) {
        gapi.auth.authorize({
            client_id: '616786009709-f6g6dm3jaj8jbobng8eo5dp3augg2dmj.apps.googleusercontent.com',
            scope: 'https://www.googleapis.com/auth/userinfo.email',
            immediate: now
        }, function () {
            googleSigninCallback(callback);
        });
    }
    var googleSigninCallback = function (nextCallback) {
        gapi.client.oauth2.userinfo.get().execute(function (resp) {
            if (!resp.code) {
                authed = true;
            }
            if (nextCallback) {
                nextCallback();
            }
        })
    }
    var customSigninCallback = function (resp, nextCallback) {
        if (!resp.code) {
            gapi.auth.setToken(resp.token);
            authed = true;
            if (nextCallback) {
                nextCallback();
            }
        }
    }
    this.customSignin = function (credentials, callback) {
        gapi.client.formwallApi.userEndpoint.signin(credentials)
            .execute(function () {
                customSigninCallback();
                if (callback) {
                    callback();
                }
            });
    }
    this.loudGoogleSignin = function (callback) {
        signInWithGoogle(false, callback);
    }
    this.silentGoogleSignin = function (callback) {
        signInWithGoogle(true, callback);
    }
    this.fireLoginModal = function (returnUrl) {
        $modal.open({
            templateUrl: 'app/views/modals/login.html',
            controller: 'LoginModalCtrl',
            size: 'sm'
        });
    }
    this.signOut = function () {
        gapi.auth.setToken(null);
        authed = false;
    }
    this.isAuthed = function () {
        return authed;
    }
    this.customRegister = function (email, callback) {
        gapi.client.formwallApi.userEndpoint.register({ email: email })
        .execute(function (resp) {
            customSigninCallback(resp, callback);
        });
    }
}]);