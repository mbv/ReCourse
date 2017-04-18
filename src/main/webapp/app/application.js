angular
    .module('app', ['ui.router', 'ngResource', 'ui.bootstrap', 'ui.select', 'ngSanitize']).run(AuthServiceInitializer);

function AuthServiceInitializer(AuthService, $rootScope, $state) {
    $rootScope.$on('$stateChangeStart', function(event, toState) {
        if (!AuthService.isAuthorized) {
            if (toState.name != 'login' && toState.name != 'register') {
                event.preventDefault();
                $state.go('login');
            }
        }
    });
}


