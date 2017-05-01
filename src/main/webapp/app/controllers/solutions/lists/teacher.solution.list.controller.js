angular
    .module('app')
    .controller('TeacherSolutionListController', TeacherSolutionListController);

function TeacherSolutionListController($controller, $mdDialog, SolutionFactory, AuthService, $stateParams) {
    var self = this;

    $controller('SolutionListController', {self: self});

    self.title = 'Lesson Solutions';
    self.lessonId = $stateParams.id;
    self.solutions = [];
    self.isUpdatingChosen = false;
    self.teacherId = AuthService.user.id;

    self.showSolution = showSolution;

    refresh();

    function refresh() {
        SolutionFactory.getForLesson({id: self.lessonId}).$promise.then(function (result) {
            self.solutions = result;
            for (var i = 0; i < self.solutions.length; i++) {
                const index = i;
                SolutionFactory.getMark({id: self.solutions[index].id}).$promise.then(function (result) {
                    self.solutions[index].mark = result;
                })
            }
        });
    }

    function showSolution(solution) {
        openShowModal(solution);
    }

    function openShowModal(solution) {
        $mdDialog.show({
            controller: 'TeacherLessonSolutionModalController as self',
            templateUrl: 'templates/teacher/lessons/solutions/modal.html',
            parent: angular.element(document.body),
            clickOutsideToClose: true,
            locals: {
                solution: angular.copy(solution),
                lessonId: self.lessonId
            }
        }).then(refresh, refresh);
    }
}
