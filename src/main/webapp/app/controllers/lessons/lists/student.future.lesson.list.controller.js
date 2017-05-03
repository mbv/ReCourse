angular
    .module('app')
    .controller('StudentFutureLessonListController', StudentFutureLessonListController);

function StudentFutureLessonListController($controller, $mdDialog, LessonFactory, AuthService) {
    var self = this;
    $controller('StudentLessonListController', { self: self });

    self.title = 'Future lessons';
    self.isUpdatingChosen = false;
    self.lessonsType = 'future';
    self.lessons = [];

    self.showLesson = showLesson;
    self.refresh = refresh;

    AuthService.prepareAuthInfo().then(function() {
        self.studentId = AuthService.user.id;
        refresh();
    });

    function refresh() {
        LessonFactory.getFutureForStudent({ id: self.studentId }).$promise.then(function (result) {
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
}


