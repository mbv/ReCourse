angular
    .module('app')
    .controller('StudentLessonListController', StudentLessonListController);

function StudentLessonListController($controller, self) {
    $controller('LessonListController', { self: self });

    self.isFutureLessons = isFutureLessons;
    self.isPastLessons = isPastLessons;

    function isFutureLessons() {
        return self.lessonsType === 'future';
    }

    function isPastLessons() {
        return !isFutureLessons();
    }
}


