angular
    .module('app')
    .controller('TeacherLessonSolutionModalController', TeacherLessonSolutionModalController);

function TeacherLessonSolutionModalController($mdDialog, SolutionFactory, MarkFactory, UserFactory, solution, lessonId) {
    var self = this;

    self.solution = solution;
    self.saveMark = saveMark;
    self.cancel = cancel;
    self.updateMode = !!self.solution.mark;
    self.removeMark = removeMark;
    self.addMark = addMark;

    function saveMark() {
        if (self.solution.mark) {
            self.solution.mark.solutionId = self.solution.id;
            if (self.updateMode){
                MarkFactory.update(self.solution.mark, $mdDialog.hide);
            } else {
                MarkFactory.save(self.solution.mark, $mdDialog.hide);
            }
        } else {
            $mdDialog.hide();
        }
    }

    function removeMark() {
        if (self.solution.mark){
            if (self.solution.mark.id){
                MarkFactory.delete({id: self.solution.mark.id}, function() {
                    self.solution.mark = null;
                })
            } else {
                self.solution.mark = null;
            }
        }


    }

    function addMark() {
        self.solution.mark = {};
    }


    function cancel() {
        $mdDialog.cancel();
    }
}