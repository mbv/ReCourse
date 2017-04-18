angular
    .module('app')
    .controller('LoginController', LoginController);

function LoginController($http, $state, AuthService) {
    var self = this;
    self.user = { username: "", password: "" };
    self.needToRemember = true;
    self.login = login;
    self.successCallback = successCallback;
    self.errorCallback = errorCallback;

    function login() {
        var tokenRequestConfig = {
            url: '/ReCourse/oauth/token',
            method: 'POST',
            headers: {
                'Authorization': 'Basic d2ViX2NsaWVudDpsSDJIdlE0VmszZjhrMFM=',
                'Accept': 'application/json'
            },
            params: {
                username: self.user.username,
                password: self.user.password,
                grant_type: 'password'
            }
        };
        $http(tokenRequestConfig).then(self.successCallback, self.errorCallback);
    }

    function successCallback(response) {
        self.password = null;
        if (response.data.access_token) {
            $http.defaults.headers.common['Authorization'] = 'Bearer ' + response.data.access_token;
            AuthService.isAuthorized = true;
            // $rootScope.$broadcast('LoginSuccessful');
            $state.go('home');
        } else {
            alert('Error!');
        }
    }

    function errorCallback() {
        alert('Error!');
    }
}
