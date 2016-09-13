/**
 * Created by asdfLaptop on 02.09.2016.
 */
function clicked(x) {
    if(document.getElementById(x).className==="rute_color1" || document.getElementById(x).className ==="rute_color2") {
        document.getElementById(x).className="rute";
    } else {
        document.getElementById(x).className="rute_color1";
    }
    //checkWinner();
}

function dblClicked(x) {
    document.getElementById(x).className="rute_color2";
    //checkWinner();
}

/*
function checkWinner() {
    var color1_pos = [];
    var color2_pos = [];
    for(var i = 1; i < 10; i++) {
        if(document.getElementById(i.toString()).className=="rute_color1") {
            color1_pos.push(i);
        } else if(document.getElementById(i.toString()).className=="rute_color2") {
            color2_pos.push(i);
        }
    }
    if(color1_pos.length > 2) {
        winnerDance();
    }
    console.log(color1_pos);
    console.log(color2_pos);
}

function winnerDance() {
    for(var j = 0; j < 1000; j++) {
        for(var i = 1; i < 10; i++) {
            document.getElementById(i.toString()).classList.add("flash");
        }
    }

    console.log("Vinner!");
}
*/

