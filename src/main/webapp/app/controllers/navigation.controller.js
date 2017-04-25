angular
    .module('app')
    .controller('NavigationController', NavigationController);

function NavigationController(AuthService) {
    var self = this;

    self.isAuthorized = isAuthorized;
    self.signOut = signOut;

    function isAuthorized() {
        return AuthService.isAuthorized;
    }

    function signOut() {
        AuthService.signOut();
    }
}
