angular
    .module('app')
    .factory('CourseFactory', CourseFactory);

function CourseFactory($resource) {
    return $resource('api/courses/:id', { id: '@id' }, {
        update: { method: 'PUT' },
        getLessons: {
            method: 'GET',
            url: 'api/courses/:id/lessons',
            isArray: true
        },
        getFeedbacks: {
            method: 'GET',
            url: 'api/courses/:id/feedbacks',
            isArray: true
        },
        registerStudent: {
            method: 'POST',
            url: 'api/courses/:id/register?studentId=:studentId',
            params: {
                studentId: '@studentId'
            }
        },
        unregisterStudent: {
            method: 'POST',
            url: 'api/courses/:id/unregister?studentId=:studentId',
            params: {
                studentId: '@studentId'
            }
        },
        getStudentCourses: {
            method: 'GET',
            url: 'api/courses/registered/:id',
            isArray: true
        }

    });
}
