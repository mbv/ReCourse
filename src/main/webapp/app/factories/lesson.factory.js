angular
    .module('app')
    .factory('LessonFactory', LessonFactory);

function LessonFactory($resource) {
    return $resource('api/lessons/:id', { id: '@id' }, {
        update: { method: 'PUT' },
        getForTeacher: {
            url: 'api/teachers/:id/lessons',
            method: 'GET',
            isArray: true
        },
        getFutureForStudent: {
            url: 'api/students/:id/future_lessons',
            method: 'GET',
            isArray: true
        },
        getPastForStudent: {
            url: 'api/students/:id/past_lessons',
            method: 'GET',
            isArray: true
        }
    });
}
