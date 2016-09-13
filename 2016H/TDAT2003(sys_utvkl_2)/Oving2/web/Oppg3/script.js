/**
 * Created by asdfLaptop on 07.09.2016.
 */
$(document).ready(function() {
    var jul = new Date("December 24, 2016");
    var days = (jul - Date.now()) / (1000*60*60*24);

    if(days >= 90) {
        $("p.black").html(parseInt(days) + " dager til jul!");
    } else if(30 < days && 90 > days) {
        $(".black").removeClass("black").addClass("green");
        $(".green").html(parseInt(days) + " dager til jul!");
    } else if(days <= 30) {
        $(".black").removeClass("green").addClass("red");
        $(".red").html(parseInt(days) + " dager til jul!");
    }

});