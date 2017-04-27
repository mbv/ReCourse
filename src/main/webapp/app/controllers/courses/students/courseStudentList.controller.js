angular
    .module('app')
    .controller('CourseStudentListController', CourseStudentListController);

function CourseStudentListController($mdDialog, CourseFactory, $stateParams) {
    var self = this;

    self.course = {};
    self.students = [];

    self.openModal = openModal;
    self.unregisterStudent = unregisterStudent;

    refresh();

    function refresh() {
        CourseFactory.get({ id: $stateParams.id }).$promise.then(function (result) {
            self.course = result;
            self.students = self.course.students || [];
        });
    }

    function unregisterStudent(student) {
        CourseFactory.unregisterStudent({ id: self.course.id, studentId: student.id }, refresh);
    }

    function openModal() {
        $mdDialog.show({
            controller: 'CourseStudentModalController as self',
            templateUrl: 'templates/crud/courses/students/modal.html',
            parent: angular.element(document.body),
            clickOutsideToClose: true,
            locals: {
                courseId: self.course.id
            }
        }).then(refresh, refresh);
    }
}


