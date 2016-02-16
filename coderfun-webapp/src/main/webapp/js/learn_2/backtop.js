/**
 * Created by imafan on 2015/11/30 0030.
 */

define(["jquery","learn_2/scrollto"],function($,scrollto){
    function BackTop(el,opts){
        this.opts = $.extend({},BackTop.DEAFULTS,opts);
        this.$el = $(el);
        this.scroll = new scrollto.ScrollTo({
            dest: 0,
            speed: 800
        });
        this._checkPosition();
        if(this.opts.mode == "move"){
            this.$el.on("click", $.proxy(this._move,this));
        }else{
            this.$el.on("click", $.proxy(this._go,this));
        }

        $(window).on("scroll", $.proxy(this._checkPosition,this));
    }
    BackTop.DEAFULTS = {
        mode : "move" ,
        pos : $(window).height() ,
        speed: 800
    }
    BackTop.prototype._move = function(){
        this.scroll.move();
    }
    BackTop.prototype._go = function(){
        this.scroll.go();
    }
    BackTop.prototype._checkPosition = function(){
        var $el = this.$el;
        if($(window).scrollTop() > this.opts.pos){
           $el.fadeIn();
        }else{
           $el.fadeOut();
        }
    }

    //注册成为jq插件
    $.fn.extend({
        backtop: function(opts){
            return this.each(function(){
                new BackTop(this, opts);
            })
        }
    });

    return {
        BackTop :   BackTop
    }
})