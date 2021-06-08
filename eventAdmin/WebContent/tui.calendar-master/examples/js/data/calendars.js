'use strict';

/* eslint-disable require-jsdoc, no-unused-vars */

var CalendarPropsList = [];

function CalendarProps() {
    this.id = null;
    this.name = null;
    this.checked = true;
    this.color = null;
    this.bgColor = null;
    this.borderColor = null;
}

function addCalendar(calendar) {
    CalendarPropsList.push(calendar);
}

function findCalendar(id) {
    var found;

    CalendarPropsList.forEach(function(calendar) {
        if (calendar.id === id) {
            found = calendar;
        }
    });

    return found || CalendarPropsList[0];
}

function hexToRGBA(hex) {
    var radix = 16;
    var r = parseInt(hex.slice(1, 3), radix),
        g = parseInt(hex.slice(3, 5), radix),
        b = parseInt(hex.slice(5, 7), radix),
        a = parseInt(hex.slice(7, 9), radix) / 255 || 1;
    var rgba = 'rgba(' + r + ', ' + g + ', ' + b + ', ' + a + ')';

    return rgba;
}

(function() {
    var calendar;
    var id = 0;
    var ajaxInformation= new AjaxInformation("calendarSchedule" );
    ajaxInformation.data = {'bean': 'BeanCalendarProps','orderField':'id',  'listName':'CalendarInfoList','proccess':'getinfo' }

    var result =openConnection(ajaxInformation);
 
    for (var i = 0; i < result.length; i++) {
        var item = result[i];
        var calendarprop = $.extend(new CalendarProps(), item);
        addCalendar(calendarprop);

    }

 
})();
