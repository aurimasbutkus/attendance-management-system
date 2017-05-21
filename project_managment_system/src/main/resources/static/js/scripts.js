$(function() {
    $( ".datepicker" ).datepicker({ dateFormat: "yy-mm-dd" });
});
$(function() {
    var div = document.getElementById("scrollDivID");
    if (div != null){
        div.scrollTop = div.scrollHeight - div.clientHeight;
    }
});