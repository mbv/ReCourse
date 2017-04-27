angular
    .module('app')
    .controller('CourseLessonListController', CourseLessonListController);

function CourseLessonListController($mdDialog, CourseFactory, $stateParams) {
    var self = this;

    self.courseId = $stateParams.id;
    self.lessons = [];
    self.isUpdatingChosen = false;

    self.addLesson = addLesson;
    self.deleteLesson = deleteLesson;
    self.editLesson = editLesson;

    refresh();

    function refresh() {
        CourseFactory.getLessons({ id: self.courseId }).$promise.then(function (result) {
            self.lessons = result;
        });
    }

    function addLesson() {
        openModal();
    }

    function deleteLesson(lesson ) {
        LessonFactory.delete(lesson, refresh);
    }

    function editLesson (lesson) {
        openModal(lesson);
    }

    function openModal(lesson) {
        $mdDialog.show({
            controller: 'CourseLessonModalController as self',
            templateUrl: 'templates/crud/courses/lessons/modal.html',
            parent: angular.element(document.body),
            clickOutsideToClose: true,
            locals: {
                lesson: angular.copy(lesson),
                courseId: self.courseId
            }
        }).then(refresh, refresh);
    }
}


