angular
    .module('app')
    .controller('RootController', RootController);

function RootController(AuthService, $state) {
    AuthService.prepareAuthInfo().then(function () {
        if (AuthService.role === 'ADMIN') {
            $state.go('users')
        } else if (AuthService.role === 'TEACHER') {
            $state.go('teacher-lessons');
        } else {
            $state.go('signIn');
        }
    });
}
