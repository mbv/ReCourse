angular
    .module('app', [
        'ui.router',
        'ngResource',
        'ngSanitize',
        'ngCookies',
        'ngAnimate',
        'ngMaterial',
        'angular-loading-bar',
        'md.data.table'
    ])
    .config(UnauthorizedRequestHandler)
    .run(InitAuthenticationService);


function UnauthorizedRequestHandler($httpProvider) {
    $httpProvider.interceptors.push(interceptor);

    function interceptor($injector) {
        return {
        responseError: function(response) {
            if (response.status === 401) {
                // $injector.get('AuthService').checkAuthorization();
            }
        }
    };
  }
}

function InitAuthenticationService(AuthService, $rootScope, $state) {
  $rootScope.$on('$stateChangeStart', function(event, toState) {
    if (!AuthService.isAuthorized) {
      if (toState.name !== 'signIn' && toState.name !== 'signUp') {
        event.preventDefault();
        $state.go('signIn');
      }
    }
  });
  AuthService.checkAuthorization();
}
