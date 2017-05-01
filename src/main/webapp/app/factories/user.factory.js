angular
    .module('app')
    .factory('UserFactory', UserFactory);

function UserFactory($resource) {
    return $resource('api/users/:id', { id: '@id' }, {
        update: { method: 'PUT' },
        getForCourse: {
            method: 'GET',
            url: 'api/courses/:id/students',
            isArray: true
        },
        getCurrentUser: {
            method: 'GET',
            url: 'api/users/me'
        }
    });
}
