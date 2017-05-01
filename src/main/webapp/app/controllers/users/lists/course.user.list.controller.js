angular
    .module('app')
    .controller('CourseUserListController', CourseUserListController);

function CourseUserListController($mdDialog, UserFactory, $stateParams, $controller) {
    var self = this;
    $controller('UserListController', {self: self});

    self.title = 'Course';
    self.subtitle = 'Students';
    self.course = $stateParams.id;
    self.users = [];
    self.registerStudent = registerStudent;
    self.unregisterStudent = unregisterStudent;

    refresh();

    function refresh() {
        UserFactory.getForCourse({ id: self.course }).$promise.then(function (result) {
            self.users = result;
        });
    }

    function registerStudent() {
        openRegisterModal();
    }

    function unregisterStudent(user) {
        CourseFactory.unregisterStudent({ id: self.course, studentId: user.id }, refresh);
    }

    function openRegisterModal() {
        $mdDialog.show({
            controller: 'CourseStudentModalController as self',
            templateUrl: 'templates/students/modal.html',
            parent: angular.element(document.body),
            clickOutsideToClose: true,
            locals: {
                courseId: self.course
            }
        }).then(refresh, refresh);
    }
}


