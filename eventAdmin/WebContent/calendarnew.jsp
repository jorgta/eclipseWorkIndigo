<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>TOAST UI Calendar App DEMO</title>
    <!--
    <link rel="stylesheet" href="./css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="./css/tui-time-picker.css">
    <link rel="stylesheet" type="text/css" href="./css/tui-date-picker.css">
    <link rel="stylesheet" type="text/css" href="../dist/tui-calendar.css">
    <link rel="stylesheet" type="text/css" href="./css/default.css">
    <link rel="stylesheet" type="text/css" href="./css/icons.css">
    -->
    <link rel="stylesheet" href="tui.calendar-master/examples/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="tui.calendar-master/examples/css/tui-time-picker.css">
    <link rel="stylesheet" type="text/css" href="tui.calendar-master/examples/css/tui-date-picker.css">    
    <link rel="stylesheet" type="text/css" href="tui.calendar-master/dist/tui-calendar.css">
    <link rel="stylesheet" type="text/css" href="tui.calendar-master/examples/css/default.css">
    <link rel="stylesheet" type="text/css" href="tui.calendar-master/examples/css/icons.css">


</head>
<body>
   
    <div id="lnb">
        <div class="lnb-new-schedule">
            <button id="btn-new-schedule" type="button" class="btn btn-default btn-block lnb-new-schedule-btn" data-toggle="modal">
                Nuevo Evento</button>
        </div>
        <div id="lnb-calendars" class="lnb-calendars">
            <div>
                <div class="lnb-calendars-item">
                    <label>
                        <input class="tui-full-calendar-checkbox-square" type="checkbox" value="all" checked>
                        <span></span>
                        <strong>Todos</strong>
                    </label>
                </div>
            </div>
            <div id="calendarList" class="lnb-calendars-d1">
            </div>
        </div>
        <div class="lnb-footer">
            © Bituos.
        </div>
    </div>
    <div id="right">
        <div id="menu">
            <span class="dropdown">
                <button id="dropdownMenu-calendarType" class="btn btn-default btn-sm dropdown-toggle" type="button" data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="true">
                    <i id="calendarTypeIcon" class="calendar-icon ic_view_month" style="margin-right: 4px;"></i>
                    <span id="calendarTypeName">Lista</span>&nbsp;
                    <i class="calendar-icon tui-full-calendar-dropdown-arrow"></i>
                </button>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu-calendarType">
                    <li role="presentation">
                        <a class="dropdown-menu-title" role="menuitem" data-action="toggle-daily">
                            <i class="calendar-icon ic_view_day"></i>Diario
                        </a>
                    </li>
                    <li role="presentation">
                        <a class="dropdown-menu-title" role="menuitem" data-action="toggle-weekly">
                            <i class="calendar-icon ic_view_week"></i>Semanal
                        </a>
                    </li>
                    <li role="presentation">
                        <a class="dropdown-menu-title" role="menuitem" data-action="toggle-monthly">
                            <i class="calendar-icon ic_view_month"></i>Mensual
                        </a>
                    </li>
                    <li role="presentation">
                        <a class="dropdown-menu-title" role="menuitem" data-action="toggle-weeks2">
                            <i class="calendar-icon ic_view_week"></i>2 semanas
                        </a>
                    </li>
                    <li role="presentation">
                        <a class="dropdown-menu-title" role="menuitem" data-action="toggle-weeks3">
                            <i class="calendar-icon ic_view_week"></i>3 semanas
                        </a>
                    </li>
                    <li role="presentation" class="dropdown-divider"></li>
                    <li role="presentation">
                        <a role="menuitem" data-action="toggle-workweek">
                            <input type="checkbox" class="tui-full-calendar-checkbox-square" value="toggle-workweek" checked>
                            <span class="checkbox-title"></span>Mostrar fines de semana
                        </a>
                    </li>
                    <li role="presentation">
                        <a role="menuitem" data-action="toggle-start-day-1">
                            <input type="checkbox" class="tui-full-calendar-checkbox-square" value="toggle-start-day-1">
                            <span class="checkbox-title"></span>Comienza la semana el lunes
                        </a>
                    </li>
                    <li role="presentation">
                        <a role="menuitem" data-action="toggle-narrow-weekend">
                            <input type="checkbox" class="tui-full-calendar-checkbox-square" value="toggle-narrow-weekend">
                            <span class="checkbox-title"></span>Más estrecho que los días laborables
                        </a>
                    </li>
                </ul>
            </span>
            <span id="menu-navi">
                <button type="button" class="btn btn-default btn-sm move-today" data-action="move-today">Hoy</button>
                <button type="button" class="btn btn-default btn-sm move-day" data-action="move-prev">
                    <i class="calendar-icon ic-arrow-line-left" data-action="move-prev"></i>
                </button>
                <button type="button" class="btn btn-default btn-sm move-day" data-action="move-next">
                    <i class="calendar-icon ic-arrow-line-right" data-action="move-next"></i>
                </button>
            </span>
            <span id="renderRange" class="render-range"></span>
        </div>
        <div id="calendar"></div>
    </div>

<!--
    <script src="./js/jquery-3.2.1.slim.min.js"></script>
    <script src="./js/bootstrap.min.js"></script>
    <script src="./js/tui-code-snippet.min.js"></script>
    <script src="./js/tui-dom.js"></script>
    <script src="./js/tui-time-picker.min.js"></script>
    <script src="./js/tui-date-picker.min.js"></script>
    <script src="./js/moment.min.js"></script>
    <script src="./js/chance.min.js"></script>
    <script src="../dist/tui-calendar.js"></script>
    <script src="./js/data/calendars.js"></script>
    <script src="./js/data/schedules.js"></script>
-->

     
    <script src="tui.calendar-master/examples/js/jquery-3.2.1.slim.min.js"></script>
    <script src="tui.calendar-master/examples/js/bootstrap.min.js"></script>
    <script src="tui.calendar-master/examples/js/tui-code-snippet.min.js"></script>
    <script src="tui.calendar-master/examples/js/tui-dom.js"></script>
    <script src="tui.calendar-master/examples/js/tui-time-picker.min.js"></script>
    <script src="tui.calendar-master/examples/js/tui-date-picker.min.js"></script>
    <script src="tui.calendar-master/examples/js/moment.min.js"></script>
    <script src="tui.calendar-master/examples/js/chance.min.js"></script>
    <script src="tui.calendar-master/dist/tui-calendar.js"></script>
    <script src="tui.calendar-master/examples/js/data/calendars.js"></script>
    <script src="tui.calendar-master/examples/js/data/schedules.js"></script>

    <!-- <script src="./js/theme/dooray.js"></script> -->
     
    
    <script src="tui.calendar-master/examples/js/app.js"></script>
    
    
</body>
</html>
