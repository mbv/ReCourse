angular
    .module('app')
    .controller('LessonSolutionModalController', LessonSolutionModalController);

function LessonSolutionModalController($mdDialog, SolutionFactory, MarkFactory, solution, lessonId) {
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
            SolutionFactory.update(self.solution, function() {
                if (self.solution.mark){
                    if (self.solution.mark.id) {
                        MarkFactory.update(self.solution.mark, $mdDialog.hide);
                    } else {
                        MarkFactory.save(self.solution.mark, $mdDialog.hide);
                    }
                } else {
                    $mdDialog.hide();
                }
            });
        } else {
            SolutionFactory.save(self.solution, function() {
                if (self.solution.mark) {
                    MarkFactory.save(self.solution.mark, $mdDialog.hide);
                }
            });
        }
    }

    function removeMark() {
        MarkFactory.delete({id: self.solution.mark.id}, function() {
            self.solution.mark = null;
        })

    }

    function addMark() {
        self.solution.mark = {};
    }


    function cancel() {
        $mdDialog.cancel();
    }
}