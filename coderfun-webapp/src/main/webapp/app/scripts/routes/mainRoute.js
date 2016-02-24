/**
 * Created by imafan on 2016/2/24 0024.
 */
define(['angular', 'app'], function(angular, app) {
    'use strict';

    return app.config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/view1', {
            templateUrl: 'app/view/test1.html',
            controller: 'MyCtrl1'
        });
        $routeProvider.when('/view2', {
            templateUrl: 'app/view/test2.html',
            controller: 'MyCtrl2'
        });
        $routeProvider.when('/', {
            templateUrl: 'app/index.html'
        });
        $routeProvider.otherwise({redirectTo: '/'});
    }]);

});