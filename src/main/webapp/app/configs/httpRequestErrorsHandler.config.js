angular
    .module('app')
    .config(HttpRequestErrorsHandler);

function HttpRequestErrorsHandler($httpProvider) {
    $httpProvider.interceptors.push(interceptor);

    function interceptor($q, $rootScope) {
        return {
            responseError: function(response) {
                var errors = [];
                if (!!response.data.errors) {
                    errors.concat(response.data.errors);
                } else if (!!response.data.error_description) {
                    errors.push({ title: 'Auth', message: response.data.error_description })
                }
                $rootScope.$broadcast('httpError', errors);
                return $q.reject(response);
            }
        };
    }
}