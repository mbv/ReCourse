angular
    .module('app')
    .controller('CourseListController', CourseListController);

function CourseListController($mdDialog, CourseFactory) {
    var self = this;

    self.courses = [];
    self.isUpdatingChosen = false;

    self.addCourse = addCourse;
    self.deleteCourse = deleteCourse;
    self.editCourse = editCourse;

    refresh();

    function refresh() {
        CourseFactory.query().$promise.then(function (result) {
            self.courses = result;
        });
    }

    function addCourse() {
        openModal();
    }

    function deleteCourse(course) {
        CourseFactory.delete(course, refresh);
    }

    function editCourse (course) {
        openModal(course);
    }

    function openModal(course) {
        $mdDialog.show({
            controller: 'CourseModalController as self',
            templateUrl: 'templates/crud/courses/modal.html',
            parent: angular.element(document.body),
            clickOutsideToClose: true,
            locals: {
                course: angular.copy(course)
            }
        }).then(refresh, refresh);
    }
}


