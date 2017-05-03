angular
    .module('app')
    .controller('CourseFeedbackListController', CourseFeedbackListController);

function CourseFeedbackListController($mdDialog, CourseFactory, FeedbackFactory, $stateParams) {
    var self = this;

    self.title = 'Course Feedbacks';
    self.courseId = $stateParams.course;
    self.feedbacks = [];
    self.isUpdatingChosen = false;
    self.pagination = { page: 1, limit: 7 };

    self.addFeedback = addFeedback;
    self.deleteFeedback = deleteFeedback;
    self.editFeedback = editFeedback;

    refresh();

    function refresh() {
        CourseFactory.getFeedbacks({ id: self.courseId }).$promise.then(function (result) {
            self.feedbacks = result;
        });
    }

    function addFeedback() {
        openModal();
    }

    function deleteFeedback(feedback) {
        FeedbackFactory.delete({ id: feedback.id }, refresh);
    }

    function editFeedback(feedback) {
        openModal(feedback);
    }

    function openModal(feedback) {
        $mdDialog.show({
            controller: 'CourseFeedbackModalController as self',
            templateUrl: 'templates/crud/courses/feedbacks/modal.html',
            parent: angular.element(document.body),
            clickOutsideToClose: true,
            locals: {
                feedback: angular.copy(feedback),
                courseId: self.courseId
            }
        }).then(refresh, refresh);
    }
}


