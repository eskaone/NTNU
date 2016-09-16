/**
 * Created by asdfLaptop on 16.09.2016.
 */
$("document").ready(function timeOut(){
    setInterval(get, 1000)
});
function get() {
    $.get("/rest/Javatext", function (data){
        $("#box").html(data);
    })
}