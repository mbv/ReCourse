angular
    .module('app')
    .config(AppStates);

function AppStates($stateProvider, $urlRouterProvider) {
    [
        { name: 'root', url: '/' },
        { name: 'home', url: '/home', templateUrl: 'templates/home.html' },
        { name: 'signIn', url: '/sign_in', templateUrl: 'templates/sign_in.html' },
        { name: 'signUp', url: '/sign_up', templateUrl: 'templates/sign_up.html' },
        { name: 'about', url: '/about', templateUrl: 'templates/about.html' },
        { name: 'crud', url: '/crud', templateUrl: 'templates/crud/index.html' },
        { name: 'crud.users',  url: '/users', templateUrl: 'templates/crud/users/index.html' },
        { name: 'crud.courses',  url: '/courses', templateUrl: 'templates/crud/courses/index.html' },
        { name: 'otherwise', url: '/otherwise', template: '<h1>None</h1><p>Nothing has been selected</p>' }
    ].forEach(function(state) { $stateProvider.state(state) });

    $urlRouterProvider.when('', '/');
    $urlRouterProvider.when('/', '/home');
    $urlRouterProvider.otherwise('/otherwise');
}
