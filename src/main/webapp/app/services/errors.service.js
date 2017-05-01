angular
    .module('app')
    .service('ErrorsService', ErrorsService);

function ErrorsService(toast) {

    var self = {
        showErrors: showErrors
    };

    return self;

    function showErrors(errors) {
        errors.forEach(function (error) {
            toast.show(error.title + ": " + error.message, 10000);
        });
    }
}