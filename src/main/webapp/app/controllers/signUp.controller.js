angular
    .module('app')
    .controller('SignUpController', SignUpController);

function SignUpController(AuthService) {
    var self = this;

    self.email = null;
    self.name = null;
    self.surname = null;
    self.password = null;
    self.passwordConfirmation = null;
    self.gender = null;
    self.birthday = null;

    self.genders = ['MALE', 'FEMALE'];
    self.signUp = signUp;

    function signUp() {
        AuthService.signUp({
            email: self.email,
            name: self.name,
            surname: self.surname,
            password: self.password,
            passwordConfirmation: self.passwordConfirmation,
            gender: self.gender,
            birthday: self.birthday
        });
    }
}