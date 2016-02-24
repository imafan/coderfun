/**
 * Created by imafan on 2016/2/24 0024.
 */
define(['angular', 'services'], function (angular, services) {
    'use strict';

    /* Filters */

    angular.module('myApp.filters', ['myApp.services'])
        .filter('interpolate', ['version', function(version) {
            return function(text) {
                return String(text).replace(/\%VERSION\%/mg, version);
            };
        }]);
});