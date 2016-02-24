/**
 * Created by imafan on 2016/2/24 0024.
 */
define(['angular'], function (angular) {
    'use strict';

    /* Services */

    // Demonstrate how to register services
    // In this case it is a simple value service.
    angular.module('myApp.services', [])
        .value('version', '0.1');
});