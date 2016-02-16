/**
 * Created by imafan_work on 2015/11/9 0009.
 */
function test(count, ms) {
    var c = 1;
    var time = [new Date() * 1];
    var id = setTimeout(function () {
        time.push(new Date() * 1);
        c += 1;
        if (c <= count) {
            setTimeout(arguments.callee, ms);
        } else {
            clearTimeout(id);
            var tl = time.length;
            var av = 0;
            for (var i = 1; i < tl; i++) {
                var n = time[i] - time[i - 1]; //收集每次与上一次相差的时间数
                av += n;
            }
            console.info(av / count); // 求取平均值
        }
    }, ms);
}
window.onload = function () {
    var id = setTimeout(function () {
        test(100, 1);
        clearTimeout(id);
    }, 3000);
}