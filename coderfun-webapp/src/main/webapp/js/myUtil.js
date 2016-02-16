/**
 * Created by imafan_work on 2015/11/18 0018.
 */

//如何实现继承的方法,例如：extend(Cat,Animal);
function extend(Child, Parent) {
    var F = function(){};
    F.prototype = Parent.prototype;
    Child.prototype = new F();
    Child.prototype.constructor = Child;
    Child.uber = Parent.prototype;
}