angular
    .module('app')
    .factory('FeedbackFactory', FeedbackFactory);

function FeedbackFactory($resource) {
    return $resource('api/courses/feedbacks/:id', { id: '@id' }, { update: { method: 'PUT' } });
}
