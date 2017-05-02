angular
    .module('app')
    .controller('TeacherLessonListController', TeacherLessonListController);

function TeacherLessonListController($controller, $mdDialog, AuthService, LessonFactory, $state, self) {
    $controller('LessonListController', {self: self});
    // self.title = 'My lessons';
    // self.lessons = [];
    // self.isUpdatingChosen = false;

    // self.showLesson = showLesson;
    // self.showSolutions = showSolutions;
    self.isFutureLessons = isFutureLessons;
    self.isPastLessons = isPastLessons;
    self.refresh = refresh;

    AuthService.prepareAuthInfo().then(function() {
        self.teacherId = AuthService.user.id;
        refresh();
    });

    function refresh() {
        LessonFactory.getForTeacher({id: self.teacherId}).$promise.then(self.filterLessons)
    }

    function isFutureLessons() {
        return self.lessonsType === 'future';
    }

    function isPastLessons() {
        return !isFutureLessons();
    }

    // function showLesson(lesson) {
    //     openShowModal(lesson);
    // }
    //
    // function showSolutions(lesson) {
    //     $state.go('teacher-solutions', { id: lesson.id });
    // }
    //
    //
    // function openShowModal(lesson) {
    //     $mdDialog.show({
    //         controller: 'TeacherLessonModalController as self',
    //         templateUrl: 'templates/teacher/lessons/modal.html',
    //         parent: angular.element(document.body),
    //         clickOutsideToClose: true,
    //         locals: {
    //             lesson: angular.copy(lesson),
    //             courseId: self.courseId
    //         }
    //     }).then(refresh, refresh);
    // }
}


