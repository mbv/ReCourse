angular
    .module('app')
    .controller('CoursesListController', CoursesListController);

function CoursesListController($resource, $controller) {
    var self = this;
    self.statuses = ['ONGOING', 'REGISTRATION', 'FINISHED'];
    self.teachers = [];

    $resource('users/:id').query().$promise.then(function (result) {
        self.organizers = result.filter(function (user) { return user.role === 'TEACHER' });
    });
    $controller('EntityListController', { resourceName: 'courses', self: self });
}
