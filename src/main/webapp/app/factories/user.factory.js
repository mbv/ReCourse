angular
    .module('app')
    .factory('UserFactory', UserFactory);

function UserFactory($resource) {
    return $resource('api/users/:id', { id: '@id' }, {update: { method: 'PUT' } });
}
