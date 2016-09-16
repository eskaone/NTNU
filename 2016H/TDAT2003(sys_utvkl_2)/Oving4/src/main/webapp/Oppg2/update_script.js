/**
 * Created by asdfLaptop on 16.09.2016.
 */
function update() {
    var text = document.getElementById("message").value;
    $.post("/rest/Javatext/" + text)
}
$("document").ready(function(){
    document.getElementById("button").addEventListener("click", update);
});