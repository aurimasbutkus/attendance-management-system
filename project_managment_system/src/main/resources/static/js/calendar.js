var calendar = '#calendar';
var months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "November", "December"];

$(document).ready(function () {
    var date = new Date();
    RenderCalendar(date.getFullYear(), date.getMonth()+1);
});

function RenderCalendar(year, month) {
    $(calendar).html('');
    $(calendar).append("<table id='calendar-table'></table>");
    $("#calendar-table").append("<tr><td colspan='7'><b>"+ months[month-1] +"</b></td></tr>");
    $("#calendar-table").append("<tr><td>P</td><td>A</td><td>T</td><td>K</td><td>P</td><td>S</td><td>S</td></tr>");
    var daysCount = 0;
    var endReached = false;
    for (var a = 1; a <= 6; a++) {
        var row = "calendar-row-" + a;
        $("#calendar-table").append("<tr id="+ row +"></tr>");
        for (var b = 1; b <= 7; b++) {
            var column = "calendar-column-" + b
            if (GetFirstDayOfMonth(year, month) == a * b || daysCount > 0 || endReached === true) {
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
                var daysTillStart = GetFirstDayOfMonth(year, month) - b;
                var day = GetLastDayOfMonth(year, month-1) - daysTillStart + 1;
                $("#" + row).append("<td>"+ day +"</td>");
            }
        }
    }
}

function GetFirstDayOfMonth(year, month) {
    var date = new Date(year, month-1, 1);
    return date.getDay();
}

function GetLastDayOfMonth(year, month) {
    var date = new Date(year, month, 0);
    return date.getDate();
}

