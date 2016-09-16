$(document).ready(function(){
    $("div").mouseenter(function(){
        var y = Math.random()*(window.innerHeight-100);
        var x = Math.random()*(window.innerWidth-100);
        $("div").animate({left: x, top: y});
    });
    $("div").click(function(){
        $("div").stop();
        $("div").fadeOut(1000);
    });
});