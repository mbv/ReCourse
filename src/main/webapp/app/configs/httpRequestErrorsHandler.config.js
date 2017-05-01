angular
    .module('app')
    .config(HttpRequestErrorsHandler);

function HttpRequestErrorsHandler($httpProvider) {
    $httpProvider.interceptors.push(interceptor);

    function interceptor($q, $rootScope) {
        return {
            responseError: function(response) {
                $rootScope.$broadcast('httpError',response.data.errors);
                return $q.reject(response);
            }
        };
    }
}