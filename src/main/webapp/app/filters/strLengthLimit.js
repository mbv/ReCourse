angular
    .module('app')
    .filter('strLengthLimit', StringLengthLimitFilter);

function StringLengthLimitFilter($filter) {
    return function (inputString, limit) {
        if (inputString.length <= limit) {
            return inputString;
        }

        return $filter('limitTo')(inputString, limit) + '...';
    }
}