/**
 * Created by imafan on 2015/11/29 0029.
 */

requirejs.config({
   baseUrl : "/js",   //如果指定了baseUrl，那么在该模块引用别的模块都是相对于baseUrl
   paths: {
       jquery: "jquery-1.11.2.min"
   }
}) ;

requirejs(["jquery","learn_2/backtop"],function($,backtop){

    /*new backtop.BackTop($("#backTop"),{
        mode : "move",
        speed: 3000
    })  ;*/

     $("#backTop").backtop({
         mode : "move",
         speed: 800
     })

});