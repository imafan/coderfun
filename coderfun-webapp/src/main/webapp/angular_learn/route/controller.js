/**
 * Created by imafan on 2016/2/4 0004.
 */
var aMailServices = angular.module("AMail",[]);

function emailRouteConfig($routeProvider){
    $routeProvider.
        when("/",{
            controller: ListController,
            templateUrl: "list.html"
        }).
        when("/view/:id",{
            controller: DetailController,
            templateUrl: "detail.html"
        }).
        otherwise({
            redirectTo: "/"
        });
}

aMailServices.config(emailRouteConfig);

var messages = [
    {id: 0,sender: "test@qq.com",subject: "Test",date: "2016-02-04 14:42:00", recipients:["test_1@qq.com"],message: "你瞅啥"}
];

function ListController($scope){
    $scope.messages = messages;
}

function DetailController($scope,$routeParams){
    $scope.message = messages[$routeParams.id];
}