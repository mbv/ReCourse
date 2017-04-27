angular
    .module('app')
    .controller('CourseLessonModalController', CourseLessonModalController);

function CourseLessonModalController($mdDialog, UserFactory, LessonFactory, lesson, courseId) {
    var self = this;

    if (lesson && lesson.startTime) {
        lesson.startTime = new Date(lesson.startTime);
    }

    self.lesson = lesson;
    self.saveLesson = saveLesson;
    self.cancel = cancel;
    self.updateMode = !!self.lesson;

    self.teachers = [];

    UserFactory.query().$promise.then(function (result) {
        self.teachers = result.filter(function (user) { return user.role === 'TEACHER' });
    });

    function saveLesson() {
        self.lesson.courseId = courseId;
        if (self.updateMode){
            LessonFactory.update(self.lesson, $mdDialog.hide);
        } else {
            LessonFactory.save(self.lesson, $mdDialog.hide);
        }
    }

    function cancel() {
        $mdDialog.cancel();
    }
}