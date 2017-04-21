angular
    .module('app')
    .controller('LoginController', LoginController);

function LoginController(AuthService) {
    var self = this;
    self.username = "";
    self.password = ""
    self.needToRemember = true;
    self.login = login;

    function login() {
        AuthService.login(self.username, self.password, self.needToRemember);
    }
}
