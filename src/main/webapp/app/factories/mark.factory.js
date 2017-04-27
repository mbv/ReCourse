angular
    .module('app')
    .factory('MarkFactory', MarkFactory);

function MarkFactory($resource) {
    return $resource('api/hometasks/solutions/marks/:id', { id: '@id' }, {
        update: { method: 'PUT' },
        delete: { method: 'DELETE' }
    });
}
