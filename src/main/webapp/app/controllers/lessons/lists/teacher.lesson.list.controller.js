angular
    .module('app')
    .controller('TeacherLessonListController', TeacherLessonListController);

function TeacherLessonListController($controller, $mdDialog, AuthService, LessonFactory, $state) {
    var self = this;
    $controller('LessonListController', {self: self});
    self.title = 'My lessons';
    self.lessons = [];
    self.isUpdatingChosen = false;

    self.showLesson = showLesson;
    self.showSolutions = showSolutions;
    self.teacherId = AuthService.user.id;
    self.lessonsTypes = ['past', 'future'];

    refresh();

    function refresh() {
        LessonFactory.getForTeacher({id: self.teacherId}).$promise.then(function (result) {
            self.lessons = result;
        })
    }

    function showLesson(lesson) {
        openShowModal(lesson);
    }

    function showSolutions(lesson) {
        $state.go('teacher.solutions', { id: lesson.id });
    }


    function openShowModal(lesson) {
        $mdDialog.show({
            controller: 'TeacherLessonModalController as self',
            templateUrl: 'templates/teacher/lessons/modal.html',
            parent: angular.element(document.body),
            clickOutsideToClose: true,
            locals: {
                lesson: angular.copy(lesson),
                courseId: self.courseId
            }
        }).then(refresh, refresh);
    }
}


