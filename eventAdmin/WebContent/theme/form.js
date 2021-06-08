function init() {
	var els = getElementsByClassName(document, '*','date');
	
	/*var label = document.createElement('label');
	label.appendChild(document.createTextNode('Date of Birth'));
	label.setAttribute('for','dob');*/
	
	/*removeChildren(els[0]);	
	els[0].appendChild(label);
	els[0].appendChild(document.createTextNode(' '));
	
	var dateSel = document.createElement('input');
	dateSel = document.createElement('input');
	dateSel.type='text';
	dateSel.id='dob';
	dateSel.name='dob';
	dateSel.defaultValue='DD/MM/YYYY'; dateSel.value='DD/MM/YYYY';
	dateSel.className+='default';
	
	els[0].appendChild(dateSel);*/
	
	
	var trmDate;
	 
	 
	for(var i=0; i < els.length; i++)
	{
	  for(var j=0; j < els[i].childNodes.length; j++)
	  {
	    if(els[i].childNodes[j].tagName == 'INPUT')
	       trmDate = new calendarInput(els[i].childNodes[j]);
	    
	      // alert(els[i].childNodes[j].name)
	  }
	}
	
	  date = trmDate;
	//date = new calendarInput(dateSel);
}


AttachEvent(window,'load',init,false);