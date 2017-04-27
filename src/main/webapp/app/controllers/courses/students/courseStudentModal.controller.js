angular
    .module('app')
    .controller('CourseStudentModalController', CourseStudentModalController);

function CourseStudentModalController($mdDialog, UserFactory, CourseFactory, courseId) {
    var self = this;

    self.student = null;
    self.students = [];

    UserFactory.query().$promise.then(function (result) {
        self.students = result.filter(function (user) { return user.role === 'STUDENT' });
    });

    self.registerStudent = registerStudent;
    self.cancel = cancel;

    function registerStudent() {
        CourseFactory.registerStudent({ id: courseId, studentId: self.student.id }, $mdDialog.hide);
    }

    function cancel() {
        $mdDialog.cancel();
    }
}