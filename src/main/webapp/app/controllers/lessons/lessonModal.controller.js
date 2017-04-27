angular
    .module('app')
    .controller('LessonModalController', LessonModalController);

function LessonModalController($mdDialog, UserFactory, CourseFactory, LessonFactory, lesson, courseId) {
    var self = this;

    if (lesson && lesson.startTime) {
        lesson.startTime = new Date(lesson.startTime);
    }

    self.lesson = lesson;
    self.saveLesson = saveLesson;
    self.cancel = cancel;
    self.courseSelected = courseSelected;
    self.updateMode = !!self.lesson;
    self.courseId = courseId;

    self.teachers = [];
    self.courses = [];
    self.course = {};

    UserFactory.query().$promise.then(function (result) {
        self.teachers = result.filter(function (user) { return user.role === 'TEACHER' });
    });

    CourseFactory.query().$promise.then(function (result) {
        self.courses = result;
        self.course = self.courses.find(function (course) {
            return course.id === self.lesson.courseId;
        })
    });

    function courseSelected(course) {
        if (course) {
            self.lesson.courseId = course.id;
        }
    }

    function saveLesson() {
        if (self.courseId) {
            self.lesson.courseId = courseId;
        }
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