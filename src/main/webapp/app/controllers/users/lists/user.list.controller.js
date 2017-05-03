angular
    .module('app')
    .controller('UserListController', UserListController);

function UserListController( $state, self) {
    self.isAllUsers = isAllUsers;
    self.isCourseUsers = isCourseUsers;
    self.pagination = { page: 1, limit: 7 };

    function isAllUsers() {
        return $state.current.name === 'users';
    }

    function isCourseUsers() {
        return $state.current.name === 'course-users';
    }
}


