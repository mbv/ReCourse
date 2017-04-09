angular
    .module('app')
    .controller('UsersListController', UsersListController);

function UsersListController($controller) {
    var self = this;
    self.genders = ['MALE', 'FEMALE'];
    self.roles = ['STUDENT', 'TEACHER', 'ADMIN'];
    $controller('EntityListController', { resourceName: 'users', self: self });
}
