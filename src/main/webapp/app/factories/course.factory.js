angular
    .module('app')
    .factory('CourseFactory', CourseFactory);

function CourseFactory($resource) {
    return $resource('api/courses/:id', { id: '@id' }, {update: { method: 'PUT' } });
}
