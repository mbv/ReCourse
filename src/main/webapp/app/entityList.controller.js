angular
    .module('app')
    .controller('EntityListController', EntityListController);

function EntityListController($resource, $uibModal, resourceName, self) {
    self.resourceName = resourceName;
    self.EntityService = $resource(self.resourceName +'/:id', { id: '@id' }, { update: { method: 'PUT' } });

    self.entities = [];
    self.isUpdatingChosen = false;

    self.addEntity = addEntity;
    self.deleteEntity = deleteEntity;
    self.editEntity = editEntity;

    refresh();

    function refresh() {
        self.EntityService.query().$promise.then(function (result) {
            self.entities = result;
        });
    }

    function addEntity() {
        openModal();
    }

    function deleteEntity(entity) {
        self.EntityService.delete(entity, refresh);
    }

    function editEntity (entity) {
        openModal(entity);
    }

    function openModal(entity) {
        var modalInstance = $uibModal.open({
            templateUrl: 'templates/' + self.resourceName + '/modal.html',
            controller: 'EntityModalController as self',
            resolve: {
                entity: function () {
                    return angular.copy(entity);
                },
                parentController: function() {
                    return self;
                },
                EntityService: function () {
                    return self.EntityService
                }
            }
        });
        modalInstance.result.then(refresh);
    }
}


