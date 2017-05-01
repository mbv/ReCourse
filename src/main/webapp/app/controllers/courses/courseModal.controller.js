angular
    .module('app')
    .controller('CourseModalController', CourseModalController);

function CourseModalController($mdDialog, CourseFactory, course) {
    var self = this;

    self.course = course;
    self.saveCourse = saveCourse;
    self.cancel = cancel;
    self.updateMode = !!self.course;

    self.statuses = ['DRAFT', 'PUBLISHED', 'FINISHED'];

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