define(['angular', 'services'], function(angular, services) {
    'use strict';

    /* Directives */

    angular.module('myApp.directives', ['myApp.services'])
        .directive('appVersion', ['version', function(version) {
            return function(scope, elm, attrs) {
                elm.text(version);
            };
        }]);
});/**
 * Created by imafan on 2016/2/24 0024.
 */
