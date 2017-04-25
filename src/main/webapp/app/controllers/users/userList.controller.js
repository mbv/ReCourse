angular
    .module('app')
    .controller('UserListController', UserListController);

function UserListController($mdDialog, UserFactory) {
    var self = this;

    self.users = [];
    self.isUpdatingChosen = false;

    self.addUser = addUser;
    self.deleteUser = deleteUser;
    self.editUser = editUser;

    refresh();

    function refresh() {
        UserFactory.query().$promise.then(function (result) {
            self.users = result;
        });
    }

    function addUser() {
        openModal();
    }

    function deleteUser(user) {
        UserFactory.delete(user, refresh);
    }

    function editUser(user) {
        openModal(user);
    }

    function openModal(user) {
        $mdDialog.show({
            controller: 'UserModalController as self',
            templateUrl: 'templates/crud/users/modal.html',
            parent: angular.element(document.body),
            clickOutsideToClose: true,
            locals: {
                user: angular.copy(user)
            }
        }).then(refresh, refresh);
    }
}


