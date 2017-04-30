angular
    .module('app')
    .controller('CourseListController', CourseListController);

function CourseListController($mdDialog, CourseFactory, AuthService, $state) {
    var self = this;

    self.courses = [];
    self.isUpdatingChosen = false;

    self.title = '';
    self.isStudent = AuthService.user.role === 'STUDENT';

    self.addCourse = addCourse;
    self.deleteCourse = deleteCourse;
    self.editCourse = editCourse;
    self.showLessons = showLessons;
    self.showStudents = showStudents;
    self.showFeedbacks = showFeedbacks;

    refresh();

    function refresh() {
        if (self.isStudent) {
            self.title = 'Available Courses'
            CourseFactory.availableForStudent({ studentId: AuthService.user.id}).$promise.then(function (result) {
                self.courses = result;
            })
        } else {
            self.title = 'Courses';
            CourseFactory.query().$promise.then(function (result) {
                self.courses = result;
            });
        }
    }

    function addCourse() {
        openModal();
    }

    function deleteCourse(course) {
        CourseFactory.delete(course, refresh);
    }

    function editCourse(course) {
        openModal(course);
    }

    function registerStudent() {
        CourseFactory.registerStudent(AuthService.user.id).then(refresh);
    }

    function showLessons(course) {
        $state.go('crud.lessons', { course: course.id });
    }

    function showStudents(course) {
        $state.go('crud.users', { course: course.id });
    }

    function showFeedbacks(course) {
        $state.go('crud.feedbacks', { course: course.id });
    }

    function openModal(course) {
        $mdDialog.show({
            controller: 'CourseModalController as self',
            templateUrl: 'templates/crud/courses/modal.html',
            parent: angular.element(document.body),
            clickOutsideToClose: true,
            locals: {
                course: angular.copy(course)
            }
        }).then(refresh, refresh);
    }
}


