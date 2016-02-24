/**
 * Created by imafan on 2016/2/24 0024.
 */
require.config({
    paths: {
        angular: 'vendor/angular',
        angularRoute: 'vendor/angular-route',
        twBootstrap: 'vendor/bootstrap',
        jquery: 'vendor/jquery-1.11.2.min'
    },
    shim: {
        'twBootstrap': {
            deps:['jquery'],
            'exports':'bootstrap'
        },
        'angular' : {
            deps:['jquery','twBootstrap'],
            'exports' : 'angular'
        },
        'angularRoute': ['angular']
    }
});

//http://code.angularjs.org/1.2.1/docs/guide/bootstrap#overview_deferred-bootstrap
window.name = "NG_DEFER_BOOTSTRAP!";

require( [
    'angular',
    'app',
    'routes'
], function(angular, app, routes) {
    'use strict';
    var $html = angular.element(document.getElementsByTagName('html')[0]);

    angular.element().ready(function() {
        angular.resumeBootstrap([app['name']]);
    });
});