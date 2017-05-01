angular
    .module('app')
    .run(InitAuthenticationService);

function InitAuthenticationService(AuthService, $rootScope, $state) {
    $rootScope.$on('$stateChangeStart', function (event, toState) {
        AuthService.prepareAuthInfo().then(function () {
            if (!AuthService.isAuthorized) {
                if (toState.name !== 'signIn' && toState.name !== 'signUp') {
                    event.preventDefault();
                    $state.go('signIn');
                }
            }
        });
    });
}
