/// <reference path="../references.js" />
formwallSvcs.service("AuthSvc", ["$modal", function ($modal) {
    var authed = false;
    var currentUser = {};
    var inLoginAttempt = false;
    var postSigninCallbacks = [];
    var _appendCallback = function (callback) {
        if (callback) {
            postSigninCallbacks.push(callback);
        }
    }
    var _prependCallback = function (callback) {
        if (callback) {
            postSigninCallbacks.unshift(callback);
        }
    }
    this.callAfterCurrentSignin = function (callback) {
        if (inLoginAttempt) {
            _appendCallback(callback);
        } else if(callback) {
            callback();
        }
    }
    this.callAfterNextSignin = _appendCallback;
    var executeSigninCallbacks = function (resp) {
        for (var i = 0; i < postSigninCallbacks.length; i++) {
            postSigninCallbacks[i](resp);
        }
        postSigninCallbacks = [];
    }
    this.isInLoginAttempt = function () {
        return inLoginAttempt;
    };
    var signInWithGoogle = function (now) {
        authed = false;
        inLoginAttempt = true;
        gapi.auth.authorize({
            client_id: '616786009709-f6g6dm3jaj8jbobng8eo5dp3augg2dmj.apps.googleusercontent.com',
            scope: 'https://www.googleapis.com/auth/userinfo.email',
            immediate: now
        }, googleSigninCallback);
    }
    var googleSigninCallback = function () {
        gapi.client.oauth2.userinfo.get().execute(function (resp) {
            if (!resp.code) {
                authed = true;
                currentUser = resp;
            }
            inLoginAttempt = false;
            executeSigninCallbacks(resp);
        });
    }
    var customSigninCallback = function (resp) {
        if (!resp.code) {
            gapi.auth.setToken({ access_token: resp.token });
            authed = true;
            currentUser.email = resp.identifier;
        }
        inLoginAttempt = false;
    }
    this.customSignin = function (credentials) {
        inLoginAttempt = true;
        _prependCallback(customSigninCallback);
        gapi.client.formwallApi.userEndpoint.signin(credentials)
            .execute(executeSigninCallbacks);
    }
    this.getCurrentUser = function () {
        return currentUser;
    }
    this.loudGoogleSignin = function () {
        signInWithGoogle(false);
    }
    this.silentGoogleSignin = function () {
        signInWithGoogle(true);
    }
    this.signOut = function () {
        gapi.auth.setToken(null);
        authed = false;
        currentUser = {};
    }
    this.isAuthed = function () {
        return authed;
    }
    this.customRegister = function (email) {
        _prependCallback(customSigninCallback);
        gapi.client.formwallApi.userEndpoint.register({ email: email })
        .execute(executeSigninCallbacks);
    }
    this.fireLoginModal = function () {
        $modal.open({
            templateUrl: 'app/views/modals/login.html',
            controller: 'LoginModalCtrl',
            size: 'lg'
        });
    }
}]);