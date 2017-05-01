angular
    .module('app')
    .controller('SolutionListController', SolutionListController);

function SolutionListController($state, self, $mdDialog, SolutionFactory, MarkFactory, AuthService, $stateParams) {
    // var self = this;

    self.isAdminSolutions = isAdminSolutions;
    self.isTeacherSolutions = isTeacherSolutions;

    function isAdminSolutions() {
        return $state.current.name === 'lesson-solutions';
    }

    function isTeacherSolutions() {
        return $state.current.name === 'teacher-solutions';
    }

    // self.title = 'Lesson Solutions';
    // self.lessonId = $stateParams.id;
    // self.solutions = [];
    // self.isUpdatingChosen = false;

    // self.addSolution = addSolution;
    // self.deleteSolution = deleteSolution;
    // self.editSolution = editSolution;
    // self.showSolution = showSolution;
    //
    // if (AuthService.user.role === 'TEACHER'){
    //     self.teacherId = AuthService.user.id;
    // }
    //
    // refresh();
    //
    // function refresh() {
    //     SolutionFactory.getForLesson({ id: self.lessonId }).$promise.then(function (result) {
    //         self.solutions = result;
    //         for (var i = 0; i < self.solutions.length; i++) {
    //             const index = i;
    //             SolutionFactory.getMark({id: self.solutions[index].id}).$promise.then(function (result) {
    //                 self.solutions[index].mark = result;
    //             })
    //         }
    //     });
    // }
    //
    // function addSolution() {
    //     openEditModal();
    // }
    //
    // function deleteSolution(solution) {
    //     if (solution.mark.id){
    //         MarkFactory.delete({id: solution.mark.id}, function() {
    //             SolutionFactory.delete({id: solution.id}, refresh);
    //         })
    //     } else {
    //         SolutionFactory.delete({id: solution.id}, refresh);
    //     }
    //
    // }
    //
    // function editSolution (solution) {
    //     openEditModal(solution);
    // }
    //
    // function showSolution(solution) {
    //     openShowModal(solution);
    // }
    //
    // function openEditModal(solution) {
    //     $mdDialog.show({
    //         controller: 'LessonSolutionModalController as self',
    //         templateUrl: 'templates/crud/lessons/solutions/modal.html',
    //         parent: angular.element(document.body),
    //         clickOutsideToClose: true,
    //         locals: {
    //             solution: angular.copy(solution),
    //             lessonId: self.lessonId
    //         }
    //     }).then(refresh, refresh);
    // }
    //
    // function openShowModal(solution) {
    //     $mdDialog.show({
    //         controller: 'TeacherLessonSolutionModalController as self',
    //         templateUrl: 'templates/teacher/lessons/solutions/modal.html',
    //         parent: angular.element(document.body),
    //         clickOutsideToClose: true,
    //         locals: {
    //             solution: angular.copy(solution),
    //             lessonId: self.lessonId
    //         }
    //     }).then(refresh, refresh);
    // }
}


