angular
    .module('app')
    .factory('MarkFactory', MarkFactory);

function SolutionFactory($resource) {
    return $resource('api/hometasks/solutions/marks/:id', { id: '@id' }, {
        update: { method: 'PUT' },
        delete: { method: 'DELETE' }
    });
}
