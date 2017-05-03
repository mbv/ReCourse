angular
    .module('app')
    .controller('StudentPastLessonListController', StudentPastLessonListController);

function StudentPastLessonListController($controller, $mdDialog, $state, LessonFactory, AuthService) {
    var self = this;
    $controller('StudentLessonListController', { self: self });

    self.title = 'Past lessons';
    self.isUpdatingChosen = false;
    self.lessonsType = 'past';
    self.lessons = [];

    self.showLesson = showLesson;
    self.showSolutions = showSolutions;
    self.refresh = refresh;

    AuthService.prepareAuthInfo().then(function() {
        self.studentId = AuthService.user.id;
        refresh();
    });

    function refresh() {
        LessonFactory.getPastForStudent({ id: self.studentId }).$promise.then(function (result) {
            self.lessons = result;
        })
    }

    function showLesson(lesson) {
        openShowModal(lesson);
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
        }).then(self.refresh, self.refresh);
    }

    function showSolutions(lesson) {
        $state.go('teacher-solutions', { id: lesson.id });
    }
}


