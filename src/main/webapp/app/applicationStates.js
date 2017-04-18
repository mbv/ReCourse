angular
    .module('app')
    .config(AppStates);

function AppStates($stateProvider, $urlRouterProvider) {
    [
        { name: 'home', url: '/', templateUrl: 'templates/home.html' },
        { name: 'login', url: '/login', templateUrl: 'templates/login.html' },
        { name: 'about', url: '/about', templateUrl: 'templates/about.html' },
        { name: 'users',  url: '/users', templateUrl: 'templates/users/index.html' },
        { name: 'courses',  url: '/courses', templateUrl: 'templates/courses/index.html' },
        { name: 'otherwise', url: '/otherwise', template: '<h1>None</h1><p>Nothing has been selected</p>' }
    ].forEach(function(state) { $stateProvider.state(state) });

    $urlRouterProvider.when('', '/');
    $urlRouterProvider.otherwise('/otherwise');
}
