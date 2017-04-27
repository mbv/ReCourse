angular
    .module('app')
    .controller('LessonListController', LessonListController);

function LessonListController($mdDialog, LessonFactory, CourseFactory, $state, $stateParams) {
    var self = this;

    self.title = '';
    self.lessons = [];
    self.isUpdatingChosen = false;

    self.addLesson = addLesson;
    self.deleteLesson = deleteLesson;
    self.editLesson = editLesson;
    self.showSolutions = showSolutions;
    self.courseId = $stateParams.course;

    refresh();

    function refresh() {
        if (self.courseId){
            self.title = 'Course Lessons';
            CourseFactory.getLessons({ id: self.courseId }).$promise.then(function (result) {
                self.lessons = result;
            });
        } else {
            self.title = 'Lessons';
            LessonFactory.query().$promise.then(function (result) {
                self.lessons = result;
            });
        }
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

    function showSolutions(lesson) {
        $state.go('crud.lessons.solutions', { id: lesson.id });
    }

    function openModal(lesson) {
        $mdDialog.show({
            controller: 'LessonModalController as self',
            templateUrl: 'templates/crud/lessons/modal.html',
            parent: angular.element(document.body),
            clickOutsideToClose: true,
            locals: {
                lesson: angular.copy(lesson),
                courseId: self.courseId
            }
        }).then(refresh, refresh);
    }
}


