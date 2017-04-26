angular
    .module('app')
    .controller('SignInController', SignInController);

function SignInController(AuthService) {
    var self = this;

    self.email = "";
    self.password = "";
    self.needToRemember = true;
    self.signIn = signIn;

    function signIn() {
        AuthService.signIn(self.email, self.password, self.needToRemember);
    }
}