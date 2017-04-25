angular
    .module('app')
    .service('AuthService', AuthService);

function AuthService($http, $state, $cookies) {

    var self = {
        isAuthorized: false,
        tryAuthorize: tryAuthorize,
        unauthorize: unauthorize,
        signIn: signIn,
        signUp: signUp,
        signOut: signOut
    };

    return self;

    function tryAuthorize() {
        var accessToken = $cookies.get('recourse-access-token');
        if (!!accessToken) {
            self.isAuthorized = true;
            injectAccessTokenToOutgoingHttpRequests(accessToken);
        }
    }
    function unauthorize() {
        $cookies.remove('recourse-access-token');
        rejectAccessTokenToOutgoingHttpRequests();
        self.isAuthorized = false;
        $state.go('root');
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
        makeLogoutRequest(self.unauthorize);
    }

    function handleAccessTokenRequest(response, needToRemember) {
        var accessToken = response.data.access_token;
        var tokenExpiresIn = response.data.expires_in;

        if (!!accessToken) {
            injectAccessTokenToOutgoingHttpRequests(accessToken);
            self.isAuthorized = true;
            if (needToRemember) {
                $cookies.put('recourse-access-token', accessToken, { expires: new Date() + tokenExpiresIn } );
            }
        }

        $state.go('root');
    }

    function injectAccessTokenToOutgoingHttpRequests(token) {
        $http.defaults.headers.common['Authorization'] = 'Bearer ' + token;
    }

    function rejectAccessTokenToOutgoingHttpRequests() {
        $http.defaults.headers.common['Authorization'] = undefined;
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
