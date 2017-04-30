angular
    .module('app')
    .config(AppStates);

function AppStates($stateProvider, $urlRouterProvider) {
    [
        { name: 'about', url: '/about', templateUrl: 'templates/about.html' },
        {
            name: 'signIn',
            url: '/sign_in',
            controller: 'SignInController as self',
            templateUrl: 'templates/sign_in.html'
        },
        {
            name: 'signUp',
            url: '/sign_up',
            controller: 'SignUpController as self',
            templateUrl: 'templates/sign_up.html'
        },
        {
            name: 'users',
            url: '/users?course',
            controller: 'UserListController as self',
            templateUrl: 'templates/crud/users/index.html'
        },
        {
            name: 'courses',
            url: '/courses',
            controller: 'CourseListController as self',
            templateUrl: 'templates/courses/index.html'
        },
        {
            name: 'feedbacks',
            url: '/feedbacks?course',
            controller: 'CourseFeedbackListController as self',
            templateUrl: 'templates/crud/courses/feedbacks/index.html'
        },
        {
            name: 'course-lessons',
            url: '/lessons?course',
            controller: 'LessonListController as self',
            templateUrl: 'templates/crud/lessons/index.html'
        },
        {
            name: 'lesson-solutions',
            url: '/solutions/?lesson',
            controller: 'LessonSolutionListController as self',
            templateUrl: 'templates/crud/lessons/solutions/index.html'
        },
        {
            name: 'teacher-lessons',
            url: '/teacher/lessons',
            controller: 'LessonListController as self',
            templateUrl: 'templates/crud/lessons/index.html'
        },
        {
            name: 'student-courses',
            url: '/student/courses',
            controller: 'StudentCoursesController as self',
            templateUrl: 'templates/student/courses/index.html'
        },
        {
            name: 'student-lessons',
            url: '/student/lessons',
            controller: 'StudentCoursesController as self',
            templateUrl: 'templates/student/courses/index.html'
        },
        { name: 'otherwise', url: '/otherwise', template: '<h1>404</h1>' }
    ].forEach(function(state) { $stateProvider.state(state) });

    $urlRouterProvider.when('', '/');
    $urlRouterProvider.otherwise('/otherwise');
}
