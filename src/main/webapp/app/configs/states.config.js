angular
    .module('app')
    .config(AppStates);

function AppStates($stateProvider, $urlRouterProvider) {
    [
        { name: 'root', url: '/', redirectTo: 'home' },
        { name: 'home', url: '/home', templateUrl: 'templates/home.html' },
        { name: 'signIn', url: '/sign_in', templateUrl: 'templates/sign_in.html' },
        { name: 'signUp', url: '/sign_up', templateUrl: 'templates/sign_up.html' },
        { name: 'about', url: '/about', templateUrl: 'templates/about.html' },
        { name: 'crud', url: '/crud', redirectTo: 'crud.users', templateUrl: 'templates/crud/index.html' },
        { name: 'crud.users',  url: '/users', templateUrl: 'templates/crud/users/index.html' },
        { name: 'crud.courses',  url: '/courses', templateUrl: 'templates/crud/courses/index.html' },
        // { name: 'crud.courses',  url: '/courses', templateUrl: 'templates/crud/courses/index.html' },
        { name: 'crud.lessons',  url: '/lessons', templateUrl: 'templates/crud/lessons/index.html' },
        { name: 'otherwise', url: '/otherwise', template: '<h1>404</h1>' }
    ].forEach(function(state) { $stateProvider.state(state) });

    $urlRouterProvider.when('', '/');
    $urlRouterProvider.otherwise('/otherwise');
}
