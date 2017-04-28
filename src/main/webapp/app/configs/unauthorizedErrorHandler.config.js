angular
    .module('app')
    .config(UnauthorizedRequestHandler);

function UnauthorizedRequestHandler($httpProvider) {
    $httpProvider.interceptors.push(interceptor);

    function interceptor($injector) {
        return {
            responseError: function(response) {
                if (response.status === 401) {
                    $injector.get('AuthService').unauthorize();
                }
            }
        };
    }
}