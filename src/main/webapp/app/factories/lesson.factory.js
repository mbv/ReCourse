angular
    .module('app')
    .factory('LessonFactory', LessonFactory);

function LessonFactory($resource) {
    return $resource('api/lessons/:id', { id: '@id' }, { update: { method: 'PUT' } });
}
