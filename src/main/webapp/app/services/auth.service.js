angular
    .module('app')
    .service('AuthService', AuthService);

function AuthService($http, $state, $cookies) {

    var self = {
        needToRemember: true,
        isAuthorized: false,
        checkAuthorization: checkAuthorization,
        signIn: signIn,
        signUp: signUp,
        signOut: signOut
    };

    return self;

    function checkAuthorization() {
        var accessToken = $cookies.get('recourse-access-token');
        if (!!accessToken) {
            checkAccessToken(accessToken);
        } else {
            $state.go('signIn');
        }
    }
    
    function checkAccessToken(accessToken) {
        $http(oauthRequestConfig('/ReCourse/oauth/check_token', { token: accessToken })).then(function(response) {
            if (!!response.data.user_name) {
                self.isAuthorized = true;
                injectAccessTokenToOutgoingHttpRequests(accessToken);
                $state.go('home');
            } else {
                $cookies.remove('recourse-access-token');
                $state.go('signIn');
            }
        });
    }

    function signIn(email, password, needToRemember) {
        var requestParams = {
            username: email,
            password: password,
            grant_type: 'password'
        };

        $http(oauthRequestConfig('/ReCourse/oauth/token', requestParams))
            .then(function (response) {
                handleAccessTokenRequest(response, needToRemember);
            });
    }

    function signUp(newUser) {
        $http.post('/ReCourse/api/users/register', newUser).then(function (response) {
            if (response.status === 200) {
                signIn(newUser.email, newUser.password, true);
            }
        });
    }

    function signOut() {
        makeLogoutRequest(function () {
            $cookies.remove('recourse-access-token');
            self.isAuthorized = false;
            $state.go('signIn');
        });
    }

    function handleAccessTokenRequest(response, needToRemember) {
        var accessToken = response.data.access_token;

        if (!!accessToken) {
            injectAccessTokenToOutgoingHttpRequests(accessToken);
            self.isAuthorized = true;
            if (needToRemember) {
                $cookies.put('recourse-access-token', accessToken);
            }
        }

        $state.go('home');
    }

    function injectAccessTokenToOutgoingHttpRequests(token) {
        $http.defaults.headers.common['Authorization'] = 'Bearer ' + token;
    }

    function makeLogoutRequest(callback) {
        $http.post('/ReCourse/api/users/logout').then(callback);
    }

    function oauthRequestConfig(url, params) {
        return {
            url: url,
            method: 'POST',
            headers: {
                'Authorization': 'Basic d2ViX2NsaWVudDpsSDJIdlE0VmszZjhrMFM=',
                'Accept': 'application/json'
            },
            params: params
        };
    }
}
