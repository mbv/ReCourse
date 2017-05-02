angular
    .module('app')
    .controller('TeacherFutureLessonListController', TeacherFutureLessonListController);

function TeacherFutureLessonListController($controller, $mdDialog) {
    var self = this;
    self.filterLessons = filterLessons;
    $controller('TeacherLessonListController', {self: self});
    self.title = 'Future lessons';
    self.isUpdatingChosen = false;
    self.lessonsType = 'future';
    self.showLesson = showLesson;


    function filterLessons(lessons) {
        self.lessons = lessons.filter(function (lesson) {
            return lesson.startTime > (+new Date());
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
}


