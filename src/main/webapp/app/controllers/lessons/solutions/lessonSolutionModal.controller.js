angular
    .module('app')
    .controller('LessonSolutionModalController', LessonSolutionModalController);

function LessonSolutionModalController($mdDialog, SolutionFactory, solution, lessonId) {
    var self = this;

    self.solution = solution;
    self.saveSolution = saveSolution;
    self.cancel = cancel;
    self.updateMode = !!self.solution;

    function saveSolution() {
        self.solution.lessonId = lessonId;
        if (self.updateMode){
            SolutionFactory.update(self.solution, $mdDialog.hide);
        } else {
            SolutionFactory.save(self.solution, $mdDialog.hide);
        }
    }

    function cancel() {
        $mdDialog.cancel();
    }
}