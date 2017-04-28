angular
    .module('app')
    .factory('SolutionFactory', SolutionFactory);

function SolutionFactory($resource) {
    return $resource('api/hometasks/solutions/:id', { id: '@id' }, {
        update: { method: 'PUT' },
        delete: { method: 'DELETE' },
        getForLesson: {
            method: 'GET',
            url: 'api/hometasks/solutions/lesson/:id',
            isArray: true
        },
        getMark: {
            method: 'GET',
            url: 'api/hometasks/solutions/:id/mark'
        }
    });
}
