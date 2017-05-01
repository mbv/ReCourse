angular
    .module('app')
    .controller('AllUserListController', AllUserListController);

function AllUserListController($mdDialog, UserFactory, $controller) {
    var self = this;

    $controller('UserListController', {self: self});

    self.title = 'Users';
    self.course = null;
    self.users = [];
    self.isUpdatingChosen = false;
    self.addUser = addUser;
    self.editUser = editUser;

    refresh();

    function refresh() {
        UserFactory.query().$promise.then(function (result) {
            self.users = result;
        });
    }

    function addUser() {
        openEditModal();
    }

    function editUser(user) {
        openEditModal(user);
    }

    function openEditModal(user) {
        $mdDialog.show({
            controller: 'UserModalController as self',
            templateUrl: 'templates/users/modal.html',
            parent: angular.element(document.body),
            clickOutsideToClose: true,
            locals: {
                user: angular.copy(user)
            }
        }).then(refresh, refresh);
    }

}


