angular
    .module('app')
    .run(InitAuthenticationService);

function InitAuthenticationService(AuthService, $rootScope, $state) {
    AuthService.tryAuthorize();
    $rootScope.$on('$stateChangeStart', function(event, toState) {
        if (!AuthService.isAuthorized) {
            if (toState.name !== 'signIn' && toState.name !== 'signUp') {
                event.preventDefault();
                $state.go('signIn');
            }
        }
    });
}
