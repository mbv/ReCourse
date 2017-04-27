angular
    .module('app')
    .controller('LessonSolutionModalController', LessonSolutionModalController);

function LessonSolutionModalController($mdDialog, SolutionFactory, solution, lessonId) {
    var self = this;

    self.solution = solution;
    self.saveSolution = saveSolution;
    self.cancel = cancel;
    self.updateMode = !!self.solution;
    self.removeMark = removeMark;
    self.addMark = addMark;

    function saveSolution() {
        self.solution.lessonId = lessonId;
        if (self.solution.mark) {
            self.solution.mark.solutionId = self.solution.id;
        }
        if (self.updateMode){
            SolutionFactory.update(self.solution, $mdDialog.hide);
        } else {
            SolutionFactory.save(self.solution, $mdDialog.hide);
        }
    }

    function removeMark() {
        self.solution.mark = null;
    }

    function addMark() {
        self.solution.mark = {};
    }


    function cancel() {
        $mdDialog.cancel();
    }
}