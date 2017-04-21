angular
    .module('app')
    .controller('NavigationController', NavigationController);

function NavigationController(AuthService) {
    var self = this;

    self.signUp = function() {}
    self.authService = AuthService;
}
