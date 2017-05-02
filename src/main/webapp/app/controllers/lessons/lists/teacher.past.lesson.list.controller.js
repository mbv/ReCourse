angular
    .module('app')
    .controller('TeacherPastLessonListController', TeacherPastLessonListController);

function TeacherPastLessonListController($controller, $mdDialog, $state) {
    var self = this;
    self.filterLessons = filterLessons;
    $controller('TeacherLessonListController', {self: self});
    self.title = 'Past lessons';
    self.isUpdatingChosen = false;
    self.lessonsType = 'past';
    self.showLesson = showLesson;
    self.showSolutions = showSolutions;


    function filterLessons(lessons) {
        self.lessons = lessons.filter(function (lesson) {
            return lesson.startTime <= (+new Date());
        });
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


