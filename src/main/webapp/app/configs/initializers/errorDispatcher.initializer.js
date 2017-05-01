angular
    .module('app')
    .run(ErrorDispatcher);

function ErrorDispatcher($rootScope, ErrorsService) {
    $rootScope.$on('httpError', function(event, errors) {
        ErrorsService.showErrors(errors);
    });
}
