'use strict';
 

var ScheduleList = [];
var from;

var useCreationPopup;
var useDetailPopup;
var isReadOnly;

(function(window, Calendar) {

    
    from = $('#from').val();


    var defaultVw = 'month';
    var ajaxInformation= new AjaxInformation("calendarSchedule" );
    ajaxInformation.data = {'bean': 'BeanOptions','orderField':'id',  'listName':'scheduleInfoList','proccess':'getinfo','searchParam':'defaultView','searchValue':defaultVw}

    var result =openConnection(ajaxInformation);
    var options = null;
    for (var i = 0; i < result.length; i++) {
        options = result[i];
        //var schedule = $.extend(new ScheduleInfo(), item);
        

    }

    var monthDaynames = [];

    for (var i = 0; i < options.month.daynames.length; i++) {
        var item = options.month.daynames[i];
        if(item  != null)
        {
            monthDaynames.push(item.name);
        }
        
    }

    var weekDaynames = [];
    for (var i = 0; i < options.week.daynames.length; i++) {
        var item = options.month.daynames[i];
        if(item  != null)
        {
            weekDaynames.push(item.name);
        }
        
    }

    
    if(from=='quoteNew')
    {
        useCreationPopup = false;
        useDetailPopup = true;
        isReadOnly = true;
    }
    else
    {

        useCreationPopup = options.useCreationPopup;
        useDetailPopup = options.useDetailPopup;
        isReadOnly =  options.isReadOnly;
    }

 
    var cal, resizeThrottled;

   
    cal = new Calendar('#calendar', {
        defaultView: defaultVw,
        useCreationPopup: useCreationPopup,
        useDetailPopup: useDetailPopup,
        isReadOnly: isReadOnly,
        calendars: CalendarPropsList,
        template: {
              popupIsAllDay: function() {
                //return 'Todo el dia';
                return options.template.popupIsAllDay;
              },
              popupStateFree: function() {
                //return 'Libre';
                return options.template.popupStateFree;
              },
              popupStateBusy: function() {
                //return 'Ocupado';
                return options.template.popupStateBusy;
              },
              titlePlaceholder: function() {
                //return 'Subject';
                return  options.template.titlePlaceholder;
              },
              locationPlaceholder: function() {
                //return 'Locaci??n';
                return  options.template.locationPlaceholder;
              },
              bodyPlaceholder: function() {
                //return 'Body';
                return  options.template.bodyPlaceholder;
              },
              startDatePlaceholder: function() {
                //return 'Inicio';
                return  options.template.startDatePlaceholder;
              },
              endDatePlaceholder: function() {
                //return 'Fin';
                return  options.template.endDatePlaceholder;
              },
              popupSave: function() {
                //return 'Guardar';
                return  options.template.popupSave;
              },
              popupUpdate: function() {
                //return 'Actualizar';
                return  options.template.popupUpdate;
              },
            milestone: function(model) {
                return '<span class="calendar-font-icon ic-milestone-b"></span> <span style="background-color: ' + model.bgColor + '">' + model.title + '</span>';
            },
            milestoneTitle: function() {
                
              //return '<span class="tui-full-calendar-left-content">Hito</span>';
              return '<span class="tui-full-calendar-left-content">' + options.template.milestoneTitle +'</span>';
             },
            allday: function(schedule) {
                return getTimeTemplate(schedule, true);
            },
            alldayTitle: function() {
                   // return '<span class="tui-full-calendar-left-content">Todo el d??a</span>';
                    return '<span class="tui-full-calendar-left-content">'+options.template.alldayTitle +'</span>';

                    
            },
            task: function(schedule) {
              return '#' + schedule.title;
              },
             taskTitle: function() {
             //return '<span class="tui-full-calendar-left-content">Tarea</span>';
             return '<span class="tui-full-calendar-left-content">'+options.template.taskTitle +'</span>';
             
             },
            time: function(schedule) {
                return getTimeTemplate(schedule, false);
            },
            time: function(schedule) {
                return '<strong>' + moment(schedule.start.getTime()).format('HH:mm') + '</strong> ' + schedule.title;
            },
            popupEdit: function() {
                //return 'Editar';
                return  options.template.popupEdit;
            },
            popupDelete: function() {
            //return 'Eliminar';
            return  options.template.popupDelete;
            },
            popupDetailLocation: function(schedule) {
                //return 'Locaci??n : ' + schedule.location;
                return  options.template.popupDetailLocation +' : ' + schedule.location;
              },
              popupDetailUser: function(schedule) {
                return 'Usario : ' + (schedule.attendees || []).join(', ');
              },
              popupDetailState: function(schedule) {
               // return 'Estado : ' + schedule.state || 'Ocupado';
                return options.template.popupDetailState +' : ' + schedule.state || 'Ocupado';
                 
              },
              popupDetailRepeat: function(schedule) {
                //return 'Repetir : ' + schedule.recurrenceRule;
                return options.template.popupDetailRepeat +' : ' + schedule.recurrenceRule;
                   
              },
              popupDetailBody: function(schedule) {
                //return '' + schedule.body;
                return '' +  options.template.popupDetailBody
              } 
        },  
        timezones: [         // set timezone config
             
            {
              timezoneOffset: -420,
              tooltip: 'Los Angeles'
            }
          ],
           
        month: {
              daynames: monthDaynames,
              startDayOfWeek: options.month.startDayOfWeek,
              narrowWeekend: options.month.narrowWeekend
          },
        week: {
              daynames: weekDaynames,
              startDayOfWeek: options.month.startDayOfWeek,
              narrowWeekend: options.month.narrowWeekend
         }
    });


    


    // event handlers
    cal.on({
        'clickMore': function(e) {
            console.log('clickMore', e);
        },
        'clickSchedule': function(e) {
            console.log('clickSchedule', e);
        },
        'clickDayname': function(date) {
            console.log('clickDayname', date);
        },
        'beforeCreateSchedule': function(e) {
            console.log('beforeCreateSchedule', e);
            /*if (e.triggerEventName === 'click') {
                // open writing simple schedule popup
                console.log('click', e);
            }*/
            saveNewSchedule(e);
        },
        'beforeUpdateSchedule': function(e) {
            console.log('beforeUpdateSchedule', e);
            e.schedule.start = e.start;
            e.schedule.end = e.end;
            cal.updateSchedule(e.schedule.id, e.schedule.calendarId, e.schedule);
        },
        'beforeDeleteSchedule': function(e) {
            console.log('beforeDeleteSchedule', e);
            cal.deleteSchedule(e.schedule.id, e.schedule.calendarId);
        },
        'afterRenderSchedule': function(e) {
            var schedule = e.schedule;
            // var element = cal.getElement(schedule.id, schedule.calendarId);
            // console.log('afterRenderSchedule', element);
        },
        'clickTimezonesCollapseBtn': function(timezonesCollapsed) {
            console.log('timezonesCollapsed', timezonesCollapsed);

            if (timezonesCollapsed) {
                cal.setTheme({
                    'week.daygridLeft.width': '77px',
                    'week.timegridLeft.width': '77px'
                });
            } else {
                cal.setTheme({
                    'week.daygridLeft.width': '60px',
                    'week.timegridLeft.width': '60px'
                });
            }

            return true;
        }
    });

    /**
     * Get time template for time and all-day
     * @param {Schedule} schedule - schedule
     * @param {boolean} isAllDay - isAllDay or hasMultiDates
     * @returns {string}
     */
    function getTimeTemplate(schedule, isAllDay) {
        var html = [];
        var start = moment(schedule.start.toUTCString());
        if (!isAllDay) {
            html.push('<strong>' + start.format('HH:mm') + '</strong> ');
        }
        if (schedule.isPrivate) {
            html.push('<span class="calendar-font-icon ic-lock-b"></span>');
            html.push(' Private');
        } else {
            if (schedule.isReadOnly) {
                html.push('<span class="calendar-font-icon ic-readonly-b"></span>');
            } else if (schedule.recurrenceRule) {
                html.push('<span class="calendar-font-icon ic-repeat-b"></span>');
            } else if (schedule.attendees.length) {
                html.push('<span class="calendar-font-icon ic-user-b"></span>');
            } else if (schedule.location) {
                html.push('<span class="calendar-font-icon ic-location-b"></span>');
            }
            html.push(' ' + schedule.title);
        }

        return html.join('');
    }

    /**
     * A listener for click the menu
     * @param {Event} e - click event
     */
    function onClickMenu(e) {
        var target = $(e.target).closest('a[role="menuitem"]')[0];
        var action = getDataAction(target);
        var options = cal.getOptions();
        var viewName = '';

        console.log(target);
        console.log(action);
        switch (action) {
            case 'toggle-daily':
                viewName = 'day';
                break;
            case 'toggle-weekly':
                viewName = 'week';
                break;
            case 'toggle-monthly':
                options.month.visibleWeeksCount = 0;
                viewName = 'month';
                break;
            case 'toggle-weeks2':
                options.month.visibleWeeksCount = 2;
                viewName = 'month';
                break;
            case 'toggle-weeks3':
                options.month.visibleWeeksCount = 3;
                viewName = 'month';
                break;
            case 'toggle-narrow-weekend':
                options.month.narrowWeekend = !options.month.narrowWeekend;
                options.week.narrowWeekend = !options.week.narrowWeekend;
                viewName = cal.getViewName();

                target.querySelector('input').checked = options.month.narrowWeekend;
                break;
            case 'toggle-start-day-1':
                options.month.startDayOfWeek = options.month.startDayOfWeek ? 0 : 1;
                options.week.startDayOfWeek = options.week.startDayOfWeek ? 0 : 1;
                viewName = cal.getViewName();

                target.querySelector('input').checked = options.month.startDayOfWeek;
                break;
            case 'toggle-workweek':
                options.month.workweek = !options.month.workweek;
                options.week.workweek = !options.week.workweek;
                viewName = cal.getViewName();

                target.querySelector('input').checked = !options.month.workweek;
                break;
            default:
                break;
        }

        cal.setOptions(options, true);
        cal.changeView(viewName, true);

        setDropdownCalendarType();
        setRenderRangeText();
        setSchedules();
    }

    function onClickNavi(e) {
        var action = getDataAction(e.target);

        switch (action) {
            case 'move-prev':
                cal.prev();
                break;
            case 'move-next':
                cal.next();
                break;
            case 'move-today':
                cal.today();
                break;
            default:
                return;
        }

        setRenderRangeText();
        setSchedules();
    }

    function onNewSchedule() {
        var title = $('#new-schedule-title').val();
        var location = $('#new-schedule-location').val();
        var isAllDay = document.getElementById('new-schedule-allday').checked;
        var start = datePicker.getStartDate();
        var end = datePicker.getEndDate();
        var calendar = selectedCalendar ? selectedCalendar : CalendarPropsList[0];

        if (!title) {
            return;
        }

        cal.createSchedules([{
            id: String(chance.guid()),
            calendarId: calendar.id,
            title: title,
            isAllDay: isAllDay,
            start: start,
            end: end,
            category: isAllDay ? 'allday' : 'time',
            dueDateClass: '',
            color: calendar.color,
            bgColor: calendar.bgColor,
            dragBgColor: calendar.bgColor,
            borderColor: calendar.borderColor,
            raw: {
                location: location
            },
            state: 'Busy'
        }]);

        $('#modal-new-schedule').modal('hide');
    }

    function onChangeNewScheduleCalendar(e) {
        var target = $(e.target).closest('a[role="menuitem"]')[0];
        var calendarId = getDataAction(target);
        changeNewScheduleCalendar(calendarId);
    }

    function changeNewScheduleCalendar(calendarId) {
        var calendarNameElement = document.getElementById('calendarName');
        var calendar = findCalendar(calendarId);
        var html = [];

        html.push('<span class="calendar-bar" style="background-color: ' + calendar.bgColor + '; border-color:' + calendar.borderColor + ';"></span>');
        html.push('<span class="calendar-name">' + calendar.name + '</span>');

        calendarNameElement.innerHTML = html.join('');

        selectedCalendar = calendar;
    }

    function createNewSchedule(event) {
        var start = event.start ? new Date(event.start.getTime()) : new Date();
        var end = event.end ? new Date(event.end.getTime()) : moment().add(1, 'hours').toDate();

        if (useCreationPopup) {
            cal.openCreationPopup({
                start: start,
                end: end
            });
        }
    }
    /*
    function saveNewSchedule(scheduleData) {

        var calendar = scheduleData.calendar || findCalendar(scheduleData.calendarId);
        var schedule = {
            id: String(chance.guid()),
            title: scheduleData.title,
            isAllDay: scheduleData.isAllDay,
            start: scheduleData.start,
            end: scheduleData.end,
            category: scheduleData.isAllDay ? 'allday' : 'time',
            dueDateClass: '',
            color: calendar.color,
            bgColor: calendar.bgColor,
            dragBgColor: calendar.bgColor,
            borderColor: calendar.borderColor,
            location: scheduleData.location,
            raw: {
                class: scheduleData.raw['class']
            },
            state: scheduleData.state
        };
        if (calendar) {
            schedule.calendarId = calendar.id;
            schedule.color = calendar.color;
            schedule.bgColor = calendar.bgColor;
            schedule.borderColor = calendar.borderColor;
        }

        cal.createSchedules([schedule]);

        refreshScheduleVisibility();
    }*/

    function saveNewSchedule(scheduleData) {

        var calendar = scheduleData.calendar || findCalendar(scheduleData.calendarId);
        var schedule = {
            //id: String(chance.guid()),
            title: scheduleData.title,
            body: scheduleData.body,
            isAllDay: scheduleData.isAllDay,
            start: scheduleData.start,
            end: scheduleData.end,
            category: scheduleData.isAllDay ? 'allday' : 'time',
            dueDateClass: '',
            color: calendar.color,
            bgColor: calendar.bgColor,
            dragBgColor: calendar.bgColor,
            borderColor: calendar.borderColor,
            location: scheduleData.location,
            raw: {
                class: scheduleData.raw['class']
            },
            state: scheduleData.state
        };


        if (calendar) {
            schedule.calendarId = calendar.id;
            schedule.color = calendar.color;
            schedule.bgColor = calendar.bgColor;
            schedule.borderColor = calendar.borderColor;
        }


        var scheduleInfo = new ScheduleInfo();
        scheduleInfo.isAllday = schedule.isAllDay;
     

        //scheduleInfo.id = schedule.id || '';
        scheduleInfo.title = schedule.title || '';
        scheduleInfo.body = schedule.body || '';
        scheduleInfo.isVisible= true;
        /*if (typeof schedule.isVisible !== 'undefined'  )
        {
            schedule.isVisible = true;
        }*/


        schedule.isVisible = true;

        //scheduleInfo.isVisible = util.isExisty(schedule.isVisible) ? schedule.isVisible : true;

        scheduleInfo.color = schedule.color || scheduleInfo.color;
        scheduleInfo.bgColor = schedule.bgColor || scheduleInfo.bgColor;
        scheduleInfo.dragBgColor = schedule.dragBgColor || scheduleInfo.dragBgColor;
        scheduleInfo.borderColor = schedule.borderColor || scheduleInfo.borderColor;
        scheduleInfo.calendarId = schedule.calendarId || '';
        scheduleInfo.category = schedule.category || '';
        scheduleInfo.dueDateClass = schedule.dueDateClass || '';
        scheduleInfo.customStyle = schedule.customStyle || '';
        scheduleInfo.location = schedule.location || '';
        scheduleInfo.attendees = schedule.attendees || [];
        scheduleInfo.recurrenceRule = schedule.recurrenceRule || '';
        scheduleInfo.isPrivate = schedule.isPrivate || false;
        scheduleInfo.isPending = schedule.isPending || false;
        scheduleInfo.isFocused = schedule.isFocused || false;
        scheduleInfo.isReadOnly = schedule.isReadOnly || false;
        scheduleInfo.goingDuration = schedule.goingDuration || 0;
        scheduleInfo.comingDuration = schedule.comingDuration || 0;
        //scheduleInfo.state = schedule.state || '';

      
        scheduleInfo.category = schedule.category;
        /*var startDate = Date.parse(schedule.start);
        var endDate =  Date.parse( schedule.end);*/
        scheduleInfo.start = schedule.start.toDate();
        scheduleInfo.end = schedule.end.toDate();
 

        scheduleInfo.raw = schedule.raw || null;
 
        var scheduleJSON = JSON.stringify(scheduleInfo);
 
        var ajaxInformation= new AjaxInformation("calendarSchedule" );
        ajaxInformation.data = {'bean': 'BeanScheduleInfo','active':'Y','orderField':'id',  'listName':'CalendarInfoList','proccess':'saveScheduleInfoToDatabase','schedule':scheduleJSON }
        var result =openConnection(ajaxInformation);
        
        var scheinf = $.extend(new ScheduleInfo(), result);

 
       

        schedule.id=scheinf.guid;
        cal.createSchedules([schedule]);

        refreshScheduleVisibility();
        window.history.back();

    }

    function onChangeCalendars(e) {
        var calendarId = e.target.value;
        var checked = e.target.checked;
        var viewAll = document.querySelector('.lnb-calendars-item input');
        var calendarElements = Array.prototype.slice.call(document.querySelectorAll('#calendarList input'));
        var allCheckedCalendars = true;

        if (calendarId === 'all') {
            allCheckedCalendars = checked;

            calendarElements.forEach(function(input) {
                var span = input.parentNode;
                input.checked = checked;
                span.style.backgroundColor = checked ? span.style.borderColor : 'transparent';
            });

            CalendarPropsList.forEach(function(calendar) {
                calendar.checked = checked;
            });
        } else {
            findCalendar(calendarId).checked = checked;

            allCheckedCalendars = calendarElements.every(function(input) {
                return input.checked;
            });

            if (allCheckedCalendars) {
                viewAll.checked = true;
            } else {
                viewAll.checked = false;
            }
        }

        refreshScheduleVisibility();
    }

    function refreshScheduleVisibility() {
        var calendarElements = Array.prototype.slice.call(document.querySelectorAll('#calendarList input'));

        CalendarPropsList.forEach(function(calendar) {
            cal.toggleSchedules(calendar.id, !calendar.checked, false);
        });

        cal.render(true);

        calendarElements.forEach(function(input) {
            var span = input.nextElementSibling;
            span.style.backgroundColor = input.checked ? span.style.borderColor : 'transparent';
        });
    }

    function setDropdownCalendarType() {
        var calendarTypeName = document.getElementById('calendarTypeName');
        var calendarTypeIcon = document.getElementById('calendarTypeIcon');
        var options = cal.getOptions();
        var type = cal.getViewName();
        var iconClassName;

        if (type === 'day') {
            type = 'Diario';
            iconClassName = 'calendar-icon ic_view_day';
        } else if (type === 'week') {
            type = 'Semanal';
            iconClassName = 'calendar-icon ic_view_week';
        } else if (options.month.visibleWeeksCount === 2) {
            type = '2 semanas';
            iconClassName = 'calendar-icon ic_view_week';
        } else if (options.month.visibleWeeksCount === 3) {
            type = '3 semanas';
            iconClassName = 'calendar-icon ic_view_week';
        } else {
            type = 'Mensual';
            iconClassName = 'calendar-icon ic_view_month';
        }

        calendarTypeName.innerHTML = type;
        calendarTypeIcon.className = iconClassName;
    }

    function setRenderRangeText() {
        var renderRange = document.getElementById('renderRange');
        var options = cal.getOptions();
        var viewName = cal.getViewName();
        var html = [];
        if (viewName === 'day') {
            html.push(moment(cal.getDate().getTime()).format('YYYY.MM.DD'));
        } else if (viewName === 'month' &&
            (!options.month.visibleWeeksCount || options.month.visibleWeeksCount > 4)) {
            html.push(moment(cal.getDate().getTime()).format('YYYY.MM'));
        } else {
            html.push(moment(cal.getDateRangeStart().getTime()).format('YYYY.MM.DD'));
            html.push(' ~ ');
            html.push(moment(cal.getDateRangeEnd().getTime()).format(' MM.DD'));
        }
        renderRange.innerHTML = html.join('');
    }

    function setSchedules() {
        cal.clear();
        generateSchedule(cal.getViewName(), cal.getDateRangeStart(), cal.getDateRangeEnd());
        cal.createSchedules(ScheduleList);
        // var schedules = [
        //     {id: 489273, title: 'Workout for 2019-04-05', isAllDay: false, start: '2018-02-01T11:30:00+09:00', end: '2018-02-01T12:00:00+09:00', goingDuration: 30, comingDuration: 30, color: '#ffffff', isVisible: true, bgColor: '#69BB2D', dragBgColor: '#69BB2D', borderColor: '#69BB2D', calendarId: 'logged-workout', category: 'time', dueDateClass: '', customStyle: 'cursor: default;', isPending: false, isFocused: false, isReadOnly: true, isPrivate: false, location: '', attendees: '', recurrenceRule: '', state: ''},
        //     // {id: 18073, title: 'completed with blocks', isAllDay: false, start: '2018-11-17T09:00:00+09:00', end: '2018-11-17T10:00:00+09:00', color: '#ffffff', isVisible: true, bgColor: '#54B8CC', dragBgColor: '#54B8CC', borderColor: '#54B8CC', calendarId: 'workout', category: 'time', dueDateClass: '', customStyle: '', isPending: false, isFocused: false, isReadOnly: false, isPrivate: false, location: '', attendees: '', recurrenceRule: '', state: ''}
        // ];
        // cal.createSchedules(schedules);
        refreshScheduleVisibility();
    }

    function setEventListener() {
        $('#menu-navi').on('click', onClickNavi);
        $('.dropdown-menu a[role="menuitem"]').on('click', onClickMenu);
        $('#lnb-calendars').on('change', onChangeCalendars);

        $('#btn-save-schedule').on('click', onNewSchedule);
        $('#btn-new-schedule').on('click', createNewSchedule);

        $('#dropdownMenu-calendars-list').on('click', onChangeNewScheduleCalendar);

        window.addEventListener('resize', resizeThrottled);
    }

    function getDataAction(target) {
        return target.dataset ? target.dataset.action : target.getAttribute('data-action');
    }

    resizeThrottled = tui.util.throttle(function() {
        cal.render();
    }, 50);

    window.cal = cal;
 
    setDropdownCalendarType();
    setRenderRangeText();
    setSchedules();
    setEventListener();
})(window, tui.Calendar);

// set calendars
(function() {
    var calendarList = document.getElementById('calendarList');
    var html = [];
    CalendarPropsList.forEach(function(calendar) {
        html.push('<div class="lnb-calendars-item"><label>' +
            '<input type="checkbox" class="tui-full-calendar-checkbox-round" value="' + calendar.id + '" checked>' +
            '<span style="border-color: ' + calendar.borderColor + '; background-color: ' + calendar.borderColor + ';"></span>' +
            '<span>' + calendar.name + '</span>' +
            '</label></div>'
        );
    });
    calendarList.innerHTML = html.join('\n');
})();
