angular
    .module('app')
    .controller('StudentCoursesController', StudentCoursesController);

function StudentCoursesController($mdDialog, CourseFactory, AuthService, $state) {
    var self = this;

    self.ongoingCourses = [];

    self.title = 'Courses';
    self.showLessons = showLessons;

    if (AuthService.user.role === 'STUDENT'){
        self.studentId = AuthService.user.id;
    }

    refresh();

    function refresh() {
        CourseFactory.getStudentCourses({id : self.studentId}).$promise.then(function (result) {
            self.ongoingCourses = result;
        });
    }

    function showLessons(course) {
        $state.go('student.lessons', { id: course.id });
    }
}
