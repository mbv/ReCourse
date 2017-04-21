angular
    .module('app')
    .service('AuthService', AuthService);

function AuthService($http, $state, $cookies) {

    var self = {
        needToRemember: true,
        isAuthorized: isAuthorized,
        checkAuthorization: checkAuthorization,
        login: login,
        logout: logout
    };

    return self;

    function checkAuthorization(toState) {
        refreshToken = $cookies.get('recourse-refresh-token');
        if (!!refreshToken) {
            loginWithCookieStoredRefreshToken(refreshToken);
        } else {
            $state.go('login');
        }
    }

    function isAuthorized() {
        return !!self.needToRemember && !!$cookies.get('recourse-refresh-token');
    }

    function login(username, password, needToRemember) {
        self.needToRemember = needToRemember;
        makeAccessTokenRequest({
            username: username,
            password: password,
            grant_type: 'password'
        });
    }

    function loginWithCookieStoredRefreshToken(refreshToken) {
        makeAccessTokenRequest({
            refresh_token: refreshToken,
            grant_type: 'refresh_token'
        });
    }

    function makeAccessTokenRequest(requestParams) {
        $http(oauthRequestConfig('/ReCourse/oauth/token', requestParams))
            .then(handleAccessTokenRequest);
    }

    function logout() {
        rejectAccessTokenFromOutgoingHttpRequests();
        $cookies.remove('recourse-refresh-token');
        $state.go('home');
    }

    function handleAccessTokenRequest(response) {
        var accessToken = response.data.access_token;
        var refreshToken = response.data.refresh_token;

        if (!!accessToken && !!refreshToken) {
            injectAccessTokenToOutgoingHttpRequests(accessToken);
            if (self.needToRemember) {
                $cookies.put('recourse-refresh-token', refreshToken);
            }
        }
        $state.go('home');
    }

    function injectAccessTokenToOutgoingHttpRequests(token) {
        $http.defaults.headers.common['Authorization'] = 'Bearer ' + token;
    }

    function rejectAccessTokenFromOutgoingHttpRequests() {
        $http.defaults.headers.common['Authorization'] = undefined;
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
