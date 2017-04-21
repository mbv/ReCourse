angular
    .module('app', ['ui.router', 'ngResource', 'ui.bootstrap', 'ui.select', 'ngSanitize', 'ngCookies'])
    .run(InitAuthenticationService)
    .config(UnauthorizedRequestHandler);

function UnauthorizedRequestHandler($injector, $httpProvider) {
  $httpProvider.interceptors.push(interceptor);

  function interceptor($injector) {
    return {
      responseError: function(response) {
        if (response.status == 401) {
          $injector.get('$state').transitionTo('login');
        }
      }
    };
  }
}

function InitAuthenticationService(AuthService, $rootScope, $state) {
  AuthService.checkAuthorization()
  $rootScope.$on('$stateChangeStart', function(event, toState) {
    if (!AuthService.isAuthorized()) {
      if (toState.name != 'login' && toState.name != 'register') {
        event.preventDefault();
        $state.go('login');
      }
    } else {
      if (toState.name == 'login') {
        event.preventDefault();
        $state.go('home');
      }
    }
  });
}
