angular
    .module('app')
    .controller('LessonListController', LessonListController);

function LessonListController($mdDialog, LessonFactory, $state) {
    var self = this;

    self.lessons = [];
    self.isUpdatingChosen = false;

    self.addLesson = addLesson;
    self.deleteLesson = deleteLesson;
    self.editLesson = editLesson;
    self.showSolutions = showSolutions;

    refresh();

    function refresh() {
        LessonFactory.query().$promise.then(function (result) {
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
                lesson: angular.copy(lesson)
            }
        }).then(refresh, refresh);
    }
}


