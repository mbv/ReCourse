angular
    .module('app')
    .controller('LessonListController', LessonListController);

function LessonListController($mdDialog, LessonFactory, CourseFactory, AuthService, $state, $stateParams) {
    var self = this;

    self.title = '';
    self.lessons = [];
    self.isUpdatingChosen = false;

    self.addLesson = addLesson;
    self.deleteLesson = deleteLesson;
    self.editLesson = editLesson;
    self.showLesson = showLesson;
    self.showSolutions = showSolutions;
    self.courseId = $stateParams.course;
    if (AuthService.user.role === 'TEACHER'){
        self.teacherId = AuthService.user.id;
    }


    refresh();

    function refresh() {
        if (self.teacherId) {
            LessonFactory.getForTeacher({id: self.teacherId}).$promise.then(function (result) {
                self.lessons = result;
            })
        } else {
            if (self.courseId) {
                self.title = 'Course Lessons';
                CourseFactory.getLessons({id: self.courseId}).$promise.then(function (result) {
                    self.lessons = result;
                });
            } else {
                self.title = 'Lessons';
                LessonFactory.query().$promise.then(function (result) {
                    self.lessons = result;
                });
            }
        }
    }

    function addLesson() {
        openEditModal();
    }

    function deleteLesson(lesson ) {
        LessonFactory.delete(lesson, refresh);
    }

    function editLesson (lesson) {
        openEditModal(lesson);
    }

    function showLesson(lesson) {
        openShowModal(lesson);
    }

    function showSolutions(lesson) {
        if (!self.teacherId){
            $state.go('crud.lessons.solutions', { id: lesson.id });
        } else {
            $state.go('teacher.solutions', { id: lesson.id });
        }

    }

    function openEditModal(lesson) {
        $mdDialog.show({
            controller: 'LessonModalController as self',
            templateUrl: 'templates/crud/lessons/modal.html',
            parent: angular.element(document.body),
            clickOutsideToClose: true,
            locals: {
                lesson: angular.copy(lesson),
                courseId: self.courseId
            }
        }).then(refresh, refresh);
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
        }).then(refresh, refresh);
    }
}


