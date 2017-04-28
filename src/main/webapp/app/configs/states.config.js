angular
    .module('app')
    .config(AppStates);

function AppStates($stateProvider, $urlRouterProvider) {
    [
        { name: 'root', url: '/', redirectTo: 'home' },
        { name: 'home', url: '/home', templateUrl: 'templates/home.html' },
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
            name: 'crud',
            url: '/crud',
            templateUrl: 'templates/crud/index.html',
            redirectTo: 'crud.users'
        },
        {
            name: 'crud.users',
            url: '/users?course',
            controller: 'UserListController as self',
            templateUrl: 'templates/crud/users/index.html'
        },
        {
            name: 'crud.courses',
            url: '/courses',
            controller: 'CourseListController as self',
            templateUrl: 'templates/crud/courses/index.html'
        },
        {
            name: 'crud.feedbacks',
            url: '/feedbacks?course',
            controller: 'CourseFeedbackListController as self',
            templateUrl: 'templates/crud/courses/feedbacks/index.html'
        },
        {
            name: 'crud.lessons.solutions',
            url: '/:id/solutions',
            controller: 'LessonSolutionListController as self',
            templateUrl: 'templates/crud/lessons/solutions/index.html'
        },
        {
            name: 'crud.lessons',
            url: '/lessons?course&teacher',
            controller: 'LessonListController as self',
            templateUrl: 'templates/crud/lessons/index.html'
        },
        {
            name: 'lessons',
            url: '/lessons/teacher/:teacher',
            controller: 'LessonListController as self',
            templateUrl: 'templates/crud/lessons/index.html'
        },
        {
            name: 'teacher',
            url: '/teacher/lessons',
            controller: 'LessonListController as self',
            templateUrl: 'templates/crud/lessons/index.html'
        },
        {
            name: 'teacher.solutions',
            url: '/:id/solutions',
            controller: 'LessonSolutionListController as self',
            templateUrl: 'templates/crud/lessons/solutions/index.html'
        },
        {
            name: 'student',
            url: '/student/mycourses',
            controller: 'StudentCoursesController as self',
            templateUrl: 'templates/student/courses/index.html'
        },
        {
            //TODO fix
            name: 'student.lessons',
            url: '/:id/lesson',
            controller: 'StudentCoursesController as self',
            templateUrl: 'templates/student/courses/index.html'
        },
        { name: 'otherwise', url: '/otherwise', template: '<h1>404</h1>' }
    ].forEach(function(state) { $stateProvider.state(state) });

    $urlRouterProvider.when('', '/');
    $urlRouterProvider.otherwise('/otherwise');
}
