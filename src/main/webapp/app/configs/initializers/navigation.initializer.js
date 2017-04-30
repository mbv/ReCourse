angular
    .module('app')
    .run(InitNavigation);

function InitNavigation($rootScope) {
    $rootScope.currentNavItem = "signIn";
    $rootScope.$on('$stateChangeSuccess', function(event, toState) {
        $rootScope.currentNavItem = toState.name;
    });
}
