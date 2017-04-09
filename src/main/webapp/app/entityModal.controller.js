angular
    .module('app')
    .controller('EntityModalController', EntityModalController);

function EntityModalController($uibModalInstance, EntityService, entity, parentController) {
    var self = this;

    self.entity = entity;
    self.parentController = parentController;
    self.saveEntity = saveEntity;
    self.cancel = cancel;
    self.updateMode = !!self.entity;

    console.log(self.parentController.teachers);

    function saveEntity() {
        if (self.updateMode){
            EntityService.update(self.entity, $uibModalInstance.close)
        } else {
            EntityService.save(self.entity, $uibModalInstance.close)
        }
    }

    function cancel() {
        $uibModalInstance.dismiss('cancel');
    }
}