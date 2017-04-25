angular
    .module('app')
    .filter('propertyFilter', PropertyFilter);

function PropertyFilter() {
    return function (entities, searchQuery) {
        var filteredEntities = [];
        var properties = searchQuery.properties;
        var searchText = searchQuery.searchText.toLowerCase();

        entities.forEach(function (entity) {
            for (var i = 0; i < properties.length - 1; i++) {
                var property = properties[i];
                if (entity[property].toString().toLowerCase().indexOf(searchText) !== -1) {
                    filteredEntities.push(entity);
                    break;
                }
            }
        });
        return filteredEntities;
    }
}
