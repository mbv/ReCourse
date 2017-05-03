angular
    .module('app')
    .controller('TeacherLessonModalController', TeacherLessonModalController);

function TeacherLessonModalController($mdDialog, LessonFactory, CourseFactory, lesson) {
    var self = this;

    if (lesson && lesson.startTime) {
        lesson.startTime = new Date(lesson.startTime);
    }

    self.lesson = lesson;
    self.cancel = cancel;
    self.course = {};
    self.saveHometask = saveHometask;
    self.now = now;

    CourseFactory.query().$promise.then(function (result) {
        self.courses = result;
        self.course = self.courses.find(function (course) {
            return course.id === self.lesson.courseId;
        })
    });

    function now() {
        return +new Date();
    }

    function saveHometask() {
        LessonFactory.update(self.lesson, $mdDialog.hide);
    }

    function cancel() {
        $mdDialog.cancel();
    }
}