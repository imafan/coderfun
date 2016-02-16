/**
 * Created by imafan_work on 2015/11/12 0012.
 */
(function($){
    var ModalWindow = (function(){

        function ModalWindow(element, options){

        }

        ModalWindow.prototype = {
            init : function(){

            }
        }
        return ModalWindow;
    })();

    $.fn.ModalWindow = function(options){
        return this.each(function(){
            var me = $(this),
                instance = me.data("ModalWindow");
            if(!instance){
                instance = new PageSwitch(me, options);
                me.data("PageSwitch", instance);
            }
            if($.type(options)==="string") return instance[options]();
        });
    }
    $.fn.ModalWindow.defaults = {

    };
})(jQuery)