/**
 * Created by imafan on 2016/2/17 0017.
 */

define(['angular', 'app'], function(angular, app) {

    return angular.module('myApp.controllers')
        // Sample controller where service is being used
        .controller('MyCtrl1', function($scope){
            $scope.text = "hello";
        })

});