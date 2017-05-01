angular
    .module('app')
    .controller('UserListController', UserListController);

function UserListController($mdDialog, UserFactory, CourseFactory, $state) {
    // var self = this;

    // self.title = '';
    // self.course = null;
    // self.users = [];
    // self.isUpdatingChosen = false;
    // self.addUser = addUser;
    // self.editUser = editUser;
    // self.registerStudent = registerStudent;
    // self.unregisterStudent = unregisterStudent;
    self.isAllUsers = isAllUsers;
    self.isCourseUsers = isCourseUsers;

    function isAllUsers() {
        return $state.current.name === 'users';
    }

    function isCourseUsers() {
        return $state.current.name === 'course-users';
    }

    // refresh();

    // function refresh() {
    //     if ($stateParams.course) {
    //         self.title = 'Course Students';
    //         CourseFactory.get({ id: $stateParams.course }).$promise.then(function (result) {
    //             self.course = result;
    //             self.users = self.course.students;
    //         });
    //     } else {
    //         self.title = 'Users';
    //         UserFactory.query().$promise.then(function (result) {
    //             self.users = result;
    //         });
    //     }
    // }
    //
    // function addUser() {
    //     openEditModal();
    // }
    //
    // function editUser(user) {
    //     openEditModal(user);
    // }
    //
    // function registerStudent() {
    //     openRegisterModal();
    // }
    //
    // function unregisterStudent(user) {
    //     CourseFactory.unregisterStudent({ id: self.course.id, studentId: user.id }, refresh);
    // }
    //
    // function openEditModal(user) {
    //     $mdDialog.show({
    //         controller: 'UserModalController as self',
    //         templateUrl: 'templates/crud/users/modal.html',
    //         parent: angular.element(document.body),
    //         clickOutsideToClose: true,
    //         locals: {
    //             user: angular.copy(user)
    //         }
    //     }).then(refresh, refresh);
    // }
    //
    // function openRegisterModal() {
    //     $mdDialog.show({
    //         controller: 'CourseStudentModalController as self',
    //         templateUrl: 'templates/crud/courses/students/modal.html',
    //         parent: angular.element(document.body),
    //         clickOutsideToClose: true,
    //         locals: {
    //             courseId: self.course.id
    //         }
    //     }).then(refresh, refresh);
    // }
}


