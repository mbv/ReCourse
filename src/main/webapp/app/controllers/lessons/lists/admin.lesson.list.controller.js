angular
    .module('app')
    .controller('AdminLessonListController', AdminLessonListController);

function AdminLessonListController($controller, $mdDialog, $stateParams, CourseFactory, LessonFactory, $state) {
    var self = this;
    $controller('LessonListController', {self: self});
    self.lessons = [];
    self.title = 'Course lessons';
    self.isUpdatingChosen = false;

    self.addLesson = addLesson;
    self.deleteLesson = deleteLesson;
    self.editLesson = editLesson;
    self.showSolutions = showSolutions;
    self.courseId = $stateParams.course;

    refresh();

    function refresh() {
        CourseFactory.getLessons({id: self.courseId}).$promise.then(function (result) {
            self.lessons = result;
        });
    }


    function addLesson() {
        openEditModal();
    }

    function deleteLesson(lesson ) {
        LessonFactory.delete(lesson, refresh);
    }

    function editLesson (lesson) {
        openEditModal(lesson);
    }

    function showSolutions(lesson) {
        $state.go('crud.lessons.solutions', { id: lesson.id });
    }

    function openEditModal(lesson) {
        $mdDialog.show({
            controller: 'LessonModalController as self',
            templateUrl: 'templates/lessons/modal.html',
            parent: angular.element(document.body),
            clickOutsideToClose: true,
            locals: {
                lesson: angular.copy(lesson),
                courseId: self.courseId
            }
        }).then(refresh, refresh);
    }
}


