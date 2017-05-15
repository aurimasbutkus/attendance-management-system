var calendar = '#calendar';
var months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
var otherMonth = 0;
var otherYear = 0;
$(document).ready(function () {
    var date = new Date();
    RenderCalendar(date.getFullYear(), date.getMonth()+1);
});


function RenderCalendar(year, month) {
    month = checkMonth(month);
    $(calendar).html('');
    $(calendar).append("<table id='calendar-table' class='calendar-table'></table>");
    $("#calendar-table").append("<tr><td colspan='7'><span class='leftArr' align='left' onclick='GetPreviousMonth();findDeadlines()'><b><</b></span><b>"+ months[month-1] +"</b><span class='rightArr' onclick='GetNextMonth();findDeadlines()'><b>></b></span></td></tr>");
    $("#calendar-table").append("<tr><td>P</td><td>A</td><td>T</td><td>K</td><td>P</td><td>S</td><td>S</td></tr>");
    var daysCount = 0;
    var endReached = false;
    for (var a = 1; a <= 6; a++) {
        var row = "calendar-row-" + a;
        $("#calendar-table").append("<tr id="+ row +"></tr>");
        for (var b = 1; b <= 7; b++) {
            if(GetFirstDayOfMonth(year, month - 1) > 0) var firstDay = GetFirstDayOfMonth(year, month - 1);
            else firstDay = 7;
            var column = "calendar-column-" + b;
            if (firstDay == a * b || daysCount > 0 || endReached === true) {
                daysCount++;
                var bold = endReached === false ? "<b>" + daysCount + "</b>" : daysCount;
                var data = endReached === false ? "data-day='" + year + "-" + (month < 10 ? "0" + month : month) + "-" + daysCount + "'" : "";
                $("#" + row).append("<td "+ data +">" + bold + "</td>");
                if (daysCount == GetLastDayOfMonth(year, month)) {
                    daysCount = 0;
                    endReached = true;
                }
            }
            else {
                var daysTillStart = firstDay - b;
                var day = GetLastDayOfMonth(year, month-1) - daysTillStart + 1;
                $("#" + row).append("<td>"+ day +"</td>");
            }
        }
    }
}
function checkMonth(month) {
    if(month%12==0){
        return (month - (12 * ((Math.floor(month/12)) - 1)));
    }
    else return (month - (12 * (Math.floor(month/12))));
}


function checkYear(goingToNextYear){
    var date = new Date();
    if(goingToNextYear == 0){
        otherYear -= 1;
        return date.getFullYear() + otherYear;
    }
    else if(goingToNextYear == 1){
        otherYear += 1;
        return date.getFullYear() + otherYear;
    }
    else{
        return date.getFullYear() + otherYear;
    }
}

function GetFirstDayOfMonth(year, month) {
    var date = new Date(year, month, 1);
    return date.getDay();
}

function GetLastDayOfMonth(year, month) {
    var date = new Date(year, month, 0);
    return date.getDate();
}
// goingToNextYear = 0 if going back in years
// goingToNextYear = 1 if going to next year
// goingToNextYear = 2 if year stays the same
function GetPreviousMonth(){
    var date = new Date();
    otherMonth -= 1;
    if(((date.getMonth() + 1) + otherMonth) % 12 == 0){
        var goingToNextYear = 0;
    }
    else{
        var goingToNextYear = 2;
    }
    RenderCalendar(checkYear(goingToNextYear), (date.getMonth() + 1) + otherMonth);

}
function GetNextMonth(){
    var date = new Date();
    otherMonth += 1;
    if((date.getMonth() + otherMonth) % 12 == 0){
        var goingToNextYear = 1;
    }
    else{
        var goingToNextYear = 2;
    }
    RenderCalendar(checkYear(goingToNextYear), (date.getMonth() + 1) + otherMonth);
}
