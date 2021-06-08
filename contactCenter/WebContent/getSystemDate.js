function getDateClient() {
        var sys_date = new Date();

		var s_year = sys_date.getFullYear();
		var s_month = sys_date.getMonth() + 1;
		var s_day = sys_date.getDate();
		var s_hours = sys_date.getHours();
		var s_minutes = sys_date.getMinutes();
		var s_seconds = sys_date.getSeconds();

		var m;
		var d;
		var hh;
		var mm;
		var ss;

		if (s_month <= 9) m = "0" + s_month.toString();
		else m = s_month.toString();
		if (s_day <= 9) d = "0" + s_day.toString();
		else d = s_day.toString();
		if (s_hours <= 9) hh = "0" + s_hours.toString();
		else hh = s_hours.toString();
		if (s_minutes <= 9) mm = "0" + s_minutes.toString();
		else mm = s_minutes.toString();
		if (s_seconds <= 9) ss = "0" + s_seconds.toString();
		else ss = s_seconds.toString();

        var s_date = d + "/"+ m + "/" + s_year;
        return(s_date);
}