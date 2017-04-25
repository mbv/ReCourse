angular
    .module('app')
    .controller('CourseModalController', CourseModalController);

function CourseModalController($mdDialog, UserFactory, CourseFactory, course) {
    var self = this;

    self.course = course;
    self.saveCourse = saveCourse;
    self.cancel = cancel;
    self.updateMode = !!self.course;

    self.statuses = ['ONGOING', 'REGISTRATION', 'FINISHED'];
    self.teachers = [];

    UserFactory.query().$promise.then(function (result) {
        self.teachers = result.filter(function (user) { return user.role === 'TEACHER' });
    });

    function saveCourse() {
        if (self.updateMode){
            CourseFactory.update(self.course, $mdDialog.hide);
        } else {
            CourseFactory.save(self.course, $mdDialog.hide);
        }
    }

    function cancel() {
        $mdDialog.cancel();
    }
}